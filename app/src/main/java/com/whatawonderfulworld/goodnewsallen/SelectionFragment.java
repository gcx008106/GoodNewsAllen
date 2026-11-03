package com.whatawonderfulworld.goodnewsallen;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        mIndicator = (TextView) view.findViewById(R.id.page_indicator);
        mPrevButton = (Button) view.findViewById(R.id.prev_button);
        mNextButton = (Button) view.findViewById(R.id.next_button);

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { moveSelection(-1); }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { moveSelection(1); }
        });
        view.findViewById(R.id.choose_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getAdapter() != null) {
                    mCallback.onAnswerSelected(mPager.getCurrentItem());
                }
            }
        });
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) { updateControls(); }
        });
        if (mPendingImages != null) {
            applySelectionImages(mPendingImages);
        }

		return view;
    }
    
    private ViewPager mPager;
    private TextView mIndicator;
    private Button mPrevButton;
    private Button mNextButton;
    private List<Integer> mPendingImages;

    public void setSelectionImages(List<Integer> images){
        mPendingImages = images;
        if (mPager != null) {
            applySelectionImages(images);
        }
    }

    /** Pages the image choices by {@code delta} (-1 = previous, +1 = next). Returns true if it moved. */
    public boolean moveSelection(int delta){
        if (mPager == null || mPager.getAdapter() == null) {
            return false;
        }
        int target = mPager.getCurrentItem() + delta;
        if (target < 0 || target >= mPager.getAdapter().getCount()) {
            return false;
        }
        mPager.setCurrentItem(target, true);
        return true;
    }

    private void applySelectionImages(List<Integer> images){
		ViewPagerAdapter adapter = new ViewPagerAdapter((MainActivity) getActivity(), images);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);
		updateControls();
    }

    /** Refreshes the "1 / 3" indicator and dims ◀ / ▶ at either end. */
    private void updateControls(){
        int count = mPager.getAdapter() == null ? 0 : mPager.getAdapter().getCount();
        int current = mPager.getCurrentItem();
        mIndicator.setText(getString(R.string.page_indicator, count == 0 ? 0 : current + 1, count));
        setEnabledLook(mPrevButton, current > 0);
        setEnabledLook(mNextButton, current < count - 1);
    }

    private static void setEnabledLook(Button b, boolean enabled){
        b.setEnabled(enabled);
        b.setAlpha(enabled ? 1f : 0.3f);
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