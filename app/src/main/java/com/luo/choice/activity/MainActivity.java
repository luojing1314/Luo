package com.luo.choice.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.choice.R;
import com.luo.choice.Utils.NetworkUtils;
import com.luo.choice.bean.Picture;
import com.luo.choice.bean.Text;
import com.luo.choice.bean.Video;
import com.luo.choice.data.LoadData;
import com.luo.choice.fragment.LoaddingFragment;
import com.luo.choice.fragment.MoreFragment;
import com.luo.choice.fragment.PictureFragment;
import com.luo.choice.fragment.TextFragment;
import com.luo.choice.fragment.VideoFragment;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    @InjectViews({R.id.btn_text_fragment, R.id.btn_pic_fragment, R.id.btn_video_fragment, R.id
            .btn_more_fragment})
    List<Button> buttonLists;
    @InjectView(R.id.iv_title_share)
    ImageView iv_title_share;
    @InjectView(R.id.tv_title_text)
    TextView tv_title_text;
    @InjectView(R.id.iv_title_appicon)
    ImageView iv_title_appicon;

    private LoaddingFragment loaddingFragment;
    private TextFragment textFragment;
    private PictureFragment pictureFragment;
    private VideoFragment videoFragment;
    private MoreFragment moreFragment;
    private boolean textFragmentIsLoadData = false;
    private boolean pictureFragmentIsLoadData = false;
    private boolean videoFragmentIsLoadData = false;
    private boolean moreFragmentIsLoadData = false;
    private static final int FRAGMENT_COUNT = 4;
    public static int mCurrentSelected = 0;
    public static final int TEXT_PAGE = 0;
    public static final int PICTURE_PAGE = 1;
    public static final int VIDEO_PAGE = 2;
    public static final int MORE_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        ShareSDK.initSDK(this, "168ef3f3f1d2c");
        MobclickAgent.setSessionContinueMillis(1 * 60 * 1000);
//		int width = getWindowManager().getDefaultDisplay().getWidth();
//		int height = getWindowManager().getDefaultDisplay().getHeight();
//		Log.w("hhhhh","手机分辨率是 宽:"+width+"高 :"+height);
        iv_title_appicon.setVisibility(View.VISIBLE);
        iv_title_share.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        tv_title_text.setText(R.string.textfragment_title);
        setCurrentSelected();
        showLoaddingFragment(true);
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "网络不给力哦,检查下网络状态吧", Toast.LENGTH_LONG).show();
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    BmobUpdateAgent.update(MainActivity.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        LoadData.getDataFromServer(TEXT_PAGE, this);
    }

    public void showLoaddingFragment(boolean isShowLoadding) {
        if (isShowLoadding) {
            if (loaddingFragment == null) {
                loaddingFragment = new LoaddingFragment();
            }
        } else {
            loaddingFragment.setErrorLayout();
        }
        if (!loaddingFragment.isAdded()) {
            getFragmentTransaction().add(R.id.fl_fragment, loaddingFragment).commit();
        } else {
            getFragmentTransaction().show(loaddingFragment).commit();
        }

    }

    public FragmentTransaction getFragmentTransaction() {
        FragmentManager fm = getFragmentManager();
        return fm.beginTransaction();
    }

    private void removeLoaddingFragment() {
        getFragmentTransaction().remove(loaddingFragment).commit();
    }

    public void showCurrentFragmentDataFromServer(int type, List<?> lists) {
        switch (type) {
            case TEXT_PAGE:
                showTextFragmentDataFromServer(lists);
                break;
            case PICTURE_PAGE:
                showPicFragmentFromServer(lists);
                break;
            case VIDEO_PAGE:
                showVideoFragmentFromServer(lists);
                break;
        }
    }

    private void showTextFragmentDataFromServer(List<?> lists) {
        if (lists.size() > 0) {
            List<Text> textList = (List<Text>) lists;
            textFragment = new TextFragment();
            Bundle argument = new Bundle();
            argument.putSerializable("LIST", (Serializable) textList);
            textFragment.setArguments(argument);
            removeLoaddingFragment();
            getFragmentTransaction().add(R.id.fl_fragment, textFragment).commit();
            textFragmentIsLoadData = true;
            if (mCurrentSelected != MainActivity.TEXT_PAGE) {
                getFragmentTransaction().hide(textFragment).commit();
            }
        } else {
            loaddingFragment.setErrorLayout();
        }
    }

    private void showVideoFragmentFromServer(List<?> lists) {
        if (lists.size() > 0) {
            List<Video> videoList = (List<Video>) lists;
            videoFragment = new VideoFragment();
            Bundle argument = new Bundle();
            argument.putSerializable("LIST", (Serializable) videoList);
            videoFragment.setArguments(argument);
            removeLoaddingFragment();
            getFragmentTransaction().add(R.id.fl_fragment, videoFragment).commit();
            videoFragmentIsLoadData = true;
            if (mCurrentSelected != MainActivity.VIDEO_PAGE) {
                getFragmentTransaction().hide(videoFragment).commit();
            }
        } else {
            loaddingFragment.setErrorLayout();
        }
    }

    private void showPicFragmentFromServer(List<?> lists) {
        if (lists.size() > 0) {
            List<Picture> picList = (List<Picture>) lists;
            pictureFragment = new PictureFragment();
            Bundle argument = new Bundle();
            argument.putSerializable("LIST", (Serializable) picList);
            pictureFragment.setArguments(argument);
            removeLoaddingFragment();
            getFragmentTransaction().add(R.id.fl_fragment, pictureFragment).commit();
            pictureFragmentIsLoadData = true;
            if (mCurrentSelected != MainActivity.PICTURE_PAGE) {
                getFragmentTransaction().hide(pictureFragment).commit();
            }
        } else {
            loaddingFragment.setErrorLayout();
        }
    }

    private void hideCurrentFragment() {
        switch (mCurrentSelected) {
            case TEXT_PAGE:
                getFragmentTransaction().hide(textFragment).commit();
                break;
            case PICTURE_PAGE:
                getFragmentTransaction().hide(pictureFragment).commit();
                break;
            case VIDEO_PAGE:
                getFragmentTransaction().hide(videoFragment).commit();
                break;
            case MORE_PAGE:
                getFragmentTransaction().hide(moreFragment).commit();
                break;
        }
    }

    public LoaddingFragment getLoaddingFragment() {
        if (loaddingFragment != null) {
            return loaddingFragment;
        } else {
            return null;
        }
    }

    @OnClick({R.id.btn_text_fragment, R.id.btn_pic_fragment, R.id.btn_video_fragment, R.id
            .btn_more_fragment, R.id.iv_title_share})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_text_fragment:
                showTextFragment();
                break;
            case R.id.btn_pic_fragment:
                showPicFragment();
                break;
            case R.id.btn_video_fragment:
                showVideoFragment();

                break;
            case R.id.btn_more_fragment:
                showMoreFragment();
                break;
            case R.id.iv_title_share:
                showShare();
                break;
        }

    }

    private void showMoreFragment() {
        if (mCurrentSelected != MORE_PAGE) {
            tv_title_text.setText(R.string.morefragment_title);
            if (moreFragment == null) {
                moreFragment = new MoreFragment();
                getFragmentTransaction().add(R.id.fl_fragment, moreFragment).commit();
                moreFragmentIsLoadData = true;
            }
            if (isCurrentItemAlreadyLoadData()) {
                hideCurrentFragment();
            }
            if (loaddingFragment != null) {
                removeLoaddingFragment();
            }
            getFragmentTransaction().show(moreFragment).commit();
            mCurrentSelected = MORE_PAGE;
            setCurrentSelected();
        }
    }

    private void showVideoFragment() {
        if (mCurrentSelected != VIDEO_PAGE) {
            tv_title_text.setText(R.string.videofragment_title);
            if (isCurrentItemAlreadyLoadData()) {
                hideCurrentFragment();
            }
            if (!videoFragmentIsLoadData) {
                showLoaddingFragment(true);
                mCurrentSelected = VIDEO_PAGE;
                setCurrentSelected();
                LoadData.getDataFromServer(VIDEO_PAGE, MainActivity.this);
            } else {
                if (loaddingFragment != null) {
                    removeLoaddingFragment();
                }
                getFragmentTransaction().show(videoFragment).commit();
                mCurrentSelected = VIDEO_PAGE;
                setCurrentSelected();
            }
        }
    }

    private void showTextFragment() {
        if (mCurrentSelected != TEXT_PAGE) {
            tv_title_text.setText(R.string.textfragment_title);
            if (isCurrentItemAlreadyLoadData()) {
                hideCurrentFragment();
            }
            if (!textFragmentIsLoadData) {
                showLoaddingFragment(true);
                mCurrentSelected = TEXT_PAGE;
                setCurrentSelected();
                LoadData.getDataFromServer(TEXT_PAGE, this);
            } else {
                if (loaddingFragment != null) {
                    removeLoaddingFragment();
                }
                getFragmentTransaction().show(textFragment).commit();
                mCurrentSelected = TEXT_PAGE;
                setCurrentSelected();
            }
        }
    }

    private void showPicFragment() {
        if (mCurrentSelected != PICTURE_PAGE) {
            tv_title_text.setText(R.string.picfragment_title);
            if (isCurrentItemAlreadyLoadData()) {
                hideCurrentFragment();
            }
            if (!pictureFragmentIsLoadData) {
                showLoaddingFragment(true);
                mCurrentSelected = PICTURE_PAGE;
                setCurrentSelected();
                LoadData.getDataFromServer(PICTURE_PAGE, this);
            } else {
                if (loaddingFragment != null) {
                    removeLoaddingFragment();
                }
                getFragmentTransaction().show(pictureFragment).commit();
                mCurrentSelected = PICTURE_PAGE;
                setCurrentSelected();
            }

        }
    }

    public boolean isCurrentItemAlreadyLoadData() {
        boolean isAlreadyLoad = false;
        switch (mCurrentSelected) {
            case TEXT_PAGE:
                isAlreadyLoad = textFragmentIsLoadData;
                break;
            case PICTURE_PAGE:
                isAlreadyLoad = pictureFragmentIsLoadData;
                break;
            case VIDEO_PAGE:
                isAlreadyLoad = videoFragmentIsLoadData;
                break;
            case MORE_PAGE:
                isAlreadyLoad = moreFragmentIsLoadData;
                break;
        }
        return isAlreadyLoad;
    }

    private void setCurrentSelected() {
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            if (i == mCurrentSelected) {
                buttonLists.get(i).setSelected(true);
            } else {
                buttonLists.get(i).setSelected(false);
            }
        }
    }

    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("向你推荐一个app");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
//		oks.setTitleUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.luo.choice");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("安卓应用市场搜索“小憩”下载，每日精选作品更新哦！");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.luo.choice");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//		oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        mCurrentSelected = TEXT_PAGE;
        super.onDestroy();
    }
}
