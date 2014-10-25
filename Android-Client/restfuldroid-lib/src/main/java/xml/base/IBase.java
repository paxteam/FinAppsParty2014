/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.base;


import xml.ui.base.BaseDialog;

public interface IBase<T> {

    public static final int BASE_UPDATE = 0;

    /**
     * There are two methods to feedback to user:
     *     * Progress dialog in a dialog
     *     * Circular progress embedded in layout
     *
     */

    public enum ProgressType {PROGRESS_DIALOG, CIRCULAR_PROGRESS};

    public void updateData(int type, ProgressType progressType);
    public void showCustomDialog(BaseDialog dialog);


    public abstract T loading_data(int type);
    public abstract void setting_data(T data);

}
