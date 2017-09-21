package com.bawei.gekaixuan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.gekaixuan.R;

public class Welcome extends AppCompatActivity {
Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
      handler.postDelayed(new Thread(){
          @Override
          public void run() {
              super.run();
              Intent  it=new Intent(Welcome.this,MainActivity.class);
              startActivity(it);
          }
      },3000);

    }


}
