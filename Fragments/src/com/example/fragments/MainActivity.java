package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	FragmentManager fm;
	Fragment frag;
	FragmentTransaction ft;
	View b;
	ImageButton globalNavigationButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 * FragmentManager fragmentManager = getFragmentManager();
		 * FragmentTransaction fragmentTransaction = fragmentManager
		 * .beginTransaction();
		 * 
		 * // get the current display info WindowManager wm =
		 * getWindowManager(); Display d = wm.getDefaultDisplay(); if
		 * (d.getWidth() > d.getHeight()) { // landscape mode Fragment1
		 * fragment1 = new Fragment1();
		 * fragmentTransaction.replace(android.R.id.content, fragment1); } else{
		 * Fragment2 fragment2=new Fragment2();
		 * fragmentTransaction.replace(android.R.id.content, fragment2); }
		 * fragmentTransaction.addToBackStack(null);
		 * fragmentTransaction.commit();
		 */
		
		globalNavigationButton = (ImageButton) findViewById(R.id.globalNavigationButtonOfPlayDetails);
		globalNavigationButton.setOnClickListener(globalNavigationListener);
		
		b = findViewById(R.id.fragment1);
		fm= getFragmentManager();
		frag= fm.findFragmentById(R.id.fragment1);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	private OnClickListener globalNavigationListener = new OnClickListener() {
		public void onClick(View v) {
			ft=fm.beginTransaction();
			
			if(frag.isVisible()){
				
				/*b.setVisibility(View.INVISIBLE);*/
				ft.hide(frag);
//				ft.addToBackStack(null);
				Toast.makeText(MainActivity.this, "button clicked visible", Toast.LENGTH_SHORT)
					.show();
			
				
				
			}else if(frag.isHidden()){
				
				
				ft.show(frag);
				/*ft.addToBackStack(null);*/
				Toast.makeText(MainActivity.this, "button clicked hidden", Toast.LENGTH_SHORT)
				.show();
		
			}
			
			ft.commit();
			

		}
	};
}
