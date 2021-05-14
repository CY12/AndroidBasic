package com.example.space.thread;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.space.R;

import java.lang.ref.WeakReference;

/**
 * 一个AsyncTask实例只能被执行一次；采用此方式那么传参可以直接通过线程的构造函数即可。多次会抛出异常 java.lang.IllegalStateException: Cannot execute task: the task is already running.
 *
 * AsyncTask 导致的内存泄漏
 * 内部类虽然和外部类写在同一个文件中， 但是编译完成后， 还是生成各自的class文件，内部类通过this访问外部类的成员。
 * 1 编译器自动为内部类添加一个成员变量， 这个成员变量的类型和外部类的类型相同， 这个成员变量就是指向外部类对象(this)的引用；
 * 2 编译器自动为内部类的构造方法添加一个参数， 参数的类型是外部类的类型， 在构造方法内部使用这个参数为内部类中添加的成员变量赋值；
 * 3在调用内部类的构造函数初始化内部类对象时，会默认传入外部类的引用。
 * 假如在AsyncTask任务没有执行完毕的时候就去销毁Activity，因为 AsyncTask任务没有执行完毕，所以线程池会一直持有该对象，也就是一直间接的持有Activity的对象，这样的话就会导致内存泄漏。
 *
 *
 * 解决方案
 * 线程的关闭，可以通过在activity的destroy方法中执行task的cancel方法，通过判断isCancelled值来退出任务，
 * 在activity结束的时候就结束了任务。但是相对于其他情况（比如说操作网络的时候），doInBackground方法里面还是会继续执行，
 * 直到结束。但是至少，我们使用了cancel方法，最终不会执行onPostExecute，而改去执行onCancelled。
 * 于是相对于不能中断的任务，为避免任务一直持有activity，
 * 我们可以通过使用静态内部类，并且使用WeakReference来包裹我们的activity以达到更新UI的目的。
 */
public class AsyncActivity extends AppCompatActivity {

    // 线程变量
    MyTask mTask;

    // 主布局中的UI组件
    Button button,cancel; // 加载、取消按钮
    TextView text; // 更新的UI组件
    ProgressBar progressBar; // 进度条

    /**
     * 步骤1：创建AsyncTask子类
     * 注：
     *   a. 继承AsyncTask类
     *   b. 为3个泛型参数指定类型；若不使用，可用java.lang.Void类型代替
     *      此处指定为：输入参数 = String类型、执行进度 = Integer类型、执行结果 = String类型
     *   c. 根据需求，在AsyncTask子类内实现核心方法
     */
    private class MyTask extends AsyncTask<String, Integer, String> {
        private WeakReference<AsyncActivity> weakAty;

        public MyTask(AsyncActivity activity){
            weakAty = new WeakReference<AsyncActivity>(activity);
        }
        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        @Override
        protected void onPreExecute() {
            weakAty.get().loadSuccess("加载中");
            // 执行前显示提示
        }


        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... params) {
//            Log.e("Test","doInBackground params"+params[0]);

            try {
                int count = 0;
                int length = 1;
                while (count<99) {

                    count += length;
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        @Override
        protected void onProgressUpdate(Integer... progresses) {

            weakAty.get().setMyProgress(progresses[0]);
            weakAty.get().loadSuccess("loading..." + progresses[0] + "%");

        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            weakAty.get().loadSuccess("加载完成");
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {

            weakAty.get().loadSuccess("已取消");
            weakAty.get().setMyProgress(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async);
        button = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        /**
         * 步骤2：创建AsyncTask子类的实例对象（即 任务实例）
         * 注：AsyncTask子类的实例必须在UI线程中创建
         */

        // 加载按钮按按下时，则启动AsyncTask
        // 任务完成后更新TextView的文本
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = new MyTask(AsyncActivity.this);
                /**
                 * 步骤3：手动调用execute(Params... params) 从而执行异步线程任务
                 * 注：
                 *    a. 必须在UI线程中调用
                 *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
                 *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
                 *    d. 不能手动调用上述方法
                 */
                mTask.execute();
            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消一个正在执行的任务,onCancelled方法将会被调用
                mTask.cancel(true);
            }
        });

    }


    public void loadSuccess(String s){
        text.setText(s);
    }

    public void setMyProgress(int progress){
        progressBar.setProgress(progress);
    }
}
