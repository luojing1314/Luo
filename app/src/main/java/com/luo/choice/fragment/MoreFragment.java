package com.luo.choice.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.choice.R;
import com.luo.choice.Utils.CacheUtils;
import com.luo.choice.Utils.VersionUtils;
import com.luo.choice.activity.MainActivity;
import com.luo.choice.activity.RecommendActivity;
import com.luo.choice.activity.SuggestActivity;

/**
 * Created by luojing on 2016/8/23.
 */
public class MoreFragment extends Fragment implements View.OnClickListener{
    private TextView tv_cache_size;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,null);
        context = getActivity();
        Button btn_more_recommend = (Button) view.findViewById(R.id.btn_more_recommend);
        Button btn_more_suggest = (Button) view.findViewById(R.id.btn_more_suggest);
        Button btn_more_share = (Button) view.findViewById(R.id.btn_more_share);
//        Button btn_more_score = (Button) view.findViewById(R.id.btn_more_score);
        TextView tv_version = (TextView) view.findViewById(R.id.tv_version);
        LinearLayout ll_clean_cache = (LinearLayout) view.findViewById(R.id.ll_clean_cache);
        tv_cache_size = (TextView) view.findViewById(R.id.tv_cache_size);
        try {
            long totalCacheSize = CacheUtils.getTotalCacheSize(context);
            String formatSize = CacheUtils.getFormatSize(totalCacheSize);
            tv_cache_size.setText(formatSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String versionName = VersionUtils.getLocalVersionName(context);
        if(!TextUtils.isEmpty(versionName)){
            tv_version.setText("版本: "+ versionName);
        }
        btn_more_recommend.setOnClickListener(this);
        btn_more_suggest.setOnClickListener(this);
        btn_more_share.setOnClickListener(this);
//        btn_more_score.setOnClickListener(this);
        ll_clean_cache.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_more_recommend:
                intent = new Intent(context, RecommendActivity.class);
                break;
            case R.id.btn_more_suggest:
                intent = new Intent(context, SuggestActivity.class);
                break;
            case R.id.btn_more_share:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showShare();
                break;
//            case R.id.btn_more_score:
//                Toast.makeText(context,"暂未实现",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.ll_clean_cache:
                try {
                    if(CacheUtils.getTotalCacheSize(context)>0){
                        CacheUtils.clearAllCache(context);
                        long totalCacheSize = CacheUtils.getTotalCacheSize(context);
                        String formatSize = CacheUtils.getFormatSize(totalCacheSize);
                        tv_cache_size.setText(formatSize);
                        Toast.makeText(context,"缓存已清理",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"暂无缓存",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        if(intent!=null){
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.activity_animation_start_in,R.anim.activity_animation_start_out);
        }
    }
}
