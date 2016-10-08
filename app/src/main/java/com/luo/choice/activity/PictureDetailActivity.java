package com.luo.choice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luo.choice.R;
import com.luo.choice.bean.Picture;
import com.squareup.picasso.Picasso;
/**
 * Created by luojing on 2016/8/24.
 */
public class PictureDetailActivity extends BaseActivity implements View.OnClickListener{
    private  Picture picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv_title_back.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        picture = (Picture) bundle.get("picture");
        View view = View.inflate(this, R.layout.activity_picture_detail, null);
        TextView tv_pic_detail_title = (TextView) view.findViewById(R.id.tv_pic_detail_title);
        TextView tv_pic_detail_author = (TextView) view.findViewById(R.id.tv_pic_detail_author);
        ImageView tv_pic_detail_contentpic = (ImageView) view.findViewById(R.id.tv_pic_detail_contentpic);
        TextView tv_pic_detail_content = (TextView) view.findViewById(R.id.tv_pic_detail_content);
        TextView tv_pic_detail_referrer = (TextView) view.findViewById(R.id.tv_picture_detail_referrer);
        TextView tv_pic_detail_reason = (TextView) view.findViewById(R.id.tv_picture_detail_reason);
        TextView tv_pic_detail_source = (TextView) view.findViewById(R.id.tv_pic_detail_source);

        tv_title_text.setText(R.string.picfragment_title);
        tv_pic_detail_title.setText(picture.getTitle());

        tv_pic_detail_contentpic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PictureDetailActivity.this,PictureScaleDownloadActivity.class);
                        intent.putExtra("url",picture.getContentPic().getFileUrl());
                        startActivity(intent);
            }
        });
        if(picture.getAuthor()!=null){
            tv_pic_detail_author.setText(picture.getAuthor());
        }else{
            tv_pic_detail_author.setVisibility(View.GONE);
        }
        Picasso.with(this)
                .load(picture.getContentPic().getFileUrl())
                .error(R.drawable.pic_load_error)
                .into(tv_pic_detail_contentpic);

        if(picture.getContent()!=null){
            tv_pic_detail_content.setText(picture.getContent());
        }else{
            tv_pic_detail_content.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(picture.getReferrer())){
            tv_pic_detail_referrer.setVisibility(View.GONE);
        }else{
            tv_pic_detail_referrer.setText(picture.getReferrer());
        }
        if(TextUtils.isEmpty(picture.getReason())){
            tv_pic_detail_reason.setVisibility(View.GONE);
        }else{
            tv_pic_detail_reason.setText(picture.getReason());
        }
        if(picture.getSource()!=null){
            tv_pic_detail_source.setText(picture.getSource());
        }else{
            tv_pic_detail_source.setVisibility(View.GONE);
        }
        iv_title_back.setOnClickListener(this);
        fl_detail_content.addView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
