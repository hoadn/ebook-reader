package com.example.ebookreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		Button logout = (Button) findViewById(R.id.logout_summary);
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
							
							Intent myIntent = new Intent(SummaryActivity.this, LoginActivity.class);
							SummaryActivity.this.startActivity(myIntent);				
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(SummaryActivity.this);
				builder.setMessage("Are you sure you wish to logout?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();
			}
		}
		);	
		
		ImageButton backButton = (ImageButton) findViewById(R.id.back_button_summary);
		backButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					finish();
				}
			}
		);
		
		ImageButton homeButton = (ImageButton) findViewById(R.id.home_button_summary);
		homeButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(SummaryActivity.this, HomeActivity.class);
				SummaryActivity.this.startActivity(myIntent);	
			}
		}
		);
		
		ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button_summary);
		settingsButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent myIntent = new Intent(SummaryActivity.this, SettingsActivity.class);
				SummaryActivity.this.startActivity(myIntent);	
			}
		}
		);						
		
		TextView summaryText = (TextView) findViewById(R.id.summary_text);
		
		String eventString = "In the summer of 1922, Nick writes, he had just arrived in New York, " +
				"where he moved to work in the bond business, and rented a house on a part of Long Island " +
				"called West Egg. Unlike the conservative, aristocratic East Egg, West Egg is home to " +
				"the “new rich,” those who, having made their fortunes recently, have neither the " +
				"social connections nor the refinement to move among the East Egg set. West Egg is " +
				"characterized by lavish displays of wealth and garish poor taste. Nick’s comparatively " +
				"modest West Egg house is next door to Gatsby’s mansion, a sprawling Gothic monstrosity." +
				"\n\n" +
				"Nick is unlike his West Egg neighbors; whereas they lack social connections and " +
				"aristocratic pedigrees, Nick graduated from Yale and has many connections on East " +
				"Egg. One night, he drives out to East Egg to have dinner with his cousin Daisy and " +
				"her husband, Tom Buchanan, a former member of Nick’s social club at Yale. Tom, " +
				"a powerful figure dressed in riding clothes, greets Nick on the porch. Inside, " +
				"Daisy lounges on a couch with her friend Jordan Baker, a competitive golfer who " +
				"yawns as though bored by her surroundings.";
		
		summaryText.setText(eventString);
		
		ListView primaryList = (ListView) findViewById(R.id.primary_character_list);
		String[] primaryArr = {"Daisy Buchanan", "Jay Gatsby", "Nick Carraway", "Tom Buchanan"};
		
		primaryList.setAdapter(new ArrayAdapterPrimary(this, primaryArr));
		
		ListView secondaryList = (ListView) findViewById(R.id.secondary_character_list);
		String[] secondaryArr = {"Jordan Baker", "George Wilson", "Myrtle Wilson"};
		
		secondaryList.setAdapter(new ArrayAdapterPrimary(this, secondaryArr));
		
		ListView tertiaryList = (ListView) findViewById(R.id.tertiary_character_list);
		String[] tertiaryArr = {"Klipspringer", "Owl Eyes", "Meyer Wolfsheim"};
		
		tertiaryList.setAdapter(new ArrayAdapterPrimary(this, tertiaryArr));
		
		primaryList.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view,
					    int position, long id) 
				{
					if (position == 0)
					{
						final Dialog dialog = new Dialog(SummaryActivity.this);
						dialog.setContentView(R.layout.character_summary_fragment);
						dialog.setTitle("Character Summary: Daisy Buchanan");
						
						String characterString = "Partially based on Fitzgerald’s wife, Zelda, " +
								"Daisy is a beautiful young woman from Louisville, Kentucky. " +
								"She is Nick’s cousin and the object of Gatsby’s love. As a young " +
								"debutante in Louisville, Daisy was extremely popular among the " +
								"military officers stationed near her home, including Jay Gatsby. " +
								"Gatsby lied about his background to Daisy, claiming to be from a " +
								"wealthy family in order to convince her that he was worthy of her. " +
								"Eventually, Gatsby won Daisy’s heart, and they made love before Gatsby " +
								"left to fight in the war. Daisy promised to wait for Gatsby, but in " +
								"1919 she chose instead to marry Tom Buchanan, a young man from a solid, " +
								"aristocratic family who could promise her a wealthy lifestyle and who " +
								"had the support of her parents." +
								"\n\n" +
								"After 1919, Gatsby dedicated himself to winning Daisy back, making her " +
								"the single goal of all of his dreams and the main motivation behind his " +
								"acquisition of immense wealth through criminal activity. To Gatsby, " +
								"Daisy represents the paragon of perfection—she has the aura of charm, " +
								"wealth, sophistication, grace, and aristocracy that he longed for as a " +
								"child in North Dakota and that first attracted him to her. In reality, " +
								"however, Daisy falls far short of Gatsby’s ideals. She is beautiful and " +
								"charming, but also fickle, shallow, bored, and sardonic. Nick " +
								"characterizes her as a careless person who smashes things up and " +
								"then retreats behind her money. Daisy proves her real nature when " +
								"she chooses Tom over Gatsby in Chapter 7, then allows Gatsby to take " +
								"the blame for killing Myrtle Wilson even though she herself was driving " +
								"the car. Finally, rather than attend Gatsby’s funeral, Daisy and Tom " +
								"move away, leaving no forwarding address.";
						
						TextView summaryText = (TextView) dialog.findViewById(R.id.character_summary_text);
						summaryText.setText(characterString);
						
						ImageButton close = (ImageButton) dialog.findViewById(R.id.close_dialogue);
						close.setOnClickListener(new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialog.dismiss();	
							}	
						});
						
						
						dialog.show();							
					}
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	
	public class ArrayAdapterPrimary extends ArrayAdapter<String> 
	{
		  private final Context context;
		  private final String[] values;

		  public ArrayAdapterPrimary(Context context, String[] values) 
		  {
			  super(context, R.layout.list_item, values);
			  this.context = context;
			  this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) 
		  {
			    LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    
			    View rowView = inflater.inflate(R.layout.list_item, parent, false);
			    TextView textView = (TextView) rowView.findViewById(R.id.list_item_text);
			    textView.setText(values[position]);

			    return rowView;
		  }
	} 	

	public class ArrayAdapterSecondary extends ArrayAdapter<String> 
	{
		  private final Context context;
		  private final String[] values;

		  public ArrayAdapterSecondary(Context context, String[] values) 
		  {
			  super(context, R.layout.list_item, values);
			  this.context = context;
			  this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) 
		  {
			    LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    
			    View rowView = inflater.inflate(R.layout.list_item, parent, false);
			    TextView textView = (TextView) rowView.findViewById(R.id.list_item_text);
			    textView.setText(values[position]);

			    return rowView;
		  }
	} 		

	public class ArrayAdapterTertiary extends ArrayAdapter<String> 
	{
		  private final Context context;
		  private final String[] values;

		  public ArrayAdapterTertiary(Context context, String[] values) 
		  {
			  super(context, R.layout.list_item, values);
			  this.context = context;
			  this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) 
		  {
			    LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    
			    View rowView = inflater.inflate(R.layout.list_item, parent, false);
			    TextView textView = (TextView) rowView.findViewById(R.id.list_item_text);
			    textView.setText(values[position]);

			    return rowView;
		  }
	} 		
	
}
