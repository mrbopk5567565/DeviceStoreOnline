package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment.ContactFragment;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        String text = intent.getStringExtra("chuoi");

//        ContactFragment contactFragment = (ContactFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContact);
//        if (contactFragment != null){
//            contactFragment.txtContact.setText(text);
//        }

    }
}
