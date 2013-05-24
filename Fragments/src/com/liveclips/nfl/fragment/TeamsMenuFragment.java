package com.liveclips.nfl.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.liveclips.nfl.R;
import com.liveclips.nfl.activity.GameActivity;

public class TeamsMenuFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	Button buffaloBills,miamiDolphins,newEnglandPartriots,newYarkJets,
	baltimoreRevens,cincinnatiBengals,clevelandBroens,pittsburghSteelers,
	houstonTexans,indianapolisColts,jacksonvilleJaguars,tennesseeTitans,
	denverBroncos,kansasCityCjiefs,other1,other2;

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment 1", "onCreateView");

		currentView= inflater.inflate(R.layout.team_fragment_view, container, false);
		setContent();
		return currentView;
		
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		final View newfragMenuHeader = getActivity().getLayoutInflater().inflate(
				R.layout.teams_fragment_actionbar_header_show_teams, null);
		ActionBar actionBar = getActivity().getActionBar();
		
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		
		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
	
		RelativeLayout commonfragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.commonFragmentMenuHeader);

		if (commonfragMenuHeader != null) {
			commonfragMenuHeader.setVisibility(View.INVISIBLE);
		}

		RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.fragmentMenuHeader);
		actionBarLayout.removeView(fragMenuHeader);

		actionBarLayout.addView(newfragMenuHeader, 300, 52);
		
		Button back=(Button)actionBarLayout.findViewById(R.id.backToMainScreen);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getActivity()
						.getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment frg = fragmentManager
						.findFragmentById(R.id.menuFragment);
				ft.hide(frg);
				ft.commit();
				actionBarLayout.removeView(newfragMenuHeader);
				View sliderView = activityHeaderLayout
						.findViewById(R.id.sliderView);
				if (sliderView.getVisibility() != View.VISIBLE)
					sliderView.setVisibility(View.VISIBLE);
				activityHeaderLayout.setVisibility(View.VISIBLE);
				View commonfragMenuHeader = getActivity().getLayoutInflater()
						.inflate(R.layout.common_fragment_menu_header, null);
				actionBarLayout.addView(commonfragMenuHeader, 300, 52);
				
			}
		});
		
	}
	
	private void setContent() {
		
		        buffaloBills=(Button)currentView.findViewById(R.id.team_menu_fragement_buffalo_bills);
		        buffaloBills.setOnClickListener(listner);
		        miamiDolphins=(Button)currentView.findViewById(R.id.team_menu_fragement_miami_dolphins);
		        miamiDolphins.setOnClickListener(listner);
		        newEnglandPartriots=(Button)currentView.findViewById(R.id.team_menu_fragement_new_england_partriots);
		        newEnglandPartriots.setOnClickListener(listner);
		        newYarkJets=(Button)currentView.findViewById(R.id.team_menu_fragement_new_york_jets);
		        newYarkJets.setOnClickListener(listner);
		        
				baltimoreRevens=(Button)currentView.findViewById(R.id.team_menu_fragement_batimore_ravens);
				baltimoreRevens.setOnClickListener(listner);
				cincinnatiBengals=(Button)currentView.findViewById(R.id.team_menu_fragement_cincinnati_bengals);
				cincinnatiBengals.setOnClickListener(listner);
				clevelandBroens=(Button)currentView.findViewById(R.id.team_menu_fragement_clevelend_browns);
				clevelandBroens.setOnClickListener(listner);
				pittsburghSteelers=(Button)currentView.findViewById(R.id.team_menu_fragement_clevelend_browns);
				pittsburghSteelers.setOnClickListener(listner);
				
				houstonTexans=(Button)currentView.findViewById(R.id.team_menu_fragement_houston_texans);
				
				indianapolisColts=(Button)currentView.findViewById(R.id.team_menu_fragement_indianapolis_colts);
			
				jacksonvilleJaguars=(Button)currentView.findViewById(R.id.team_menu_fragement_jacksonville_jaguars);
				
				tennesseeTitans=(Button)currentView.findViewById(R.id.team_menu_fragement_tennessee_titans);
				
				
			    denverBroncos=(Button)currentView.findViewById(R.id.team_menu_fragement_denver_broncos);
			    denverBroncos.setOnClickListener(listner);
			    kansasCityCjiefs=(Button)currentView.findViewById(R.id.team_menu_fragement_Kansas_city_chicfs);
			    kansasCityCjiefs.setOnClickListener(listner);
			    other1=(Button)currentView.findViewById(R.id.team_menu_fragement_others_1);
			    other1.setOnClickListener(listner);
			    other2=(Button)currentView.findViewById(R.id.team_menu_fragement_others_2);
			    other2.setOnClickListener(listner);
	}
	OnClickListener listner=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			switch (v.getId()) {
			/*case R.id.team_menu_fragement_back:
				Fragment teamMenuFragment = new TopicMenuFragment();
				Fragment fragment = fragmentManager.findFragmentById(R.id.menuFragment);
				ft.hide(fragment);
				ft.commit();
				break;*/
				
           case R.id.team_menu_fragement_buffalo_bills:
        	   Intent intent1=new Intent(getActivity(), GameActivity.class);
        	   intent1.putExtra("TeanName", "");
        	   startActivity(intent1);
        	   break;
           case R.id.team_menu_fragement_miami_dolphins:
        	   Intent intent2=new Intent(getActivity(), GameActivity.class);
        	   intent2.putExtra("TeanName", "");
        	   startActivity(intent2);
        	   
        	   break;
           case R.id.team_menu_fragement_new_england_partriots:
        	   Intent intent3=new Intent(getActivity(), GameActivity.class);
        	   intent3.putExtra("TeanName", "");
        	   startActivity(intent3);
        	   break;
           case R.id.team_menu_fragement_new_york_jets:
        	   Intent intent4=new Intent(getActivity(), GameActivity.class);
        	   intent4.putExtra("TeanName", "");
        	   startActivity(intent4);
        	   break;
        	   
        	   
        	   
           case R.id.team_menu_fragement_batimore_ravens:
        	   Intent intent5=new Intent(getActivity(), GameActivity.class);
        	   intent5.putExtra("TeanName", "");
        	   startActivity(intent5);
        	   break;
           case R.id.team_menu_fragement_cincinnati_bengals:
        	   Intent intent6=new Intent(getActivity(), GameActivity.class);
        	   intent6.putExtra("TeanName", "");
        	   startActivity(intent6);
        	   break;
           case R.id.team_menu_fragement_clevelend_browns:
        	   Intent intent7=new Intent(getActivity(), GameActivity.class);
        	   intent7.putExtra("TeanName", "");
        	   startActivity(intent7);
        	   break;
           case R.id.team_menu_fragement_pittsburgh_steelers:
        	   Intent intent8=new Intent(getActivity(), GameActivity.class);
        	   intent8.putExtra("TeanName", "");
        	   startActivity(intent8);
        	   break;
        	   
        	 
        	   
           case R.id.team_menu_fragement_houston_texans:
        	   Intent intent9=new Intent(getActivity(), GameActivity.class);
        	   intent9.putExtra("TeanName", "");
        	   startActivity(intent9);
        	   break;
           case R.id.team_menu_fragement_indianapolis_colts:
        	   Intent intent10=new Intent(getActivity(), GameActivity.class);
        	   intent10.putExtra("TeanName", "");
        	   startActivity(intent10);
        	   break;
           case R.id.team_menu_fragement_jacksonville_jaguars:
        	   Intent intent11=new Intent(getActivity(), GameActivity.class);
        	   intent11.putExtra("TeanName", "");
        	   startActivity(intent11);
        	   break;
           case R.id.team_menu_fragement_tennessee_titans:
        	   Intent intent12=new Intent(getActivity(), GameActivity.class);
        	   intent12.putExtra("TeanName", "");
        	   startActivity(intent12);
        	   break;
        	   
        	   
           case R.id.team_menu_fragement_denver_broncos:
        	   Intent intent13=new Intent(getActivity(), GameActivity.class);
        	   intent13.putExtra("TeanName", "");
        	   startActivity(intent13);
        	   break;
           case R.id.team_menu_fragement_Kansas_city_chicfs:
        	   Intent intent14=new Intent(getActivity(), GameActivity.class);
        	   intent14.putExtra("TeanName", "");
        	   startActivity(intent14);
        	   break;
           case R.id.team_menu_fragement_others_1:
        	   Intent intent15=new Intent(getActivity(), GameActivity.class);
        	   intent15.putExtra("TeanName", "");
        	   startActivity(intent15);
           case R.id.team_menu_fragement_others_2:
        	   Intent intent16=new Intent(getActivity(), GameActivity.class);
        	   intent16.putExtra("TeanName", "");
        	   startActivity(intent16);
        	   break;
        	   
			default:
			
				break;
			}
			
		}
	};
}
