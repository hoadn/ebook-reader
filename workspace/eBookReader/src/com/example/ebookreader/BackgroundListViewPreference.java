package com.example.ebookreader;

import android.content.ClipData.Item;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BackgroundListViewPreference extends Preference 
{
	private final String TAG = getClass().getName();

	private static final String ROBOBUNNYNS = "http://robobunny.com";
	private static final int DEFAULT_VALUE = 50;

	private int mCurrentValue;
	private String mUnitsLeft = "";
	private String mUnitsRight = "";

	private HorizontalListView mListView;

	private TextView mStatusText;

	public BackgroundListViewPreference(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		initPreference(context, attrs);
	}

	public BackgroundListViewPreference(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		initPreference(context, attrs);
	}

	private void initPreference(Context context, AttributeSet attrs) 
	{
		setValuesFromXml(attrs);

		mListView = new HorizontalListView(context, attrs);
		mListView.setAdapter(new ListAdapter(context));
	}

	private void setValuesFromXml(AttributeSet attrs) 
	{
		mUnitsLeft = getAttributeStringValue(attrs, ROBOBUNNYNS, "unitsLeft",
				"");
		String units = getAttributeStringValue(attrs, ROBOBUNNYNS, "units", "");
		mUnitsRight = getAttributeStringValue(attrs, ROBOBUNNYNS, "unitsRight",
				units);
	}

	private String getAttributeStringValue(AttributeSet attrs,
			String namespace, String name, String defaultValue) 
	{
		String value = attrs.getAttributeValue(namespace, name);
		
		if (value == null)
			value = defaultValue;

		return value;
	}

	@Override
	protected View onCreateView(ViewGroup parent) 
	{
		RelativeLayout layout = null;

		try 
		{
			LayoutInflater mInflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			layout = (RelativeLayout) mInflater.inflate(
					R.layout.horizontal_list_view_preference, parent, false);
		} 
		catch (Exception e) 
		{
			Log.e(TAG, "Error creating seek bar preference", e);
		}

		return layout;
	}

	@Override
	public void onBindView(View view) 
	{
		super.onBindView(view);

		try 
		{
			// move our seekbar to the new view we've been given
			ViewParent oldContainer = mListView.getParent();
			ViewGroup newContainer = (ViewGroup) view
					.findViewById(R.id.listViewPrefBarContainer);

			if (oldContainer != newContainer) 
			{
				// remove the seekbar from the old view
				if (oldContainer != null) 
				{
					((ViewGroup) oldContainer).removeView(mListView);
				}
				// remove the existing seekbar (there may not be one) and add
				// ours
				newContainer.removeAllViews();
				newContainer.addView(mListView,
						ViewGroup.LayoutParams.FILL_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
			}
		} catch (Exception ex) {
			Log.e(TAG, "Error binding view: " + ex.toString());
		}

		updateView(view);
	}

	/**
	 * Update a SeekBarPreference view with our current state
	 * 
	 * @param view
	 */
	protected void updateView(View view) {
		try {
			RelativeLayout layout = (RelativeLayout) view;

			mStatusText = (TextView) layout.findViewById(R.id.listViewPrefValue);
			mStatusText.setText(String.valueOf(mCurrentValue));
			mStatusText.setMinimumWidth(30);

			TextView unitsRight = (TextView) layout
					.findViewById(R.id.listViewPrefUnitsRight);
			unitsRight.setText(mUnitsRight);

			TextView unitsLeft = (TextView) layout
					.findViewById(R.id.listViewPrefUnitsLeft);
			unitsLeft.setText(mUnitsLeft);

		} catch (Exception e) {
			Log.e(TAG, "Error updating seek bar preference", e);
		}

	}

	@Override
	protected Object onGetDefaultValue(TypedArray ta, int index) {

		int defaultValue = ta.getInt(index, DEFAULT_VALUE);
		return defaultValue;

	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {

		if (restoreValue) {
			mCurrentValue = getPersistedInt(mCurrentValue);
		} else {
			int temp = 0;
			try {
				temp = (Integer) defaultValue;
			} catch (Exception ex) {
				Log.e(TAG, "Invalid default value: " + defaultValue.toString());
			}

			persistInt(temp);
			mCurrentValue = temp;
		}

	}
	
	private class ListAdapter extends BaseAdapter
	{
		private Context mContext;
		private int[] mThumbs = {R.drawable.no_wallpaper , R.drawable.wallpaper, R.drawable.wallpaper1, R.drawable.wallpaper2, R.drawable.login_wallpaper};
		
		private ListAdapter(Context context)
		{
			mContext = context;
		}

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {       
    		View v;
            
            if (convertView == null) 
            {
                    LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.preference_list_item, null);
            }
            else
            	v = (View) convertView;
            
            ImageView thumb = (ImageView)v.findViewById(R.id.preference_image);
            thumb.setImageResource(mThumbs[position]);
            
            ImageView check = (ImageView)v.findViewById(R.id.check);
            
            if (position == 1)
            	check.setImageResource(R.id.check);
            
            return v;
	    }

		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return mThumbs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) 
		{
			return position;
		}
	}
}
