package com.luo.choice.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luo.choice.R;
import com.luo.choice.Utils.PixelUtils;
import com.luo.choice.bean.Video;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.ThumbnailUtils;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by luojing on 2016/8/27.
 */
public class VideoDetailActivity extends BaseActivity {
    private static final java.lang.String TAG ="标记" ;
    private ImageView iv_video_thumbnail;
    private ImageView iv_video_start;
    private MediaController controller;
    private VideoView videoView;
    private List<View> views;
    private FrameLayout fl_container;
    private Boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        views = new ArrayList<View>();
        iv_title_back.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        final Video video = (Video) bundle.get("video");
        Integer type = video.getType();
        if(type != null){
            if (type == 1) {
                tv_title_text.setText("电影");
            } else if (type == 2) {
                tv_title_text.setText("音乐");
            } else {
                tv_title_text.setText("视频");
            }
        }
        View view = View.inflate(this, R.layout.activity_video_detail, null);
        fl_detail_content.addView(view);

        fl_container = (FrameLayout) view.findViewById(R.id.fl_container);

        videoView = (VideoView) view.findViewById(R.id.buffer);
        TextView tv_video_detail_title = (TextView) view.findViewById(R.id.tv_video_detail_title);
        TextView tv_video_detail_author = (TextView) view.findViewById(R.id.tv_video_detail_author);
        TextView tv_video_detail_content = (TextView) view.findViewById(R.id.tv_video_detail_content);
        TextView tv_video_detail_referrer = (TextView) view.findViewById(R.id.tv_video_detail_referrer);
        TextView tv_video_detail_reason = (TextView) view.findViewById(R.id.tv_video_detail_reason);

        views.add(tv_video_detail_title);
        views.add(tv_video_detail_author);
        views.add(tv_video_detail_content);
        views.add(tv_video_detail_referrer);
        views.add(tv_video_detail_reason);
        views.add(ll_title_layout);

        iv_video_thumbnail = (ImageView) view.findViewById(R.id.iv_video_thumbnail);
        iv_video_start = (ImageView) view.findViewById(R.id.iv_video_start);
        new Thread(){
            @Override
            public void run() {
                final Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(VideoDetailActivity.this,
                        video.getResource().getFileUrl(), MediaStore.Video.Thumbnails.MINI_KIND);
                if(createVideoThumbnail!=null){
                    iv_video_thumbnail.post(new Runnable() {
                        @Override
                        public void run() {
                            iv_video_thumbnail.setImageBitmap(createVideoThumbnail);
                        }
            });

                }
            }
        }.start();
        tv_video_detail_title.setText(video.getTitle());
        tv_video_detail_author.setText(video.getAuthor());
        tv_video_detail_content.setText(video.getDes());
        if (TextUtils.isEmpty(video.getReferrer())) {
            tv_video_detail_referrer.setVisibility(View.GONE);
        } else {
            tv_video_detail_referrer.setText(video.getReferrer());
        }
        if (TextUtils.isEmpty(video.getReason())) {
            tv_video_detail_reason.setVisibility(View.GONE);
        } else {
            tv_video_detail_reason.setText(video.getReason());
        }
        controller = new MediaController(this, true, fl_container);
        controller.setVisibility(View.GONE);
        iv_video_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_video_start.setVisibility(View.GONE);
                iv_video_thumbnail.setVisibility(View.GONE);
                //设置video的控制器
                videoView.setVideoURI(Uri.parse(video.getResource().getFileUrl()));
                videoView.setMediaController(controller);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoView.start();
                        videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,0);
                    }
                });
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.stopPlayback();
                iv_video_start.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            isFullScreen = true;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            for (int i = 0; i < views.size(); i++) {
                views.get(i).setVisibility(View.GONE);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            fl_container.setLayoutParams(params);
            //原视频大小
//            public static final int VIDEO_LAYOUT_ORIGIN = 0;
            //最优选择，但是可能不能填满屏幕
//            public static final int VIDEO_LAYOUT_SCALE = 1;
            //拉伸，可能导致变形
//            public static final int VIDEO_LAYOUT_STRETCH = 2;
            //会放大可能超出屏幕
//            public static final int VIDEO_LAYOUT_ZOOM = 3;
            //这个暂不清除，效果还是竖屏大小
//            public static final int VIDEO_LAYOUT_FIT_PARENT = 4;
            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,0);
        }else {
//            /*清除flag,恢复显示系统状态栏*/
            isFullScreen = false;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            for (int i = 0; i < views.size(); i++) {
                views.get(i).setVisibility(View.VISIBLE);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    PixelUtils.dip2px(this,200));
            fl_container.setLayoutParams(params);

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null){
            videoView.destroyDrawingCache();
            videoView.stopPlayback();
            videoView = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4 && isFullScreen){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            controller.setFullScreenIconState(false);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}
