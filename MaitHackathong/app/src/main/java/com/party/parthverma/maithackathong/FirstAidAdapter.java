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

import static android.R.attr.type;

/**
 * Created by parthverma on 07/04/17.
 */

public class FirstAidAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<FAElemenyts> data_src;

    public FirstAidAdapter(Context context, ArrayList<FAElemenyts> items) {
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
        ViewHolder holder;
        if (convertView == null) {

            // 2
            convertView = inflater.inflate(R.layout.first_aid_row, parent, false);

            // 3
            holder = new ViewHolder();
            holder.Image = (ImageView) convertView.findViewById(R.id.image3);
            holder.title = (TextView) convertView.findViewById(R.id.Title);

            // 4
            convertView.setTag(holder);
        } else {
            // 5
            holder = (ViewHolder) convertView.getTag();
        }
        TextView name = holder.title;
        ImageView logo = holder.Image;

        FAElemenyts element = (FAElemenyts) getItem(position);
        name.setText(element.title);

        Picasso.with(context).load(element.image).into(logo);

        return convertView;

    }

    private static class ViewHolder {
        TextView title;
        ImageView Image;
    }


}
