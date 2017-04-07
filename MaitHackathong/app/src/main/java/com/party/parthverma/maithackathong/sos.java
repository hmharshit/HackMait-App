package com.party.parthverma.maithackathong;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class sos extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    String lat,lng;

    MediaPlayer mp = MediaPlayer.create(this, R.raw.videoplayback);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        mp.setLooping(true);
        mp.setVolume(100,100);
        mp.start();
        SQLiteDatabase mydatabase = openOrCreateDatabase("Safe",MODE_PRIVATE,null);

        String map = "";

        Cursor result = mydatabase.rawQuery("Select * from SOSNumbers",null);
        result.moveToFirst();
        for(int i=0;i<result.getCount();i++)
        {
            String phone = result.getString(1);
            result.moveToNext();
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phone,null,"Help me!! I am stuck. You can get my current location from this link "+map,null,null);
        }


    }
    public void stop(View view)
    {
        mp.stop();
    }
}

