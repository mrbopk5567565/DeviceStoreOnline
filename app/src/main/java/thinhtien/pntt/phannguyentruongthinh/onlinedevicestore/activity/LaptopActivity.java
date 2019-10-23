package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnListenId;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                Intent intent = new Intent(LaptopActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setListenId(OnListenId onListenId){
        this.onListenId = onListenId;
    }
}
