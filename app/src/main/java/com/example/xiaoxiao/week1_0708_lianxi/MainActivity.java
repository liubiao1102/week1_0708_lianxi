package com.example.xiaoxiao.week1_0708_lianxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyCountDown count_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count_down = findViewById(R.id.countdown_time);
        //开始倒计时
        count_down.start(this,OtherActivity.class);
        //停止倒计时
        count_down.setMyLinstern(new MyCountDown.MyLinstern() {
            @Override
            public void click(View view) {
                count_down.stop();
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }
}
