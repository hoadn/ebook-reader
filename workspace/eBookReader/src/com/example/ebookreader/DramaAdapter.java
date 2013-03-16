package com.example.ebookreader;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DramaAdapter extends BaseAdapter
{
	Context mContext;
	
	public DramaAdapter(Context context)
	{
		mContext = context;
	}
	
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
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
	    imageButton.setImageResource(R.drawable.gatsby1);
	    
	    ImageView check = (ImageView)v.findViewById(R.id.grid_check);
	    
	    if (position == 0)
	    	check.setImageResource(R.drawable.reading);
	    
	    if (position == 0)
	    {
	    	imageButton.setOnClickListener(new OnClickListener()
	    	{
				  public void onClick(View arg0) 
				  {  
						// custom dialog
						final Dialog dialog = new Dialog(mContext);
						dialog.setContentView(R.layout.book_info_overlay);
						dialog.setTitle("Open Book");
			 
						ImageView cover = (ImageView) dialog.findViewById(R.id.book_cover);
						cover.setImageResource(R.drawable.gatsby1);
						
						TextView title = (TextView) dialog.findViewById(R.id.book_title);
						title.setText("The Great Gatsby");
						
						TextView author = (TextView) dialog.findViewById(R.id.book_author);
						author.setText("F. Scott. Fitzgerald");
						
						TextView summary = (TextView) dialog.findViewById(R.id.book_summary);
						
						String summaryStr = "The Great Gatsby, F. Scott Fitzgerald’s third book, " +
								"stands as the supreme achievement of his career. This exemplary " +
								"novel of the Jazz Age has been acclaimed by generations of readers. " +
								"The story of the fabulously wealthy Jay Gatsby and his love for " +
								"the beautiful Daisy Buchanan, of lavish parties on Long Island at " +
								"a time when The New York Times noted “gin was the national drink " +
								"and sex the national obsession,” it is an exquisitely crafted tale " +
								"of America in the 1920s." +
								"\n\n" +
								"The Great Gatsby is one of the great classics of twentieth-century " +
								"literature.";
						
						summary.setText(summaryStr);
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
								
							}
						});
			 
						Button read = (Button) dialog.findViewById(R.id.read_button);
						// if button is clicked, close the custom dialog
						read.setOnClickListener(new OnClickListener() 
						{
							@Override
							public void onClick(View v) 
							{
								Intent myIntent = new Intent(mContext, BookReaderActivity.class);
								mContext.startActivity(myIntent);
							}
						});
						
						dialog.show();
					  }
	    	});
	    }
	    return v;
	}
	
}