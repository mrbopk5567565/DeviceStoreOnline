package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment.MobileFragment;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnListenId;

public class MobileActivity extends AppCompatActivity {

    int id_moblie = 0;
    OnListenId onListenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        id_moblie = bundle.getInt("idsp", -1);

        onListenId.onChangeId(id_moblie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                Intent intent = new Intent(MobileActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setListenId(OnListenId onListenId){
        this.onListenId = onListenId;
    }
}
