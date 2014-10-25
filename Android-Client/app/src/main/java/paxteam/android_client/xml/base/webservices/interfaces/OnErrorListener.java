/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.base.webservices.interfaces;

import paxteam.android_client.xml.base.webservices.enums.ConnectionErrorType;
import paxteam.android_client.xml.base.webservices.enums.DataType;

/**
 * Restfullib is a library to facilitate the creation of an android app (client side)
 * connected with server based on RESTful webservice.
 * <p/>
 * This library has been created by xaviml
 * https://github.com/xaviml
 */
public interface OnErrorListener {

    public void onError(ConnectionErrorType connectionErrorType, DataType dataType);
}
