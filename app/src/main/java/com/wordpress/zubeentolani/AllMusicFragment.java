package com.wordpress.zubeentolani;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllMusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMusicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RESOURCE = "theResourceToInflate";
    private static final String ARG_PARAM2 = "param2";
    public static final int FRICTION_FOR_ALL_MUSIC_LISTVIEW = 5;
    // TODO: Rename and change types of parameters
    private int mResource;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param resource the resource to inflate inside the fragment.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllMusicFragment newInstance(int resource, String param2) {

        AllMusicFragment fragment = new AllMusicFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_RESOURCE, resource);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }



    public AllMusicFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mResource = getArguments().getInt(ARG_RESOURCE);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("AllMusicFragment","inside onCreateView");

        View rootView = inflater.inflate(R.layout.layout_local_music_all,container,false);
        final SlidingListView slidingListView = (SlidingListView) rootView.findViewById(R.id.local_music_all_list_view);

        slidingListView.setAdapter(ActivityLevel0.mHorizontalScrollRowAdapter);
        slidingListView.setFriction(ViewConfiguration.getScrollFriction() * FRICTION_FOR_ALL_MUSIC_LISTVIEW);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                slidingListView.slideBack(1,1);
//                (new Handler()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mListener!= null)
//                        mListener.onFragmentInteraction(1);
//                    }
//                },500);
//            }
//        },10000);


        Log.d("AllMusicFragment", "leaving onCreateView");
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("AllMusicFragment", "called onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("AllMusicFragment","called onDestroyView");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(int musicColumnScrolled);
    }

}
