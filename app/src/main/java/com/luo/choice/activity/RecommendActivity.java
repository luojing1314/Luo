package com.luo.choice.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.luo.choice.R;
import com.luo.choice.Utils.NetworkUtils;
import com.luo.choice.bean.Recommend;
import com.umeng.analytics.MobclickAgent;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by luojing on 2016/8/27.
 */
public class RecommendActivity extends BaseActivity {
    String type = "文字";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_title_text.setVisibility(View.VISIBLE);
        iv_title_back.setVisibility(View.VISIBLE);
        tv_title_text.setText(R.string.recommendpage);
        final View view = View.inflate(this,R.layout.activity_more_recommend,null);
        RadioGroup rg_check = (RadioGroup) view.findViewById(R.id.rg_check);
        final EditText et_recommend_title = (EditText) view.findViewById(R.id.et_recommend_title);
        final EditText et_recommend_reason = (EditText) view.findViewById(R.id.et_recommend_reason);
        Button btn_recommend_submit = (Button) view.findViewById(R.id.btn_recommend_submit);
        final EditText et_recommend_referrer = (EditText) view.findViewById(R.id.et_recommend_referrer);
        rg_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_text:
                        type = "文字";
                        break;
                    case R.id.rb_picture:
                        type = "图片";
                        break;
                    case R.id.rb_video:
                        type = "视频";
                        break;
                }
            }
        });

        btn_recommend_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkUtils.isNetworkAvailable(RecommendActivity.this)){
                    Recommend recommend = new Recommend();
                    if(!TextUtils.isEmpty(type)){
                        recommend.setType(type);
                    }
                    String title = et_recommend_title.getText().toString().trim();
                    if(!TextUtils.isEmpty(title)){
                        recommend.setTitleAndAuthor(title);
                    }else{
                        Toast.makeText(RecommendActivity.this,"请输入作品名称",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String reason = et_recommend_reason.getText().toString().trim();
                    if(!TextUtils.isEmpty(reason)){
                        recommend.setReason(reason);
                    }
                    String referrer = et_recommend_referrer.getText().toString().trim();
                    if(!TextUtils.isEmpty(referrer)){
                        recommend.setReferrer(referrer);
                    }
                    recommend.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(RecommendActivity.this,"已将您推荐的内容上传到服务器,感谢您的支持!",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(RecommendActivity.this,"很抱歉!服务器异常,请稍后再试",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RecommendActivity.this,"无网络,请检查当前网络状态",Toast.LENGTH_LONG).show();
                }

            }
        });


        fl_detail_content.addView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
