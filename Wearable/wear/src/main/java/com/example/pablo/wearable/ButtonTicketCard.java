package com.example.pablo.wearable;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ButtonTicketCard extends Fragment {

    private ListItem item;

    public ButtonTicketCard() {}

    public void setItem(ListItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.button_ticket_card, container, false);
    }

}
