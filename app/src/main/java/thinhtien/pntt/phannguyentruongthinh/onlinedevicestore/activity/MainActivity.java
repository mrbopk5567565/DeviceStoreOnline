package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.LoaispApdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Loaisp;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ViewFlipper mViewFlipper;
    RecyclerView mRecyclerViewmanhinhchinh, mRecyclerViewMenu;
    NavigationView mNavigationView;

    ArrayList<Loaisp> mangLoaiSp;
    LoaispApdapter loaispApdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/209535/xiaomi-redmi-note-8-white-18thangbh-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/211161/realme-5-4gb-purple-docquyen-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/182153/oppo-f9-tim-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/192003/samsung-galaxy-a9-2018-blue-400x460.png");
        for (int i = 0; i < mangQuangCao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext())
                    .load(mangQuangCao.get(i))
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewFlipper.addView(imageView);
        }
        mViewFlipper.setFlipInterval(5000);
        mViewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.silde_out_right);
        mViewFlipper.setInAnimation(animation_slide_in);
        mViewFlipper.setOutAnimation(animation_slide_out);
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
        mRecyclerViewMenu = findViewById(R.id.recyclerviewMenu);
        mDrawerLayout = findViewById(R.id.drawerlayout);

        mangLoaiSp = new ArrayList<>();
        mangLoaiSp.add(new Loaisp(1,"thinh","asd"));
        mangLoaiSp.add(new Loaisp(2,"thinh","asd"));
        loaispApdapter = new LoaispApdapter(mangLoaiSp, MainActivity.this);   //
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMenu.setAdapter(loaispApdapter);
    }
}
