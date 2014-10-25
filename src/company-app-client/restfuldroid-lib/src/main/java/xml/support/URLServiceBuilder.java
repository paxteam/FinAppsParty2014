/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.support;

import android.net.Uri;

public class URLServiceBuilder {


    private Uri.Builder url;

    public URLServiceBuilder(String protocol, String domain, String service) {
        url = new Uri.Builder();
        url.scheme(protocol)
                .authority(domain)
                .appendEncodedPath(service);
    }

    public URLServiceBuilder appendParameter(String key, String value) {
        url.appendQueryParameter(key,value);

        return this;

    }

    public String getURL() {
        return url.build().toString();
    }
}
