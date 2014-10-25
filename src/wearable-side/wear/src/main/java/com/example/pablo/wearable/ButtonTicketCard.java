package com.example.pablo.wearable;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ButtonTicketCard extends Fragment {

    private ListItem item;
    private View v;
    private Button b;
    public ButtonTicketCard() {}

    public void setItem(ListItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.button_ticket_card, container, false);
        b = (Button) v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //THIS IS A STUB
                ImageView im = (ImageView) v.findViewById(R.id.qr);
                im.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.qr_img));
                b.setVisibility(View.GONE);
            }
        });
        return v;
    }

}
