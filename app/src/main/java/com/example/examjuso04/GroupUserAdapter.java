package com.example.examjuso04;

import static com.example.examjuso04.R.layout.item_userinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;

import java.util.List;

public class GroupUserAdapter extends BaseAdapter {
    List<GroupUserInfo> mData;

    public GroupUserAdapter(List<GroupUserInfo> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_userinfo, parent, false);
            TextView textView_uid = convertView.findViewById(R.id.ttv_uinfo_id);
            TextView textView_name = convertView.findViewById(R.id.ttv_uinfo_name);
            TextView textView_phone = convertView.findViewById(R.id.ttv_uinfo_phone);
            holder.uid = textView_uid;
            holder.name = textView_name;
            holder.phone = textView_phone;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GroupUserInfo userInfo = mData.get(position);
        holder.uid.setText(userInfo.getUid());
        holder.name.setText(userInfo.getName());
        holder.phone.setText(userInfo.getPhone());

        return convertView;
    }

    static class ViewHolder {
        TextView uid;
        TextView name;
        TextView phone;
    }
}
