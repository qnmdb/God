package com.qf.x.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String path="https://gtd.alicdn.com/tfscom/TB1CnTqOXXXXXcDaXXXwu0bFXXX";
                InputStream is=null;
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    if (conn.getResponseCode()==200){
                        is=conn.getInputStream();
                        byte[] b=new byte[1024];
                        int len=0;
                        while ((is.read(b))!=-1){
                            bos.write(b,0,len);
                        }
                        bos.flush();
                    }
                    byte[] data = bos.toByteArray();
                    Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
                    image.setImageBitmap(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
