package com.ylye.rollingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ylye.rollingview.data.DataServer;
import com.ylye.rollingview.widget.ScrollWinAPrizeView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/15.
 */
public class MainActivity extends AppCompatActivity {

    private ScrollWinAPrizeView scrollWinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollWinView = (ScrollWinAPrizeView) findViewById(R.id.scrollwin);
        // 移除子view
        scrollWinView.removeAllViews();
        // 初始化数据
        final List<String> listWin = new ArrayList<>();
        String[] foods = DataServer.getFoodsData();
        for (int i = 0; i < 16; i++) {
            Random random = new Random();
            String id = "10000" + (10 + random.nextInt(88));
            DecimalFormat df = new DecimalFormat("#.00");
            String money = df.format(60.0 + random.nextDouble());
            listWin.add("恭喜 ID: " + id + " " + foods[i] + " 中奖" + money + "元");
        }
        // 动态加载添加view
        scrollWinView.post(new Runnable() {
            @Override
            public void run() {
                scrollWinView.initData(listWin, MainActivity.this);
            }
        });
        // 设置item点击事件
        scrollWinView.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, (String) v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scrollWinView.cancelAnimation();
    }
}
