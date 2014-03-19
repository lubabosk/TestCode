package com.example.customlistview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class ListViewAdapter extends BaseAdapter{
	
	
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
 
    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
        
    }
 
    @Override
    public int getCount() {
        return data.size();
    }
 
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
   
    

	@Override
	//public View getView(int arg0, View arg1, ViewGroup arg2) {
	 public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		        // Declare Variables
		
		        ImageView imageView;
		        TextView Description;
		        TextView v;
		      
		        inflater = (LayoutInflater) context
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
		        View itemView = inflater.inflate(R.layout.listlayout, parent, false);
		        // Get the position
		        resultp = data.get(position);
		        
		        // Locate the ImageView in listview_item.xml
		        imageView = (ImageView) itemView.findViewById(R.id.imageview);
		        // Locate the TextViews in listview_item.xml
		        Description = (TextView) itemView.findViewById(R.id.Description);
		      		        v=(TextView)itemView.findViewById(R.id.link);
		       
		        // Passes flag images URL into ImageLoader.class
		        imageLoader.DisplayImage(resultp.get(CustomMainActivity.imageLocation), imageView);
		        // Capture position and set results to the TextViews
		        Description.setText(resultp.get(CustomMainActivity.description));
		      
		        // Capture position and set results to the ImageView
		        respodeForImageClick(imageView, position);
		        
		        setTheVedio(v);
		   
		        return itemView;
		
		
	}
	
	private void respodeForImageClick(ImageView image,final int x)
	{
		 image.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	 resultp = data.get(x);
		            Intent i = new  Intent(context, VedioSubActivity.class);  
		            i.putExtra("passLink", resultp.get(CustomMainActivity.videoLocation));
		            context.startActivity(i); 
		        }
		      });
			
	}
	
	private void setTheVedio(TextView v)
	{
		
		v.setText(resultp.get(CustomMainActivity.videoLocation));
		     v.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent i = new  Intent(context, VedioSubActivity.class);  
	            i.putExtra("passLink", resultp.get(CustomMainActivity.videoLocation));
	            context.startActivity(i); 
	        }
	      });
		
		
	}

}
