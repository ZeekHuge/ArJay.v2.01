package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;




public class OverScroll_ScrollView extends HorizontalScrollView {

    int a = 0;
    int b =0;
    int mScrollRangeX = 1000000;

    public OverScroll_ScrollView(Context context) {
        super(context);

    }

    public OverScroll_ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public OverScroll_ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
//        Log.d("Scroll-on",scrollX  +" "+scrollY+" "+ clampedX +" "+clampedY );

//        clampedX = false;

//        ((this.getChildAt(0)).findViewById(R.id.imgView)).
//                setLayoutParams(
//                        new LinearLayout.LayoutParams(
//                                0,
//                                0
//                        )
//                );

        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);

//        ((this.getChildAt(0)).findViewById(R.id.imgView)).
//                setLayoutParams(
//                        new LinearLayout.LayoutParams(
//                                Math.max(0, scrollX - mScrollRangeX),
//                                ViewGroup.LayoutParams.MATCH_PARENT
//                        )
//                );


    }

    @Override
    public int getMaxScrollAmount() {
        return super.getMaxScrollAmount() -1000 ;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

//        Log.d("Scroll","got this:");
//        Log.d("Scroll", deltaX + " " + deltaY + "  " + scrollX + "  " + scrollY + "  " + scrollRangeX + "  " + scrollRangeY + "  " + maxOverScrollX + "  " + maxOverScrollY + "  " + isTouchEvent);

//        int number = 500;
//        mScrollRangeX = Math.min(mScrollRangeX,scrollRangeX);

//        mScrollRangeX = scrollRangeX;

//        scrollRangeX += 1000;
        maxOverScrollX += 500;


//        scrollX += 500;


//        ((FloatingView)getParent()).childOverScrolled(1);
        if (scrollX + deltaX > scrollRangeX){
            if (scrollX + deltaX > scrollRangeX + 100)
            ((FloatingView)getParent()).childOverScrolled(
                    (scrollX + deltaX - scrollRangeX - 100)
            );

        }

//        isTouchEvent = true;
/*
        int number = 200;
        if (scrollRangeX <= (scrollX +deltaX ) ) {
            if (!isTouchEvent) {
                number = 150;
            }

            maxOverScrollX += number;

            Log.d("Scroll","sending:");
            Log.d("Scroll", deltaX + " " + deltaY + "  " + scrollX + "  " + scrollY + "  " + scrollRangeX + "  " + scrollRangeY + "  " + maxOverScrollX + "  " + maxOverScrollY + "  " + isTouchEvent);

            return super.overScrollBy(
                    deltaX, deltaY,
                    scrollX, scrollY,
                    scrollRangeX, scrollRangeY,
                    maxOverScrollX + number,
                    maxOverScrollY,
                    isTouchEvent
            );
        }
*/


//        Log.d("Scroll","sending:");


        boolean b = false;
//        if (scrollX  + deltaX < scrollRangeX - 500) {

         b = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
//        }



        Log.d("Scroll", deltaX + " " + deltaY + "  " + scrollX + "  " + scrollY + "  " + scrollRangeX + "  " + scrollRangeY + "  " + maxOverScrollX + "  " + maxOverScrollY + "  " + isTouchEvent + " " + b );
        return b;


    }


}
