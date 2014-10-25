package com.example.pablo.wearable;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoTicketCard extends Fragment {

    private ListItem item;

    public InfoTicketCard() {}

    public void setItem(ListItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.info_ticket_card, container, false);
        ((TextView) v.findViewById(R.id.title)).setText(item.provider);
        ((TextView) v.findViewById(R.id.discount)).setText(item.descr);
        return v;
    }
}
