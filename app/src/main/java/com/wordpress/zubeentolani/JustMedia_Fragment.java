package com.wordpress.zubeentolani;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JustMedia_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JustMedia_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JustMedia_Fragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "theTitle";
    private static final String ARG_MUSIC_COLUMN = "theMusicColumn";
    private static final String ARG_ID = "theID";

    private String mTitle;
    private int mInvokerColumn;
    private int mID;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @param column Parameter 2.
     * @return A new instance of fragment JustMedia_Fragment.
     */


    //for music from albums or artists
    public static JustMedia_Fragment newInstance(String title, int column) {
        JustMedia_Fragment fragment = new JustMedia_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_MUSIC_COLUMN, column);
        fragment.setArguments(args);
        return fragment;
    }

    //for music from genres or playlist
    public static JustMedia_Fragment newInstance(int id, int column) {
        JustMedia_Fragment fragment = new JustMedia_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putInt(ARG_MUSIC_COLUMN, column);
        fragment.setArguments(args);
        return fragment;
    }


    //For all music
    public static JustMedia_Fragment newInstance(int column) {
        JustMedia_Fragment fragment = new JustMedia_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MUSIC_COLUMN, column);
        fragment.setArguments(args);
        return fragment;
    }

    public JustMedia_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mInvokerColumn = getArguments().getInt(ARG_MUSIC_COLUMN);

            if (mInvokerColumn == MusicDataStructure.GENRE_COLUMN) {

                mID = getArguments().getInt(ARG_ID);

            }else if (mInvokerColumn != MusicDataStructure.MEDIA_COLUMN){

                mTitle = getArguments().getString(ARG_TITLE);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_detail_list,container,false);
        SlidingListView slidingListView = (SlidingListView) rootView.findViewById(R.id.detail_list_sliding_list_view);


        DetailList_Row_Adapter detailList_row_adapter = new DetailList_Row_Adapter(
                getActivity().getBaseContext(),
                MusicDataStructure.MEDIA_COLUMN,
                new ArrayList<DataStructures.String_Long>()
        );

        slidingListView.setAdapter(detailList_row_adapter);
        slidingListView.setFriction(ViewConfiguration.getScrollFriction() * DetailListFragment.FRICTION_FOR_DETAIL_LISTVIEW);

        //TODO:set the on item click listener


        if (mInvokerColumn != MusicDataStructure.GENRE_COLUMN){

            CollectionOfAsyncTasks.resolveMediaQuery(
                    getActivity(),
                    getSelectionString(),
                    getSelectionArgs(),
                    detailList_row_adapter
            );

        }else{

            CollectionOfAsyncTasks.resolveMediaQuery(
                    getActivity(),
                    detailList_row_adapter,
                    mID,
                    mInvokerColumn
            );

        }



        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public String getSelectionString(){

        if (mInvokerColumn == MusicDataStructure.ALBUM_COLUMN) {
            return String.format("%s=?", MediaStore.Audio.Media.ALBUM);
        }
        else if (mInvokerColumn == MusicDataStructure.ARTIST_COLUMN) {
            return String.format("%s=?", MediaStore.Audio.Media.ARTIST);
        }
        else if (mInvokerColumn == MusicDataStructure.MEDIA_COLUMN) {
            return String.format("%s!=0", MediaStore.Audio.Media.IS_MUSIC);
        }
        return null;
    }

    public String[] getSelectionArgs(){

        if (mInvokerColumn == MusicDataStructure.MEDIA_COLUMN){
            return null;
        }
        return new String[]{mTitle};
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(Uri uri);
    }

}
