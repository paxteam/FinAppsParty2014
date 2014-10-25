package com.example.pablo.wearable;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by pablo on 25/10/14.
 */
public class WearableListItemLayout extends LinearLayout
        implements WearableListView.Item {

    private ImageView mCircle;
    private float mScale;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        mCircle = (ImageView) findViewById(R.id.circle);
        //mName = (TextView) findViewById(R.id.name);
    }

    // Provide scaling values for WearableListView animations
    @Override
    public float getProximityMinValue() {
        return 1f;
    }

    @Override
    public float getProximityMaxValue() {
        return 1.6f;
    }

    @Override
    public float getCurrentProximityValue() {
        return mScale;
    }

    // Scale the icon for WearableListView animations
    @Override
    public void setScalingAnimatorValue(float scale) {
        mScale = scale;
        if (scale > 1) {
            mCircle.setScaleX(scale * 1.2f);
            mCircle.setScaleY(scale * 1.2f);
            return;
        }
        mCircle.setScaleX(scale * .8f);
        mCircle.setScaleY(scale * .8f);
    }

    // Change color of the icon, remove fading from the text
    @Override
    public void onScaleUpStart() {
        //mName.setAlpha(1f);
        mCircle.setAlpha(1f);
    }

    // Change the color of the icon, fade the text
    @Override
    public void onScaleDownStart() {
        mCircle.setAlpha(.5f);
        //mName.setAlpha(mFadedTextAlpha);
    }
}