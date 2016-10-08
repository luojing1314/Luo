package com.luo.choice.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luo.choice.R;
import com.luo.choice.Utils.PixelUtils;
import com.luo.choice.activity.TextDetailActivity;
import com.luo.choice.bean.Text;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TextFragment extends Fragment implements OnItemClickListener{
	private ViewPager picPager;
	private int prePosition = 0;
    private MyPagerAdapter pagerAdapter;
	private List<Text> texts;
	private Context context;
	private LinearLayout llContainer;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int currentItem = picPager.getCurrentItem();
			currentItem++;
			picPager.setCurrentItem(currentItem, true);
			handler.sendEmptyMessageDelayed(1, 3500);
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_text, null);
		context = getActivity();
		Bundle arguments = getArguments();
		if(arguments!=null){
			texts = (List<Text>) arguments.getSerializable("LIST");
		}else{
			return null;
		}
		ListView lv_text = (ListView) view.findViewById(R.id.lv_text);
		View headerView = initHeaderView();
		lv_text.addHeaderView(headerView);
		lv_text.setAdapter(new TextAdapter());
		lv_text.setOnItemClickListener(this);
		return view;
	}

	public View initHeaderView() {
		RelativeLayout rlRoot = new RelativeLayout(context);
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, PixelUtils.dip2px(context, 200));
		rlRoot.setLayoutParams(layoutParams);
		picPager = new ViewPager(context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		rlRoot.addView(picPager, params);

		llContainer = new LinearLayout(context);
		llContainer.setOrientation(LinearLayout.HORIZONTAL);
		int padding = PixelUtils.dip2px(context, 10);
		llContainer.setPadding(padding, padding, padding, padding);
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		llContainer.setLayoutParams(llParams);

		for (int i = 0; i < texts.size(); i++) {
			ImageView point = new ImageView(context);
			if (i == 0) {
                point.setImageResource(R.drawable.pager_indicator_selected);
			}else{
                point.setImageResource(R.drawable.pager_indicator_normal);
                LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                pointParams.leftMargin = PixelUtils.dip2px(context, 5);
                point.setLayoutParams(pointParams);
			}
			llContainer.addView(point);
		}

		rlRoot.addView(llContainer, llParams);
        pagerAdapter = new MyPagerAdapter();
        picPager.setAdapter(pagerAdapter);
		picPager.setCurrentItem(texts.size() * 100000);
		handler.sendEmptyMessageDelayed(0, 3500);
		picPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				int currentPosition = position % texts.size();
				ImageView point = (ImageView) llContainer.getChildAt(currentPosition);
				point.setImageResource(R.drawable.pager_indicator_selected);
				ImageView preView = (ImageView) llContainer.getChildAt(prePosition);
				preView.setImageResource(R.drawable.pager_indicator_normal);
				prePosition = currentPosition;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		return rlRoot;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = new Intent(context, TextDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("text",texts.get(position-1));
		intent.putExtras(bundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.activity_animation_start_in,R.anim.activity_animation_start_out);
	}

	private class TextAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if(texts.size()!=0){
				return texts.size();
			}else{
				return 0;
			}
		}

		@Override
		public int getItemViewType(int position) {
			return super.getItemViewType(position);
		}

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount();
		}

		@Override
		public Text getItem(int position) {
			return texts.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.text_item, null);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_item_type = (TextView) convertView.findViewById(R.id.tv_item_type);
			holder.iv_item_pic = (ImageView) convertView.findViewById(R.id.iv_item_pic);
			holder.tv_item_title = (TextView) convertView.findViewById(R.id.tv_item_title);
			holder.tv_item_author = (TextView) convertView.findViewById(R.id.tv_item_author);
			holder.tv_item_des = (TextView) convertView.findViewById(R.id.tv_item_des);

			Text text = texts.get(position);
			holder.tv_item_type.setText(text.getType());
			holder.tv_item_title.setText(text.getTitle());
			holder.tv_item_des.setText(text.getContent());
			if(text.getTypeNumber() == 1){
				holder.iv_item_pic.setImageResource(R.drawable.text_book);
			}else if(text.getTypeNumber()==2){
				holder.iv_item_pic.setImageResource(R.drawable.text_stroy);
			}else{
				holder.iv_item_pic.setImageResource(R.drawable.text_poet);
			}
			if(TextUtils.isEmpty(text.getAuthor())){
				holder.tv_item_author.setText("作者不详");
			}else{
				holder.tv_item_author.setText(text.getAuthor());
			}
			return convertView;

		}

	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int newPosition = position % texts.size();
			ImageView view = new ImageView(context);
			Picasso.with(context)
					.load(texts.get(newPosition).getPagerPic().getFileUrl())
					.error(R.drawable.pic_load_error)
					.into(view);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			container.addView(view);
			return view;
		}

	}

	static class ViewHolder {
		TextView tv_item_title, tv_item_des,tv_item_type,tv_item_author;
		ImageView iv_item_pic;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(handler!=null || pagerAdapter!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
            pagerAdapter = null;
        }
	}
}
