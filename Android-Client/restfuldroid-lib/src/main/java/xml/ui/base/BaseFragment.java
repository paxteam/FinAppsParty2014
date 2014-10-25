/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.ui.base;

import android.support.v4.app.DialogFragment;
import android.view.View;

import xml.base.BaseController;
import xml.base.IBase;

public abstract class BaseFragment<T> extends DialogFragment implements IBase<T>{

    protected String name;


    private BaseController mBase;

    public BaseFragment(String name) {
        this.name = name;
    }

    public void onCreateView(View v) {
        mBase = new BaseController<T>(getActivity()) {
            @Override
            public T loading_data(int type) {
                return BaseFragment.this.loading_data(type);
            }

            @Override
            public void setting_data(T data) {
                BaseFragment.this.setting_data(data);
            }
        };

        mBase.init(v);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void updateData(int type, ProgressType progressType) {
        mBase.updateData(type, progressType);
    }

    @Override
    public void showCustomDialog(BaseDialog dialog) {
        mBase.showCustomDialog(dialog);
    }
}
