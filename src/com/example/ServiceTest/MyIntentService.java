package com.example.ServiceTest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cl on 2015/8/13.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造函数
    }
     //onHnadleIntent处理具体逻辑,这个方法已经是在子线程中运行的
    //外根据 IntentService的特性， 这个服务在运行结束后应该是会自动停止
    @Override
    protected void onHandleIntent(Intent intent) {
    //打印当前线程的id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}
