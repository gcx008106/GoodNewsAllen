package com.whatawonderfulworld.goodnewsallen;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
	Context context;
	private int[] GalImages = new int[] {
	R.drawable.m1,
	R.drawable.m2,
	R.drawable.m3
	};
	ImageAdapter(Context context){
	this.context=context;
	}
	@Override
	public int getCount() {
	return GalImages.length;
	}
	 
	@Override
	public boolean isViewFromObject(View view, Object object) {
	return view == ((ImageView) object);
	}
	 
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		/*
        LayoutInflater inflater = (LayoutInflater) ((Activity) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View v = inflater.inflate(R.layout.page, container, false);
        
        
	    ImageView imageView = (ImageView) v.findViewById(R.id.image_view);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setImageResource(GalImages[position]);
	    		 
		return v;
		*/
	
	ImageView imageView = new ImageView(context);
	//int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
	//imageView.setPadding(padding, padding, padding, padding);
	imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	imageView.setImageResource(GalImages[position]);
	((ViewPager) container).addView(imageView, 0);
	
   // Button myButton = new Button(context);
   // myButton.setText("SOREDA!");
    
   //	((ViewPager) container).addView(myButton, 0); 
	
	return imageView;
	}
	 
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	((ViewPager) container).removeView((ImageView) object);
	}
}
