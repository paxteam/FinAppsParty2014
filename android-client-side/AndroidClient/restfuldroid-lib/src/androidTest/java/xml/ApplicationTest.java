/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import xml.support.URLServiceBuilder;
import xml.base.webservices.WebService;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testURLWebService() {
        URLServiceBuilder u = new URLServiceBuilder("http", "www.example.com", "profile/example1");
        u.appendParameter("q", "adeu");

        String url = u.getURL();
        assertEquals(url, "http://www.example.com/profile/example1?q=adeu");
    }

    public void testWebService() {
        URLServiceBuilder url = new URLServiceBuilder("http", "md5.jsontest.com", "");
        url.appendParameter("text", "hola");

        WebService w = new WebService(getContext(), false);
        String json = w.jsonFromURL(url.getURL());

        try {
            JSONObject jsonObject = new JSONObject(json);
            String md5 = jsonObject.getString("md5");
            assertEquals(md5, "4d186321c1a7f0f354b297e8914ab240");
        } catch (JSONException e) {
            assertEquals(true, false);
        }
    }
}