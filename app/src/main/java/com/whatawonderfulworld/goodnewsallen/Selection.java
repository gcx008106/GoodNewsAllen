
package com.whatawonderfulworld.goodnewsallen;

import android.graphics.Bitmap;

	
public class Selection{
		public String 	id;
		public Question question;		
		public String 	selection;
		private Bitmap  imageData;
		public boolean	check;
		
		Selection(String aid, Question aQuestion, String aSelection, Bitmap anImageData, boolean aCheck){
			id=aid;
			question=aQuestion;
			selection=aSelection;
			imageData = anImageData;
			check=aCheck;
		}
		
	    public Bitmap getImageData() {
	        return imageData;
	    }
	    
	    public String getTextData() {
	        return selection;
	    }
};
	
	
