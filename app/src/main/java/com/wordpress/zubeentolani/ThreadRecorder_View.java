package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ThreadRecorder_View extends LinearLayout {

    int mColumnNumber;
    long mId;

    String mTitle;
    String mPath;


    /**
     *
     */
    TextView tx;
    /**
     *
     */

    Thread mRunningThread;
    ImageView[] mImageViews = new ImageView[4];
    LinearLayout mContainer ;
    LinearLayout.LayoutParams mContainerLayoutParams;

    public ThreadRecorder_View(Context context) {
        super(context);
    }

    public ThreadRecorder_View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreadRecorder_View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    public void init(int columnNumber, String title, long id) {
        init(columnNumber, title, id, null);
    }


    public void init(int columnNumber, String title) {
        init(columnNumber, title, -1, null);
    }

    public void init(int columnNumber, String title, String path) {
        init(columnNumber,title,-1,path);
    }


    public void init(int columnNumber, String title, long id, String path) {

        mColumnNumber = columnNumber;
        mTitle = title;
        mId = id;
        mPath = path;

//        tx = (TextView) this.findViewById(R.id.invis);
        mImageViews[0] = (ImageView) this.findViewById(R.id.detail_list_row_image1);
        mImageViews[1] = (ImageView) this.findViewById(R.id.detail_list_row_image2);
        mImageViews[2] = (ImageView) this.findViewById(R.id.detail_list_row_image3);
        mImageViews[3] = (ImageView) this.findViewById(R.id.detail_list_row_image4);

        mContainer = (LinearLayout) this.findViewById(R.id.detail_list_row_container);
        mContainerLayoutParams = (LayoutParams) mContainer.getLayoutParams();
        ((TextView) this.findViewById(R.id.detail_list_row_text_view)).setText(mTitle);

        getImages();

    }



    public void getImages() {

        if (mId == -1) {
            this.mRunningThread = CollectionOfAsyncTasks.fillTheImageView_Dash(
                    mColumnNumber,
                    mTitle,
                    mImageViews
            );
        }else {
            this.mRunningThread = CollectionOfAsyncTasks.fillTheImageView_Dash(
                    mColumnNumber,
                    mId,
                    mImageViews
            );
        }
    }


    public void animateMargin (int height){

        Log.d("ThreadRecorder_View","inside animateMargin");

        mContainerLayoutParams.bottomMargin = height;
        mContainer.requestLayout();

        Log.d("ThreadRecorder_View", "leaving animateMargin");
    }


}
