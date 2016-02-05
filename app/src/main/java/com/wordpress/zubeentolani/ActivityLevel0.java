package com.wordpress.zubeentolani;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ActivityLevel0 extends Activity implements AllMusicFragment.OnFragmentInteractionListener
        , DetailListFragment.OnFragmentInteractionListener
        , JustMedia_Fragment.OnFragmentInteractionListener {


    private final String  BROADCAST_areYouAlive = "com.wordpress.zubeentolani.SERVICE_areYouAlive";
    private final String  BROADCAST_iAmAlive = "com.wordpress.zubeentolani.SERVICE_iAmAlive";

    public static final int PLACE_FRAGMENT_1 = 1;
    public static final int PLACE_FRAGMENT_2 = 2;
    public static final int PLACE_FRAGMENT_3 = 3;


    public static MusicDataStructure mMusicDataStructure;
    public static HorizontalScroll_Row_Adapter mHorizontalScrollRowAdapter;

    public ArrayList<DataStructures.HorizontalScrollRowDataStructure> mHorizontalScrollRowDataStructureArray = new ArrayList<DataStructures.HorizontalScrollRowDataStructure>();
    public static FragmentManager mFragmentManager;

    private IntentFilter mIntentFilter;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BROADCAST_iAmAlive)){
                Log.d("ActivityLevel0","the service is alive");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BROADCAST_iAmAlive);

        registerReceiver(mBroadcastReceiver, mIntentFilter);

        startService(new Intent(this,PlayMyMusic_Service.class));



        LinearLayout ln = (LinearLayout) findViewById(R.id.test_layout);

        MusicPlayer mp = new MusicPlayer(this);

        ln.addView(mp.getSmallView());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("ActivityLevel0", "sending broadcast");
                sendBroadcast(new Intent(BROADCAST_areYouAlive));
            }
        }, 1000);








        /*

        MusicDataStructure mds = new MusicDataStructure(getContentResolver());

        Cursor cursorList =mds.getCursor(MusicDataStructure.GENRE_COLUMN);

        if (cursorList != null) {
            if (cursorList.moveToFirst()) {

                do {
                    Log.d("Deb",
                            String.format(">>>>>>>> %25s  %3d", cursorList.getString(0), cursorList.getLong(1)));

                    Log.d("Deb"," URI - " + MediaStore.Audio.Genres.Members.getContentUri("external", cursorList.getLong(1)).toString());

                    Cursor list = getContentResolver().query(
                            MediaStore.Audio.Genres.Members.getContentUri("external", cursorList.getLong(1)),
                            new String[]{MediaStore.Audio.Genres.Members.TITLE, MediaStore.Audio.Genres.Members.AUDIO_ID,MediaStore.Audio.Genres.Members.GENRE_ID},
                            null,
                            null,
                            "lower (" + MediaStore.Audio.Genres.Members.TITLE + ") ASC"
                    );
                    if (list.moveToFirst())
                    do {
//                        Log.d("Deb",
//                                String.format(">>> %25s  %3d %3d", list.getString(0), list.getLong(1), list.getLong(2)));
                        Cursor st = getContentResolver().query(
                                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                new String[]{MediaStore.Audio.Media.DATA},
                                String.format("%s=?",MediaStore.Audio.Media.TITLE),
                                new String[]{list.getString(0)},
                                null
                        );
                        st.moveToFirst();
//                        Log.d("Deb", ">> " + st.getString(0));
                        st.close();
                    } while (list.moveToNext());
                    list.close();


                } while (cursorList.moveToNext());
                cursorList.close();

                return;
            }
        }

        Log.d("deb", "i returned back");


*/



        /*****************************************************************************/
        /*****************************************************************************/
        /*****************************************************************************/



/*


        mMusicDataStructure = new MusicDataStructure(getContentResolver());

        mHorizontalScrollRowAdapter = new HorizontalScroll_Row_Adapter(
                this,
                new ArrayList<DataStructures.HorizontalScrollRowDataStructure>()
        );
        CollectionOfAsyncTasks.fillTheMusicDataAdapter(this, mHorizontalScrollRowAdapter);

        mFragmentManager = getFragmentManager();
        placeFragment(PLACE_FRAGMENT_1);


*/


//        mListView = (SlidingListView) findViewById(R.id.local_music_all_list_view);
//        final StretchableImageView mStretchableImageView = (StretchableImageView) findViewById(R.id.local_music_stretch_image);

//        ViewTreeObserver vto = mStretchableImageView.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mStretchableImageView.scrollBy(-1 * 300, 0);
//            }
//        });
//


//TableLayout tbl = (TableLayout) findViewById(R.id.tb_l);


//        ImageView im = (ImageView) findViewById(R.id.im);

//        MusicDataStructure mds = new MusicDataStructure(this.getContentResolver());

//        Log.d("reco","here 1");
//        Cursor cursor = this.getContentResolver().query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media._ID},
//                null,
//                null,
//                "lower (" + MediaStore.Audio.Media.DISPLAY_NAME + ") ASC"
//        );
//
//        Log.d("reco","here 2");
//
//        String title = "";
//
//        if (cursor !=  null) {
//            cursor.moveToFirst();
//            title = cursor.getString(0);
//        }else{
//            Log.d("reco","Null above");
//        }
//
//        Log.d("reco","here 3");
//
//        cursor = this.getContentResolver().query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                new String[]{MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DATA},
//                MediaStore.Audio.Media.TITLE + " =? ",
//                new String[]{title},
//                null
//        );
//        cursor.moveToFirst();
//
//        Log.d("reco","here 4");
//
//        if (cursor != null){
//
//            Log.d("reco",cursor.getString(0) +" --- "+ cursor.getCount());
//        }else{
//            Log.d("reco","Null here");
//        }
//
//        Log.d("reco","here 5");

//        cursorList.moveToFirst();
//        cursorList.moveToNext();
//        cursorList.moveToNext();


//        Log.d("reco", cursorList.getString(0) + "-----" + cursorList.getString(1));

//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

//        while(!cursorList.isAfterLast()) {
//            mmr.setDataSource(cursorList.getString(1));
//            byte[] bt = mmr.getEmbeddedPicture();
//            if (bt != null) {
//                Log.d("reco", cursorList.getString(0) + "-----" + cursorList.getString(1));
//                Bitmap bmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
//                if (bmap != null) {
//                    im.setImageBitmap(bmap);
//                } else {
//                    Log.d("res", "bmap null");
//                }
//                break;
//            }
//            cursorList.moveToNext();
//        }
//
//            mmr.release();


//
//        Log.d("resolved",cursorList.getString(0)+"---"+(cursorList.getInt(1));
//        File fs = new File(MediaStore.Audio.Albums.)
//        Log.d("Resolved",((Uri) MediaStore.Audio.Albums.getContentUri(MediaStore.Audio.Albums.ALBUM)).toString());
//        Log.d("resolved",((Uri) MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI).toString());
//        Log.d("resolved",((Uri) MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI).getPath());

//        cursor.close();

    }


    @Override
    protected void onResume() {
        registerReceiver(mBroadcastReceiver, mIntentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    //To call 3rd fragment (justMedia list)
    public static void placeFragment(int fragmentIndicator, int invokerColumn, String title, int id) {
        placeFragment(fragmentIndicator, -1, invokerColumn, title, id);
    }


    //To call 2nd fragment (detail list)
    public static void placeFragment(int fragmentIndicator, int columnToOpen) {
        placeFragment(fragmentIndicator, columnToOpen, -1, null, -1);
    }


    //To call first fragment (All music fragment)
    public static void placeFragment(int fragmentIndicator) {
        placeFragment(fragmentIndicator, -1, -1, null, -1);
    }


    public static void placeFragment(int fragmentIndicator, int columnToOpen, int invokerColumn, String title, int id) {

        Log.d("ActivityLevel0", "inside placeFragment");

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (fragmentIndicator) {

            case PLACE_FRAGMENT_1:

                AllMusicFragment allMusicFragment = AllMusicFragment.newInstance(1, null);

                mFragmentTransaction.setCustomAnimations(
                        android.R.animator.fade_in,
                        android.R.animator.fade_out
//                        R.animator.fragment_animator_all_music_out,
//                        R.animator.fragment_animator_all_music_in,
//                        R.animator.fragment_animator_all_music_out
                );

                mFragmentTransaction
                        .replace(R.id.level_0_fragment_container, allMusicFragment)
                        .commit();
                break;

            case PLACE_FRAGMENT_2:

                mFragmentTransaction.setCustomAnimations(
                        android.R.animator.fade_in,
                        android.R.animator.fade_out
//                        R.animator.fragment_animator_detail_list_in,
//                        R.animator.fragment_animator_all_music_out,
//                        R.animator.fragment_animator_all_music_in,
//                        R.animator.fragment_animator_detail_list_out
                );

                if (columnToOpen != MusicDataStructure.MEDIA_COLUMN) {

                    DetailListFragment detailListFragment = DetailListFragment.newInstance(columnToOpen, "he");

                    mFragmentTransaction
                            .replace(R.id.level_0_fragment_container, detailListFragment)
                            .addToBackStack(null)
                            .commit();

                } else {

                    JustMedia_Fragment justMedia_fragment = JustMedia_Fragment.newInstance(columnToOpen);
                    mFragmentTransaction
                            .replace(R.id.level_0_fragment_container, justMedia_fragment)
                            .addToBackStack(null)
                            .commit();
                }
                break;

            case PLACE_FRAGMENT_3:

                JustMedia_Fragment justMedia_fragment;

                if (invokerColumn == MusicDataStructure.GENRE_COLUMN) {
                    justMedia_fragment = JustMedia_Fragment.newInstance(id, invokerColumn);
                } else {
                    justMedia_fragment = JustMedia_Fragment.newInstance(title, invokerColumn);
                }

                mFragmentTransaction.setCustomAnimations(
                        android.R.animator.fade_in,
                        android.R.animator.fade_out
                );


                mFragmentTransaction
                        .replace(R.id.level_0_fragment_container, justMedia_fragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }

        Log.d("ActivityLevel0", "leaving placeFragment");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_level0, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(int musicColumnScrolled) {
        placeFragment(PLACE_FRAGMENT_2, musicColumnScrolled);
    }

    @Override
    public void onFragmentInteraction(int invokerColumn, String title, int id) {
        placeFragment(PLACE_FRAGMENT_3, invokerColumn, title, id);
    }
}

