package com.whatawonderfulworld.goodnewsallen;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerAdapter extends PagerAdapter {

	 MainActivity activity;
	 List<Integer> images = new ArrayList<Integer>();

	 public ViewPagerAdapter(MainActivity act, List<Integer> imgArra) {
	  images = imgArra;
	  activity = act;
	 }

	 public int getCount() {
	  return images.size();
	 }

	 public Object instantiateItem(View collection, int position) {
	  ImageView view = new ImageView(activity);
	  //view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	  //view.setScaleType(ScaleType.FIT_XY);
	  view.setImageResource(images.get(position));
	  ((ViewPager) collection).addView(view, 0);
	  view.setId(position);
	  view.setOnClickListener(new OnClickListener(){
	        public void onClick(View v){
	            //this will log the page number that was click
	            //Log.i("TAG", "This page was clicked: " + v.getId());
	            activity.onAnswerSelected(v.getId());
	        }
	  });
	  
	  
	  return view;
	 }

	 @Override
	 public void destroyItem(View arg0, int arg1, Object arg2) {
	  ((ViewPager) arg0).removeView((View) arg2);
	 }

	 @Override
	 public boolean isViewFromObject(View arg0, Object arg1) {
	  return arg0 == ((View) arg1);
	 }

	 @Override
	 public Parcelable saveState() {
	  return null;
	 }
	}