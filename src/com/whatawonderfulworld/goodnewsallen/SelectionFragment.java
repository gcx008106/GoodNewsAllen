package com.whatawonderfulworld.goodnewsallen;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SelectionFragment extends Fragment {
	

	
	OnAnswerSelectedListener mCallback;
    
    public View view;
    public Context context;
    
    
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
        view =  inflater.inflate(R.layout.selection_view, container, false);

		return view;
    }
    
    public void setSelectionImages(List<Integer> images){
		ViewPagerAdapter adapter = new ViewPagerAdapter((MainActivity) getActivity(), images);
		ViewPager myPager = (ViewPager) view.findViewById(R.id.view_pager);
		myPager.setAdapter(adapter);
		
		myPager.setCurrentItem(0);
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
    

     
}