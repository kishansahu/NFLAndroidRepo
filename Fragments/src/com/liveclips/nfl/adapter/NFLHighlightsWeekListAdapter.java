/**
 * 
 */
package com.liveclips.nfl.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.WeekItem;

/**
 * @author mohitkumar
 *
 */
public class NFLHighlightsWeekListAdapter extends ArrayAdapter<WeekItem>{

	Context context;
	
	List<WeekItem> weekItems;
	
	
	public NFLHighlightsWeekListAdapter(Context context, int resource,
			 List<WeekItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.weekItems = objects;
	}

	
	@Override
	public int getCount() {
		return weekItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeekHolder holder = null;
		
		WeekItem weekItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.nfl_highlights_week_menu, null);
				holder = new WeekHolder();
				
				holder.weekTextView = (TextView) convertView
						.findViewById(R.id.nfl_highlights_week_text);
				
				
				convertView.setTag(holder);
				
				
			} else{
				
					holder = (WeekHolder) convertView.getTag();
										
					holder.weekTextView.setText(weekItem.getWeekName());
					
					
		
			}
	
		return convertView;
	}
	

	public static class WeekHolder{
		
		TextView weekTextView;
				
	}
	

}
