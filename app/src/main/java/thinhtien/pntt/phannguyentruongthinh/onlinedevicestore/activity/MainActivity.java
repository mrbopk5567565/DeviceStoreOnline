package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.SanphamApdater;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.LoaispApdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseLoaisp;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Loaisp;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.LoaispViewModel;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.SanphamViewModel;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ViewFlipper mViewFlipper;
    RecyclerView mRecyclerViewmanhinhchinh, mRecyclerViewMenu;
    NavigationView mNavigationView;

    ArrayList<Loaisp> mangLoaiSp;
    LoaispApdapter loaispApdapter;

    LoaispViewModel loaispViewModel;
    SanphamViewModel sanphamViewModel;

    int id = 0;
    String tenLoaisp = "";
    String hinhAnhLoaisp = "";

    ArrayList<Sanpham> mangSanpham;
    SanphamApdater sanphamApdater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        // kiểm tra kết nối Internet
        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
            ActionBar();
            ActionViewFlipper();
            getDuLieuLoaiSanPham();
            getDuLieuSanPhamMoiNhat();
        } else {
            CheckConnection.showToast_Short(MainActivity.this,"Ban hay kiem tra lai ket noi");
        }

    }

    private void getDuLieuSanPhamMoiNhat() {
        sanphamViewModel = new SanphamViewModel();
        sanphamViewModel.checkSanpham().observe(MainActivity.this, new Observer<List<ResponseSanpham>>() {
            @Override
            public void onChanged(List<ResponseSanpham> responseSanphams) {
                if (responseSanphams != null){
                    int id_1 = 0;
                    String tensp = "";
                    int giasp = 0;
                    String hinhanhsp = "";
                    String motasp = "";
                    int idsp = 0;
                    for(int i = 0; i < responseSanphams.size();i++){
                        id_1 = responseSanphams.get(i).getId();
                        tensp = responseSanphams.get(i).getTensp();
                        giasp = responseSanphams.get(i).getGiasp();
                        hinhanhsp = responseSanphams.get(i).getHinhanhsp();
                        motasp = responseSanphams.get(i).getMotasp();
                        idsp = responseSanphams.get(i).getIdsp();
                        mangSanpham.add(new Sanpham(id_1,tensp,giasp,hinhanhsp,motasp,idsp));
                        sanphamApdater.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void getDuLieuLoaiSanPham() {
        loaispViewModel = new LoaispViewModel();
        loaispViewModel.checkLoaisp().observe(MainActivity.this, new Observer<List<ResponseLoaisp>>() {
            @Override
            public void onChanged(List<ResponseLoaisp> responseLoaisps) {
                if (responseLoaisps != null){
                    for (int i = 0; i < responseLoaisps.size(); i++){
                        id = responseLoaisps.get(i).getId();
                        tenLoaisp = responseLoaisps.get(i).getTenloaisp();
                        hinhAnhLoaisp = responseLoaisps.get(i).getHinhanhloaisp();
                        mangLoaiSp.add(new Loaisp(id,tenLoaisp,hinhAnhLoaisp));
                        loaispApdapter.notifyDataSetChanged();
                    }
                    mangLoaiSp.add(3,new Loaisp(3,"thinh tien thi","https://image.flaticon.com/icons/svg/37/37557.svg"));
                    mangLoaiSp.add(4,new Loaisp(4,"Tong tin","R.drawable.common_full_open_on_phone"));
                }
            }
        });

        // su kien click item cho recyclerview menu
        ((LoaispApdapter)mRecyclerViewMenu.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                Toast.makeText(MainActivity.this, mangLoaiSp.get(position).getTenLoaiSp(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/209535/xiaomi-redmi-note-8-white-18thangbh-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/211161/realme-5-4gb-purple-docquyen-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/182153/oppo-f9-tim-400x460.png");
        mangQuangCao.add("https://cdn.tgdd.vn/Products/Images/42/192003/samsung-galaxy-a9-2018-blue-400x460.png");
        for (int i = 0; i < mangQuangCao.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
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

        // cho recyclerview menu
        mangLoaiSp = new ArrayList<>();
        mangLoaiSp.add(0,new Loaisp(0, "Trang chinh", "https://www.theaa.ie/blog/wp-content/uploads/2013/10/Home-Pic-600x320.jpg"));
        loaispApdapter = new LoaispApdapter(mangLoaiSp, MainActivity.this);
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMenu.setAdapter(loaispApdapter);

        // cho recyclerview san pham moi nhat
        mangSanpham = new ArrayList<>();
        sanphamApdater = new SanphamApdater(mangSanpham, MainActivity.this);
        // setHasFixedSize : set view giong dang gridview
        mRecyclerViewmanhinhchinh.setHasFixedSize(true);
        mRecyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        mRecyclerViewmanhinhchinh.setAdapter(sanphamApdater);
    }
}
