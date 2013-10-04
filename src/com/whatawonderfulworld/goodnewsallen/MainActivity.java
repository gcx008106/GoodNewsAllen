
package com.whatawonderfulworld.goodnewsallen;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity 
        implements SelectionFragment.OnAnswerSelectedListener{

	
    final static int SHOW_RESULT_REQUEST = 99;
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
        	disp = "_(^o^)^";
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
            startActivityForResult(i, SHOW_RESULT_REQUEST);
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 
        if (resultCode == 1 && requestCode == SHOW_RESULT_REQUEST) {
        	if( db.goToNextQuesion() ){
        		updateFragmentViews();
        	}else{
                Intent i = new Intent(this, ScoreActivity.class);
                i.putExtra("score", score);
                startActivityForResult(i, SHOW_RESULT_REQUEST);
        	}
        }
    }
    
}