package com.example.ebookreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MenuActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.activity_menu);
		
		Button logout = (Button) findViewById(R.id.logout_menu);
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
							
							Intent myIntent = new Intent(MenuActivity.this, LoginActivity.class);
							MenuActivity.this.startActivity(myIntent);								
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
				builder.setMessage("Are you sure you wish to logout?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();				
			}
		}
		);
		
		Button summary = (Button) findViewById(R.id.summary);
		summary.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(MenuActivity.this, SummaryActivity.class);
				MenuActivity.this.startActivity(myIntent);					
			}
		}
		);		

		Button showChapters = (Button) findViewById(R.id.show_chapters);
		showChapters.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
			    // Creating an instance for View Object
			    LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    View chapterView = inflater.inflate(R.layout.activity_book_reader, null);
				
	            LinearLayout hiddenLayout = (LinearLayout)chapterView.findViewById(R.id.chapters_bar_layout);
	            
	            //If the chapters bar is shown
	            if(hiddenLayout == null)
	            {
	                //Inflate the Hidden Layout Information View
	                LinearLayout myLayout = (LinearLayout)chapterView.findViewById(R.id.book_section);
	                View hiddenInfo = getLayoutInflater().inflate(R.layout.show_chapters, myLayout, false);
	                myLayout.addView(hiddenInfo);
	            }
	            
	            //if it isn't shown
	            else
	            {
	                View myView = chapterView.findViewById(R.id.chapters_bar_layout);
	                ViewGroup parent = (ViewGroup) myView.getParent();
	                parent.removeView(myView);
	            }
			}
		}
		);				
		
		ImageButton backButton = (ImageButton) findViewById(R.id.back_button_main);
		backButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					finish();
				}
			}
		);
		
		ImageButton homeButton = (ImageButton) findViewById(R.id.home_button_main);
		homeButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(MenuActivity.this, HomeActivity.class);
				MenuActivity.this.startActivity(myIntent);	
			}
		}
		);
		
		ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button_main);
		settingsButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(MenuActivity.this, SettingsActivity.class);
				MenuActivity.this.startActivity(myIntent);	
			}
		}
		);

		ImageButton coverButton = (ImageButton) findViewById(R.id.book_cover);
		coverButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				finish();	
			}
		}
		);				
		
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
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
