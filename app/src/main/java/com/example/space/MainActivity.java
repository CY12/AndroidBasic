package com.example.space;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.addview.AddViewActivity;
import com.example.space.autoview.AutoViewActivity;
import com.example.space.base.BaseToolbarActivity;
import com.example.space.databinding.Student;
import com.example.space.salary.Salary;
import com.example.space.camera.CameraActivity1;
import com.example.space.databinding.DataBindingActivity;
import com.example.space.livedata.LiveDataActivity;
import com.example.space.mvvm.MvvmActivity;
import com.example.space.salary.SalaryActivity;
import com.example.space.scroll.Scroll2Activity;
import com.example.space.server.ServiceActivity;
import com.example.space.singleClick.SingleActivity;
import com.example.space.temp.TempActivity;
import com.example.space.thinking.observer.ObserverActivity;
import com.example.space.utils.LoadingDialog;
import com.example.space.utils.ProgressUtils;
import com.example.space.view.CameraFragment;
import com.example.space.wave.WaveProgressActivity;
import com.example.space.webview.WebActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseToolbarActivity implements View.OnClickListener {
    private TextView tvAddView;
    private TextView tvDataBing;
    private TextView tvObserver;
    private TextView tvPopup;
    private TextView tvService;
    private TextView tvPlay;
    private boolean isLog = true;
    private TextView tvCamera;
    private TextView tvCamera2;
    private TextView tvCamera1;
    private TextView tvAccessibility;
    private LinearLayout linearLayout;
    private TextView tvModel;
    TextView waveProgress;
    TextView tvScroll;
    TextView tvLoading;
    RelativeLayout relativeLayout;
    boolean isRunning = false;
    TextView tvLoadingDialog;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private LoadingDialog loadingDialog;
    private TextView tvMultiRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Test","MainActivity super.onCreate");
        super.onCreate(savedInstanceState);
        Log.d("Test","MainActivity onCreate");
        String s = "{\"id\":\"3\",\n" +
                "\"name\":\"zz\"\n" +
                "\n" +
                "}";

        Student student = new Student();
        initListener();
    }


    @Override
    protected int getLayoutId() {
        Log.d("Test","MainActivity getLayoutId");
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        Salary salary = new Salary();
//        salary.getData().get(0).getList().get(0).getMonth()

        linearLayout = findViewById(R.id.ll_all);
        tvAddView = (TextView) findViewById(R.id.tv_addView);
        tvDataBing = (TextView) findViewById(R.id.tv_dataBing);
        tvObserver = (TextView) findViewById(R.id.tv_observer);
        tvPopup = (TextView) findViewById(R.id.tv_popup);
        tvService = findViewById(R.id.tv_service);
        tvPlay = findViewById(R.id.tv_play);
        tvCamera = findViewById(R.id.tv_camera);
        tvCamera2 = findViewById(R.id.tv_camera2);
        tvCamera1 = findViewById(R.id.tv_camera1);
        tvAccessibility = findViewById(R.id.tv_accessibility);
        tvModel = findViewById(R.id.tv_model);
        waveProgress = findViewById(R.id.tv_waveProgress);
        tvLoading = findViewById(R.id.tv_loading);
        relativeLayout = findViewById(R.id.relative);
        tvLoadingDialog = findViewById(R.id.tv_loading_dialog);
        tvMultiRecycler = (TextView) findViewById(R.id.tv_multiRecycler);

        tvMultiRecycler.setOnClickListener(v -> startActivity(SalaryActivity.class));

        findViewById(R.id.tv_scroll).setOnClickListener(v -> startActivity(Scroll2Activity.class));
        findViewById(R.id.tv_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ModelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_web).setOnClickListener(v -> {
            startActivity( WebActivity.class);
        });


        findViewById(R.id.tv_liveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LiveDataActivity.class);
            }
        });
        findViewById(R.id.tv_singleClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SingleActivity.class);
            }
        });
        findViewById(R.id.tv_mvvm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MvvmActivity.class);
            }
        });
        findViewById(R.id.tv_temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TempActivity.class);
            }
        });
        findViewById(R.id.tv_p).setOnClickListener(v -> {
            startActivity(SecrityActivity.class);
        });
        findViewById(R.id.tv_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTip dialogTip = new DialogTip(MainActivity.this,R.style.TipDialog);
                Window window = dialogTip.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                dialogTip.show();
                Display display = getWindowManager().getDefaultDisplay();
                WindowManager.LayoutParams lp = dialogTip.getWindow().getAttributes();
                lp.width = (int)(display.getWidth()); //设置宽度
                dialogTip.getWindow().setAttributes(lp);

            }
        });

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.loading);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(30, 30);
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        imageView.setLayoutParams(layoutParams);
//        relativeLayout.addView(imageView);
        ImageView imageView =new ImageView(this);
        TextView textView = new TextView(this);
        tvLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning){
                    ProgressUtils.stopAnimator(relativeLayout,imageView);
                    isRunning = !isRunning;
                }else {
                    ProgressUtils.rotate(imageView,relativeLayout,1000);
                    isRunning = !isRunning;
                }

            }
        });
        tvLoadingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning){
                    if (loadingDialog == null){
                        loadingDialog = new LoadingDialog(MainActivity.this,R.style.LoadingDialog,1000);
                        loadingDialog.show();

                       // loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    }
                }else {
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
            }
        });

        int c = 3;
        c = breakPointTest(c);

    }

    private int breakPointTest(int a){
        return a*3+4;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Test", "MainActivity onResume");
    }

    public void initData() {


        mediaPlayer = MediaPlayer.create(this, R.raw.time);

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("123", "小明");
        hashMap.put("124", "小红");
        hashMap.put("125", "小蓝");
        log(hashMap.toString());
        hashMap.put("123", "小云");
        hashMap.put("126", "小黄");
        log(hashMap.toString());
        hashMap.remove("126");
        log(hashMap.toString());
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            log("key:==" + entry.getKey() + "  value==" + entry.getValue());
        }


    }

    public void initListener() {
        tvAddView.setOnClickListener(this);
        tvDataBing.setOnClickListener(this);
        tvObserver.setOnClickListener(this);
        tvPopup.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvPlay.setOnClickListener(this);
        tvCamera.setOnClickListener(this);
        tvCamera2.setOnClickListener(this);
        tvCamera1.setOnClickListener(this);
        tvAccessibility.setOnClickListener(this);
        tvModel.setOnClickListener(this);
        waveProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addView:
                startActivity(AddViewActivity.class);
                break;
            case R.id.tv_dataBing:
                startActivity(DataBindingActivity.class);
                break;
            case R.id.tv_observer:
                startActivity(ObserverActivity.class);
                break;
            case R.id.tv_popup:
                startActivity(AutoViewActivity.class);
                break;
            case R.id.tv_service:
                startActivity(ServiceActivity.class);
                break;
            case R.id.tv_play:
                if (isPlaying) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                isPlaying = !isPlaying;
                break;
            case R.id.tv_camera:
                startActivity(CameraActivity.class);
                break;
            case R.id.tv_camera2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new CameraFragment(), null)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.tv_camera1:
                startActivity(CameraActivity1.class);
                break;
            case R.id.tv_accessibility:
                Log.d("Test", "你点击无障碍化按钮");
                Toast.makeText(MainActivity.this, "你点击无障碍化服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_model:
                startActivity(ModelActivity.class);
                break;
            case R.id.tv_waveProgress:
                startActivity(WaveProgressActivity.class);
                break;
            default:
                break;
        }
    }

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }

    public void log(String s) {
        if (isLog) {
            Log.d("MainActivity", s);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ModelActivity", "MainActivity destroy");
    }
}
