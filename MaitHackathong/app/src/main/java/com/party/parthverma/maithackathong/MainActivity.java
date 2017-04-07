package com.party.parthverma.maithackathong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void callAmbulance(View view)
    {
        place_call("102");

    }
    public void callPolice(View view)
    {
        place_call("100");
    }
    public void callFirebrigade(View view)
    {
        place_call("101");
    }
    public void place_call(String num)
    {
        String uri = "tel:"+num;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(permission == PackageManager.PERMISSION_GRANTED)
            startActivity(intent);
    }
}
