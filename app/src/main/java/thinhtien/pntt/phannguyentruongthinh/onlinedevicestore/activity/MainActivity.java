package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ViewFlipper mViewFlipper;
    RecyclerView mRecyclerViewmanhinhchinh;
    NavigationView mNavigationView;
    ListView mListViewmanhinhchinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ActionBar();
    }

    private void ActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_home);  // android.R.drawable.ic_menu_sort_by_size
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        mToolbar = findViewById(R.id.toolbarmanhinhchinh);
        mViewFlipper = findViewById(R.id.viewflipper);
        mRecyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        mNavigationView = findViewById(R.id.navigationview);
        mListViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        mDrawerLayout = findViewById(R.id.drawerlayout);
    }
}
