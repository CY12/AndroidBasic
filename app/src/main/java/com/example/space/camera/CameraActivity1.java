package com.example.space.camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;


import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.space.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *@description SurfaceView预览Camera
 *
 *@author zjf
 *@date 2018/9/6 16:32
 */
public class CameraActivity1 extends AppCompatActivity {
    private static final String TAG = CameraActivity1.class.getSimpleName();
    private TextureView mTextureView;
    private CameraManager cameraManager;
    CameraDevice cameraDevice;//相机设备类


    String cameraId;//相机id

    Size previewSize;//预览尺寸

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera1);
        mTextureView = (TextureView) findViewById(R.id.sfvSurfaceView);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        //设置 TextureView 的状态监听
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            //TextureView 可用时调用改回调方法
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                //TextureView 可用，启动相机
                setupCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    private void setupCamera() {
        //配置相机参数（cameraId，previewSize）
        configCamera();
        //打开相机
        openCamera();
    }

    private void configCamera() {
        try {
            //遍历相机列表，使用前置相机
            for (String cid : cameraManager.getCameraIdList()) {
                //获取相机配置
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cid);
                //使用后置相机
                int facing = characteristics.get(CameraCharacteristics.LENS_FACING);//获取相机朝向
                if (facing == CameraCharacteristics.LENS_FACING_BACK) {
                    continue;
                }

                cameraId = cid;
                previewSize = getMatchingSize();
                Log.d(TAG, "最佳预览尺寸（w-h）：" + previewSize.getWidth() + "-" + previewSize.getHeight());
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private Size getMatchingSize() {
        Size selectSize = null;
        float selectProportion = 0;
        Log.d("Test", "getMatchingSize" + " surfaceView: width:" + mTextureView.getWidth() + " height:" + mTextureView.getHeight());
        try {
            float viewProportion = (float) mTextureView.getWidth() / (float) mTextureView.getHeight();//计算View的宽高比
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] sizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
            for (int i = 0; i < sizes.length; i++) {
                Log.d("Test", "width:" + sizes[i].getWidth() + " height:" + sizes[i].getHeight());
                Size itemSize = sizes[i];
                float itemSizeProportion = (float) itemSize.getHeight() / (float) itemSize.getWidth();//计算当前分辨率的高宽比
                float differenceProportion = Math.abs(viewProportion - itemSizeProportion);//求绝对值
                Log.e("Test", "相减差值比例=" + differenceProportion);
                if (i == 0) {
                    selectSize = itemSize;
                    selectProportion = differenceProportion;
                    continue;
                }
                if (differenceProportion <= selectProportion) { //判断差值是不是比之前的选择的差值更小
                    if (differenceProportion == selectProportion) { //如果差值与之前选择的差值一样
                        if (selectSize.getWidth() + selectSize.getHeight() < itemSize.getWidth() + itemSize.getHeight()) {//选择分辨率更大的Size
                            selectSize = itemSize;
                            selectProportion = differenceProportion;
                        }

                    } else {
                        selectSize = itemSize;
                        selectProportion = differenceProportion;
                    }
                }
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
            Log.d("Test", "getMatchingSize error" + e.toString());
        }
        Log.e("Test", "getMatchingSize: 选择的比例是=" + selectProportion);
        Log.e("Test", "getMatchingSize: 选择的尺寸是 宽度=" + selectSize.getWidth() + "高度=" + selectSize.getHeight());
        return selectSize;
    }

    private void openCamera() {
        try {
            //打开相机
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            cameraManager.openCamera(cameraId,
                    new CameraDevice.StateCallback() {
                        @Override
                        public void onOpened(CameraDevice camera) {
                            cameraDevice = camera;
                            //创建相机预览 session
                            createPreviewSession();
                        }

                        @Override
                        public void onDisconnected(CameraDevice camera) {
                            //释放相机资源
                            releseCamera();
                        }

                        @Override
                        public void onError(CameraDevice camera, int error) {
                            //释放相机资源
                            releseCamera();
                        }
                    },
                    null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void createPreviewSession() {
        //根据TextureView 和 选定的 previewSize 创建用于显示预览数据的Surface
        SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());//设置SurfaceTexture缓冲区大小
        final Surface previewSurface = new Surface(surfaceTexture);

        try {
            //创建预览session
            cameraDevice.createCaptureSession(Arrays.asList(previewSurface),
                    new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(CameraCaptureSession session) {
                            try {
                                //构建预览捕获请求
                                CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                                builder.addTarget(previewSurface);//设置 previewSurface 作为预览数据的显示界面
                                CaptureRequest captureRequest = builder.build();
                                //设置重复请求，以获取连续预览数据
                                session.setRepeatingRequest(captureRequest, new CameraCaptureSession.CaptureCallback() {
                                            @Override
                                            public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
                                                super.onCaptureProgressed(session, request, partialResult);
                                            }

                                            @Override
                                            public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                                                super.onCaptureCompleted(session, request, result);
                                            }
                                        },
                                        null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession session) {

                        }
                    },
                    null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void releseCamera() {
        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }
    }
}
