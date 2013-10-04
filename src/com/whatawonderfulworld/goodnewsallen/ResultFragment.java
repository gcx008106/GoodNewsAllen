package com.whatawonderfulworld.goodnewsallen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultFragment extends Fragment {
	

	
    OnResultClosedListener mCallback;
	    
    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnResultClosedListener {
        /** Called by SelectionFragment when a list item is selected */
        public void onResultClosed();
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
//        Button button = (Button) getActivity().findViewById(R.id.close_button);
//        button.setOnClickListener(new View.OnClickListener() {  
//        	  
//            @Override  
//            public void onClick(View v) {  
//                if (mCallback != null) {  
//                    mCallback.onResultClosed(mCurrentQuestion);  
//                }  
//            }  
//        });  

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.result_view, container, false);
        
        Button closeButton = (Button) v.findViewById(R.id.close_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	mCallback.onResultClosed();
            }
        });
        
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        /*
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
        	updateResultView(args.getInt(ARG_QUESTION), mAnswer);
        } else if (mCurrentQuestion != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateResultView(mCurrentQuestion, mAnswer);
        }*/
        
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnResultClosedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnResultClosedListener");
        }
    }
    
    
    public void setResultDisp(String disp) {
        TextView result = (TextView) getActivity().findViewById(R.id.result);
        result.setText(disp);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        //outState.putInt(ARG_QUESTION, mCurrentQuestion);
        //outState.putInt(ARG_ANSWER, mAnswer);        
    }
    
}