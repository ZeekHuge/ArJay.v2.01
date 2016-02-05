package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;



public class SlidingListView extends ListView {

    public static final float MAX_MAIN_SLIDING_PERCENTAGE =(float) 0.7;
    public static final float MIN_MAIN_SLIDING_PERCENTAGE =(float) 0.2;
    public static final float MAX_SUPPORTING_SLIDING_PERCENTAGE =(float) 0.5;
    public static final float MIN_SUPPORTING_SLIDING_PERCENTAGE =(float) 0.1;

    public static final float PER_UNIT_MAIN_SLIDING_PERCENTAGE = (float) (MAX_MAIN_SLIDING_PERCENTAGE - MIN_MAIN_SLIDING_PERCENTAGE)/100 ;
    public static final float PER_UNIT_SUPPORTING_SLIDING_PERCENTAGE = (float) (MAX_SUPPORTING_SLIDING_PERCENTAGE - MIN_SUPPORTING_SLIDING_PERCENTAGE)/100 ;

    public SlidingListView(Context context) {
        super(context);

    }

    public SlidingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }








    public void slide (int strength , FloatingView view){

        for (int i = 0 ; i < this.getCount(); i++) {
            FloatingView v = (FloatingView) this.getChildAt(i);
            if (v != null && v != view) {

                v.setTranslationX( strength * -1);
            }
        }
    }


    public void slideBack (int strength, int position){

        Log.d("SlidingLisView", "inside animator ");

        for (int i = 0,j = 4 - position ; i < this.getCount(); i++) {
            if (this.getChildAt(i) != null){
                if (i == position) {
                    ((FloatingView) this.getChildAt(i)).startFloating((float) (MAX_MAIN_SLIDING_PERCENTAGE),true);
                }else {
                    ((FloatingView) this.getChildAt(i)).startFloating((float) (MAX_SUPPORTING_SLIDING_PERCENTAGE),true);
                }
            }
        }
        Log.d("SlidingListView", "leaving animator");

    }


    public void slideOut (int position){
        /*
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,(float)-MAX_MAIN_SLIDING_PERCENTAGE,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0
        );
        animation.setDuration(500);
        animation.setStartOffset(500);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);

        slideBack(1,position);
        this.startAnimation(animation);
        */

        for (int i = 0,j = 4 - position ; i < this.getCount(); i++) {
            if (this.getChildAt(i) != null){
                if (i == position) {
                    ((FloatingView) this.getChildAt(i)).startFloating((float) (MAX_MAIN_SLIDING_PERCENTAGE),false);
                }else {
                    ((FloatingView) this.getChildAt(i)).startFloating((float) (MAX_SUPPORTING_SLIDING_PERCENTAGE),false);
                }
            }
        }

    }

}
