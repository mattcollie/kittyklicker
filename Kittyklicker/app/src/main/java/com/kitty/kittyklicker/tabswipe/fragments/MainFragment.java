package com.kitty.kittyklicker.tabswipe.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.kitty.kittyklicker.R;
import com.kitty.kittyklicker.interfaces.IKitty;

/**
 * MainFragment handles the main kitty klick Button
 */
public class MainFragment extends Fragment {

    private IKitty kittyMain;

    public MainFragment() {}


    /**
     * Used to create a new instance of this fragment
     *
     * @return A new instance of fragment MainFragment
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle params here if required
        // if (getArguments() != null) {}
    }


    /**
     * Initialises and handles the kitty Button
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Handles main kitty klick button
        ImageButton kittyButtonEvent = (ImageButton) view.findViewById(R.id.imageButtonKitty);

        kittyButtonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kittyMain != null) {
                    kittyMain.incrementCount();
                }
            }
        });

        return view;
    }


    /**
     * Initialises kittyMain as an interface with MainActivity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity mainActivity = getActivity();

        if((mainActivity != null) && (mainActivity instanceof IKitty)) {
            kittyMain = (IKitty) mainActivity;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
