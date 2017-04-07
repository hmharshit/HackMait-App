package com.party.parthverma.maithackathong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String[] perms ={"android.permission.CALL_PHONE"};
    int permsRequestCode = 200;
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {

            case 200:

                boolean callaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;


                break;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ABDHABAKS:",Integer.toString(Build.VERSION.SDK_INT));
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1)
            requestPermissions(perms,permsRequestCode);

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
    public void goToFirstAids(View view) {
        Intent intent = new Intent(this, FirstAid.class);
        startActivity(intent);
    }
}
