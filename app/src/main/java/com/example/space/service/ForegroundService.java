package com.example.space.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.space.R;

import java.io.File;

/**
 * PendingIntent:将某个动作的触发时机交给其他应用；让那个应用代表自己去执行那个动作（权限都给他）
 *
 *1.1 与Intent的区别
 * Intent 是意图的意思。Android 中的　Intent 正是取自这个意思，它是一个消息对象，通过它，Android 系统的四大组件能够方便的通信，并且保证解耦。
 *
 * Intent 可以说明某种意图，携带一种行为和相应的数据，发送到目标组件。
 *
 * PendingIntent是对Intent的封装，但它不是立刻执行某个行为，而是满足某些条件或触发某些事件后才执行指定的行为
 *
 * A组件 创建了一个 PendingIntent 的对象然后传给 B组件，B 在执行这个 PendingIntent 的 send 时候，它里面的 Intent 会被发送出去，而接受到这个 Intent 的 C 组件会认为是 A 发的。
 * B以A的权限和身份发送了这个Intent
 *
 * 我们的 Activity 如果设置了 exported = false，其他应用如果使用 Intent 就访问不到这个 Activity，但是使用 PendingIntent 是可以的。
 *
 * 即：PendingIntent将某个动作的触发时机交给其他应用；让那个应用代表自己去执行那个动作（权限都给他）
 *
 * 2.2 获取PendingIntent
 * 关于PendingIntent的实例获取一般有以下五种方法，分别对应Activity、Broadcast、Service
 *
 * getActivity()
 * getActivities()
 * getBroadcast()
 * getService()
 * getForegroundService()
 * 它们的参数都相同，都是四个：Context， requestCode, Intent, flags，分别对应上下文对象、请求码、请求意图用以指明启动类及数据传递、关键标志位。
 * 前面三个参数共同标志一个行为的唯一性，而第四个参数flags：
 *
 * FLAG_CANCEL_CURRENT：如果当前系统中已经存在一个相同的PendingIntent对象，那么就将先将已有的PendingIntent取消，然后重新生成一个PendingIntent对象。
 * FLAG_NO_CREATE：如果当前系统中不存在相同的PendingIntent对象，系统将不会创建该PendingIntent对象而是直接返回null，如果之前设置过，这次就能获取到。
 * FLAG_ONE_SHOT：该PendingIntent只作用一次。在该PendingIntent对象通过send()方法触发过后，PendingIntent将自动调用cancel()进行销毁，那么如果你再调用send()方法的话，系统将会返回一个SendIntentException。
 * FLAG_UPDATE_CURRENT：如果系统中有一个和你描述的PendingIntent对等的PendingInent，那么系统将使用该PendingIntent对象，但是会使用新的Intent来更新之前PendingIntent中的Intent对象数据，例如更新Intent中的Extras
 * 备注：两个PendingIntent对等是指它们的operation一样, 且其它们的Intent的action, data, categories, components和flags都一样。但是它们的Intent的Extra可以不一样
 *
 * 2.3 使用场景
 * 关于PendingIntent的使用场景主要用于闹钟、通知、桌面部件。
 *
 * 大体的原理是: A应用希望让B应用帮忙触发一个行为，这是跨应用的通信，需要 Android 系统作为中间人，这里的中间人就是 ActivityManager。 A应用创建建 PendingIntent，在创建 PendingIntent 的过程中，向 ActivityManager 注册了这个 PendingIntent，所以，即使A应用死了，当它再次苏醒时，只要提供相同的参数，还是可以获取到之前那个 PendingIntent 的。当 A 将 PendingIntent 调用系统 API 比如 AlarmManager.set()，实际是将权限给了B应用，这时候， B应用可以根据参数信息，来从 ActivityManager 获取到 A 设置的 PendingIntent
 */
public class ForegroundService extends Service {
    private static final String TAG="MyService";
    private static final String ID="channel_1";
    private static final String NAME="Space ForegroundService 前台服务";
    private Handler handler=new Handler();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(){
        super.onCreate();
        Log.e("Test","ForegroundService onCreate");
        handler.postDelayed(runnable,4000);
    }

    private Runnable runnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            Log.e("Test","ForegroundService run");
            setForeground();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setForeground(){
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,ServiceTypeActivity.class);
        intent.putExtra("Foreground"," 来自前台 过来的通知 消息 ： 啦啦啦啊");
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,ID);
        Notification notification = builder.setContentTitle("Space通知")
                .setContentText("这是来自前台服务的测试通知")
                .setSmallIcon(R.mipmap.ic_launcher) //
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_tou))
//                .setSound(Uri.fromFile(new File(""))) //通知提示音
//                .setVibrate(new long[]{0,1000,1000,1000}) //振动 long[0] 停止时间ms long[1] 震动时间 long[2]停止...
//                .setLights(Color.GREEN,1000,1000) //亮起时间 熄灭时间
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_tou)))  长文字 长图片
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL)//默认 提示音 震动
                .setAutoCancel(true)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(1,notification);
//        NotificationChannel channel = new NotificationChannel(ID,NAME,NotificationManager.IMPORTANCE_HIGH);
//
//        manager.createNotificationChannel(channel);

//        Notification notification = new Notification.Builder(this,ID)
//                .setContentTitle("Space通知")
//                .setContentText("这是来自前台服务的测试通知")
//                .setSmallIcon(R.mipmap.ic_launcher) //
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_tou))
////                .setSound(Uri.fromFile(new File(""))) //通知提示音
////                .setVibrate(new long[]{0,1000,1000,1000}) //振动 long[0] 停止时间ms long[1] 震动时间 long[2]停止...
////                .setLights(Color.GREEN,1000,1000) //亮起时间 熄灭时间
//                .setContentIntent(pi)
//                .setDefaults(Notification.DEFAULT_ALL)//默认 提示音 震动
//                .setAutoCancel(true)
//                .build();
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        startForeground(1,notification);
    }
}
