package com.example.ServiceTest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button startIntentService;
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private MySerivce.DownloadBinder downloadBinder;
//ServiceConnection匿名类
    private ServiceConnection connection = new ServiceConnection() {
    //活动与服务绑定的业务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //向下转型得到DownloadBinder实力
            downloadBinder = (MySerivce.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        stopService.setOnClickListener(this);
        startService.setOnClickListener(this);

        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MySerivce.class);
                startService(startIntent);//启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MySerivce.class);
                stopService(stopIntent);//停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MySerivce.class);
                //调用bindService方法将MyActivity和MyService进行绑定
                //第一个参数Intent对象，第二个参数ServiceConnection的实例，第三个参数标志位
                //BIND_AUTO_CREATE 表示在活动和服务进行绑定后自动创建服务
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);//解绑服务
                break;
            case R.id.start_intent_service:
                //打印主线程的id
                Log.d("MyActivity","Thread id is "+ Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
