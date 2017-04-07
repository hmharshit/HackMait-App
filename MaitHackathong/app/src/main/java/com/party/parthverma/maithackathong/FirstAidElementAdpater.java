package com.party.parthverma.maithackathong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by harshit on 7/4/17.
 */

class FirstAidElementAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<FirstAidsElement> data_src;

    public FirstAidElementAdapter(Context context, ArrayList<FirstAidsElement> items) {
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
            convertView = inflater.inflate(R.layout.first_aid_elements, parent, false);

            // 3
            holder = new ViewHolder();
            holder.Image = (ImageView) convertView.findViewById(R.id.image12);
            holder.title = (TextView) convertView.findViewById(R.id.Title12);

            // 4
            convertView.setTag(holder);
        } else {
            // 5
            holder = (ViewHolder) convertView.getTag();
        }
        TextView name = holder.title;
        ImageView logo = holder.Image;

        FirstAids element = (FirstAids) getItem(position);
        name.setText(element.name);

        Picasso.with(context).load(element.image_loc).into(logo);

        return convertView;

    }

    private static class ViewHolder {
        TextView title;
        ImageView Image;
    }


}
class FirstAidsElement {
    String name,  image_loc;

    public FirstAidsElement(String name, String image_loc) {
        this.name = name;
        this.image_loc = image_loc;

    }

    static private String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("firstaid.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static ArrayList<FirstAidsElement> getFirstAidElement(Context context) {
        JSONObject obj;
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        JSONArray firstaid;
        try {


            firstaid = obj.getJSONArray("subtopic");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        ArrayList<FirstAidsElement> clubs = new ArrayList<FirstAidsElement>();

        for (int i = 0; i < firstaid.length(); i++) {
            FirstAidsElement x;
            try {
                JSONObject jo = firstaid.getJSONObject(i);
                x = new FirstAidsElement(jo.getString("name"),jo.getString("main_image_url"));
            } catch (JSONException ex) {
                ex.printStackTrace();
                return null;
            }
            clubs.add(x);
        }

        return clubs;
    }
}
