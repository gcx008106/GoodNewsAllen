package com.whatawonderfulworld.goodnewsallen;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
        
        mResultView = (TextView) v.findViewById(R.id.result);
        mLabelView = (TextView) v.findViewById(R.id.result_label);
        Button closeButton = (Button) v.findViewById(R.id.close_button);
        mCloseButton = closeButton;
        if (mPendingDisp != null) {
            applyResult();
        }

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
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnResultClosedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnResultClosedListener");
        }
    }
    
    
    private TextView mResultView;
    private TextView mLabelView;
    private Button mCloseButton;
    private String mPendingDisp;
    private boolean mCorrect;
    private boolean mIsLast;

    public void setResult(boolean correct, String disp, boolean isLast) {
        mPendingDisp = disp;
        mCorrect = correct;
        mIsLast = isLast;
        if (mResultView != null) {
            applyResult();
        }
    }

    private void applyResult() {
        mResultView.setText(mPendingDisp);
        mLabelView.setText(mCorrect ? R.string.result_correct : R.string.result_wrong);
        mLabelView.setTextColor(getResources().getColor(mCorrect ? R.color.correct : R.color.wrong, null));
        mCloseButton.setText(mIsLast ? R.string.see_score : R.string.close_result);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        //outState.putInt(ARG_QUESTION, mCurrentQuestion);
        //outState.putInt(ARG_ANSWER, mAnswer);        
    }
    
}