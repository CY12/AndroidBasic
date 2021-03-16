package com.example.space.ipc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.R;

import java.util.ArrayList;
import java.util.List;

public class IpcActivity extends AppCompatActivity {

    private EditText etSend;
    private TextView tvResult;
    private TextView tvActivity;
    private TextView tvContentProvider;
    private TextView tvAidl;
    private IAidlInterface iAidlInterface;
    private List<String> messages = new ArrayList<>();


    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        //当承载IBinder的进程消失时接收回调的接口
        @Override
        public void binderDied() {
            if (null == iAidlInterface) {
                return;
            }
            try {
                iAidlInterface.unregisterCallBack(iAidlCallBack);//注销
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            iAidlInterface.asBinder().unlinkToDeath(mDeathRecipient, 0);
            iAidlInterface = null;
        }
    };

    private IAidlCallBack iAidlCallBack = new IAidlCallBack.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void onMessageSuccess(String message) {
            if (messages != null) {
                messages.add(message);
                handler.sendEmptyMessage(1);
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iAidlInterface = IAidlInterface.Stub.asInterface(iBinder);
            try {
                iAidlInterface.asBinder().linkToDeath(mDeathRecipient, 0);//监听进程是否消失
                iAidlInterface.registerCallBack(iAidlCallBack);//注册消息回调
                messages.addAll(iAidlInterface.getMessages());//获取历史消息
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        etSend = (EditText) findViewById(R.id.et_send);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvActivity = (TextView) findViewById(R.id.tv_activity);
        tvContentProvider = (TextView) findViewById(R.id.tv_content_provider);
        tvAidl = (TextView) findViewById(R.id.tv_aidl);

        tvActivity.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("com.example.contentproviderdemo", "com.example.contentproviderdemo.MainActivity2");
            intent.putExtra("app", getData());
            startActivityForResult(intent, 200);
        });

        tvContentProvider.setOnClickListener(v -> {
            ContentResolver resolver = this.getContentResolver();
            String url = "content://com.example.myprovider";

            Uri uri = Uri.parse(url);

            Bundle bundle = new Bundle();
            bundle.putString("name", "Space");
            bundle.putString("msg", "你好啊");
            Bundle bundle1 = resolver.call(uri, "", "", bundle);
            if (bundle1 != null) {
                String msg = bundle1.getString("name") + bundle1.getString("msg");
                tvResult.setText(msg);
            }

        });

        tvAidl.setOnClickListener(v -> {
        });

    }


    private String getData() {
        if (TextUtils.isEmpty(etSend.getText().toString())) {
            return "space data";
        }
        return etSend.getText().toString();
    }

    private void setData(String s) {
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(IpcActivity.this, "返沪数据未空", Toast.LENGTH_SHORT).show();
        } else {
            tvResult.setText(s);
        }

    }

    private void initAidl() {
        Intent intent=new Intent(getApplicationContext(),AidlService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Test", "request" + requestCode + "result" + resultCode);
        if (requestCode == 200 && resultCode == 200) {
            Log.e("Test", "成功返回");
            if (data != null) {
                setData(data.getStringExtra("data"));
                Log.e("Test", "获取到信息" + data.getStringExtra("data"));
            }
        }
    }
    @Override
    protected void onDestroy() {
        //解除注册
        if (null != iAidlInterface && iAidlInterface.asBinder().isBinderAlive()) {
            try {
                iAidlInterface.unregisterCallBack(iAidlCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        //解除绑定服务

//        unbindService(serviceConnection);
        super.onDestroy();
    }
}