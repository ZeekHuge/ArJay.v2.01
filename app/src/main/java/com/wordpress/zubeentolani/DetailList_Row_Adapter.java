package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;




public class DetailList_Row_Adapter extends ArrayAdapter {


    Context mContext;
    int mColumn;
    ArrayList<DataStructures.String_Long> mDataContainingArray = new ArrayList<DataStructures.String_Long>();

    ImageView[] imageViews = new ImageView[4];

    public DetailList_Row_Adapter(Context context, int column, ArrayList<DataStructures.String_Long> dataContainingArray) {
        super(context, -1, dataContainingArray);
        mContext = context;
        mColumn = column;
        mDataContainingArray = dataContainingArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mDataContainingArray.size() > 0) {

            Log.d("DetailList_Row_Adapter", "inside getView");

            ThreadRecorder_View rootView = (ThreadRecorder_View) (((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_detail_list_row, parent, false));

            if (mColumn != MusicDataStructure.GENRE_COLUMN) {
                rootView.init(
                        mColumn,
                        mDataContainingArray.get(position).mTitle
                );
            } else {
                rootView.init(
                        mColumn,
                        mDataContainingArray.get(position).mTitle,
                        mDataContainingArray.get(position).mId
                );
            }

            Log.d("DetailList_Row_Adapter", "leaving getView");
            return rootView;
        }
        Log.d("DetailList_Row_Adapter", "leaving getView");
        return (new View(mContext));
    }
}
