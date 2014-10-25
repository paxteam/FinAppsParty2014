/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.ui.base;

public abstract class BaseDialog extends BaseFragment {

    public BaseDialog(String name, boolean cancelable) {
        super(name);
        super.setStyle(STYLE_NO_TITLE, 0);
        setCancelable(true);
    }
}
