// Generated code from Butter Knife. Do not modify!
package com.luo.choice.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class BaseActivity$$ViewInjector {
  public static void inject(Finder finder, final com.luo.choice.activity.BaseActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427454, "field 'iv_title_share'");
    target.iv_title_share = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427452, "field 'iv_title_back'");
    target.iv_title_back = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427451, "field 'iv_title_appicon'");
    target.iv_title_appicon = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427453, "field 'tv_title_text'");
    target.tv_title_text = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427414, "field 'fl_detail_content'");
    target.fl_detail_content = (android.widget.FrameLayout) view;
    view = finder.findRequiredView(source, 2131427450, "field 'll_title_layout'");
    target.ll_title_layout = (android.widget.LinearLayout) view;
  }

  public static void reset(com.luo.choice.activity.BaseActivity target) {
    target.iv_title_share = null;
    target.iv_title_back = null;
    target.iv_title_appicon = null;
    target.tv_title_text = null;
    target.fl_detail_content = null;
    target.ll_title_layout = null;
  }
}
