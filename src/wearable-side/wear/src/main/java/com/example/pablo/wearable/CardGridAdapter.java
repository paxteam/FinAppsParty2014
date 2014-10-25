package com.example.pablo.wearable;

        import android.app.Fragment;
        import android.app.FragmentManager;
        import android.support.wearable.view.FragmentGridPagerAdapter;

public class CardGridAdapter extends FragmentGridPagerAdapter {

    ListItem item;

    public CardGridAdapter(ListItem item, FragmentManager fm) {
        super(fm);
        this.item = item;
    }

    @Override
    public int getColumnCount(int arg0) {
        return 1;
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public Fragment getFragment(int i, int i2) {
        if(i == 0) {

            InfoTicketCard i3 = new InfoTicketCard();
            i3.setItem(item);
            return i3;
        } else{
            ButtonTicketCard b = new ButtonTicketCard();
            b.setItem(item);
            return b;
        }
    }
}