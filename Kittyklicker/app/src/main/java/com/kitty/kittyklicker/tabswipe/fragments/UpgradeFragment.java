package com.kitty.kittyklicker.tabswipe.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kitty.kittyklicker.R;
import com.kitty.kittyklicker.enums.KittyUpgradeEnum;
import com.kitty.kittyklicker.interfaces.IKitty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpgradeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpgradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpgradeFragment extends Fragment {

    private IKitty kittyMain;

    public UpgradeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment UpgradeFragment.
     */
    public static UpgradeFragment newInstance() {
        UpgradeFragment fragment = new UpgradeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle params here if required
        //if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upgrade, container, false);

        final Button[] upgradeButtons = new Button[KittyUpgradeEnum.values().length];
        final TextView[] upgradeTextViews = new TextView[KittyUpgradeEnum.values().length];

        TableLayout upgradeTable = (TableLayout) view.findViewById(R.id.upgradeTable);

        int buttonIndex = 0;
        int textIndex = 0;

        for(int i = 0; i < upgradeTable.getChildCount(); i++) {     // Loop through each TableRow

            if(!(upgradeTable.getChildAt(i) instanceof TableRow)) continue;

            TableRow row = (TableRow) upgradeTable.getChildAt(i);

            System.out.println("Child count: " + row.getChildCount());

            for(int j = 0; j < row.getChildCount(); j++) {

                View v = row.getChildAt(j);

                System.out.println("View ID: " + v.getId());

                if(v instanceof Button) {
                    upgradeButtons[buttonIndex] = (Button) v;
                    buttonIndex++;
                } else if(v instanceof TextView) {
                    upgradeTextViews[textIndex] = (TextView) v;
                    textIndex++;
                }
            }
        }

        // Handles upgrade buttons
        int i = 0;

        for(final KittyUpgradeEnum upgrade : KittyUpgradeEnum.values()) {

            final int upgradeIndex = i;

            if((upgradeButtons[i] == null) || (upgradeTextViews[i] == null)) continue;  // Avoids attempts to set button info if null

            upgradeButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(kittyMain != null) {
                        kittyMain.buyUpgrade(upgrade, upgradeIndex);

                        String upgradeName = upgrade.getName();
                        int upgradeAmount = kittyMain.getUpgradeAmount(upgradeIndex);
                        int upgradeCost = (int) (upgrade.getCost() * Math.pow(1.15, upgradeAmount));    // Increase cost with each purchase

                        double amountPerSec = upgrade.getAmountPerSec();
                        double totalIncomePerSec = Math.round(upgrade.getAmountPerSec() * upgradeAmount * 10000)/10000.0;    //rounds to 4 d.p to remove double inaccuracies

                        upgradeButtons[upgradeIndex].setText(getString(R.string.upgrade_button, upgradeName, upgradeCost, amountPerSec));

                        if((int) totalIncomePerSec == totalIncomePerSec) {     // If whole number, no need to display .0
                            upgradeTextViews[upgradeIndex].setText(getString(R.string.upgrade_text, upgradeAmount, totalIncomePerSec));
                        } else {
                            upgradeTextViews[upgradeIndex].setText(getString(R.string.upgrade_text_double, upgradeAmount, totalIncomePerSec));
                        }




                        //upgradeButtons[upgradeIndex].setText(upgrade.getName() + "\nCost: " + upgradeCost + "\nIncome: " + (int)(upgrade.getAmountPerSec()) + "/s");
                        //upgradeTextViews[upgradeIndex].setText("Currently owned: " + upgradeAmount + "\nTotal income per second: " + totalIncomePerSec);
                        /*
                        if((int) upgrade.getAmountPerSec() == upgrade.getAmountPerSec()) {     // If whole number, no need to display .0
                            upgradeButtons[upgradeIndex].setText(upgrade.getName() + "\nCost: " + upgradeCost + "\nIncome: " + (int)(upgrade.getAmountPerSec()) + "/s");
                        } else {
                            upgradeButtons[upgradeIndex].setText(upgrade.getName() + "\nCost: " + upgradeCost + "\nIncome: " + upgrade.getAmountPerSec() + "/s");
                        }

                        if((int) totalIncomePerSec == totalIncomePerSec) {     // If whole number, no need to display .0
                            upgradeTextViews[upgradeIndex].setText("Currently owned: " + upgradeAmount + "\nTotal income per second: " + (int)(totalIncomePerSec));
                        } else {
                            upgradeTextViews[upgradeIndex].setText("Currently owned: " + upgradeAmount + "\nTotal income per second: " + totalIncomePerSec);
                        }*/
                    }
                }
            });

            i++;
        }

        return view;
    }

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
