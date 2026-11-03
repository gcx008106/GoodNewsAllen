
package com.whatawonderfulworld.goodnewsallen;

import java.util.ArrayList;
import java.util.List;

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
};
	
