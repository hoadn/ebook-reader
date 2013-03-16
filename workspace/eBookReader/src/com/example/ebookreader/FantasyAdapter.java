package com.example.ebookreader;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FantasyAdapter extends BaseAdapter
{
	int[] mThumbIds = {R.drawable.acok, R.drawable.agot, R.drawable.hpatcos, R.drawable.hpatdh
			, R.drawable.hpatps, R.drawable.tdt, R.drawable.tdttg, R.drawable.th, R.drawable.tlotr};
	
	Context mContext;
	
	public FantasyAdapter(Context context)
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
	    
	    ImageView check = (ImageView)v.findViewById(R.id.grid_check);
	    
	    if (position == 1)
	    {
	    	imageButton.setOnClickListener(new OnClickListener()
	    	{
				  public void onClick(View arg0) 
				  {  
						// custom dialog
						final Dialog dialog = new Dialog(mContext);
						dialog.setContentView(R.layout.book_info_overlay);
						dialog.setTitle("Open Book");
			 
						String summaryString = "Long ago, in a time forgotten, a preternatural event threw the seasons " +
								"out of balance. In a land where summers can last decades and winters a lifetime, trouble " +
								"is brewing. The cold is returning, and in the frozen wastes to the north of Winterfell, " +
								"sinister and supernatural forces are massing beyond the kingdom’s protective Wall. " +
								"At the center of the conflict lie the Starks of Winterfell, a family as harsh and " +
								"unyielding as the land they were born to. Sweeping from a land of brutal cold to a " +
								"distant summertime kingdom of epicurean plenty, here is a tale of lords and ladies, " +
								"soldiers and sorcerers, assassins and bastards, who come together in a time of grim omens." +
								"\n\n"+
								"Here an enigmatic band of warriors bear swords of no human metal; " +
								"a tribe of fierce wildlings carry men off into madness; a cruel young dragon " +
								"prince barters his sister to win back his throne; and a determined woman undertakes the most " +
								"treacherous of journeys. Amid plots and counterplots, tragedy and betrayal, " +
								"victory and terror, the fate of the Starks, their allies, and their enemies " +
								" hangs perilously in the balance, as each endeavors to win that deadliest" +
								" of conflicts: the game of thrones.";
						
						TextView summary = (TextView) dialog.findViewById(R.id.book_summary);
						summary.setText(summaryString);
						summary.setMovementMethod(new ScrollingMovementMethod());
						
						ImageButton close = (ImageButton) dialog.findViewById(R.id.close_dialogue);
						close.setOnClickListener(new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialog.dismiss();	
							}	
						});
						
						Button delete = (Button) dialog.findViewById(R.id.delete_button);
						// if button is clicked, close the custom dialog
						delete.setOnClickListener(new OnClickListener() 
						{
							@Override
							public void onClick(View v) 
							{
								int[] temp = new int[8];
								
								temp[0] = mThumbIds[0];
								temp[1] = mThumbIds[2];
								temp[2] = mThumbIds[3];
								temp[3] = mThumbIds[4];
								temp[4] = mThumbIds[5];
								temp[5] = mThumbIds[6];
								temp[6] = mThumbIds[7];
								temp[7] = mThumbIds[8];
								
								mThumbIds = new int[8];
								mThumbIds = temp;
								
								dialog.dismiss();
								
								notifyDataSetChanged();
							}
						});
			 
						Button read = (Button) dialog.findViewById(R.id.read_button);
						// if button is clicked, close the custom dialog
						read.setOnClickListener(new OnClickListener() 
						{
							@Override
							public void onClick(View v) 
							{
								//Intent myIntent = new Intent(mContext, BookReaderActivity.class);
								//mContext.startActivity(myIntent);
							}
						});
						
						dialog.show();
					  }
	    	});
	    }
	    return v;
	}		
}