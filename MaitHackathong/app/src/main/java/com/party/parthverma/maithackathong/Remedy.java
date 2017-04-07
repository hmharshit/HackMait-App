package com.party.parthverma.maithackathong;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Remedy extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedy);

        listView = (ListView) findViewById(R.id.remedy_list);
        final ArrayList<RemedyItem> remedyItems = RemedyItem.getClubs(this);
        RemedyAdapter adapter = new RemedyAdapter(this, remedyItems);
        listView.setAdapter(adapter);    }
}
