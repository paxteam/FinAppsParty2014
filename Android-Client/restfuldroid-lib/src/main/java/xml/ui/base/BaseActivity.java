/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import xml.base.BaseController;
import xml.base.IBase;

public abstract class BaseActivity<T> extends FragmentActivity implements IBase<T>{

    private BaseController<T> mBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mBase = new BaseController<T>(this) {
            @Override
            public T loading_data(int type) {
                return BaseActivity.this.loading_data(type);
            }

            @Override
            public void setting_data(T data) {
                BaseActivity.this.setting_data(data);
            }
        };
        mBase.init(this);
    }

    public void initActivity(Class<?> c) {
        Intent i = new Intent(this,c);
        startActivity(i);
    }


    /*
    public void loginWithSession() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Loading");
        pDialog.show();
        SessionManager session = new SessionManager(this);
        final String[] account = session.loadSession();
        if(true) { //TODO: account == null
            initActivity(LoginActivity.class);
            finish();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                     //Realitzar el login
                    if(true){
                        initActivity(HomeActivity.class);
                        finish();
                    }
                    pDialog.dismiss();
                }
            }).start();
        }
    }*/


    @Override
    public void updateData(int type, ProgressType progressType) {
        mBase.updateData(type, progressType);
    }

    @Override
    public void showCustomDialog(BaseDialog dialog) {
        mBase.showCustomDialog(dialog);
    }
}
