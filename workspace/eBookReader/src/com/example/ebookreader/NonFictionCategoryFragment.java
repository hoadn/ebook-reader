package com.example.ebookreader;

import java.util.Arrays;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.ebookreader.CategoryFragment.OnFragmentInteractionListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link CategoryFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class NonFictionCategoryFragment extends Fragment
{
	IntHolder selectedItem = new IntHolder(-1); // nothing selected at first
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		final FrameLayout theLayout = (FrameLayout)inflater.inflate(R.layout.fragment_category, container, false);
		
		Button nonFiction = (Button) theLayout.findViewById(R.id.nonfiction);
		nonFiction.setSelected(true);
		
		Button fiction = (Button) theLayout.findViewById(R.id.fiction);
		
		fiction.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				
			}	
		});
		
		final GridView mGrid = (GridView) theLayout.findViewById(R.id.category_book_grid);
		
		HorizontalListView categoryList = (HorizontalListView) theLayout.findViewById(R.id.category_list);
		
		categoryList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) 
			{
				Toast.makeText(parent.getContext(), position + "", Toast.LENGTH_SHORT).show();
				
				// TODO Auto-generated method stub
				if (position == 0)
				{
					mGrid.invalidate();
					mGrid.setAdapter(new BiographyAdapter(theLayout.getContext()));
				}
				
			}
		});

	    List<String> items = Arrays.asList("Autobiography", "Biography", "Business/Finance", "Children", "DIY","Entertainment", "Self-Help");
	    setListItems(theLayout.getContext(), categoryList, items, selectedItem);
		
		// Inflate the layout for this fragment
		return theLayout;
	}
	
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
	
	public class IntHolder 
	{
	    public int value;
	    
	    public IntHolder() {}
	    
	    public IntHolder(int v) 
	    { 
	    	value = v; 
	    } 
	}	
	
	static void setListItems(Context context, AdapterView listView, List listItems, final IntHolder selectedPosition)
	{
	    setListItems(context, listView, listItems, selectedPosition, 
	                 android.R.layout.simple_list_item_1, 
	                 android.R.layout.simple_spinner_dropdown_item);
	}
	
	static void setListItems(final Context context, AdapterView listView, final List listItems, final IntHolder selectedPosition, 
	                         int list_item_id, int dropdown_id)
	{
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
	        public void onItemClick(AdapterView<?> list, View lv, int position, long id) 
	        {
	            selectedPosition.value = position;
	        }
	    });
	    
	    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context, list_item_id, listItems) { 
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) 
	        {
	        	 LayoutInflater inflater = (LayoutInflater) 
	        			 context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	 LinearLayout itemView = (LinearLayout) inflater.inflate(R.layout.list_item, parent, false);
	        	 
	           // View itemView = super.getView(position, convertView, parent);
	        	 if (selectedPosition.value == position)
	                itemView.setBackgroundResource(R.drawable.list_item_selected); // orange
	        	 else
	        		 itemView.setBackgroundResource(R.drawable.list_item_unselected);
	        	 
	        	 TextView genre = (TextView) itemView.findViewById(R.id.list_item_text);
	        	 genre.setText((String)listItems.get(position));
	        	 
	        	 return itemView;
	        }
	    };
	    adapter.setDropDownViewResource(dropdown_id);
	    listView.setAdapter(adapter);
	}	
}
