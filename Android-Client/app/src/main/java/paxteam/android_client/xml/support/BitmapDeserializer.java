/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package paxteam.android_client.xml.support;

import android.graphics.Bitmap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import paxteam.android_client.xml.base.webservices.WebService;

public class BitmapDeserializer implements JsonDeserializer<Bitmap> {

    private WebService webService;

    public BitmapDeserializer(WebService webService) {
        this.webService = webService;
    }

    @Override
    public Bitmap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException, paxteam.android_client.xml.base.webservices.throwers.ConnectionErrorException {
        return webService.imageFromURL(json.getAsString());
    }
}
