package com.party.parthverma.maithackathong;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FirstAid extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("First Aid");
        ActionBar ab= getSupportActionBar();
        listView = (ListView) findViewById(R.id.first_aid_list);
        final ArrayList<FirstAids> firstAidList = FirstAids.getFirstAid(this);
        FirstAidAdapter adapter = new FirstAidAdapter(this, firstAidList);
        listView.setAdapter(adapter);

        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, FirstAidElements.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
