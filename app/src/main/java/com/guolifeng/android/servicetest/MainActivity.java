package com.guolifeng.android.servicetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{

    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bindService = (Button) this.findViewById(R.id.bind);
        Button unBindService = (Button) this.findViewById(R.id.unBind);
        context = this;
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        bindService.setOnClickListener(myOnClickListener);
        unBindService.setOnClickListener(myOnClickListener);

    }

    /**
     * 监听器
     */
    private class MyOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            intent = new Intent(context, FirstService.class);
            switch (v.getId())
            {
                case R.id.bind:
                    startService(intent);
                    break;
                case R.id.unBind:
                    stopService(intent);
                    break;
                default:
                    break;
            }
        }
    }
}
