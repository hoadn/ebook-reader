package com.example.ebookreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity 
{
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_layout);
		setContentView(R.layout.settings_layout);
	
		Button logout = (Button) findViewById(R.id.logout_settings);
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
							
							Intent myIntent = new Intent(SettingsActivity.this, LoginActivity.class);
							SettingsActivity.this.startActivity(myIntent);								
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};
			}
		});

		ImageButton backButton = (ImageButton) findViewById(R.id.back_button_settings);
		backButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					finish();
				}
			}
		);
		
		ImageButton homeButton = (ImageButton) findViewById(R.id.home_button_settings);
		homeButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(SettingsActivity.this, HomeActivity.class);
				SettingsActivity.this.startActivity(myIntent);	
			}
		}
		);
		
		
		
	}
	
	 SharedPreferences.OnSharedPreferenceChangeListener prefChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { 
	        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) 
	        { 
	        	if(key.equals("screenBrightness")) 
	        	{
	        		//set brightness here
	        		float sb = sharedPreferences.getInt(key, 1);
	        		WindowManager.LayoutParams lp = getWindow().getAttributes();
	        		lp.screenBrightness = sb / 100.0f;
	        		getWindow().setAttributes(lp);
	        	}
	        }
	 };	
	
	/**
	 * Determines whether to always show the simplified settings UI, where
	 * settings are presented in a single list. When false, settings are shown
	 * as a master/detail two-pane view on tablets. When true, a single pane is
	 * shown on tablets.
	 */
	private static final boolean ALWAYS_SIMPLE_PREFS = false;

	@Override
	protected void onPostCreate(Bundle savedInstanceState) 
	{
		super.onPostCreate(savedInstanceState);
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
	
	private void setupSimplePreferencesScreen()
	{
		addPreferencesFromResource(R.xml.settings_layout);
	}
}
