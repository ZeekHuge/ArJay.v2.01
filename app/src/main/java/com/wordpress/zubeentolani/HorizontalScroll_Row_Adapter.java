package com.wordpress.zubeentolani;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.wordpress.zubeentolani.MusicDataStructure.ALBUM_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.ARTIST_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.GENRE_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.MEDIA_COLUMN;




public class HorizontalScroll_Row_Adapter extends ArrayAdapter {

    Context mContext;
    int mResource = R.layout.layout_horizontal_scroll_row;

    ArrayList<DataStructures.HorizontalScrollRowDataStructure> mDataContainingArray = new ArrayList<DataStructures.HorizontalScrollRowDataStructure>();

    public HorizontalScroll_Row_Adapter(Context context, int resource) {
        super(context, -1);
        Log.d("HoriScrollRowAdapter", "inside constructor");
        mContext = context;
        Log.d("HoriScrollRowAdapter", "leaving constructor");
    }

    public HorizontalScroll_Row_Adapter(Context context, ArrayList<DataStructures.HorizontalScrollRowDataStructure> dataContainingArray) {

        super(context, -1, dataContainingArray);

        Log.d("HoriScrollRowAdapter", "inside constructor");
        mContext = context;
        mDataContainingArray = dataContainingArray;
        Log.d("HoriScrollRowAdapter", "leaving constructor");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mDataContainingArray.size() > 0) {

            FloatingView rootView = (FloatingView) convertView;

            Log.d("HoriScrollRowAdapter",
                    "inside getView - " + rootView==null?"newView":"convertView"
            );

            if (rootView == null) {
                rootView = (FloatingView) ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(mResource, parent, false);
            }

            rootView.mColumn = mDataContainingArray.get(position).mColumn;
            TextView titleTextView = (TextView) rootView.findViewById(R.id.horizontal_scroll_row_title);
            LinearLayout contentContainingLinearLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_scroll_row_content);

            rootView.setId(position);
            titleTextView.setText(mDataContainingArray.get(position).mTitle);
            contentContainingLinearLayout.removeAllViews();

            int i =0;
            for (DataStructures.MusicCardDataStructure d : mDataContainingArray.get(position).mMusicCardsDataArray) {
                MusicCard musicCard = (MusicCard) contentContainingLinearLayout.getChildAt(i);
                if (musicCard == null){
                    contentContainingLinearLayout.addView(new MusicCard(getContext(),d));
                }else {
                    musicCard.transform(d);
                }
                i++;
            }
            Log.d("HoriScrollRowAdapter", "leaving getView(new view)");

            return rootView;
        }
        return (new View(mContext));
    }

    /**
     * UserDefined functions to get the relevant dataStructres
     *
     * @param dataContainingCursor
     * @param cursorColumn
     * @return
     */


    public static DataStructures.HorizontalScrollRowDataStructure getHorizontalDataStructure(Cursor dataContainingCursor, int cursorColumn) {

        Log.d("HoriScrollRowAdapter", "inside getHorizontalDataStructure");

        DataStructures.HorizontalScrollRowDataStructure dataStructure = new DataStructures.HorizontalScrollRowDataStructure();

        dataContainingCursor.moveToFirst();

        if (dataContainingCursor.getCount() > MusicDataStructure.HORIZONTAL_OBJECTS_LIMITER_NUMBER) {

            dataStructure.mIsExpansionPresent = true;

            for (int d = 0; d < 6; d++) {
                dataStructure.mMusicCardsDataArray.add(
                        MusicCard.getMusicCardDataStructure(
                                dataContainingCursor.getString(0), dataContainingCursor.getInt(1), cursorColumn));
                dataContainingCursor.moveToNext();
            }

        } else {
            while (!dataContainingCursor.isAfterLast()) {
                dataStructure.mMusicCardsDataArray.add(
                        MusicCard.getMusicCardDataStructure(
                                dataContainingCursor.getString(0), dataContainingCursor.getInt(1), cursorColumn));
                dataContainingCursor.moveToNext();
            }
        }


        switch (cursorColumn) {
            case ARTIST_COLUMN:
                dataStructure.mColumn = ARTIST_COLUMN;
                dataStructure.mTitle = "Artists";
                break;

            case MEDIA_COLUMN:
                dataStructure.mColumn = MEDIA_COLUMN;
                dataStructure.mTitle = "Songs";
                break;

            case GENRE_COLUMN:
                dataStructure.mColumn = GENRE_COLUMN;
                dataStructure.mTitle = "Genres";
                break;

            case ALBUM_COLUMN:
                dataStructure.mColumn = ALBUM_COLUMN;
                dataStructure.mTitle = "Albums";
                break;
        }

        dataContainingCursor.close();

        Log.d("HoriScrollRowAdapter", "leaving getHorizontalDataStructure");
        return dataStructure;
    }
}
