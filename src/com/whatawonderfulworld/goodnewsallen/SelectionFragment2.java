package com.whatawonderfulworld.goodnewsallen;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SelectionFragment2 extends Fragment {
	

	
	OnAnswerSelectedListener mCallback;
    
    public LinearLayout mainLayout;
    public Context context;
    
    public List<ImageView> imageViews = new ArrayList<ImageView>();
    
    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnAnswerSelectedListener {
        public void onAnswerSelected(int answer);
        //public void updateView();
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            //mCurrentQuestion = savedInstanceState.getInt(ARG_QUESTION);
            //mAnswer = savedInstanceState.getInt(ARG_ANSWER);            
        }
        

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.selection_view2, container, false);
        mainLayout = (LinearLayout) v.findViewById(R.id._linearLayoutForPicture);
                
        return v;
    }
    

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnAnswerSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnResultClosedListener");
        }
    }
    

    public void setSelectionImages(List<Integer> images){
    	ImageView imageView = null;
    	
    	// Have to improve below.
        for (int i = 0; i < images.size() ; i++) {
        	 if(imageViews.size()<=i){
	        	 imageView = new ImageView(getActivity());
	        	 imageViews.add(imageView);
	             imageView.setId(i);  
	             imageView.setOnClickListener(new OnClickListener() {
	                        @Override  
	                        public void onClick(View v) {  
	                        	int ans = v.getId();
	                            mCallback.onAnswerSelected(ans);
	                        }  
	                   });  
	             mainLayout.addView(imageView);  
        	 }else{
        		 imageView = imageViews.get(i);
        	 }
             imageView.setImageResource( images.get(i) );  
        }
        
    }
     
}