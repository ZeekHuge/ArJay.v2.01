package com.wordpress.zubeentolani;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.wordpress.zubeentolani.MusicDataStructure.ALBUM_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.ARTIST_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.BITMAP_COMPRESSION_FACTOR;
import static com.wordpress.zubeentolani.MusicDataStructure.MEDIA_COLUMN;
import static com.wordpress.zubeentolani.MusicDataStructure.contentResolver;



public class CollectionOfAsyncTasks {


    /**
     *  The function fills up the Array adapter that is used for the list view to show data on the first
     *  fragment. The first fragment that shows data in form horizontal scroll views and music cards on it
     *
     *  The function has been used onlt in the ActivityLevel0 to fill the initial ArrayAdapter
     *
     * @param context the activity context to run the required methods on UI thread.
     * @param horizontalScrollRowAdapter - the adapter the is going to be updated and then is notified of the data change.
     */


    static void fillTheMusicDataAdapter(final Context context, final HorizontalScroll_Row_Adapter horizontalScrollRowAdapter) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("ActivityLevel0", "inside Thread 1");
                final ArrayList<DataStructures.HorizontalScrollRowDataStructure> tempList = ActivityLevel0.mMusicDataStructure.getTheDataArray();

                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        horizontalScrollRowAdapter.addAll(tempList);
                        horizontalScrollRowAdapter.notifyDataSetChanged();
                    }
                });

                Log.d("ActivityLevel0", "leaving Thread 1");
            }
        }).start();
    }


    /**
     * The following function resolves the query using the provided selection and selectionArguments
     * The supplied array adapter is the updated with the data and then is notified.
     * The data fetched is only from the Media column and collects the data about Songs Title and Data
     *
     * @param activity the activity to run the required method on UI thread
     * @param selection the argument to supply a selectionArguments for the query
     * @param detailList_row_adapter the arrayAdapter to be updated and notified about the add data.
     */


    public static void resolveMediaQuery (final Activity activity, final String selection,final String[] selectionArgs, final DetailList_Row_Adapter detailList_row_adapter  ){

        final ArrayList<DataStructures.String_Long> list = new ArrayList<DataStructures.String_Long>();

        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media._ID},
                selection,
                selectionArgs,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

                    /* the list is being added with                 */
                    /* title name and the id of the column element  */
                    /* i.e list.get(n).[0] = title of the file      */
                    /* and list.get(n).[1] = id of the column       */

            list.add( new DataStructures.String_Long(cursor.getString(0),cursor.getLong(1)));
            cursor.moveToNext();
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                detailList_row_adapter.addAll(list);
                detailList_row_adapter.notifyDataSetChanged();
            }
        });

    }


    public static void resolveMediaQuery (final Activity activity, final DetailList_Row_Adapter detailList_row_adapter, int ID , int column ){

        final ArrayList<DataStructures.String_Long> list = new ArrayList<DataStructures.String_Long>();
        Cursor cursor = null;

        if (column == MusicDataStructure.GENRE_COLUMN) {

            cursor = contentResolver.query(
                    MediaStore.Audio.Genres.Members.getContentUri("external",ID),
                    new String[]{MediaStore.Audio.Genres.Members.TITLE, MediaStore.Audio.Genres.Members._ID},
                    null,
                    null,
                    null
            );
        }

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

                    /* the list is being added with                 */
                    /* title name and the id of the column element  */
                    /* i.e list.get(n).[0] = title of the file      */
                    /* and list.get(n).[1] = id of the column       */

            list.add( new DataStructures.String_Long("sd",1));
            cursor.moveToNext();
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                detailList_row_adapter.addAll(list);
                detailList_row_adapter.notifyDataSetChanged();
            }
        });

    }


    /**
     *
     * @param activity
     * @param columnToFill
     * @param detailList_row_adapter
     * @return
     */


    public static Thread fillTheMusicColumn(final Activity activity, final int columnToFill, final DetailList_Row_Adapter detailList_row_adapter) {

        Thread runningThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Cursor cursor = ActivityLevel0.mMusicDataStructure.getCursor(columnToFill);
                final ArrayList<DataStructures.String_Long> list = new ArrayList<DataStructures.String_Long>();

                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                    /* the list is being added with                 */
                    /* title name and the id of the column element  */
                    /* i.e list.get(n).[0] = title of the file      */
                    /* and list.get(n).[1] = id of the column       */

                    list.add(new DataStructures.String_Long(
                                    cursor.getString(0),
                                    cursor.getLong(1))
                    );
                    cursor.moveToNext();
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        detailList_row_adapter.addAll(list);
                        detailList_row_adapter.notifyDataSetChanged();
                    }
                });

            }
        });

        runningThread.start();
        return runningThread;
    }


    /**
     *
     *
     * @param columnNumber
     * @param id
     * @param imageViews
     * @return
     */



    public static Thread fillTheImageView_Dash(final int columnNumber, final long id, final ImageView[] imageViews) {


        Thread runningThread = new Thread(new Runnable() {
            @Override
            public void run() {

                MediaMetadataRetriever dataRetriever = new MediaMetadataRetriever();

                byte[] bitmapByte;
                Bitmap bitmap = null;
                Cursor cursor = null;
                BitmapFactory.Options options = new BitmapFactory.Options();

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
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    if (bitmapByte != null) {
                        options.inSampleSize = 6;
                        bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length, options);
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                            if (bitmap != null) {
                                final Bitmap finalBitmap = bitmap;
                                final int finalD = d;
                                imageViews[finalD].post(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageViews[finalD].setImageBitmap(finalBitmap);
                                        imageViews[finalD].setVisibility(View.VISIBLE);
                                    }
                                });
                                d++;
                            }
                        }
                    }
                }
            }
        });
        runningThread.start();
        return runningThread;
    }


    /**
     *
     * @param columnNumber
     * @param title
     * @param imageViews
     * @return
     */




    public static Thread fillTheImageView_Dash(final int columnNumber, final String title, final ImageView[] imageViews) {


        final Thread runningThread = new Thread(new Runnable() {
            @Override
            public void run() {

                MediaMetadataRetriever dataRetriever = new MediaMetadataRetriever();
                byte[] bitmapByte;
                Bitmap bitmap = null;
                Cursor cursor = null;

                BitmapFactory.Options options = new BitmapFactory.Options();

                switch (columnNumber) {

                    case ALBUM_COLUMN: {
                        Log.i("CollectionOfAsyncTasks", "in AlbumColumn");
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
                        ByteArrayOutputStream out = new ByteArrayOutputStream();



                        Log.i("CollectionOfAsyncTasks", "is bitmapByte null - " + (bitmapByte == null ? "yes" : "no"));
                        if (bitmapByte != null) {

                            options.inSampleSize = 6;
                            bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length, options);
                            Log.i("CollectionOfAsyncTasks", "height*Width - " + options.outHeight + "*" + options.outWidth);
                            Log.i("CollectionOfAsyncTasks", "is 1.bitmap null - " + (bitmap == null ? "yes" : "no"));
                            if (bitmap != null) {
                                bitmap.compress(Bitmap.CompressFormat.PNG, MusicDataStructure.BITMAP_COMPRESSION_FACTOR_Smaller, out);
                                bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                                Log.i("CollectionOfAsyncTasks", "is 2.bitmap null - " + (bitmap == null ? "yes" : "no"));
                                if (bitmap != null ) {

                                    final Bitmap finalBitmap = bitmap;
                                    imageViews[0].post(new Runnable() {
                                        @Override
                                        public void run() {
                                            imageViews[0].setImageBitmap(finalBitmap);
                                            imageViews[0].startAnimation(
                                                    AnimationUtils.loadAnimation(imageViews[0].getContext(),
                                                            R.anim.fade_in_animation)
                                            );
                                            imageViews[0].setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                            }
                        }
                    }
                    break;


                    case ARTIST_COLUMN: {
                        Log.i("CollectionOfAsyncTasks", "in ArtistColumn");
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
                            ByteArrayOutputStream out = new ByteArrayOutputStream();

                            if (bitmapByte != null) {
                                options.inSampleSize = 6;
                                bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length, options);
                                if (bitmap != null) {
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                                    bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                                    if (bitmap != null) {
                                        final Bitmap finalBitmap = bitmap;
                                        final int finalD = d;
                                        imageViews[finalD].post(new Runnable() {
                                            @Override
                                            public void run() {
                                                imageViews[finalD].setImageBitmap(finalBitmap);
                                                imageViews[finalD].setVisibility(View.VISIBLE);
                                            }
                                        });
                                        d++;
                                    }
                                }
                            }
                        }
                    }
                    break;


                    case MEDIA_COLUMN: {
                        Log.i("CollectionOfAsyncTasks", "in MediaColumn - "+ title);
                        cursor = contentResolver.query(
                                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                new String[]{MediaStore.Audio.Media.DATA},
                                MediaStore.Audio.Media.TITLE + "=?",
                                new String[]{title},
                                null
                        );


                        if (cursor.moveToFirst());
                        dataRetriever.setDataSource(cursor.getString(0));
                        bitmapByte = dataRetriever.getEmbeddedPicture();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();


                        if (bitmapByte != null) {

                            options.inSampleSize = 6;
                            bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length, options);
                            if (bitmap != null) {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_COMPRESSION_FACTOR, out);
                                bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                                if (bitmap != null) {
                                    final Bitmap finalBitmap = bitmap;
                                    imageViews[0].post(new Runnable() {
                                        @Override
                                        public void run() {
                                            imageViews[0].setImageBitmap(finalBitmap);
                                            imageViews[0].setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                            }
                        }
                    }
                    break;
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        });

        runningThread.start();

        return runningThread;
    }
}


