package com.example.examjuso04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyFirstAdapter extends BaseAdapter {
    private List<Weather> mData;

    public MyFirstAdapter(List<Weather> data) {
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
                    .inflate(R.layout.item_weather, parent, false);

            ImageView weatherImage = convertView.findViewById(R.id.weather_image);
            TextView cityText = convertView.findViewById(R.id.city_text);
            TextView tempText = convertView.findViewById(R.id.temp_text);

            holder.weatherImage = weatherImage;
            holder.cityText = cityText;
            holder.tempText = tempText;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather weather = mData.get(position);
        holder.cityText.setText(weather.getCity());
        holder.tempText.setText(weather.getTemp());

        return convertView;
    }

    static class ViewHolder {
        ImageView weatherImage;
        TextView cityText;
        TextView tempText;
    }
}
