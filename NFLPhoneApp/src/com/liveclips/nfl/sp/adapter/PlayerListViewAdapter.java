package com.liveclips.nfl.sp.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.model.PlayerItem;

public class PlayerListViewAdapter extends ArrayAdapter<PlayerItem> {

	Context context;
	List<PlayerItem> items;

	public PlayerListViewAdapter(Context context, int resourceId,
			List<PlayerItem> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	private class PlayerViewHolder {
		TextView playerData1;
		TextView playerData2;
		TextView playerData3;
		TextView playerData4;
		TextView playerDetails;
		TextView playerName;
		ImageView playerImage;
		// ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		PlayerViewHolder holder = null;
		PlayerItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.popover_game_player_list_item, null);
			holder = new PlayerViewHolder();
			holder.playerImage = (ImageView) convertView
					.findViewById(R.id.popover_player_pic);
			holder.playerName = (TextView) convertView
					.findViewById(R.id.popover_player_name);
			holder.playerDetails = (TextView) convertView
					.findViewById(R.id.popover_player_details);
			holder.playerData1 = (TextView) convertView
					.findViewById(R.id.popover_player_data1);
			holder.playerData2 = (TextView) convertView
					.findViewById(R.id.popover_player_data2);
			holder.playerData3 = (TextView) convertView
					.findViewById(R.id.popover_player_data3);
			holder.playerData4 = (TextView) convertView
					.findViewById(R.id.popover_player_data4);

			convertView.setTag(holder);
		} else
			holder = (PlayerViewHolder) convertView.getTag();

		holder.playerImage.setImageResource(rowItem.playerImage);
		//holder.playerImage.getResources().getInteger(id);
		holder.playerName.setText(rowItem.playerName);
		holder.playerDetails.setText(rowItem.playerDetails);
		holder.playerData1.setText(rowItem.playerdata1);
		holder.playerData2.setText(rowItem.playerdata2);
		holder.playerData3.setText(rowItem.playerdata3);
		holder.playerData4.setText(rowItem.playerdata4);
		
		return convertView;
	}

}
