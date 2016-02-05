package com.wordpress.zubeentolani;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static com.wordpress.zubeentolani.MusicDataStructure.*;



public class MusicCard extends LinearLayout{

    TextView mTitleTextView;
    ArrayList <ImageView> mImageViews = new ArrayList<ImageView>();

    public MusicCard(Context context) {
        super(context);
    }

    public MusicCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public MusicCard(Context context, DataStructures.MusicCardDataStructure dataStructure) {
        super(context);

        Log.d("MusicCard", "inside constructor");

        inflate(context, R.layout.layout_music_card, this);

        mTitleTextView = (TextView)findViewById(R.id.music_card_title);
        mTitleTextView.setText(dataStructure.mTitle);

//        mImageViews.add ((ImageView) findViewById(R.id.music_card_image2));

//        if (im == null){
//            Log.d("MusicCard","this also null !");
//        }else {
//            Log.d("MusicCard","this not null !");
//        }
        ImageView im = (ImageView)findViewById(R.id.music_card_image1);
        mImageViews.add(im);
        im = (ImageView)findViewById(R.id.music_card_image2);
        mImageViews.add(im);
        im = (ImageView)findViewById(R.id.music_card_image3);
        mImageViews.add(im);
        im = (ImageView)findViewById(R.id.music_card_image4);
        mImageViews.add(im);

        if (dataStructure.mArrayOfImages.size() > 0){
            int d = 0;
            for (Bitmap bmap : dataStructure.mArrayOfImages ) {
                if (bmap == null){

                }
                if (mImageViews.get(d) ==  null){
                    Log.d("MusicCard","imageView null at  "+d);
                }
                mImageViews.get(d) .setImageBitmap(bmap);
                mImageViews.get(d) .setVisibility(VISIBLE);
                d++;
            }
        }else {
            mImageViews.get(0) .setImageResource(context.getResources().getIdentifier(
                            "_" + dataStructure.mTitle.substring(0, 1).toLowerCase(),
                            "drawable",
                            context.getPackageName())
            );
            mImageViews.get(0) .setVisibility(VISIBLE);
        }

        Log.d("MusicCard", "leaving constructor");

    }


    public void transform(DataStructures.MusicCardDataStructure musicCardDataStructure){

        mTitleTextView.setText(musicCardDataStructure.mTitle);

        if (musicCardDataStructure.mArrayOfImages.size() > 0){
            int d = 0;
            for (Bitmap bmap : musicCardDataStructure.mArrayOfImages ) {
                mImageViews.get(d) .setImageBitmap(bmap);
                mImageViews.get(d) .setVisibility(VISIBLE);
                d++;
            }
        }else {
            mImageViews.get(0) .setImageResource(getContext().getResources().getIdentifier(
                            "_" + musicCardDataStructure.mTitle.substring(0, 1).toLowerCase(),
                            "drawable",
                            getContext().getPackageName())
            );
            mImageViews.get(0) .setVisibility(VISIBLE);
        }
    }


    /**
     * User defined functions for the dataStructures
     * @param title - the title of that is to be displayed on the Music Card
     * @param id    - the id of the titleField in the table
     * @param column- the column to which the titleField belongs (this function supports only following columns
     *              Album, Artist and Genre
     * @return - returns the MusicCardDataStructure object.
     */

    public static DataStructures.MusicCardDataStructure getMusicCardDataStructure(String title, int id, int column ){

        Log.d("MusicCard","inside getMusicCardDataStructure");

        DataStructures.MusicCardDataStructure musicCardDataStructure = new DataStructures.MusicCardDataStructure();


        musicCardDataStructure.mTitle = title != null ? title : "";
        musicCardDataStructure.mID = id;
        musicCardDataStructure.mColumn = column;

        MediaMetadataRetriever dataRetriever = new MediaMetadataRetriever();
        byte[] bitmapByte;
        Bitmap bitmap =  null;
        Cursor cursor = null;

        switch (column) {

            case ALBUM_COLUMN: {
                cursor = contentResolver.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Media.DATA},
                        String.format("%s =?", MediaStore.Audio.Media.ALBUM),
                        new String[]{title},
                        null
                );
                cursor.moveToFirst();

                dataRetriever.setDataSource(cursor.getString(0));
                bitmapByte = dataRetriever.getEmbeddedPicture();

                if (bitmapByte != null) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                        if (bitmap != null) {
                            musicCardDataStructure.mArrayOfImages.add(bitmap);
                        }
                    }
                }
            }
            break;


            case ARTIST_COLUMN: {
                cursor = contentResolver.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Media.DATA},
                        String.format("%s =?", MediaStore.Audio.Media.ARTIST),
                        new String[]{title},
                        null
                );

                cursor.moveToFirst();
                int d;
                for (d = 0; d < 4 && !cursor.isAfterLast(); cursor.moveToNext()) {
                    dataRetriever.setDataSource(cursor.getString(0));
                    bitmapByte = dataRetriever.getEmbeddedPicture();

                    if (bitmapByte != null) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                            if (bitmap != null) {
                                musicCardDataStructure.mArrayOfImages.add(bitmap);
                                d++;
                            }
                        }
                    }
                }
            }break;


            case GENRE_COLUMN: {
                cursor = contentResolver.query(
                        MediaStore.Audio.Genres.Members.getContentUri("external", id),
                        new String[]{MediaStore.Audio.Genres.Members.DATA},
                        null,
                        null,
                        null
                );

                cursor.moveToFirst();
                int d;
                for (d = 0; d < 4 && !cursor.isAfterLast(); cursor.moveToNext()) {
                    dataRetriever.setDataSource(cursor.getString(0));
                    bitmapByte = dataRetriever.getEmbeddedPicture();

                    if (bitmapByte != null) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                            if (bitmap != null) {
                                musicCardDataStructure.mArrayOfImages.add(bitmap);
                                d++;
                            }
                        }
                    }
                }
            }break;

            case MEDIA_COLUMN: {
                cursor = contentResolver.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Audio.Media.DATA},
                        MediaStore.Audio.Media.TITLE + "=?",
                        new String[]{title},
                        null
                );


                cursor.moveToFirst();

                dataRetriever.setDataSource(cursor.getString(0));
                bitmapByte = dataRetriever.getEmbeddedPicture();


                if (bitmapByte != null) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                        if (bitmap != null) {
                            musicCardDataStructure.mArrayOfImages.add(bitmap);
                        }
                    }
                }
            }break;
        }
        cursor.close();

        Log.d("MusicCard", "leaving getMusicCardDataStructure");
        return musicCardDataStructure;
    }

}
