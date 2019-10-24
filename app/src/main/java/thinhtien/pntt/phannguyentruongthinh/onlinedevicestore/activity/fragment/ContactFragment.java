package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    Toolbar toolbarContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);

        toolbarContact = view.findViewById(R.id.toolbarContact);

        ActionToolBar();

        return view;
    }

    private void ActionToolBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarContact);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarContact.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

}
