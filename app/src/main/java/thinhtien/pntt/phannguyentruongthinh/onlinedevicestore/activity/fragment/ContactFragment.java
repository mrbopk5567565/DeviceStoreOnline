package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    View view;
    public TextView txtContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);

        txtContact = view.findViewById(R.id.textviewcontact);

        return view;
    }

}
