/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.base.webservices;

import android.app.Activity;
import android.support.v4.app.Fragment;

import xml.base.webservices.enums.ConnectionErrorType;
import xml.base.webservices.enums.DataType;
import xml.base.webservices.interfaces.OnErrorListener;
import xml.base.webservices.throwers.ConnectionErrorException;

public class CallBackUtily {

    private CallBackUtily() { }

    public static void callbackToUser(Object obj, final Runnable runnable) {
        if(obj instanceof Activity) {
            ((Activity) obj).runOnUiThread(runnable);
        }else if(obj instanceof Fragment) {
            ((Fragment)obj).getActivity().runOnUiThread(runnable);
        } else if(obj instanceof android.app.Fragment) {
            ((android.app.Fragment)obj).getActivity().runOnUiThread(runnable);
        }else
            runnable.run();
    }

    public static void callbackIOException(final OnErrorListener listener, final DataType dataType) {
        if(listener != null) {
            CallBackUtily.callbackToUser(listener, new Runnable() {
                @Override
                public void run() {
                    listener.onError(ConnectionErrorType.NO_INTERNET_CONNECTION, dataType);
                }
            });
        }else
            throw new ConnectionErrorException("Problem with internet connection. If you want control this," +
                    "you must implements OnErrorListener and include it on WebServiceConfig");
    }
}
