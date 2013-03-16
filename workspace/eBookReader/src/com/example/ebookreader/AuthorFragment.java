package com.example.ebookreader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link AuthorFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link AuthorFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class AuthorFragment extends Fragment 
{
	private ExpandListAdapter expAdapter;
	private ArrayList<ExpandListGroup> expListItems;
	
	private static FrameLayout theLayout;
	private static ExpandableListView expandList;
	
	private boolean firstnameBool = true;
	private boolean surnameBool = false;
	
	private boolean visible = false;
	
	//private OnFragmentInteractionListener mListener;
	
	static IntHolder selectedItem;// nothing selected at first
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		selectedItem = new IntHolder(-1); // nothing selected at first
		
		theLayout = (FrameLayout) inflater.inflate(R.layout.fragment_author, container, false);
		
		ListView alphabetList = (ListView) theLayout.findViewById(R.id.alphabet_list);		
	    List<String> items = Arrays.asList("A - E", "F - J", "K - O" ,"M - Q", "R - U", "V - Z");
	    setListItems(theLayout.getContext(), alphabetList, items, selectedItem);
	   
	    expandList = (ExpandableListView) theLayout.findViewById(R.id.author_list);
	    
	    alphabetList.setOnItemClickListener(new ItemClickListener());
	   
	    
	    final Button firstname = (Button) theLayout.findViewById(R.id.firstname_button);
	    final Button surname = (Button) theLayout.findViewById(R.id.surname_button);	   
	    
	    firstname.setSelected(true);
	    
		firstname.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (surnameBool)
				{
					surnameBool = false;
					firstnameBool = true;
					
					firstname.setSelected(true);
					surname.setSelected(false);

					//expandList.setAdapter(new ExpandListAdapter(theLayout.getContext(), null));
					
				    expListItems = SetStandardGroupsFirstname();
				    expAdapter = new ExpandListAdapter(theLayout.getContext(), expListItems);
				    	    
				    if (visible)
				    	expandList.setAdapter(expAdapter);						    
				    
				}
			}
		});		    
	   
		surname.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (firstnameBool)
				{
					surnameBool = true;
					firstnameBool = false;
					
					firstname.setSelected(false);
					surname.setSelected(true);
					
					//expandList.setAdapter(new ExpandListAdapter(theLayout.getContext(), null));
					
				    expListItems = SetStandardGroupsSurname();
				    expAdapter = new ExpandListAdapter(theLayout.getContext(), expListItems);
				 
				    if (visible)
				    	expandList.setAdapter(expAdapter);		
				    
				    
				}
			}
		});		    
		
		return theLayout;
	}
	
   public ArrayList<ExpandListGroup> SetStandardGroupsFirstname() 
   {
    	ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
    	ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
   
    	ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Ayn Rand");
        
        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setName("Atlas Shrugged");
        ch1_1.setTag(null);
        list2.add(ch1_1);
        
        ExpandListChild ch1_2 = new ExpandListChild();
        ch1_2.setName("The Fountainhead");
        ch1_2.setTag(null);
        list2.add(ch1_2);
        gru1.setItems(list2);
        
        list2 = new ArrayList<ExpandListChild>();
        
        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("Ernest Hemingway");
        
        ExpandListChild ch2_1 = new ExpandListChild();
        ch2_1.setName("A Farewell to Arms");
        ch2_1.setTag(null);
        list2.add(ch2_1);
        
        ExpandListChild ch2_2 = new ExpandListChild();
        ch2_2.setName("For Whom the Bell Tolls");
        ch2_2.setTag(null);
        list2.add(ch2_2);
        
        ExpandListChild ch2_3 = new ExpandListChild();
        ch2_3.setName("The Old Man and the Sea");
        ch2_3.setTag(null);
        list2.add(ch2_3);
        gru2.setItems(list2);
        
        list.add(gru1);
        list.add(gru2);
        
        return list;
    }
   
   public ArrayList<ExpandListGroup> SetStandardGroupsSurname() 
   {
    	ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
    	ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
   
    	ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Isaac Asimov");
        
        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setName("Caliban");
        ch1_1.setTag(null);
        list2.add(ch1_1);
        
        ExpandListChild ch1_2 = new ExpandListChild();
        ch1_2.setName("Fantastic Voyage");
        ch1_2.setTag(null);
        list2.add(ch1_2);
        gru1.setItems(list2);
        
        list2 = new ArrayList<ExpandListChild>();
        
        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("Sir Arthur Conan Doyle");
        
        ExpandListChild ch2_1 = new ExpandListChild();
        ch2_1.setName("The Hound of the Baskerville");
        ch2_1.setTag(null);
        list2.add(ch2_1);
        
        gru2.setItems(list2);
        
        list.add(gru1);
        list.add(gru2);
        
        return list;
    }   
   
   public class ExpandListAdapter extends BaseExpandableListAdapter 
   {
		private Context context;
		private ArrayList<ExpandListGroup> groups;
		
		public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups) 
		{
			this.context = context;
			this.groups = groups;
		}
		
		public void addItem(ExpandListChild item, ExpandListGroup group) 
		{
			if (!groups.contains(group)) {
				groups.add(group);
			}
			int index = groups.indexOf(group);
			ArrayList<ExpandListChild> ch = groups.get(index).getItems();
			ch.add(item);
			groups.get(index).setItems(ch);
		}
		public Object getChild(int groupPosition, int childPosition) {
			ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
			return chList.get(childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
				ViewGroup parent) {
			ExpandListChild child = (ExpandListChild) getChild(groupPosition, childPosition);
			
			if (view == null) 
			{
				LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				view = infalInflater.inflate(R.layout.expandlist_child_item, null);
			}
			
			TextView tv = (TextView) view.findViewById(R.id.expandlist_child_text);
			tv.setText(child.getName().toString());
			tv.setTag(child.getTag());
			
			return view;
		}

		public int getChildrenCount(int groupPosition) 
		{	
			ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();

			return chList.size();

		}

		public Object getGroup(int groupPosition) {
			
			return groups.get(groupPosition);
		}

		public int getGroupCount() {
			
			return groups.size();
		}

		public long getGroupId(int groupPosition) {
		
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isLastChild, View view,
				ViewGroup parent) {
			ExpandListGroup group = (ExpandListGroup) getGroup(groupPosition);
			if (view == null) {
				LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				view = inf.inflate(R.layout.expandlist_group_item, null);
			}
			TextView tv = (TextView) view.findViewById(R.id.expandlist_group_text);
			tv.setText(group.getName());
			
			return view;
		}

		public boolean hasStableIds() {
		
			return true;
		}

		public boolean isChildSelectable(int arg0, int arg1) {
		
			return true;
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
		                itemView.setBackgroundResource(R.drawable.list_item_selected); // orange
		         else
		        		itemView.setBackgroundResource(R.drawable.list_item_unselected);
	        	 
	        	 return itemView;
	        }
	    };
	    adapter.setDropDownViewResource(dropdown_id);
	    listView.setAdapter(adapter);
	}
	
	public class ExpandListGroup 
	{
		private String Name;
		private ArrayList<ExpandListChild> Items;
		
		public String getName() 
		{
			return Name;
		}
		public void setName(String name) 
		{
			this.Name = name;
		}
		public ArrayList<ExpandListChild> getItems() 
		{
			return Items;
		}
		public void setItems(ArrayList<ExpandListChild> Items) 
		{
			this.Items = Items;
		}
	}
	
	public class ExpandListChild 
	{
		private String Name;
		private String Tag;
		
		public String getName() 
		{
			return Name;
		}
		
		public void setName(String Name) 
		{
			this.Name = Name;
		}
		
		public String getTag() 
		{
			return Tag;
		}
		
		public void setTag(String Tag) 
		{
			this.Tag = Tag;
		}
	}	
	
	private class ItemClickListener implements OnItemClickListener
	{
        public void onItemClick(AdapterView<?> list, View lv, int position, long id) 
        {
        	selectedItem = new IntHolder(position);
        	
        	if (selectedItem.value == position)
				lv.setBackgroundResource(R.drawable.list_item_selected);
        	
            if (position == 0)
            {
            	selectedItem = new IntHolder(0);
            	
            	if (firstnameBool)
            	{
				    expListItems = SetStandardGroupsFirstname();
				    expAdapter = new ExpandListAdapter(theLayout.getContext(), expListItems);
				    expandList.setAdapter(expAdapter);			            		
            	}
            	else
            	{
				    expListItems = SetStandardGroupsSurname();
				    expAdapter = new ExpandListAdapter(theLayout.getContext(), expListItems);
				    expandList.setAdapter(expAdapter);			 	            		
            	}
            	
            	visible = true;
            }
        }		
	}
}
