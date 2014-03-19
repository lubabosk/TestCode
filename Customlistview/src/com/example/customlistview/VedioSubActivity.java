package com.example.customlistview;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;

import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import android.widget.VideoView;

public class VedioSubActivity extends Activity
{ 
	private VideoView mVV;
	
	
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.activity_vedio_sub);
			VideoView v= (VideoView)findViewById(R.id.videoView1);
			String linkResult=getIntent().getStringExtra("passLink");
			if(linkResult !=null)
			{
				
				v.setVideoURI(Uri.parse(linkResult));
		        v.setMediaController(new MediaController(this));
		        
		        v.requestFocus();
		                
	            v.start();
		     
			}
		}
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vedio_sub, menu);
		return true;
	}

}
