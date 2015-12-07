// Generated code from Butter Knife. Do not modify!
package com.bigevent.noc.bigeventdataverification;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.bigevent.noc.bigeventdataverification.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492974, "field '_usernameText'");
    target._usernameText = finder.castView(view, 2131492974, "field '_usernameText'");
    view = finder.findRequiredView(source, 2131492975, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131492975, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131492976, "field '_loginButton'");
    target._loginButton = finder.castView(view, 2131492976, "field '_loginButton'");
    view = finder.findRequiredView(source, 2131492977, "field '_signupLink'");
    target._signupLink = finder.castView(view, 2131492977, "field '_signupLink'");
  }

  @Override public void unbind(T target) {
    target._usernameText = null;
    target._passwordText = null;
    target._loginButton = null;
    target._signupLink = null;
  }
}
