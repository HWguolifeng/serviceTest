package com.guolifeng.android.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{

    private static final String TAG = "MainActivity";
    private Context context;

    private BindService.MyBinder myBinder;
    private boolean isConnection;

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.i(TAG, "onServiceConnected: ");
//            myBinder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得View
        Button bindService = (Button) this.findViewById(R.id.bind);
        Button unBindService = (Button) this.findViewById(R.id.unBind);
        Button getStatus = (Button) this.findViewById(R.id.get_status);
        //获得上下文
        context = this;
        //设置View的监听
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        bindService.setOnClickListener(myOnClickListener);
        unBindService.setOnClickListener(myOnClickListener);
        getStatus.setOnClickListener(myOnClickListener);

    }

    /**
     * 监听器
     */
    private class MyOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, MyIntentService.class);
            switch (v.getId())
            {
                case R.id.bind:
                    bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    isConnection = true;
                    break;
                case R.id.unBind:
                    if (isConnection)
                    {
                        unbindService(serviceConnection);
                        isConnection = false;
                    }

                    break;
                case R.id.get_status:
                    startService(intent);
                    break;
                default:
                    break;
            }
        }
    }

    private int getCount()
    {
        int count = 0;
        if (myBinder != null)
        {
            count = myBinder.getCount();
        }
        Log.i(TAG, "getCount: " + count);
        return count;
    }
}
