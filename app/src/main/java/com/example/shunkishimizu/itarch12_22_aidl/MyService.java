package com.example.shunkishimizu.itarch12_22_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a) throws RemoteException {
            Random r = new Random();
            int n = r.nextInt(a) + 1;

            return n;
        }
    };
}