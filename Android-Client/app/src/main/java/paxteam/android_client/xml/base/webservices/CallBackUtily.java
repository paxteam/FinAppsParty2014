/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.base.webservices;

import android.app.Activity;
import android.support.v4.app.Fragment;

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

    public static void callbackIOException(final paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener listener, final paxteam.android_client.xml.base.webservices.enums.DataType dataType) {
        if(listener != null) {
            CallBackUtily.callbackToUser(listener, new Runnable() {
                @Override
                public void run() {
                    listener.onError(paxteam.android_client.xml.base.webservices.enums.ConnectionErrorType.NO_INTERNET_CONNECTION, dataType);
                }
            });
        }else
            throw new paxteam.android_client.xml.base.webservices.throwers.ConnectionErrorException("Problem with internet connection. If you want control this," +
                    "you must implements OnErrorListener and include it on WebServiceConfig");
    }
}
