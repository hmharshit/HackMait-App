package com.party.parthverma.maithackathong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FirstAidElements extends AppCompatActivity {
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_aid_elements);
        listView = (ListView) findViewById(R.id.first_aid_element_list);
        int position = this.getIntent().getExtras().getInt("position");
        FirstAidsElement firstAidsElement = FirstAidsElement.getFirstAidElement(this).get(position);
        final ArrayList<FirstAidsElement> clubList = FirstAidsElement.getFirstAidElement(this);

        FirstAidElementAdapter adapter = new FirstAidElementAdapter(this, clubList);
        listView.setAdapter(adapter);

        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, Remedy.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}