package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaptopFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_laptop, container, false);

        return view;
    }

}
