package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnListenId;

import android.content.Intent;
import android.os.Bundle;

public class LaptopActivity extends AppCompatActivity {

    int id_laptop = 0;
    OnListenId onListenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        id_laptop = bundle.getInt("idsp",-1);
        onListenId.onChangeId(id_laptop);
    }

    public void setOnListenId(OnListenId onListenId){
        this.onListenId = onListenId;
    }
}
