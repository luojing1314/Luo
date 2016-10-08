package com.luo.choice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.luo.choice.R;

import cn.bmob.v3.Bmob;
import io.vov.vitamio.Vitamio;

/**
 * Created by luojing on 2016/8/30.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ImageView iv_splash_view = (ImageView) findViewById(R.id.iv_splash_view);
        Bmob.initialize(this,"08b2f7b22180b6bda043725b1a129dc1");
        AnimationSet set = new AnimationSet(true);
        new Thread(){
            @Override
            public void run() {
                Vitamio.initialize(getApplicationContext());
            }
        }.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(2000);
        set.addAnimation(alphaAnimation);
        iv_splash_view.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
