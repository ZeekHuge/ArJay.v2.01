package com.wordpress.zubeentolani;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.LinearLayout;



public class ListRowExpander_Animation extends Animation {

    private View mAnimatedView;
    AdapterView<?> mParent ;
    private LinearLayout.LayoutParams mViewLayoutParams;
    private int mBottomMarginStart, mBottomMarginEnd;
    private boolean mIsVisibleAfter = false;
    private boolean mWasEndedAlready = false;

    /**
     * Initialize the animation
     * @param view The layout we want to animate
     * @param duration The duration of the animation, in ms
     */

    public ListRowExpander_Animation(View view, int duration, AdapterView<?> parent) {

        setDuration(duration);

        mAnimatedView = view;
        mViewLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        mParent = parent;

        mIsVisibleAfter = (view.getVisibility() == View.VISIBLE);

        mBottomMarginStart = mViewLayoutParams.bottomMargin ;

        mBottomMarginEnd = parent.getHeight() - view.getHeight();

//        mBottomMarginEnd = (mBottomMarginStart == 0 ? (0 - view.getHeight()) : 0) ;

        view.setVisibility(View.VISIBLE);
    }



    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        if (interpolatedTime < 1.0f) {


            mViewLayoutParams.bottomMargin = mBottomMarginStart
                    + (int) ((mBottomMarginEnd - mBottomMarginStart) * interpolatedTime);

            Log.d("Deb","interpT - "+ interpolatedTime + "  -  " + ((ThreadRecorder_View)mAnimatedView.getParent()).getY());


            mAnimatedView.requestLayout();


        } else if (!mWasEndedAlready) {
            mViewLayoutParams.bottomMargin = mBottomMarginEnd;

            mAnimatedView.requestLayout();

            if (mIsVisibleAfter) {
//                mAnimatedView.setVisibility(View.GONE);
            }
            mWasEndedAlready = true;
        }
    }

}