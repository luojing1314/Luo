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
import com.luo.choice.activity.VideoDetailActivity;
import com.luo.choice.bean.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luojing on 2016/8/23.
 */
public class VideoFragment extends Fragment {
    public static final Integer FILM_TYPE = 1;
    public static final Integer MUSIC_TYPE = 2;
    private Context context;
    private List<Video> videoList;
    private ListView lv_video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,null);
        context =getActivity();
        Bundle arguments = getArguments();
        if(arguments!=null){
            videoList = (List<Video>) arguments.getSerializable("LIST");
        }else{
            return null;
        }
        lv_video = (ListView) view.findViewById(R.id.lv_video);
        lv_video.setAdapter(new VideoAdapter());
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(context, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("video",videoList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_animation_start_in,R.anim.activity_animation_start_out);
            }
        });
        return view;
    }

    private class VideoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(videoList.size()!=0){
                return videoList.size();
            }else{
                return 0;
            }
        }

        @Override
        public Video getItem(int i) {
            return videoList.get(i);
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
                convertView = View.inflate(context,R.layout.video_item,null);
                holder.iv_video_item_typePic = (ImageView) convertView.findViewById(R.id.iv_video_item_typePic);
                holder.tv_video_item_typeDes = (TextView) convertView.findViewById(R.id.tv_video_item_typeDes);
                holder.tv_video_item_title = (TextView) convertView.findViewById(R.id.tv_video_item_title);
                holder.iv_video_item_thumbnail = (ImageView) convertView.findViewById(R.id.iv_video_item_thumbnail);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            Video video = videoList.get(position);
            if(video.getType()==FILM_TYPE){
                holder.iv_video_item_typePic.setImageResource(R.drawable.film_type);
            }else if(video.getType() == MUSIC_TYPE){
                holder.iv_video_item_typePic.setImageResource(R.drawable.music_type);
            }else{
                holder.iv_video_item_typePic.setImageResource(R.drawable.video_type);
            }
            holder.tv_video_item_typeDes.setText(video.getTypeDes());
            holder.tv_video_item_title.setText(video.getTitle());
            Picasso.with(context)
                    .load(video.getThumbnail().getFileUrl())
                    .error(R.drawable.alizee)
                    .into(holder.iv_video_item_thumbnail);
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_video_item_typePic,iv_video_item_thumbnail;
        TextView tv_video_item_typeDes,tv_video_item_title;
    }


}
