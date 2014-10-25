/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import xml.base.webservices.WebServicesConfig;
import xml.base.webservices.enums.ConnectionErrorType;
import xml.base.webservices.enums.DataType;
import xml.base.webservices.interfaces.OnErrorListener;
import xml.support.URLServiceBuilder;
import xml.base.webservices.WebService;
import xml.ui.base.BaseActivity;

public class MyActivity extends BaseActivity<MyActivity.MyClass> implements OnErrorListener {

    private TextView myText;
    private ImageView myImage;

    public class MyClass {
        public Bitmap img;

        public String string;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myText = new TextView(this);
        myImage = new ImageView(this);

        LinearLayout myLayout = new LinearLayout(this);
        myLayout.addView(myText);
        myLayout.addView(myImage);
        setContentView(myLayout);

        updateData(BASE_UPDATE, ProgressType.PROGRESS_DIALOG);
    }

    @Override
    public MyClass loading_data(int type) {
        URLServiceBuilder url = new URLServiceBuilder("https", "gist.githubusercontent.com", "xaviml/f2e738129c2f79b3b29d/raw/a3f553b9042e9c55a8c516e36a542c9769d56cdc/testimage.json");

        WebServicesConfig config = new WebServicesConfig();

        WebService w = config.create(this);
        //w.registerTextView(myText);
        return w.objectFromURL(url.getURL(), MyClass.class);
    }

    @Override
    public void setting_data(MyClass myClass) {
        myText.setText(myClass.string);
        myImage.setImageBitmap(myClass.img);
    }

    @Override
    public void onError(ConnectionErrorType connectionErrorType, DataType dataType) {

    }

}
