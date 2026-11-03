package com.whatawonderfulworld.goodnewsallen;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
        // AndroidX overwrites the root view id with the <fragment> tag id, so grab the
        // ViewPager now (it is the root of selection_view.xml).
        mPager = (ViewPager) view.findViewById(R.id.view_pager);
        if (mPendingImages != null) {
            applySelectionImages(mPendingImages);
        }

		return view;
    }
    
    private ViewPager mPager;
    private List<Integer> mPendingImages;

    public void setSelectionImages(List<Integer> images){
        mPendingImages = images;
        if (mPager != null) {
            applySelectionImages(images);
        }
    }

    private void applySelectionImages(List<Integer> images){
		ViewPagerAdapter adapter = new ViewPagerAdapter((MainActivity) getActivity(), images);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnAnswerSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnResultClosedListener");
        }
    }
    

     
}