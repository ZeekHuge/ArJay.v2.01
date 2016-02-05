package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class StretchableImageView extends ImageView {
    public StretchableImageView(Context context) {
        super(context);
    }

    public StretchableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public StretchableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    void startStretching(int y){

        this.scrollBy(-1*300, 0);
        AnimationSet animation =new AnimationSet(false);


        Animation animationGo = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,(float)0,
                Animation.RELATIVE_TO_PARENT,(float)(-1*0.5),
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0
        );
        animationGo.setDuration(5000);
        animationGo.setInterpolator(new DecelerateInterpolator());

        Animation animationBack = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,(float)0.5,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0
        );
        animationBack.setDuration(500);
        animation.setStartOffset(500);
        animationBack.setInterpolator(new AccelerateInterpolator());


//        animationGo.setFillEnabled(true);
//        animationGo.setFillAfter(true);

        animation.addAnimation(animationGo);
      //  animation.addAnimation(animationBack);

        this.startAnimation(animationGo);
    }
}
