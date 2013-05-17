package com.liveclips.nfl.adapter;

import java.util.List;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TopicMenuArrayAdapter extends ArrayAdapter<String> {

	private String[] items;
	private final Context context;
	int layoutResourceId;

	public TopicMenuArrayAdapter(Context context, int layoutResourceId,
			String[] items) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.text1);
		textView.setText(items[position]);
		return convertView;

	}

}
