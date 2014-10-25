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
import android.content.Context;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import xml.base.webservices.enums.CookieType;
import xml.base.webservices.interfaces.OnErrorListener;
import xml.base.webservices.interfaces.OnImageListener;

public class WebServicesConfig {
    public static final OnErrorListener DEFAULT_ERROR_LISTENER = null;
    public static final CookieType DEFAULT_COOKIE_TYPE = CookieType.NO_COOKIE;
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
    private OnImageListener onImageListener = DEFAULT_IMAGE_LISTENER;

    public WebServicesConfig() { }

    public WebServicesConfig setOnErrorListener(OnErrorListener listener) {
        this.onErrorListener = listener;
        return this;
    }

    public WebServicesConfig setCookieType(CookieType cookieType) {
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

    public WebService create(Context context) {
        return new WebService(context,
                this.onErrorListener, this.cookieType,
                this.httpParams, this.onImageListener);
    }
}
