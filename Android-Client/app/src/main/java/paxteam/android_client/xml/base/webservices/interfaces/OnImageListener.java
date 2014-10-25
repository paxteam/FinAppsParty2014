/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.base.webservices.interfaces;

import android.graphics.Bitmap;

/**
 * Restfullib is a library to facilitate the creation of an android app (client side)
 * connected with server based on RESTful webservice.
 * <p/>
 * This library has been created by xaviml
 * https://github.com/xaviml
 */
public interface OnImageListener {

    public void onImage(String key, Bitmap bitmap);
}
