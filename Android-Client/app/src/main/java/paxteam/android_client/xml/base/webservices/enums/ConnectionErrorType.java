/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.base.webservices.enums;

/**
 * Restfullib is a library to facilitate the creation of an android app (client side)
 * connected with server based on RESTful webservice.
 * <p/>
 * This library has been created by xaviml
 * https://github.com/xaviml
 */
public enum ConnectionErrorType {

    /**
     * This error can occur when:
     *      * There is no internet connection.
     *      * The url is incorrect
     *      * The service is off.
     *
     */
    NO_INTERNET_CONNECTION,

    /**
     * This error belongs to the <b>status code 400</b> of http
     *
     */
    NO_SERVICES_FOUND,

    /**
     * Other error types
     *
     */
    OTHER_ERROR
}
