package com.luo.choice.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luo.choice.R;
import com.luo.choice.Utils.NetworkUtils;
import com.luo.choice.bean.Suggest;
import com.umeng.analytics.MobclickAgent;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by luojing on 2016/8/28.
 */
public class SuggestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_title_text.setVisibility(View.VISIBLE);
        iv_title_back.setVisibility(View.VISIBLE);
        tv_title_text.setText("意见建议");
        View view = View.inflate(this, R.layout.activity_more_suggest,null);
        final EditText et_suggest_content = (EditText) view.findViewById(R.id.et_suggest_content);
        final EditText et_suggest_email = (EditText) view.findViewById(R.id.et_suggest_email);
        final EditText et_suggest_name = (EditText) view.findViewById(R.id.et_suggest_name);
        Button btn_suggest_submit = (Button) view.findViewById(R.id.btn_suggest_submit);
        btn_suggest_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkUtils.isNetworkAvailable(SuggestActivity.this)){
                    Suggest suggest = new Suggest();
                    String content = et_suggest_content.getText().toString().trim();
                    if(!TextUtils.isEmpty(content)){
                        suggest.setSuggestContent(content);
                    }else{
                        Toast.makeText(SuggestActivity.this,"请填写意见建议内容",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String email = et_suggest_email.getText().toString().trim();
                    if(!TextUtils.isEmpty(email)){
                        suggest.setContactEmail(email);
                    }
                    String name = et_suggest_name.getText().toString().trim();
                    if(!TextUtils.isEmpty(name)){
                        suggest.setName(name);
                    }
                    suggest.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(SuggestActivity.this,"以将您建议的内容上传到服务器,感谢您的支持!",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(SuggestActivity.this,"很抱歉!服务器异常,请稍后再试",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SuggestActivity.this,"无网络,请检查当前网络状态",Toast.LENGTH_LONG).show();
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
