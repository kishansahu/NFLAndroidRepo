package com.liveclips.nfl.sp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddPlayersActivity extends Activity{

	static ArrayAdapter<String> adapterForCategoryList, adapterForSearchList;

	String[] playersCategoriesMenuItems = { "Top Players", "By Team ",
			"By Position", "By College" };

	String[] anySearchForPlayersMenuItems = { "Tom Brady", "Rob Gronkowski", };
	ListView findPLayerByCategoryListView;
	EditText searchBarPlayer;
	Context context;
	private Button back,done;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_player);
		context=this;
	}
		
		
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		back=(Button)findViewById(R.id.add_player_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		findPLayerByCategoryListView = (ListView)findViewById(
				R.id.findPLayerByCategoryListView);

		adapterForCategoryList = new ArrayAdapter<String>(context,
				R.layout.simple_list_item_1, playersCategoriesMenuItems);

		findPLayerByCategoryListView.setAdapter(adapterForCategoryList);

		adapterForSearchList = new ArrayAdapter<String>(context,
				R.layout.simple_list_item_1,
				anySearchForPlayersMenuItems);

		findPLayerByCategoryListView
				.setOnItemClickListener(findPLayerByCategoryListViewItemListener);

		 searchBarPlayer = (EditText)findViewById(
				R.id.searchBarPlayerByCategory);
		searchBarPlayer.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence charSequence, int arg1,
					int arg2, int arg3) {
				// When user changed the Text
				AddPlayersActivity.adapterForSearchList.getFilter().filter(
						charSequence);
				findPLayerByCategoryListView.setAdapter(adapterForSearchList);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				if (arg0.length() == 0) {
					findPLayerByCategoryListView
							.setAdapter(adapterForCategoryList);
				}
			}
		});
		
	}
	private OnItemClickListener findPLayerByCategoryListViewItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
	
		
			Intent i=new Intent(context,AddPlayerSelectedCategoryMenuActivity.class);
			i.putExtra("selectedcategory",arg2);
			startActivityForResult(i,100002);
			/*FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
			ft.replace(R.id.menuFragment, addPlayerSelectedCategoryMenuFragment);
			ft.commit();*/
		}

	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("onActivityResult", requestCode+"   "+ resultCode);
		if (resultCode==RESULT_OK&&requestCode==100002) {
			setResult(RESULT_OK, getIntent());
			finish();
		}
	}
	
}
