
package com.liveclips.nfl.sp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class HomeActivity extends Activity {
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final String packageName ="com.liveclips.nfl.sp.fragement";
        final Context context = this;
      Button next=(Button)findViewById(R.id.home_menu_live_click);
next.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  startActivity(new Intent(context, LiveClips.class));
	}
});
   
       
       
    }
}
