package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;


public class SlidingFrameLayout extends FrameLayout {

    float mYTransitionValue = 0;
    SlidingListView mListView;

    ThreadRecorder_View mClickedChildView = null;
    ViewTreeObserver.OnPreDrawListener preDrawListener = null;

    public SlidingFrameLayout(Context context) {

        super(context);
        mListView = (SlidingListView) findViewById(R.id.detail_list_sliding_list_view);

    }

    public SlidingFrameLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        mListView = (SlidingListView) findViewById(R.id.detail_list_sliding_list_view);
    }

    public SlidingFrameLayout(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        mListView = (SlidingListView) findViewById(R.id.detail_list_sliding_list_view);
    }


    public void setAnimationYTransition(final float value) {

        Log.d("SlidingFrameLayout", "inside setAnimationYTransition");

        float x = this.getWidth();
        mYTransitionValue = value;

        if (x == 0) {
            if (preDrawListener == null) {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
                        setAnimationYTransition(value);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
        } else {
            setTranslationX(x * value);
        }

        Log.d("SlidingFrameLayout", "leaving setAnimationYTransition");
    }

    public float getAnimationYTransition() {
        return mYTransitionValue;
    }


    public void setAnimateMargin(final float value) {

        Log.d("SlidingFrameLayout","inside setAnimateMargin value - " + value);

        if (mClickedChildView != null) {
            mListView.scrollTo(0, (int) (mClickedChildView.getY() * (value)));
            mClickedChildView.animateMargin(
                    (int) ((mListView.getHeight() - mClickedChildView.getHeight()) * value)
            );
        }

        Log.d("SlidingFrameLayout","leaving setAnimateMargin");
    }

    public float getAnimateMargin() {
        return 1;
    }

    public void childIsClicked(View view, SlidingListView listView) {

        Log.d("SlidingFrameLayout","inside childIsClicked");
        mClickedChildView = (ThreadRecorder_View) view;
        mListView = listView;
        Log.d("SlidingFrameLayout","leaving childIsClicked");
    }


    public void setHeight(final float value ){
        if (value < 0.9){
            this.setVisibility(GONE);
        }else{
            this.setVisibility(VISIBLE);
        }
    }



}
