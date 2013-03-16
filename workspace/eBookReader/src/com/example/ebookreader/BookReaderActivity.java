package com.example.ebookreader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class BookReaderActivity extends FragmentActivity 
{
	private PagerAdapter mPagerAdapter;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		
		setContentView(R.layout.activity_book_reader);
		
		initialisePaging();
		Button logout = (Button) findViewById(R.id.logout_book);
		logout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) 
				    {
				        switch (which)
				        {
				        case DialogInterface.BUTTON_POSITIVE:
				        	setResult(100);
							finish();
							
							Intent myIntent = new Intent(BookReaderActivity.this, LoginActivity.class);
							BookReaderActivity.this.startActivity(myIntent);								
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(BookReaderActivity.this);
				builder.setMessage("Are you sure you wish to logout?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();	
			}
		}
		);
		
		ImageButton menuButton = (ImageButton) findViewById(R.id.main_menu_book);
		menuButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
				
					Intent myIntent = new Intent(BookReaderActivity.this, MenuActivity.class);
					BookReaderActivity.this.startActivity(myIntent);	
				}
			}
		);
		
		ImageButton homeButton = (ImageButton) findViewById(R.id.home_button_book);
		homeButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				finish();
				
				Intent myIntent = new Intent(BookReaderActivity.this, HomeActivity.class);
				BookReaderActivity.this.startActivity(myIntent);	
			}
		}
		);
		
		ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button_book);
		settingsButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(BookReaderActivity.this, SettingsActivity.class);
				BookReaderActivity.this.startActivity(myIntent);	
			}
		}
		);		
		
		ImageButton searchButton = (ImageButton) findViewById(R.id.search_button);
		searchButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				
			}
		}
		);		
	}
	
	public void initialisePaging()
	{
		/*List<ImageView> fragments = new Vector<ImageView>();
		fragments.add(Fragment.instantiate(this, Page1Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page2Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page3Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page4Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page5Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page6Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Page7Fragment.class.getName()));
		*/
		
		
		
		mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager());
		//
		pager = (ViewPager)findViewById(R.id.book_view);
		Log.i("Test","Count:"+mPagerAdapter.getCount());
		pager.setOffscreenPageLimit(10);
		pager.setAdapter(mPagerAdapter);		
		pager.setOffscreenPageLimit(10);
	}
	
	protected void onActivityResult(int requestCode, int resultCode,Intent data) 
	{
	    switch(resultCode)
	    {
	    	case 100:           
	    		setResult(100);
	    		finish();          // to close this activity
	        break;  
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_reader, menu);
		return true;
	}

	public class PagerAdapter extends FragmentPagerAdapter 
	{
		private ArrayList<Page1Fragment> screens;

		private List<Page1Fragment> fragments;
		/**
		 * @param fm
		 * @param fragments
		 */
		public PagerAdapter(FragmentManager fm) 
		{
			super(fm);
			
			screens = new ArrayList<Page1Fragment>(7);
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby1));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby2));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby3));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby4));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby5));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby6));
			screens.add(Page1Fragment.getInstance(R.drawable.gatsby7));
			
			this.fragments = screens;
		}
		/* (non-Javadoc)
		 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int position) 
		{
			return this.fragments.get(position);
		}

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() 
		{
			return this.fragments.size();
		}
	}		
}
