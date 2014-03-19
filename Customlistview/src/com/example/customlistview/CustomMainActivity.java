package com.example.customlistview;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class CustomMainActivity extends Activity {

	JSONObject Jsonobj;
	JSONArray Jsonarray;
	ListView Listview;
	ArrayList<HashMap<String, String>> arraylist;
	ListViewAdapter adapter;
	static String imageLocation = "imageLocation";
    static String description = "description";
    static String videoLocation = "videoLocation";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		
		  
		  if (isNetworkConnectionAvailable() == true) {
			  Toast.makeText(CustomMainActivity.this, "Connected to your Network ", Toast.LENGTH_SHORT).show();
			  new Jsondownload(this).execute();
			} 
		  else {
				Toast.makeText(CustomMainActivity.this, "No Network Connection Please connect to your Network", Toast.LENGTH_SHORT).show();
			}
		  
	}

	
	//check the if there is a network 
	protected boolean isNetworkConnectionAvailable() {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		
	}

	// Download Json content
	public class Jsondownload extends AsyncTask<Void, Void, Void>
	{
  
		Context cx;
		ProgressDialog p;
		
		public Jsondownload (Context cx)
		{
			this.cx=cx;
			p=new ProgressDialog(cx);
			
		}
		
		
		  @Override
	        protected void onPreExecute() {
			  Log.v("i am in JonPreExecute()", " i am in onPreExecute()");
	            super.onPreExecute();
	            p.show();
	     
		  }
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			// Create an array
			  Log.v("i am in doinbackgroun()", " i am in doinbackground()");
			
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            Jsonobj = JSONfunctions.getJSONfromURL("https://keene.com/streamtest/videolist.aspx");
            
            try {
                // Locate the array name in JSON
                Jsonarray = Jsonobj.getJSONArray("videos");
 
                for (int i = 0; i < Jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    Jsonobj = Jsonarray.getJSONObject(i);
                                
                    map.put("imageLocation", Jsonobj.getString("imageLocation"));
                
                    map.put("description", Jsonobj.getString("description"));
                    map.put("videoLocation", Jsonobj.getString("videoLocation"));
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
					
			return null;
		}
		
		
		 @Override
	        protected void onPostExecute(Void args) {
		 	 
			    p.dismiss();
			    Log.v("i am in onpostExecute()", " i am in onpostExecute()");
	            Listview = (ListView) findViewById(R.id.listView1);
	            adapter = new ListViewAdapter(CustomMainActivity.this, arraylist);
	            Listview.setAdapter(adapter);
	            
		
	}
		 



	}
}
