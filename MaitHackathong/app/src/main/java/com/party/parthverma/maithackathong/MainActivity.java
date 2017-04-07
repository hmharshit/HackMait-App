package com.party.parthverma.maithackathong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] perms ={"android.permission.CALL_PHONE","android.permission.SEND_SMS","android.permission.READ_CONTACTS"};
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
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Safe");
        ActionBar ab= getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Log.d("ABDHABAKS:",Integer.toString(Build.VERSION.SDK_INT));
        if(Build.VERSION.SDK_INT>22)
            requestPermissions(perms,permsRequestCode);

        SQLiteDatabase mydatabase = openOrCreateDatabase("Safe",MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS SOSNumbers(Name VARCHAR,Number VARCHAR);");


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
    public void getMoney(View view)
    {
        Uri uri1 = Uri.parse("content://contacts");
        Intent intent1 = new Intent(Intent.ACTION_PICK, uri1 );
        intent1.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent1, permsRequestCode);

    }
    public void settings(View view){
        Intent intent= new Intent(this,settings.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == permsRequestCode) {
            if (resultCode == RESULT_OK) {
                Uri uri = intent.getData();
                String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };

                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);

                Log.d("kasgdhjg", "ZZZ number : " + number +" , name : "+name);

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(number,null,"Enter message to send",null,null);
                Toast.makeText(this,"SMS Sent to "+name,Toast.LENGTH_LONG).show();

            }
        }
    };

    public void sos(View view)
    {
        Intent intent= new Intent(this,sos.class);
        startActivity(intent);
    }
}
