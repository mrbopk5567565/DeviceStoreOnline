package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;

import android.content.Intent;
import android.os.Bundle;

public class LaptopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
    }
}
