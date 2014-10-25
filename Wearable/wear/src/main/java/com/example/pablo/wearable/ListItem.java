package com.example.pablo.wearable;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by pablo on 25/10/14.
 */
public class ListItem implements Serializable{

    Bitmap img;
    int id;
    String provider;
    String descr;

    public ListItem(Bitmap i, int id)
    {
        this.img = i;
        this.id = id;
        this.provider = "";
        this.descr = "";
    }
}
