package com.wordpress.zubeentolani;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;




public class FinalMediaList_Row_Adapter extends ArrayAdapter {


    private int mResource = R.layout.layout_detail_list_row;
    int mColumn ;
    Context mContext;
    ArrayList<DataStructures.String_String_Int> mDataContainingArray = new ArrayList<DataStructures.String_String_Int>();

    public FinalMediaList_Row_Adapter(Context context, int column, ArrayList<DataStructures.String_String_Int> dataContainingArray) {
        super(context, -1,dataContainingArray );
        mDataContainingArray = dataContainingArray;
        mColumn= column;
        mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mDataContainingArray.size() > 0) {

            Log.d("FinMediaListRowAdapter", "inside getView");

            ThreadRecorder_View rootView = (ThreadRecorder_View) (((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(mResource, parent, false));

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

            Log.d("FinMediaListRowAdapter", "leaving getView");

            return rootView;
        }

        Log.d("FinMediaListRowAdapter", "leaving getView");
        return (new View(mContext));
    }
}

