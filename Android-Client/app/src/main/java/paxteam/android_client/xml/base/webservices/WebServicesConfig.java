/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.base.webservices;

import android.content.Context;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import paxteam.android_client.xml.base.webservices.enums.CookieType;
import paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener;
import paxteam.android_client.xml.base.webservices.interfaces.OnImageListener;

public class WebServicesConfig {
    public static final paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener DEFAULT_ERROR_LISTENER = null;
    public static final paxteam.android_client.xml.base.webservices.enums.CookieType DEFAULT_COOKIE_TYPE = paxteam.android_client.xml.base.webservices.enums.CookieType.NO_COOKIE;
    public static final HttpParams DEFAULT_HTTP_PARAMS = getDefaultHttpParams();
    private static final HttpParams getDefaultHttpParams() {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
        HttpConnectionParams.setSoTimeout(httpParameters, 30000);
        return httpParameters;
    }
    public static final OnImageListener DEFAULT_IMAGE_LISTENER = null;


    private OnErrorListener onErrorListener = DEFAULT_ERROR_LISTENER;
    private CookieType cookieType = DEFAULT_COOKIE_TYPE;
    private HttpParams httpParams = DEFAULT_HTTP_PARAMS;
    private paxteam.android_client.xml.base.webservices.interfaces.OnImageListener onImageListener = DEFAULT_IMAGE_LISTENER;

    public WebServicesConfig() { }

    public WebServicesConfig setOnErrorListener(paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener listener) {
        this.onErrorListener = listener;
        return this;
    }

    public WebServicesConfig setCookieType(paxteam.android_client.xml.base.webservices.enums.CookieType cookieType) {
        this.cookieType = cookieType;
        return this;
    }

    public WebServicesConfig setHttpParams(HttpParams httpParams) {
        this.httpParams = httpParams;
        return this;
    }

    public WebServicesConfig downloadImagesOnParallel(OnImageListener onImageListener) {
        this.onImageListener = onImageListener;
        return this;
    }

    public WebServicesConfig downloadImagesOnSameThread() {
        this.onImageListener = null;
        return this;
    }

    public paxteam.android_client.xml.base.webservices.WebService create(Context context) {
        return new paxteam.android_client.xml.base.webservices.WebService(context,
                this.onErrorListener, this.cookieType,
                this.httpParams, this.onImageListener);
    }
}
