
package com.whatawonderfulworld.goodnewsallen;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends FragmentActivity 
        implements SelectionFragment.OnAnswerSelectedListener{

	
    // Result screen: "Next" returns RESULT_NEXT -> show the next question or the score.
    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_NEXT) {
                    onResultClosed();
                }
            });

    // Score screen: 「おしまい」 (or back) ends the quiz.
    private final ActivityResultLauncher<Intent> scoreLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> finish());

    final static int RESULT_NEXT = 1;
    public Db db = new Db();
    public int score = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        setContentView(R.layout.activity_main);
        updateFragmentViews();
    	
    
    }

    


    
    public String getResultDesign(boolean result) {
    	String 	 disp = "";        
    	if(result){
        	disp = "＼(^o^)／";
        	score++;
        }else{
        	disp = "(>_<)";        	
        }
        return disp;
    }
    
    public void updateFragmentViews(){
       	QuestionFragment qf = (QuestionFragment)getSupportFragmentManager().findFragmentById(R.id.question_fragment);
    	String statement = db.getCurrentQuesitionStatement();
       	qf.setQuestionStatement(statement);  
    
      	SelectionFragment sf = (SelectionFragment)getSupportFragmentManager().findFragmentById(R.id.selection_fragment);
    	List<Integer> imageList = db.getCurrentSelectionImages();
      	sf.setSelectionImages(imageList); 
    }

    
    
    public void onAnswerSelected(int answer) {
            boolean result = db.isAnswerCorrect(answer);
            String disp = getResultDesign(result);
            
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("resultDisp", disp);
            resultLauncher.launch(i);
    }
    
    
    private void onResultClosed() {
        if( db.goToNextQuesion() ){
            updateFragmentViews();
        }else{
            Intent i = new Intent(this, ScoreActivity.class);
            i.putExtra("score", score);
            scoreLauncher.launch(i);
        }
    }
    
}