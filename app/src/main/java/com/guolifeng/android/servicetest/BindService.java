package com.guolifeng.android.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service
{
    private static final String TAG = "BindService";
    private int count;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    public BindService()
    {
        Log.i(TAG, "BindService: ");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.i(TAG, "onBind: ");
        return binder;
    }

    class MyBinder extends Binder
    {
        int getCount()
        {
            return count;
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        new Thread()
        {
            @Override
            public void run()
            {
                while (!quit)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        Log.i(TAG, "run: e");
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.i(TAG, "onUnbind: ");
        return true;

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        quit = true;
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent)
    {
        super.onRebind(intent);
        Log.i(TAG, "onRebind: ");
    }
}
