package com.example.ebookreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Page1Fragment extends Fragment 
{
	private int imageResource = R.drawable.gatsby1;
	
	public static Page1Fragment getInstance(int imgRes)
	{
		Page1Fragment frag = new Page1Fragment();
		
		Bundle args = new Bundle();
		args.putInt("imageResource", imgRes);
		frag.setArguments(args);
		
		return frag;
	}
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		Log.i("Test","onCreate");
		imageResource = getArguments()!=null?getArguments().getInt("imageResource"):R.drawable.gatsby1;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		Log.i("Test","onCreateView");
		View v = inflater.inflate(R.layout.page1, container, false);
		ImageView iv = ((ImageView)v.findViewById(R.id.page1_img_view));
		iv.setImageResource(imageResource);
		return v;
	}
}
