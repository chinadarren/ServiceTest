package com.example.ServiceTest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private MySerivce.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MySerivce.class);
                startService(startIntent);//��������
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MySerivce.class);
                stopService(stopIntent);//ֹͣ����
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MySerivce.class);
                //BIND_AUTO_CREATE ��ʾ�ڻ�ͷ�����а󶨺��Զ���������
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//�󶨷���
                break;
            case R.id.unbind_service:
                unbindService(connection);//������
                break;
            default:
                break;
        }
    }
}