package com.example.ebookreader;

import java.util.Arrays;
import java.util.List;

import com.example.ebookreader.AuthorFragment.IntHolder;

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

public class CategoryFragment extends Fragment
{
	static IntHolder selectedItem; // nothing selected at first
	private boolean fictionBool = true;
	private boolean nonfictionBool = false;
	private static GridView mGrid; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		selectedItem = new IntHolder(-1);
		
		final FrameLayout theLayout = (FrameLayout)inflater.inflate(R.layout.fragment_category, container, false);
		
		/*
		fiction.setOnClickListener(new OnClickListener() {

		    public void onClick(View button) 
		    {
		        if (button.isSelected())
		        {
		            button.setSelected(false);
		            //...Handle toggle off
		        } 
		        else 
		        {
		            button.setSelected(true);
		            //...Handled toggle on
		        }
		    }

		});
		*/
		mGrid = (GridView) theLayout.findViewById(R.id.category_book_grid);		
		
		final HorizontalListView categoryList = (HorizontalListView) theLayout.findViewById(R.id.category_list);	

	    categoryList.setOnItemClickListener(new ItemClickListener());	
		
	    List<String> items = Arrays.asList("Crime", "Drama", "Fantasy", "Horror", "Romance" ,"Science Fiction", "Thriller");
	    setListItems(theLayout.getContext(), categoryList, items, selectedItem);
		
		final Button fiction = (Button) theLayout.findViewById(R.id.fiction);
		final Button nonFiction = (Button) theLayout.findViewById(R.id.nonfiction);
		
		fiction.setSelected(true);
		fictionBool = true;
		
		fiction.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (nonfictionBool)
				{
					fiction.setSelected(true);
					nonFiction.setSelected(false);
					
					nonfictionBool = false;
					fictionBool = true;
					
				    List<String> items = Arrays.asList("Crime", "Drama", "Fantasy", "Horror", "Romance" ,"Science Fiction", "Thriller");
				    setListItems(theLayout.getContext(), categoryList, items, selectedItem);
				    
					mGrid.setAdapter(null);				    
				}
			}
		});			
		
		nonFiction.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (fictionBool)
				{
					fiction.setSelected(false);
					nonFiction.setSelected(true);
					
					nonfictionBool = true;
					fictionBool = false;					
					
				    List<String> items = Arrays.asList("Autobiography", "Biography", "Business/Finance", "Children", "DIY","Entertainment", "Self-Help");
				    setListItems(theLayout.getContext(), categoryList, items, selectedItem);
				    
				    mGrid.setAdapter(null);
				}
			}
		});		    
	    
		// Inflate the layout for this fragment
		return theLayout;
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
	    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context, list_item_id, listItems) { 
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) 
	        {
	        	 LayoutInflater inflater = (LayoutInflater) 
	        			 context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	 LinearLayout itemView = (LinearLayout) inflater.inflate(R.layout.list_item, parent, false);
	        	 
	        	 TextView genre = (TextView) itemView.findViewById(R.id.list_item_text);
	        	 genre.setText((String)listItems.get(position));	        	 
	        	 
	        	 if (selectedItem.value == position)
	                itemView.setBackgroundResource(R.drawable.list_item_selected);
	        	 else
	        		 itemView.setBackgroundResource(R.drawable.list_item_unselected);
 	 
	        	 return itemView;
	        }
	    };
	    adapter.setDropDownViewResource(dropdown_id);
	    listView.setAdapter(adapter);
	}	
	
	public class ItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) 
		{
			if (selectedItem.value == position)
				view.setBackgroundResource(R.drawable.list_item_selected);
			
			if (fictionBool)
			{
				// TODO Auto-generated method stub
				if (position == 1)
				{
					mGrid.setAdapter(null);
					mGrid.setAdapter(new DramaAdapter(parent.getContext()));
				}
				else if (position == 2)
				{
					mGrid.setAdapter(null);
					mGrid.setAdapter(new FantasyAdapter(parent.getContext()));
				}
			}
			
			else
			{
				if (position == 1)
				{
					mGrid.setAdapter(null);
					mGrid.setAdapter(new BiographyAdapter(parent.getContext()));					
				}
			}
		}
		
	}
}
