package com.example.space.life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.space.R;

/**
 * singleInstance
 * 这个模式才是重点，也是比较容易入坑的一种启动模式。字面上理解为单一实例。它具备所有singleTask的特点，唯一不同的是，
 * 它是存在于另一个任务栈中。上面的三种模式都存在于同一个任务栈中，而这种模式则是存在于另一个任务栈中。
 * 举个例子，上面的启动模式都存在于地球上，而这种模式存在于火星上。整个Android系统就是个宇宙。下面来详细介绍一下singleInstance的坑。
 *
 * singleInstance之一坑
 * 此时有三个activity，ActivityA，ActivityB，ActivityC，除了ActivityB的启动模式为singleInstance，其他的启动模式都为默认的。
 * startActivity了一个ActivityA，在ActivityA里startActivity了一个ActivityB，在ActivityB里startActivity了一个ActivityC。
 * 此时在当前的任务栈中的顺序是，ActivityA->ActivityB->ActivityC。
 * 照理来说在当前ActivityC页面按返回键，finish当前界面后应当回到ActivityB界面。但
 * 是事与愿违，奇迹出现了，页面直接回到了ActivityA。这是为什么呢？其实想想就能明白了，上面已经说过，
 * singleInstance模式是存在于另一个任务栈中的。也就是说ActivityA和ActivityC是处于同一个任务栈中的，ActivityB则是存在另个栈中。
 * 所以当关闭了ActivityC的时候，它自然就会去找当前任务栈存在的activity。当前的activity都关闭了之后，才会去找另一个任务栈中的activity。
 * 也就是说当在ActivityC中finish之后，会回到ActivityA的界面，在ActivityA里finish之后会回到ActivityB界面。
 * 如果还想回到ActivityB的页面怎么办呢？我的做法是，在ActivityB定义一个全局变量，public static boolean returnActivityB;
 * 界面需要跳转的时候将returnActivityB=true;然后在ActivityA界面onstart方法里判断returnActivityB是否为true，是的话就跳转到ActivityB，
 * 同时将returnActivityB=false;这样就能解决跳转的问题了。不过感觉还不是很好，如果有更好的方法，欢迎大家给我留言告诉我一声。
 *
 * singleInstance之二坑
 * 此时有两个个activity，ActivityA，ActivityB，ActivityA的启动模式为默认的，ActivityB的启动模式为singleInstance。
 * 当在ActivityA里startActivity了ActivityB，当前页面为ActivityB。按下home键。应用退到后台。此时再点击图标进入APP，
 * 按照天理来说，此时的界面应该是ActivityB，可是奇迹又出现了，当前显示的界面是ActivityA。这是因为当重新启动的时候，
 * 系统会先去找主栈（我是这么叫的）里的activity，也就是APP中LAUNCHER的activity所处在的栈。查看是否有存在的activity。
 * 没有的话则会重新启动LAUNCHER。要解决这个方法则是和一坑的解决办法一样，在ActivityB定义一个全局变量，
 * public static boolean returnActivityB;在oncreat方法将returnActivityB=true;然后在ActivityA界面onstart
 * 方法里判断returnActivityB是否为true，是的话就跳转到ActivityB，同时将returnActivityB=false;这样就能解决跳转的问题了。
 *
 */
public class SingleInstanceActivity extends AppCompatActivity {
    private TextView tvStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        tvStart = (TextView) findViewById(R.id.tv_start);

        tvStart.setOnClickListener(v -> {
            Intent intent = new Intent(SingleInstanceActivity.this,SimpleActivity.class);
            startActivity(intent);
        });
    }
}