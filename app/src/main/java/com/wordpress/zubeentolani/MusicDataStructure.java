package com.wordpress.zubeentolani;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;



public class MusicDataStructure {

    public static final int HORIZONTAL_OBJECTS_LIMITER_NUMBER = 10;
    public static final int BITMAP_COMPRESSION_FACTOR = 10;
    public static final int BITMAP_COMPRESSION_FACTOR_Smaller = 100;


    public static final int PARTIAL_LIST   =10;
    public static final int FULL_LIST      =11;

    public static final int MEDIA_COLUMN   =0;
    public static final int ALBUM_COLUMN   =1;
    public static final int ARTIST_COLUMN  =2;
    public static final int GENRE_COLUMN   =3;



    public static ContentResolver contentResolver;




    public MusicDataStructure(ContentResolver givenContentResolver){
        Log.d("MusicDataStructure", "inside constructor");

        contentResolver = givenContentResolver;

        Log.d("MusicDataStructure", "leaving constructor");
    }






    public ArrayList<DataStructures.HorizontalScrollRowDataStructure> getTheDataArray(){

        Log.d("MusicDataStructure","inside getTheDataArray");

        ArrayList<DataStructures.HorizontalScrollRowDataStructure> arrayList = new ArrayList<DataStructures.HorizontalScrollRowDataStructure>();

        Cursor cursor = getCursor(MEDIA_COLUMN);
        if (cursor != null){
            arrayList.add(com.wordpress.zubeentolani.HorizontalScroll_Row_Adapter.getHorizontalDataStructure(cursor, MEDIA_COLUMN));
        }
        cursor = getCursor(ALBUM_COLUMN);
        if (cursor!=null){
            arrayList.add(com.wordpress.zubeentolani.HorizontalScroll_Row_Adapter.getHorizontalDataStructure(cursor, ALBUM_COLUMN));
        }
        cursor = getCursor(ARTIST_COLUMN);
        if (cursor!=null){
            arrayList.add(com.wordpress.zubeentolani.HorizontalScroll_Row_Adapter.getHorizontalDataStructure(cursor, ARTIST_COLUMN));
        }
        cursor = getCursor(GENRE_COLUMN);
        if (cursor!=null){
            arrayList.add(com.wordpress.zubeentolani.HorizontalScroll_Row_Adapter.getHorizontalDataStructure(cursor, GENRE_COLUMN));
        }

        cursor.close();

        Log.d("MusicDataStructure", "leaving getTheDataArray");
        return arrayList;
    }





    public  Cursor getCursor(int columnToFetch){

        Log.d("MusicDataStructure", "inside getCursor");

        Cursor cursorList = null;

        switch (columnToFetch) {

            case MEDIA_COLUMN:
                cursorList = contentResolver.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media._ID
                        },
                        null,
                        null,
                        "lower (" + MediaStore.Audio.Media.DISPLAY_NAME + ") ASC"
                );
            break;

            case ALBUM_COLUMN:
                cursorList = contentResolver.query(
                        MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums._ID},
                        null,
                        null,
                        "lower (" + MediaStore.Audio.Albums.ALBUM + ") ASC"
                );
            break;

            case ARTIST_COLUMN:
                cursorList = contentResolver.query(
                        MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Artists.ARTIST, MediaStore.Audio.Artists._ID},
                        null,
                        null,
                        "lower (" + MediaStore.Audio.Artists.ARTIST + ") ASC"
                );
            break;

            case GENRE_COLUMN:

                cursorList = contentResolver.query(
                        MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Genres.NAME, MediaStore.Audio.Genres._ID},
                        null,
                        null,
                        "lower (" + MediaStore.Audio.Genres.NAME + ") ASC"
                );
                if (cursorList.moveToFirst()){
                    do {


                    }while(cursorList.moveToNext());
                }
            break;
        }

        Log.d("MusicDataStructure", "leaving getCursor");
        return cursorList;
    }

}


