package com.example.ebookreader;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

@SuppressLint("NewApi")
public class HomeActivity extends FragmentActivity implements OnTabChangeListener 
{

	private static TabHost mTabHost;
	private HashMap mapTabInfo = new HashMap();
	private TabInfo mLastTab = null;

	private class TabInfo 
	{
		 private String tag;
         private Class clss;
         private Bundle args;
         private Fragment fragment;
         
         TabInfo(String tag, Class clazz, Bundle args) 
         {
        	 this.tag = tag;
        	 this.clss = clazz;
        	 this.args = args;
         }
	}

	class TabFactory implements TabContentFactory 
	{
		private final Context mContext;

	    /**
	     * @param context
	     */
	    public TabFactory(Context context) 
	    {
	        mContext = context;
	    }

	    /** (non-Javadoc)
	     * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
	     */
	    public View createTabContent(String tag) 
	    {
	        View v = new View(mContext);
	        v.setMinimumWidth(0);
	        v.setMinimumHeight(0);
	        return v;
	    }

	}
	/** (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_home);
		// Step 2: Setup TabHost
		initialiseTabHost(savedInstanceState);
		
		if (savedInstanceState != null) 
            mTabHost.setCurrentTab(-1);
		
		Button logout = (Button) findViewById(R.id.logout_home);
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
							
							Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
							HomeActivity.this.startActivity(myIntent);								
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
				builder.setMessage("Are you sure you wish to logout?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();				
			}
		}
		);

		ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button_home);
		settingsButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					Intent myIntent = new Intent(HomeActivity.this, SettingsActivity.class);
					HomeActivity.this.startActivity(myIntent);	
				}
			}
		);		
		
		ImageButton backButton = (ImageButton) findViewById(R.id.back_button_home);
		backButton.setOnClickListener(new OnClickListener()
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
					            break;

					        case DialogInterface.BUTTON_NEGATIVE:
					            //No button clicked
					            break;
					        }
					    }
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
					builder.setMessage("Are you sure you wish to exit?").setPositiveButton("Yes", dialogClickListener)
					    .setNegativeButton("No", dialogClickListener).show();	
				}
			}
		);
		
		
	}

	/** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
     */
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }

	/**
	 * Step 2: Setup TabHost
	 */
	private void initialiseTabHost(Bundle args) 
	{
		mTabHost = (TabHost)findViewById(R.id.tabhost);
        mTabHost.setup();
        
        TabInfo tabInfo = null;
        HomeActivity.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Author").setIndicator("Author"), ( tabInfo = new TabInfo("Author", AuthorFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        HomeActivity.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Category").setIndicator("Category"), ( tabInfo = new TabInfo("Category", CategoryFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        HomeActivity.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Search").setIndicator("Search"), ( tabInfo = new TabInfo("Search", SearchFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        // Default to first tab
        this.onTabChanged("Author");
        //
        mTabHost.setOnTabChangedListener(this);
        
        int height;
        int width;
        
        if (android.os.Build.VERSION.SDK_INT >= 13)
        {
        	Display display = getWindowManager().getDefaultDisplay();
        	Point size = new Point();
        	display.getSize(size);
        	width = size.x;
        }
        else
        {
        	Display display = getWindowManager().getDefaultDisplay(); 
        	width = display.getWidth();  // deprecated
        }
        
        for (int i = 0 ; i < 3 ; i++)
        	mTabHost.getTabWidget().getChildAt(i).getLayoutParams().width = (width/3)-2;
        
	}

	private static View createTabView(final Context context, final String text) 
	{
		View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}	
	
	/**
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private static void addTab(final HomeActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) 
	{
        String tag = tabSpec.getTag();		
		
		View tabview = createTabView(mTabHost.getContext(), tag);
		
		tabSpec = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabContentFactory() 
		{
			public View createTabContent(String tag) 
			{
				return new TextView(activity);
			}
		});
		
		// Attach a Tab view factory to the spec
		//tabSpec.setContent(activity.new TabFactory(activity));

        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state.  If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        tabInfo.fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
        
        
        if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) 
        {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.detach(tabInfo.fragment);
            ft.commit();
            activity.getSupportFragmentManager().executePendingTransactions();
        }

        tabHost.addTab(tabSpec);
	}

	/** (non-Javadoc)
	 * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
	 */
	public void onTabChanged(String tag) 
	{
		TabInfo newTab = (TabInfo) this.mapTabInfo.get(tag);
		if (mLastTab != newTab) {
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                	ft.detach(mLastTab.fragment);
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(this,
                            newTab.clss.getName(), newTab.args);
                    ft.add(R.id.realtabcontent, newTab.fragment, newTab.tag);
                } else {
                    ft.attach(newTab.fragment);
                }
            }

            mLastTab = newTab;
            ft.commit();
            this.getSupportFragmentManager().executePendingTransactions();
		}
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
	
}
