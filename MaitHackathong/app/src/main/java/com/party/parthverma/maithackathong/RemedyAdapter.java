package com.party.parthverma.maithackathong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by parthverma on 07/04/17.
 */

public class RemedyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<RemedyItem> data_src;

    public RemedyAdapter(Context context, ArrayList<RemedyItem> items) {
        this.context = context;
        data_src = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data_src.size();
    }

    @Override
    public Object getItem(int position) {
        return data_src.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RemedyAdapter.ViewHolder holder;
        if (convertView == null) {

            // 2
            convertView = inflater.inflate(R.layout.remedy_layout, parent, false);

            // 3
            holder = new RemedyAdapter.ViewHolder();
            holder.Image = (ImageView) convertView.findViewById(R.id.remedy_image);
            holder.title = (TextView) convertView.findViewById(R.id.remedy_text);

            // 4
            convertView.setTag(holder);
        } else {
            // 5
            holder = (RemedyAdapter.ViewHolder) convertView.getTag();
        }
        TextView name = holder.title;
        ImageView logo = holder.Image;

        RemedyItem element = (RemedyItem) getItem(position);
        name.setText(element.text);

        Picasso.with(context).load(element.image).into(logo);

        return convertView;

    }

    private static class ViewHolder {
        TextView title;
        ImageView Image;
    }


}


public class RemedyItem{
    String text,image;
}
