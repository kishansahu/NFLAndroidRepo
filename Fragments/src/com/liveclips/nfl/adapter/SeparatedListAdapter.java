package com.liveclips.nfl.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.liveclips.nfl.R;

public class SeparatedListAdapter extends BaseAdapter {

	public Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();
	public ArrayAdapter<Object> headers;
	public final static int TYPE_SECTION_HEADER = 0;

	public SeparatedListAdapter(Context context) {
		headers = new ArrayAdapter<Object>(context,
				R.layout.common_popover_header_section);
	}
	
	
	public SeparatedListAdapter(Context context, boolean header) {
		if (!header) {
			headers = new ArrayAdapter<Object>(context,
					R.layout.common_popover_header_empty_section);
		} else {
			headers = new ArrayAdapter<Object>(context,
					R.layout.common_popover_header_section);
		}

	}

	public SeparatedListAdapter(Context context, String standingHeaders) {
		headers = new ArrayAdapter<Object>(context,
				R.layout.nfl_highlights_popover_header_standing);
	}
	
	public void addSection(String section, Adapter adapter) {
		this.headers.add(section);
		this.sections.put(section, adapter);
	}
	
	
	
	public void addSection(int section , Adapter adapter) {
		this.sections.put("" + section, adapter);
	}

	public void removeAllSections() {
		this.sections.clear();
		this.headers.clear();
	}

	public Object getItem(int position) {
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0)
				return section;
			if (position < size)
				return adapter.getItem(position - 1);

			// otherwise jump into next section
			position -= size;
		}
		return null;
	}

	public int getCount() {
		// total together all sections, plus one for each section header
		int total = 0;
		for (Adapter adapter : this.sections.values())
			total += adapter.getCount() + 1;
		return total;
	}

	public int getViewTypeCount() {
		// assume that headers count as one, then total all sections
		int total = 1;
		for (Adapter adapter : this.sections.values())
			total += adapter.getViewTypeCount();
		return total;
	}

	public int getItemViewType(int position) {
		int type = 1;
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0)
				return TYPE_SECTION_HEADER;
			if (position < size)
				return type + adapter.getItemViewType(position - 1);

			// otherwise jump into next section
			position -= size;
			type += adapter.getViewTypeCount();
		}
		return -1;
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isEnabled(int position) {
		return (getItemViewType(position) != TYPE_SECTION_HEADER);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int sectionnum = 0;
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0){
				/*if(NFLBaseAdapter.class.isInstance(headers)){
					NFLBaseAdapter baseAdapter = (NFLBaseAdapter) headers;
					return baseAdapter.getView(sectionnum, convertView, parent);
				}*/
				return headers.getView(sectionnum, convertView, parent);
			}
				
			if (position < size)
				return adapter.getView(position - 1, convertView, parent);

			// otherwise jump into next section
			position -= size;
			sectionnum++;
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
