package com.yg.fundrink.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yg.fundrink.R;

public class AdviceActivity extends AppCompatActivity {

    /**
     * 标题栏
     */
    private TextView tvTitle;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, AdviceActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("意见反馈");
    }


}
