package com.example.pablo.wearable;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pablo on 25/10/14.
 */
public class ListItem implements Serializable{

    Bitmap img;
    int id;
    String provider;
    String descr;

    public ListItem(ArrayList<String> a){
        this.id = Integer.parseInt(a.get(0));
        this.provider = a.get(1);
        this.descr = a.get(2);
    }


    public ListItem(Bitmap i, int id, String provider, String descr)
    {
        this.img = i;
        this.id = id;
        this.provider = provider;
        this.descr = descr;
    }

    public ArrayList<String> getData(){
        ArrayList<String> a = new ArrayList<String>();
        a.add(""+id);
        a.add(provider);
        a.add(descr);
        return a;

    }
}
