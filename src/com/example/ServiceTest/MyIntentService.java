package com.example.ServiceTest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cl on 2015/8/13.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");//���ø�����вι��캯��
    }
     //onHnadleIntent��������߼�,��������Ѿ��������߳������е�
    //����� IntentService�����ԣ� ������������н�����Ӧ���ǻ��Զ�ֹͣ
    @Override
    protected void onHandleIntent(Intent intent) {
    //��ӡ��ǰ�̵߳�id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}
