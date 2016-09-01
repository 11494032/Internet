package com.example.administrator.internet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    private TextView tv = null;
    private ImageView imageView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById( R.id.tv);

        imageView = (ImageView)findViewById(R.id.iv);

    }

    public void start( View view )
    {
        DownLoad downLoad =  new DownLoad();
        downLoad.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(downLoad.getBitmap());


    }
}

class DownLoad extends Thread
{

   private Bitmap bitmap = null;
    public Bitmap getBitmap()
    {
        return bitmap;
    }

    @Override
    public void run() {
        super.run();
        String path = "http://www.shixiu.net/d/file/p/2bc22002a6a61a7c5694e7e641bf1e6e.jpg";
        HttpURLConnection conn = null; //连接对象
        InputStream is = null;


        try {
            URL url = new URL(path); //URL对象

            conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接

            conn.setDoInput(true);

            conn.setAllowUserInteraction(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.connect();

            Log.d("Intnet","connect");
            is = conn.getInputStream();

            Log.d("Intnet","getInputStream");

            bitmap = BitmapFactory.decodeStream(is);
            Log.d("Intnet","decodeStream"+conn.getResponseCode());

            is.close();

        }
        catch (Exception e) {
            Log.d("Intnet","Exception");

            e.printStackTrace();
        }

    }
}

