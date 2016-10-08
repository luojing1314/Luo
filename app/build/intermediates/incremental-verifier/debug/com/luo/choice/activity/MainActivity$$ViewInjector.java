// Generated code from Butter Knife. Do not modify!
package com.luo.choice.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.luo.choice.activity.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427454, "field 'iv_title_share' and method 'onClick'");
    target.iv_title_share = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427453, "field 'tv_title_text'");
    target.tv_title_text = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427451, "field 'iv_title_appicon'");
    target.iv_title_appicon = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427417, "method 'onClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427418, "method 'onClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427419, "method 'onClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427420, "method 'onClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    target.buttonLists = Finder.listOf(
        (android.widget.Button) finder.findRequiredView(source, 2131427417, "buttonLists"),
        (android.widget.Button) finder.findRequiredView(source, 2131427418, "buttonLists"),
        (android.widget.Button) finder.findRequiredView(source, 2131427419, "buttonLists"),
        (android.widget.Button) finder.findRequiredView(source, 2131427420, "buttonLists")
    );  }

  public static void reset(com.luo.choice.activity.MainActivity target) {
    target.iv_title_share = null;
    target.tv_title_text = null;
    target.iv_title_appicon = null;
    target.buttonLists = null;
  }
}
