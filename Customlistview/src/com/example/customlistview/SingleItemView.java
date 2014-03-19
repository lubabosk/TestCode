package com.example.customlistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity{
   // Declare Variables
   String description;
   String imageLocation;
   String position;
   ImageLoader imageLoader = new ImageLoader(this);

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       setContentView(R.layout.singleitemview);

       Intent i = getIntent();
      
       description = i.getStringExtra("description");
    
       imageLocation = i.getStringExtra("imageLocation");
      
       TextView txtrank = (TextView) findViewById(R.id.Description);
       
       ImageView imgflag = (ImageView) findViewById(R.id.imageView1);
    
       txtrank.setText(description);
       
       // Capture position and set results to the ImageView
       // Passes  images URL into ImageLoader.class
       imageLoader.DisplayImage(imageLocation, imgflag);

}

}
