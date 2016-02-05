package com.wordpress.zubeentolani;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;




public class FloatingView extends LinearLayout {


    public int mColumn = 0 ;

    public FloatingView(Context context) {
        super(context);
    }

    public FloatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void childOverScrolled(int strength){
        Log.d("FloatingView","inside childOverScrolled - " + strength);

        if(strength < 400 ){

            ((SlidingListView)getParent()).slide(strength,this);

        }else{
            ActivityLevel0.placeFragment(ActivityLevel0.PLACE_FRAGMENT_2,mColumn);
        }

        Log.d("FloatingView","leaving childOverScrolled");
    }


    public void startFloating(float strength, boolean comeBack ){




        AnimationSet animation =new AnimationSet(false);

        Animation animationGo = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,(float)-1*strength,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0
        );
        animationGo.setDuration(10);
        animationGo.setInterpolator(new DecelerateInterpolator());

        Animation animationBack = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,(float)strength,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0
        );
        animationBack.setDuration(10);
        animationBack.setStartOffset(10);
        animationBack.setInterpolator(new AccelerateInterpolator());


//        animationGo.setFillEnabled(true);
//        animationGo.setFillAfter(true);

        animation.addAnimation(animationGo);
        if (comeBack) {
            animation.addAnimation(animationBack);
        }
        this.startAnimation(animation);
    }
}
