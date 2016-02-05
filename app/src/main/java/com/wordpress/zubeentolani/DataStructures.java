package com.wordpress.zubeentolani;

import android.graphics.Bitmap;

import java.util.ArrayList;



public class DataStructures {

    public DataStructures(){}

    public static class HorizontalScrollRowDataStructure {

        int mColumn;

        String mTitle;
        boolean mIsExpansionPresent;
        ArrayList<MusicCardDataStructure> mMusicCardsDataArray = new ArrayList<MusicCardDataStructure>();

        public HorizontalScrollRowDataStructure(){}

    }




    public static class MusicCardDataStructure {

        String mTitle;
        ArrayList<Bitmap> mArrayOfImages = new ArrayList<Bitmap>();
        int mColumn;
        int mID;

        public MusicCardDataStructure(){}
    }




    public static class String_Long {

        String mTitle;
        long mId;

        public String_Long(String title, long id) {
            mTitle = title;
            mId = id;
        }
    }



    public static class String_String_Int {

        String mTitle;
        String mPath;
        int mId;

        public String_String_Int(String title, String path, int id) {
            mTitle = title;
            mPath = path;
            mId = id;
        }
    }
}




