package com.whatawonderfulworld.goodnewsallen;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class ScoreActivity extends FragmentActivity 

implements ScoreFragment.OnScoreClosedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    Intent intent = getIntent();
	    
	    int		score = intent.getIntExtra("score",10000);
	    //int	position	= intent.getIntExtra("position", 0);
	    
		setContentView(R.layout.activity_score);
		// I guess fragment's view would have been created at this point. Is it right?
		
		// How can I hand data over to child fragments when they need it, such as text to display?
	
        ScoreFragment scoreFrag = (ScoreFragment)
                getSupportFragmentManager().findFragmentById(R.id.score_fragment);
    	scoreFrag.setScore(score);
        
        // Show the Up button in the action bar.
		//setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
    //public void onCloseClick(View v) {
    public void onScoreClosed() {
        setResult(1);
        finish();    
    }   
}
