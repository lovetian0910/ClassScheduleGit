package com.kjw.classschedule;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kjw.classschedule.util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class ClassListAdapter extends BaseAdapter {
    private String TAG = "ClassListAdapter";
    private List<ClassInfo> mDataList;
    private LayoutInflater mInflater;
    public ClassListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<ClassInfo> dataList){
        mDataList = dataList;
    }
    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.class_item, null);
            holder = new ViewHolder();
            holder.startTime = (TextView) convertView.findViewById(R.id.start_time);
            holder.duration = (TextView) convertView.findViewById(R.id.duration);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.isTemp = (TextView) convertView.findViewById(R.id.is_temp);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ClassInfo info = mDataList.get(position);
        SimpleDateFormat format = (SimpleDateFormat) DateFormat.getInstance();
        format.applyPattern("HH:mm");
        String startTime = format.format(info.startTime.getTime());
        holder.startTime.setText(startTime);
        holder.duration.setText(info.duration + "分钟");
        holder.name.setText(info.name);
        holder.type.setText(Constants.CLASS_TYPE_CHN[info.type]);
        Log.d(TAG, "info.isTemp = " + info.isTemp);
        if(info.isTemp){
            holder.isTemp.setVisibility(View.VISIBLE);
        }else {
            holder.isTemp.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder{
        public TextView startTime;
        public TextView duration;
        public TextView name;
        public TextView type;
        public TextView isTemp;
    }
}
