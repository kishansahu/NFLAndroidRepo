package com.example.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


public class CourseListViewAdapter extends ArrayAdapter<MyCourseRowItem> {
	 
    Context context;
 
    public CourseListViewAdapter(Context context, int resourceId,
            List<MyCourseRowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    
    /*private view holder class*/
    private class ViewHolder {
    	TextView textviewCousreTitle;
    	ProgressBar progressBarModuleLevel;
    	TextView textviewModuleStatus;
    	TextView textviewModuleAverage;
    	TextView textviewStartDate;
    	TextView textviewEndDate;
        //ImageView imageView;
    }

    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        MyCourseRowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.my_course_list_item, null);
            holder = new ViewHolder();
            holder.textviewCousreTitle = (TextView) convertView.findViewById(R.id.label_course_title);
            holder.progressBarModuleLevel = (ProgressBar) convertView.findViewById(R.id.progressbar_module_level);
            holder.textviewModuleStatus = (TextView) convertView.findViewById(R.id.label_progressbar_module_status);
            holder.textviewModuleAverage = (TextView) convertView.findViewById(R.id.label_module_average_value);
            holder.textviewStartDate = (TextView) convertView.findViewById(R.id.label_plan_start_date_value);
            holder.textviewEndDate = (TextView) convertView.findViewById(R.id.label_plan_end_date_value);          
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.textviewCousreTitle.setText(rowItem.getCourseTitle());
        //int p = (Integer.parseInt(rowItem.getTotalCompleteLesson())*100)/(2);
        holder.progressBarModuleLevel.setProgress(20);
        //holder.progressBarModuleLevel.setProgress(100);
        
        holder.textviewModuleStatus.setText(rowItem.getTotalCompleteLesson() + "/" + rowItem.getTotalLesson() + " lesson");
        
        holder.textviewModuleAverage.setText(rowItem.getModuleAverage() + "");
        holder.textviewStartDate.setText(rowItem.getStartDate());
        holder.textviewEndDate.setText(rowItem.getEndDate());
 
        return convertView;
    }


}
