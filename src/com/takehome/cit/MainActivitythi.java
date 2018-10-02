package com.takehome.cit;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;










import android.Manifest;
import android.app.Activity;
import android.os.Bundle;


import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivitythi extends Activity implements OnClickListener, OnRequestPermissionsResultCallback{
	private AdView mAdView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activitythi);
	
		
	
	    Button btsalinc =(Button) findViewById(R.id.btsalinc);
	  //  Button btcheckinc = (Button)findViewById(R.id.btcheckinc);
	    
       btsalinc.setOnClickListener(this); 
     //  btcheckinc.setOnClickListener(this); 
       mAdView = (AdView) findViewById(R.id.adView);
       mAdView.setAdListener(new ToastAdListener(this));
       mAdView.loadAd(new AdRequest.Builder().build());
       
       
       
       ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
       String permissions1[] = { Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE};
       
       int result1 = 1;
       
       
       ActivityCompat.requestPermissions(this, permissions1, result1);

       
       
	}

	
 
	
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.btsalinc:
			
			Intent startinc = new Intent("com.takehome.cit.SALARYCALCULATOR");
			startActivity(startinc);
			break;
	
		
		}
			
		}






	@Override
	public void onRequestPermissionsResult(int arg0, String[] arg1, int[] arg2) {
		// TODO Auto-generated method stub
		
		
		Toast newtoast = Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT);
		
		newtoast.show();
		
		
		
	}
		
	}
	
	
	 


