package com.example.ebookreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

public class BiographyAdapter extends BaseAdapter
{
	int[] mThumbIds = {R.drawable.bht, R.drawable.einstein, R.drawable.russell, R.drawable.tiger};
	
	Context mContext;
	
	public BiographyAdapter(Context context)
	{
		mContext = context;
	}
	
	@Override
	public int getCount() 
	{
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) 
	{
		return null;
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
	    View v;
	    if (convertView == null) 
	    {  // if it's not recycled, initialize some attributes
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v = inflater.inflate(R.layout.grid_item, parent, false);
	    } 
	    else 
	    {
	        v = convertView;
	    }
	    
	    ImageButton imageButton = (ImageButton) v.findViewById(R.id.cover_image);
	    imageButton.setImageResource(mThumbIds[position]);

	    return v;
	}		
}	
