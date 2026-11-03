/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.whatawonderfulworld.goodnewsallen;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    private TextView mQuestionView;
    private String mPendingStatement;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.question_view, container, false);
        // AndroidX attaches <fragment> views to the Activity later and overwrites the root
        // view id with the <fragment> tag id, so keep a direct reference here.
        mQuestionView = (TextView) v.findViewById(R.id.question);
        if (mPendingStatement != null) {
            mQuestionView.setText(mPendingStatement);
        }
        return v;
        
    }
 
    public void setQuestionStatement(String qstat){
        mPendingStatement = qstat;
        if (mQuestionView != null) {
            mQuestionView.setText(qstat);
        }
    }
}