/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.support;

import android.graphics.Bitmap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;

import xml.base.webservices.WebService;
import xml.base.webservices.throwers.ConnectionErrorException;

public class BitmapDeserializer implements JsonDeserializer<Bitmap> {

    private WebService webService;

    public BitmapDeserializer(WebService webService) {
        this.webService = webService;
    }

    @Override
    public Bitmap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException, ConnectionErrorException {
        return webService.imageFromURL(json.getAsString());
    }
}
