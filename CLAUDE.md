# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 概要

GoodNewsAllen は、3問の画像選択クイズを出す Android アプリ（Java のみ、単一モジュール `:app`）。2013年に Eclipse ADT で作られ、Gradle / AndroidX / Material テーマに移行済み。

- AGP 8.7.3 / Gradle 8.11.1（wrapper 同梱）。依存関係のバージョンは `gradle/libs.versions.toml` にまとめている
- compileSdk / targetSdk 35、minSdk 21、Java 1.8
- テストコード（`src/test`、`src/androidTest`）はない。動作確認はエミュレータで実際に操作して行う

## 環境と主なコマンド

このマシンでは JDK と SDK を Android Studio ではなく CLI で入れているため、Gradle を実行する前に `JAVA_HOME` を設定する必要がある。

```sh
export JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home   # Homebrew の openjdk@17
A=$HOME/Library/Android/sdk/platform-tools/adb                                     # SDK は ~/Library/Android/sdk（local.properties は gitignore 済み）

./gradlew assembleDebug          # ビルド → app/build/outputs/apk/debug/app-debug.apk
./gradlew lint                   # Android Lint

# エミュレータ（AVD: GoodNewsAllen_API35, Pixel 6 / API 35 arm64, hw.keyboard=yes）
~/Library/Android/sdk/emulator/emulator -avd GoodNewsAllen_API35 -no-snapshot-save -no-boot-anim &
$A wait-for-device
$A install -r app/build/outputs/apk/debug/app-debug.apk
$A shell am start -S -W -n com.whatawonderfulworld.goodnewsallen/.MainActivity
$A logcat -d -b crash            # クラッシュの確認
```

- javac に `-Xlint:deprecation` を付けている（`app/build.gradle`）。現在の警告は0件なので、新しい警告が出たら直す
- `implementation platform(libs.kotlin.bom)` は、AndroidX が引き込む kotlin-stdlib の重複クラスエラーを防ぐためのもの。外さないこと
- `adb shell input tap` などで操作を自動化するときは、画面遷移を待つ時間（2秒以上）を取る。短いと入力が次の画面に流れ込み、間違った結論になる

## アーキテクチャ

画面は3つの Activity で、それぞれ XML の `<fragment>` タグで Fragment を読み込む構成。

```
MainActivity (activity_main: QuestionFragment + SelectionFragment)
  ├─ 回答 → ResultActivity (ResultFragment)   … 「次の問題へ」で setResult(RESULT_NEXT)
  └─ 全問終了 → ScoreActivity (ScoreFragment) … 「おしまい」で MainActivity も finish()
```

- **問題データ**：`Db` は問題をコードに直接書いている。`Db` の中のクラス `Question` / `Selection` と、進行状況（`currentQuestion`）を持つ。`MainActivity` が `Db` を1つ持ち、スコアも `MainActivity.score` に持つ。回転などで Activity が作り直されると、進行状況は失われる
- **画面間のデータの受け渡し**：Intent の extras を使う（`resultDisp` / `correct` / `isLast`、`score` / `total`）。結果の受け取りは Activity Result API で、`MainActivity` の `resultLauncher` と `scoreLauncher` が担当する
- **Fragment から Activity への通知**：コールバック interface を使う。`onAttach(Context)` で Activity をキャストして受け取る（`OnAnswerSelectedListener` / `OnResultClosedListener` / `OnScoreClosedListener`）
- **選択肢**：`SelectionFragment` の `ViewPager` と `ViewPagerAdapter` が表示する。ページ番号がそのまま回答の番号（`Db.isAnswerCorrect(index)`）になる。回答方法は、写真のタップ、「それだ！」ボタン、◀ ▶ ボタン、矢印キー（`MainActivity.dispatchKeyEvent`）

### 変更するときに壊しやすい点

- **Fragment の View 参照**：AndroidX では、`<fragment>` タグの id が Fragment のルート View の id を上書きする。そのため、`getActivity().findViewById()` や、後からルートを id で探すと null になる。View は `onCreateView` の中で取得してフィールドに持つ。`onCreateView` より前に呼ばれる setter（`setQuestion` / `setResult` / `setScore` / `setSelectionImages`）は、値を保留しておき、`onCreateView` で反映する。新しい Fragment もこの書き方に揃える
- **`activity_main.xml` の `descendantFocusability="blocksDescendants"`**：外すと、タップ直後の最初の矢印キーが touch mode の解除に使われてしまい、効かなくなる
- **edge-to-edge**：targetSdk 35 で強制される edge-to-edge は、テーマの `windowOptOutEdgeToEdgeEnforcement` でオプトアウトしている。targetSdk を 36 以上に上げるとこの設定が効かなくなり、内容が見出しバーの裏に隠れる。上げる前に、各レイアウトで insets を処理する必要がある
- **ソースの文字コード**：UTF-8。元々は Shift_JIS で、一部を変換した

### 使われていない昔のコード

次のものは、どこからも参照されていない昔の名残。変更しても動作には影響しない。

- クラス：`SelectionFragment2`、`ImageAdapter`（主要部分はコメントアウト）、`CustomAdapter`、`CustomData`、トップレベルの `Question.java` / `Selection.java`（実際に使われているのは `Db` の中のクラス）
- リソース：`selection_view2.xml`、`custom_layout.xml`、`cell.xml`、`res/menu/*`
- `ResultActivity` / `ScoreActivity` の `setupActionBar()` と `onOptionsItemSelected()`
