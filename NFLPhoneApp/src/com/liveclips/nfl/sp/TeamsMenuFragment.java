package com.liveclips.nfl.sp;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.liveclips.nfl.sp.R;

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

		currentView= inflater.inflate(R.layout.team_menu_fragement, container, false);
		setContent();
		return currentView;
		
	}

	private void setContent() {
		Button back=(Button)currentView.findViewById(R.id.team_menu_fragement_back);
		back.setOnClickListener(listner);
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
				back.setOnClickListener(listner);
				indianapolisColts=(Button)currentView.findViewById(R.id.team_menu_fragement_indianapolis_colts);
				back.setOnClickListener(listner);
				jacksonvilleJaguars=(Button)currentView.findViewById(R.id.team_menu_fragement_jacksonville_jaguars);
				back.setOnClickListener(listner);
				tennesseeTitans=(Button)currentView.findViewById(R.id.team_menu_fragement_tennessee_titans);
				back.setOnClickListener(listner);
				
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
			/*FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			switch (v.getId()) {
			case R.id.team_menu_fragement_back:
				Fragment teamMenuFragment = new TopicMenuFragment();
				ft.replace(R.id.menuFragment, teamMenuFragment);

				ft.commit();
				break;
				
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
			*/
		}
	};
}
