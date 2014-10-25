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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import paxteam.android_client.xml.base.webservices.enums.ConnectionErrorType;
import paxteam.android_client.xml.base.webservices.enums.CookieType;
import paxteam.android_client.xml.base.webservices.enums.DataType;
import paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener;
import paxteam.android_client.xml.base.webservices.interfaces.OnImageListener;

public class WebService {

    private DefaultHttpClient httpClient;

    private Context mContext;
    private OnErrorListener errorListener;
    private OnImageListener imageListener;

    private Gson mGson;

    public WebService(Context context) {
        this(context,
                WebServicesConfig.DEFAULT_ERROR_LISTENER,
                paxteam.android_client.xml.base.webservices.WebServicesConfig.DEFAULT_COOKIE_TYPE,
                paxteam.android_client.xml.base.webservices.WebServicesConfig.DEFAULT_HTTP_PARAMS,
                paxteam.android_client.xml.base.webservices.WebServicesConfig.DEFAULT_IMAGE_LISTENER);
    }

    WebService(Context context, paxteam.android_client.xml.base.webservices.interfaces.OnErrorListener errorListener,
               paxteam.android_client.xml.base.webservices.enums.CookieType cookieType, HttpParams httpParameters,
               paxteam.android_client.xml.base.webservices.interfaces.OnImageListener imageListener) {
        this.mContext = context;
        this.errorListener = errorListener;
        this.imageListener = imageListener;

        this.httpClient = new DefaultHttpClient(httpParameters);

        httpClient.setCookieStore(new BasicCookieStore());


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Bitmap.class, new paxteam.android_client.xml.support.BitmapDeserializer(this));
        mGson = builder.create();
    }

    public String jsonFromURL(String url) throws paxteam.android_client.xml.base.webservices.throwers.ConnectionErrorException {
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse;
        request.setHeader("Content-Type", "application/json");
        try {
            httpResponse = httpClient.execute(request);
            if(!statusControl(httpResponse, DataType.JSON)) return null;
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            CallBackUtily.callbackIOException(errorListener, paxteam.android_client.xml.base.webservices.enums.DataType.JSON);
            return null;
        }
    }

    public <T> T objectFromURL(String url, Class<T> c) {
        String json = jsonFromURL(url);
        if(json == null) return null;
        return mGson.fromJson(json, c);
    }

    /*
    public Bitmap imageFromURL(final String key, final String url) {
        if(this.imageListener == null) {
            return getImageFromURL(url);
        } else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = getImageFromURL(url);
                    CallBackUtily.callbackToUser(imageListener.onImage(key, b););
                }
            }).start();
            return null;
        }
    }
    */

    public Bitmap imageFromURL(String url) {
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            if(!statusControl(response, DataType.IMAGE)) return null;
            HttpEntity entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);
            return BitmapFactory.decodeByteArray(bytes, 0,
                    bytes.length);
        } catch (IOException e) {
            CallBackUtily.callbackIOException(errorListener, DataType.IMAGE);
            return null;
        }
    }

    private boolean statusControl(HttpResponse httpResponse, final DataType dataType) throws paxteam.android_client.xml.base.webservices.throwers.ConnectionErrorException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if(statusCode == 200) return true;
        final paxteam.android_client.xml.base.webservices.enums.ConnectionErrorType connectionErrorType;
        if(this.errorListener != null) {
            switch (statusCode) {
                case 400: connectionErrorType = ConnectionErrorType.NO_SERVICES_FOUND; break;
                default: connectionErrorType = paxteam.android_client.xml.base.webservices.enums.ConnectionErrorType.OTHER_ERROR; break;
            }
            CallBackUtily.callbackToUser(errorListener, new Runnable() {
                @Override
                public void run() {
                    errorListener.onError(connectionErrorType, dataType);
                }
            });
            return false;
        } else {
            throw new paxteam.android_client.xml.base.webservices.throwers.ConnectionErrorException("Status code: "+statusCode+". If you want control this," +
                    "you must implements OnErrorListener and include it on WebServiceConfig");
        }
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        int i;
        for (byte b : hash) {
            i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }
}
