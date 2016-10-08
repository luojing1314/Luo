package com.luo.choice.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.choice.R;
import com.luo.choice.Utils.MD5Utils;
import com.luo.choice.Utils.PixelUtils;
import com.luo.choice.view.gestureimageview.GestureImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luojing on 2016/8/30.
 */
public class PictureScaleDownloadActivity extends Activity implements View.OnClickListener{
    private File file;
    private String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_scale_download);
        imageUrl = getIntent().getStringExtra("url");
        RelativeLayout rl_picroot = (RelativeLayout) findViewById(R.id.rl_picroot);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        GestureImageView view = new GestureImageView(this);
        view.setMinScale(0.75f);
        view.setMaxScale(10f);
        view.setLayoutParams(params);

        rl_picroot.addView(view);

        RelativeLayout.LayoutParams saveParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setText("保存");
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        textView.setGravity(Gravity.CENTER);
        int Padding = PixelUtils.dip2px(this, 10);
        int topPadding = PixelUtils.dip2px(this,5);
        textView.setPadding(Padding,topPadding,Padding,topPadding);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        textView.setLayoutParams(saveParams);
        rl_picroot.addView(textView);

        Picasso.with(this)
                .load(imageUrl)
                .error(R.drawable.pic_load_error)
                .into(view);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try {
                InputStream in = getImageStream(imageUrl);
                if(in!=null){
                    Bitmap bitmap =  BitmapFactory.decodeStream(in);
                    String pic_pah = Environment
                            .getExternalStorageDirectory() + "/picture/";
                    saveFile(bitmap,pic_pah);
                }else{
                    Toast.makeText(PictureScaleDownloadActivity.this,"图片保存失败,请稍后再试",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            try {
                InputStream in = getImageStream(imageUrl);
                if(in!=null){
                    Bitmap bitmap =  BitmapFactory.decodeStream(in);
                    String path = getCacheDir().getAbsolutePath()+"/picture/";
                    saveFile(bitmap,path);
                }else{
                    Toast.makeText(PictureScaleDownloadActivity.this,"图片保存失败,请稍后再试",Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);

    }
    protected InputStream getImageStream(String path) throws Exception {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(4500);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
            return null;

    }
    protected void saveFile(Bitmap bm,String path) throws Exception {
        file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File myCaptureFile = new File(path + MD5Utils.md5Encode(imageUrl)+".jpg");
        if(myCaptureFile.exists()){
            Toast.makeText(PictureScaleDownloadActivity.this,"图片己保存",Toast.LENGTH_SHORT).show();
            return;
        }
        bm.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(myCaptureFile));
        Toast.makeText(PictureScaleDownloadActivity.this,"图片保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

}

