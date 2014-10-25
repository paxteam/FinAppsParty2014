package com.example.pablo.wearable;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.CardScrollView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.Gravity;
import android.widget.TextView;

import java.io.Serializable;

public class MyActivity extends Activity
        implements WearableListView.ClickListener {

    // Sample dataset for the list
    ListItem [] elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //THIS IS A STUB
        ListItem l1 = new ListItem(BitmapFactory.decodeResource(getResources(),R.drawable.portaventura),1, "Port Aventura", "Descompte de un 40%");
        ListItem l2 = new ListItem(BitmapFactory.decodeResource(getResources(),R.drawable.nostrum),2, "Nostrum", "Menú per 5.95€");
        ListItem l3 = new ListItem(BitmapFactory.decodeResource(getResources(),R.drawable.tibidabo),2, "Tibidabo", "2x1 En les entrades al parc");
        ListItem l4 = new ListItem(BitmapFactory.decodeResource(getResources(),R.drawable.cocacola),2, "CocaCola", "Regalo de una taza por la compra de un pack de 20 latas");

        elements = new ListItem[]{l1 , l2, l3, l4 };


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub watchViewStub) {
                // Get the list component from the layout of the activity
                WearableListView listView =
                        (WearableListView) stub.findViewById(R.id.wearable_list);

                // Assign an adapter to the list
                listView.setAdapter(new Adapter(MyActivity.this, elements));

                // Set a click listener
                listView.setClickListener(MyActivity.this);
            }

        });

    }

    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {

        Integer tag = (Integer) v.itemView.getTag();
        ListItem i = elements[v.getPosition()];
        Intent intent = new Intent(this, MyActivity2.class);
        intent.putExtra("item", (Serializable) i.getData());
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() {

    }
}
