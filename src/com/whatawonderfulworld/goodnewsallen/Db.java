package com.whatawonderfulworld.goodnewsallen;

import java.util.ArrayList;
import java.util.List;

public class Db{
	public static 	List<Question> questions = new ArrayList<Question>();
	public int 	currentQuestion=0;
	
	Db(){

    	Question q1 = new Question("Q1", "次の中の間違ってるのはどれ？");
    	q1.addSelection(new Selection("s1", q1, "Badnews Allen", R.drawable.q00, false));
    	q1.addSelection(new Selection("s2", q1, "Badnews Allen", R.drawable.q01, false));
    	q1.addSelection(new Selection("s3", q1, "Badnews Allen", R.drawable.q02, true));   	
    	questions.add(q1);
    	
    	Question q2 = new Question("Q2", "だれがバンバンビガロやねん！");
    	q2.addSelection(new Selection("s1", q2, "Biga", R.drawable.q10, false));
    	q2.addSelection(new Selection("s2", q2, "Biga", R.drawable.q11, false));
    	q2.addSelection(new Selection("s3", q2, "Biga", R.drawable.q12, true));     	
    	questions.add(q2);
    	
    	Question q3 = new Question("Q3","わてスカイハイでんねん。");
    	q3.addSelection(new Selection("s1", q2, "Mas", R.drawable.q20, false));
    	q3.addSelection(new Selection("s2", q2, "Mas", R.drawable.q21, false));
    	q3.addSelection(new Selection("s3", q2, "Mas", R.drawable.q22, true));     	
    	questions.add(q3);
		
	}


	public boolean goToNextQuesion(){
		currentQuestion++;
		if( currentQuestion >= questions.size() ){
			return false;
		}else{
			return true;
		}
	}
	
	public String getCurrentQuesitionStatement(){
		Question q = (Question) questions.get(currentQuestion);
		return q.statement;
	}
	
	
	public List<Integer> getCurrentSelectionImages(){
		List<Integer> list = new ArrayList<Integer>();
		Question q = questions.get(currentQuestion);
		for(int i=0; i < q.selections.size(); i++){
			list.add( ((Selection)q.selections.get(i)).imageData );
		}
		return list;
	}
	
	public boolean isAnswerCorrect(int answer){
		Question q = (Question) questions.get(currentQuestion);
		Selection s = (Selection) q.selections.get(answer);
		return s.check;
	}
	
	
	public class Question{
			public String id;
			public String statement;
			public String author;
			public String rating;
			public String tags;
			public List<Selection>	  selections = new ArrayList<Selection>();
			public boolean isImage;
	
			Question(String anid, String aStatement){
				id=anid;
				statement=aStatement;
			}
			
			public void addSelection(Selection selection){
				selections.add(selection);
			}
			
			public void setIsImage(boolean value){
				isImage = value;
			}
	}
	
	
	public class Selection{
		public String 	id;
		public Question question;		
		public String 	selection;
		private int  imageData;
		public boolean	check;
		
		Selection(String aid, Question aQuestion, String aSelection, int anImageData, boolean aCheck){
			id=aid;
			question=aQuestion;
			selection=aSelection;
			imageData = anImageData;
			check=aCheck;
		}
		
	    public int getImageData() {
	        return imageData;
	    }
	    
	    public String getTextData() {
	        return selection;
	    }
	}
	
};
	
