package com.example.shunkishimizu.itarch12_22_aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private IMyAidlInterface myAidlInterface;
    private ServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                EditText input = (EditText)findViewById(R.id.editText);

                try {
                    int result = myAidlInterface.add(Integer.parseInt(input.getText().toString()));


                    switch (result%5){
                        case 1:
                            Toast.makeText(getApplicationContext(), "凶" + "", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "吉" + "", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "小吉" + "", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "中吉" + "", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(getApplicationContext(), "大吉" + "", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    //Toast.makeText(getApplicationContext(), "" + "", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);

            }
        });
    }


}  /////