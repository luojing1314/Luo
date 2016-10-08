package com.luo.choice.data;

import com.luo.choice.activity.MainActivity;
import com.luo.choice.bean.Picture;
import com.luo.choice.bean.Text;
import com.luo.choice.bean.Video;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by luojing on 2016/8/24.
 */
public class LoadData {

    public static void getDataFromServer(int type,final MainActivity mainActivity){
        switch (type){
            case 0:
                getTextPageDataFromServer(mainActivity);
                break;
            case 1:
                getPicturePageDataFromServer(mainActivity);
                break;
            case 2:
                getVideoPageDataFromServer(mainActivity);
                break;
        }
    }

    private static void getVideoPageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Video> viedeoQuery = new BmobQuery<Video>();
        Date currentTimeMillis = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format2 = format.format(currentTimeMillis);
        int date = Integer.parseInt(format2);
        viedeoQuery.addWhereEqualTo("date", date);
        viedeoQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        viedeoQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        viedeoQuery.findObjects(new FindListener<Video>() {
            @Override
            public void done(List<Video> list, BmobException exception) {
                if(exception==null){
                    if(list.size()==0){
                        getAllVideoPageDataFromServer(mainActivity);
                    }else{
                        mainActivity.showCurrentFragmentDataFromServer(MainActivity.VIDEO_PAGE,list);
                    }
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }

    private static void getAllVideoPageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Video> viedeoQuery = new BmobQuery<Video>();
        viedeoQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        viedeoQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        viedeoQuery.findObjects(new FindListener<Video>() {
            @Override
            public void done(List<Video> list, BmobException exception) {
                if(exception==null){
                    mainActivity.showCurrentFragmentDataFromServer(MainActivity.VIDEO_PAGE,list);
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }

    private static void getPicturePageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Picture> picQuery = new BmobQuery<Picture>();
        Date currentTimeMillis = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format2 = format.format(currentTimeMillis);
        int date = Integer.parseInt(format2);
        picQuery.addWhereEqualTo("date", date);
        picQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        picQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        picQuery.findObjects(new FindListener<Picture>() {
            @Override
            public void done(List<Picture> list, BmobException exception) {
                if(exception==null){
                    if(list.size()==0){
                        getAllPicturePageDataFromServer(mainActivity);
                    }else{
                        mainActivity.showCurrentFragmentDataFromServer(MainActivity.PICTURE_PAGE,list);
                    }
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }
    private static void getAllPicturePageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Picture> picQuery = new BmobQuery<Picture>();
        picQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        picQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        picQuery.findObjects(new FindListener<Picture>() {
            @Override
            public void done(List<Picture> list, BmobException exception) {
                if(exception==null){
                    mainActivity.showCurrentFragmentDataFromServer(MainActivity.PICTURE_PAGE,list);
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }

    private static void getTextPageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Text> textQuery = new BmobQuery<Text>();
        Date currentTimeMillis = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format2 = format.format(currentTimeMillis);
        int date = Integer.parseInt(format2);
        textQuery.addWhereEqualTo("date", date);
        textQuery.setLimit(3);
        textQuery.order("typeNumber");
        textQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        textQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        textQuery.findObjects(new FindListener<Text>() {
            @Override
            public void done(List<Text> list, BmobException exception) {
                if(exception == null){
                    if(list.size()==0){
                        getAllTextPageDataFromServer(mainActivity);
                    }else{
                        mainActivity.showCurrentFragmentDataFromServer(MainActivity.TEXT_PAGE,list);
                    }
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }

    private static void getAllTextPageDataFromServer(final MainActivity mainActivity) {
        BmobQuery<Text> textQuery = new BmobQuery<Text>();
        textQuery.order("typeNumber");
        textQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        textQuery.setMaxCacheAge(TimeUnit.HOURS.toMillis(2));
        textQuery.findObjects(new FindListener<Text>() {
            @Override
            public void done(List<Text> list, BmobException exception) {
                if(exception == null){
                    mainActivity.showCurrentFragmentDataFromServer(MainActivity.TEXT_PAGE,list);
                }else{
                    mainActivity.showLoaddingFragment(false);
                }
            }
        });
    }
}
