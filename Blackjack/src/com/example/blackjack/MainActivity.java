package com.example.blackjack;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	public class GameFragment extends MainActivity {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {
		TextView nameTextView,playTextView,conQuitTextView;
		EditText inputName;
		Button okButton,hitButton,stayButton,continueButton,quitButton;
		ArrayList<ImageView> dealerCards,playerCards;
		Blackjack game;
		RelativeLayout layout;
		public PlaceholderFragment() {
		}
		public int getIdentifierByString(String str){
			int id = getActivity().getResources().getIdentifier(str, "id", getActivity().getPackageName());	
			return id;
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			/*layout =(RelativeLayout) findViewById(R.id.background); 
			layout.setBackgroundResource(R.drawable.bg);*/
			nameTextView = (TextView) rootView.findViewById(R.id.textView1);
			playTextView = (TextView) rootView.findViewById(R.id.textView2);
			conQuitTextView = (TextView) rootView.findViewById(R.id.textView3);
			inputName = (EditText) rootView.findViewById(R.id.editText1);
			okButton = (Button) rootView.findViewById(R.id.button3);
			hitButton = (Button) rootView.findViewById(R.id.button1);
			stayButton = (Button) rootView.findViewById(R.id.button2);
			continueButton = (Button) rootView.findViewById(R.id.button4);
			quitButton = (Button) rootView.findViewById(R.id.button5);

			
			dealerCards = new ArrayList<ImageView>();
			playerCards = new ArrayList<ImageView>();
			
			okButton.setOnClickListener(this);
			hitButton.setOnClickListener(this);
			stayButton.setOnClickListener(this);
			continueButton.setVisibility(View.INVISIBLE);
			quitButton.setVisibility(View.INVISIBLE);
			hitButton.setVisibility(View.INVISIBLE);
			stayButton.setVisibility(View.INVISIBLE);
			playTextView.setVisibility(View.INVISIBLE);
			conQuitTextView.setVisibility(View.INVISIBLE);
			
			for(int i=1;i<=10;i++){
				int id1 = getIdentifierByString("i"+i);
				int id2;
				if(i<10)
					id2 = getIdentifierByString("I"+i);
				else
					id2 = getIdentifierByString("I"+i);
				ImageView v1 = (ImageView) rootView.findViewById(id1);
				ImageView v2 = (ImageView) rootView.findViewById(id2);
				v1.setVisibility(View.INVISIBLE);
				v2.setVisibility(View.INVISIBLE);
				dealerCards.add(v1);
				playerCards.add(v2);
			}
			
			myDB dbHelper = new myDB(this.getActivity());
			    SQLiteDatabase db = dbHelper.getReadableDatabase();
			    Cursor cursor = 
			            db.query("SystemUser", // a. table
			             new String[] {"ID", "Name"}, // b. column names
			             null, // c. selections 
			             null, // d. selections args
			             null, // e. group by
			             null, // f. having
			             "ID desc", // g. order by
			             null); // h. limit
			   
			    if (cursor != null && cursor.getCount() > 0) {
			        cursor.moveToFirst();
			        inputName.setText(cursor.getString(1));
			}
			db.close();
			dbHelper.close();

			return rootView;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				if(v == okButton){
					if(inputName.getText().toString().length()>0){
						myDB dbHelper = new myDB(this.getActivity());
						String idString = null;
					    SQLiteDatabase dbReader = dbHelper.getReadableDatabase();
					    Cursor cursor = 
					    		dbReader.query("SystemUser", // a. table
					             new String[] {"ID", "Name"}, // b. column names
					             null, // c. selections 
					             null, // d. selections args
					             null, // e. group by
					             null, // f. having
					             null, // g. order by
					             null); // h. limit
					    if (cursor != null && cursor.getCount() > 0) {
					        cursor.moveToFirst();
					        idString = cursor.getString(0);
					    }
					    dbReader.close();
					    SQLiteDatabase db = dbHelper.getWritableDatabase();
					    
					    ContentValues values = new ContentValues();
					    values.put("Name", inputName.getText().toString());
					    
					    if(idString == null){
					    	
					    }
					}
					/*Toast.makeText(getActivity(), "Hello," + b.getText().toString(),Toast.LENGTH_SHORT ).show();*/
					hitButton.setVisibility(View.VISIBLE);
					inputName.setVisibility(View.INVISIBLE);
					okButton.setVisibility(View.INVISIBLE);
					stayButton.setVisibility(View.VISIBLE);
					playTextView.setVisibility(View.VISIBLE);
					nameTextView.setVisibility(View.INVISIBLE);
				}
			
		}
	}

}
