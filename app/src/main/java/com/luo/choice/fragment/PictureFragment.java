package com.luo.choice.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luo.choice.R;
import com.luo.choice.activity.PictureDetailActivity;
import com.luo.choice.bean.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luojing on 2016/8/22.
 */
public class PictureFragment extends Fragment{
    private Context context;
    private List<Picture> lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, null);
        context = getActivity();
        Bundle arguments = getArguments();
        if(arguments!=null){
            lists = (List<Picture>) arguments.getSerializable("LIST");
        }else{
            return null;
        }
        ListView lv_picture = (ListView) view.findViewById(R.id.lv_picture);
        lv_picture.setAdapter(new PictureAdapter());
        lv_picture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(context, PictureDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("picture",lists.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_animation_start_in,R.anim.activity_animation_start_out);
            }
        });
        return view;
    }

    private class PictureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(lists.size()!=0){
                return lists.size();
            }else{
                return 0;
            }

        }

        @Override
        public Picture getItem(int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = View.inflate(context,R.layout.picture_item,null);
                holder.iv_pic_item_pic = (ImageView) convertView.findViewById(R.id.iv_pic_item_pic);
                holder.tv_pic_item_title = (TextView) convertView.findViewById(R.id.tv_pic_item_title);
                holder.tv_pic_item_author = (TextView) convertView.findViewById(R.id.tv_pic_item_author);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            Picture picture = lists.get(position);
            holder.tv_pic_item_title.setText(picture.getTitle());
            holder.tv_pic_item_author.setText(picture.getAuthor());
            Picasso.with(context)
                    .load(picture.getContentPic().getFileUrl())
                    .error(R.drawable.alizee)
                    .into(holder.iv_pic_item_pic);
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_pic_item_pic;
        TextView tv_pic_item_title,tv_pic_item_author;
    }

}
