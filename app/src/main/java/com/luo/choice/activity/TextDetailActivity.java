package com.luo.choice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luo.choice.R;
import com.luo.choice.bean.Text;
import com.squareup.picasso.Picasso;

public class TextDetailActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		iv_title_back.setVisibility(View.VISIBLE);
		tv_title_text.setVisibility(View.VISIBLE);
		Bundle bundle = getIntent().getExtras();
		final Text text = (Text) bundle.get("text");
		View view = View.inflate(this, R.layout.activity_text_detail,null);
		TextView tv_text_detail_title = (TextView) view.findViewById(R.id.tv_text_detail_title);
		TextView tv_text_detail_author = (TextView) view.findViewById(R.id.tv_text_detail_author);
		TextView tv_text_detail_content = (TextView) view.findViewById(R.id.tv_text_detail_content);
		TextView tv_text_detail_referrer = (TextView) view.findViewById(R.id.tv_text_detail_referrer);
		ImageView iv_text_detail_cover = (ImageView) view.findViewById(R.id.iv_text_detail_cover);
		TextView tv_text_detail_reason = (TextView) view.findViewById(R.id.tv_text_detail_reason);
		TextView tv_text_detail_source = (TextView) view.findViewById(R.id.tv_text_detail_source);
		tv_title_text.setText(text.getType());
		tv_text_detail_title.setText(text.getTitle());
		if(TextUtils.isEmpty(text.getAuthor())){
			tv_text_detail_author.setText("作者不详");
		}else{
			tv_text_detail_author.setText(text.getAuthor());
		}
		if(text.getTypeImage()!=null){
			Picasso.with(this)
					.load(text.getTypeImage().getFileUrl())
					.error(R.drawable.pic_load_error)
					.into(iv_text_detail_cover);
			iv_text_detail_cover.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(TextDetailActivity.this,PictureScaleDownloadActivity.class);
					intent.putExtra("url",text.getTypeImage().getFileUrl());
					startActivity(intent);
				}
			});
		}else{
			iv_text_detail_cover.setVisibility(View.GONE);
		}

		tv_text_detail_content.setText(text.getContent());
		if(TextUtils.isEmpty(text.getReferrer())){
			tv_text_detail_referrer.setVisibility(View.GONE);
		}else{
			tv_text_detail_referrer.setText(text.getReferrer());
		}
		if(TextUtils.isEmpty(text.getReason())){
			tv_text_detail_reason.setVisibility(View.GONE);
		}else{
			tv_text_detail_reason.setText(text.getReason());
		}
		if(TextUtils.isEmpty(text.getSource())){
			tv_text_detail_source.setVisibility(View.GONE);
		}else{
			tv_text_detail_source.setText(text.getSource());
		}
		iv_title_back.setOnClickListener(this);
		fl_detail_content.addView(view);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
