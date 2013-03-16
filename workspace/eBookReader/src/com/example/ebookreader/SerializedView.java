package com.example.ebookreader;

import java.io.Serializable;

import android.view.View;

public class SerializedView implements Serializable
{
	private static final long serialVersionUID = 1L;
	public View mView;
	
	public SerializedView(View view)
	{
		mView = view;
	}
	
	public View getView()
	{
		return mView;
	}
}