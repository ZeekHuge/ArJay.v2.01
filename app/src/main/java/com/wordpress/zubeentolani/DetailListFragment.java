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
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailListFragment extends Fragment implements ListView.OnItemSelectedListener, ListView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MUSIC_COLUMN = "musicColumnToOpen";
//    private static final String ARG_PARAM2 = "param2";


    public static int FRICTION_FOR_DETAIL_LISTVIEW = 2;

    // TODO: Rename and change types of parameters
    private int mMusicColumn;
    private String mParam2;
    ArrayList<String[]> dataContainingArrayList = new ArrayList<String[]>();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param musicColumn Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailListFragment newInstance(int musicColumn, String param2) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MUSIC_COLUMN, musicColumn);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMusicColumn = getArguments().getInt(ARG_MUSIC_COLUMN);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_detail_list,container,false);
        SlidingListView slidingListView = (SlidingListView) rootView.findViewById(R.id.detail_list_sliding_list_view);

        DetailList_Row_Adapter detailList_row_adapter = new DetailList_Row_Adapter(
                getActivity().getBaseContext(),
                mMusicColumn,
                new ArrayList<DataStructures.String_Long>()
        );

        slidingListView.setAdapter(detailList_row_adapter);
        slidingListView.setFriction(ViewConfiguration.getScrollFriction() * FRICTION_FOR_DETAIL_LISTVIEW);
        slidingListView.setOnItemSelectedListener(this);
        slidingListView.setOnItemClickListener(this);

        CollectionOfAsyncTasks.fillTheMusicColumn(
                getActivity(),
                mMusicColumn,
                detailList_row_adapter
        );

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("DetailListFragment", "inside onItemSelected");
            View v = view.findViewById(R.id.detail_list_row_container);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
            layoutParams.bottomMargin = 0;
            v.requestLayout();
            v.setVisibility(View.GONE);
        Log.d("DetailListFragment", "leaving onItemSelected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        Log.d("DetailListFragment", "inside onItemSelected");

        ((SlidingFrameLayout)parent.getParent()).childIsClicked(view, (SlidingListView) parent);

        final SlidingFrameLayout sl = (SlidingFrameLayout) parent.getParent();

        for (int i = 0; i <= 101 ; i++){
            final int i_ = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (i_==101) {
                        mListener.onFragmentInteraction(
                                mMusicColumn,
                                ((ThreadRecorder_View)view).mTitle,
                                (int) ((ThreadRecorder_View) view).mId
                        );
                    }else{
                        sl.setAnimateMargin((float) i_ / 100);
                    }
                }
            },(int)((1000/100)*i) );
        }




//        View v = view.findViewById(R.id.detail_list_row_container);
//
//        ListRowExpander_Animation anim = new ListRowExpander_Animation(v,2000, parent);
//        v.startAnimation(anim);

        Log.d("DetailListFragment", "leaving onItemSelected");
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
        public void onFragmentInteraction(int invokerColumn, String title, int id);
    }

}
