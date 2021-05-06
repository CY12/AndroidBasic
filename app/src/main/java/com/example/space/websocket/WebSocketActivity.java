package com.example.space.websocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.space.R;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;

/**
 * http://ws.douqq.com/ websocket 在线测试
 */
public class WebSocketActivity extends AppCompatActivity {
    private EditText etSend;
    private TextView tvSend;
    private TextView tvReceive;
    private StringBuilder sb = new StringBuilder();
    WebSocketClient webSocketClient;
    boolean isFirst = true;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        etSend = (EditText) findViewById(R.id.et_send);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvReceive = (TextView) findViewById(R.id.tv_receive);
        tvSend.setOnClickListener(v -> {
            if (isFirst){
                initWebSocket();//eg 111/张三
                isFirst =!isFirst;
                return;
            }
            String text = etSend.getText().toString();
            if (!TextUtils.isEmpty(text)){
                Gson gson = new Gson();
                MessageDTO msg = new MessageDTO();
                msg.setFromId(id);
                msg.setToId(112L);
                msg.setMessage(text);
                webSocketClient.send(gson.toJson(msg));
            }
        });
    }
    private void initWebSocket(){
        String u = "ws://172.16.88.66:9099/websocket/"+etSend.getText().toString();
        id = Long.parseLong(etSend.getText().toString().substring(0,3));
        String s = enString(u);
        Log.e("Test","uri  "+s);
        URI uri = URI.create(s);
        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                sb.append("onOpen at time：");
                sb.append(new Date());
                sb.append("服务器状态：");
                sb.append(handshakedata.getHttpStatusMessage());
                sb.append("\n");
                tvReceive.setText(sb.toString());

            }

            @Override
            public void onMessage(String message) {
                sb.append("收到新消息");
                sb.append(message);
                sb.append("\n");
                tvReceive.setText(sb.toString());
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                sb.append("onClose at time：");
                sb.append(new Date());
                sb.append("\n");
                sb.append("onClose info:");
                sb.append(code);
                sb.append(reason);
                sb.append(remote);
                sb.append("\n");
                tvReceive.setText(sb.toString());

            }

            @Override
            public void onError(Exception ex) {
                sb.append("onError at time：");
                sb.append(new Date());
                sb.append("\n");
                sb.append(ex);
                sb.append("\n");
                tvReceive.setText(sb.toString());

            }
        };
        webSocketClient.connect();
    }

    private String enString(String string){
        String resultURL = "";
        //遍历字符串
        for (int i = 0; i < string.length(); i++) {
            char charAt = string.charAt(i);
            //只对汉字处理
            if (isChineseChar(charAt)) {
                String encode = null;
                try {
                    encode = URLEncoder.encode(charAt+"","UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                resultURL+=encode;
            }else {
                resultURL+=charAt;
            }
        }
        return resultURL;

    }
    public  boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

}