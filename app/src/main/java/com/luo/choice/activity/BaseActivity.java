package com.luo.choice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luo.choice.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by luojing on 2016/8/19.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.iv_title_share)
    ImageView iv_title_share;
    @InjectView(R.id.iv_title_back)
    ImageView iv_title_back;
    @InjectView(R.id.iv_title_appicon)
    ImageView iv_title_appicon;
    @InjectView(R.id.tv_title_text)
    TextView tv_title_text;
    @InjectView(R.id.fl_detail_content)
    FrameLayout fl_detail_content;
    @InjectView(R.id.ll_title_layout)
    LinearLayout ll_title_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        iv_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
        overridePendingTransition(R.anim.activity_animation_finish_in,R.anim.activity_animation_finish_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4){
            finish();
            overridePendingTransition(R.anim.activity_animation_finish_in,R.anim.activity_animation_finish_out);
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
