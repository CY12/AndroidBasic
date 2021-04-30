package com.example.space.websocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.space.R;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Date;

public class WebSocketActivity extends AppCompatActivity {
    private EditText etSend;
    private TextView tvSend;
    private TextView tvReceive;
    private StringBuilder sb = new StringBuilder();
    WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        etSend = (EditText) findViewById(R.id.et_send);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvReceive = (TextView) findViewById(R.id.tv_receive);
        initWebSocket();
        tvSend.setOnClickListener(v -> {
            String text = etSend.getText().toString();
            if (!TextUtils.isEmpty(text)){
                Gson gson = new Gson();
                MessageDTO msg = new MessageDTO();
                msg.setFromUserName("王");
                msg.setTargetUserName("李");
                msg.setMessage(text);
                msg.setMessageType(MessageDTO.Type.TYPE_TEXT.getMessageType());
                webSocketClient.send(gson.toJson(msg));
            }
        });
    }
    private void initWebSocket(){
        URI uri = URI.create("ws://172.16.88.66:9099");
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
}