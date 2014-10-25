/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import xml.R;
import xml.ui.base.BaseDialog;

/**
 * Created by zenbook on 22/09/14.
 */
public abstract class BaseController<T> implements IBase<T> {



    private boolean isUpdating;
    private Activity mActivity;


    private FrameLayout mFrame;
    private ProgressDialog mDialog;

    private ProgressType mProgressType;

    public BaseController(Activity activity) {
        this.mActivity = activity;
        isUpdating = false;
    }

    public void init(View v) {
        mFrame = (FrameLayout) v.findViewById(R.id.content);
        init();
    }

    public void init(Activity activity) {
        mFrame = (FrameLayout) activity.findViewById(R.id.content);
        init();
    }

    private void init() {
        if(mFrame != null)
            mFrame.setVisibility(View.INVISIBLE);
        mDialog = new ProgressDialog(mActivity);
        mDialog.setIndeterminate(true);
        mDialog.setMessage("Loading");
        /*ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);*/

        //setting default progress
        if(mProgressType == null)
            mProgressType = ProgressType.PROGRESS_DIALOG;
        if(isUpdating) showLoading(mProgressType);
    }


    @Override
    public void updateData(final int type, final ProgressType progressType) {
        if(isUpdating) return;
        isUpdating = true;
        this.mProgressType = progressType;
        showLoading(progressType);
        //TODO: hacerlo con AsyncTask
        new Thread(new Runnable() {
            @Override
            public void run() {
                final T t = loading_data(type);
                if(mActivity!=null) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setting_data(t);
                            dismissLoading(progressType);
                        }
                    });
                }
                isUpdating = false;
            }
        }).start();
    }

    @Override
    public void showCustomDialog(BaseDialog dialog) {
        if(mActivity instanceof FragmentActivity) {
            dialog.show(((FragmentActivity)mActivity).getSupportFragmentManager(), dialog.getName());
        }
    }

    private void showLoading(ProgressType progressType) {
        if(progressType == ProgressType.CIRCULAR_PROGRESS) {
            if (mActivity != null) {
                mFrame.setVisibility(View.VISIBLE);
            } else{
                mDialog.show();
            }
        } else {
            mDialog.show();
        }
    }

    private void dismissLoading(ProgressType progressType) {
        if(progressType == ProgressType.CIRCULAR_PROGRESS) {
            if (mActivity != null) {
                mFrame.setVisibility(View.INVISIBLE);
            } else{
                mDialog.dismiss();
            }
        }else {
            mDialog.dismiss();
        }
    }
}
