package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Cart;
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

    public static ArrayList<Cart> mangCart;

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
            CatchOnItemMenu();
        } else {
            CheckConnection.showToast_Short(MainActivity.this,"Bạn hãy kiểm tra lại kết nối");
        }

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
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemMenu() {
        ((LoaispApdapter)mRecyclerViewMenu.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                switch (position){
                    case 0:
                        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(MainActivity.this,"Check your connection !!!" );
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this, MobileActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("idsp",mangLoaiSp.get(position).getId());
                            intent.putExtras(bundle);
                            startActivity(intent);
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            MobileFragment mobileFragment = new MobileFragment();
//                            mobileFragment.setArguments(bundle);
//                            fragmentTransaction.add(R.id.moblieContainer,mobileFragment);
//                            fragmentTransaction.commit();
                        } else {
                            CheckConnection.showToast_Short(MainActivity.this,"Check your connection !!!" );
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("idsp",mangLoaiSp.get(position).getId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(MainActivity.this,"Check your connection !!!" );
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(MainActivity.this,"Check your connection !!!" );
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.isNetworkAvailable(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this, InfomationActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(MainActivity.this,"Check your connection !!!" );
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
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
        // su kien click item cho man hinh san pham moi nhat
        ((SanphamApdater)mRecyclerViewmanhinhchinh.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(MainActivity.this, DetailProductActivity.class);
                intent.putExtra("InformationProduct",mangSanpham.get(position));
                CheckConnection.showToast_Short(MainActivity.this,mangSanpham.get(position).getTensp());
                startActivity(intent);
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
                    mangLoaiSp.add(3,new Loaisp(3,"Contact","https://truongthinh96.000webhostapp.com/devicestore/image/baseline_contacts_black_48dp.png"));
                    mangLoaiSp.add(4,new Loaisp(4,"Information","https://truongthinh96.000webhostapp.com/devicestore/image/baseline_info_black_48dp.png"));
                }
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
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // or FIT_XY
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
        mangLoaiSp.add(0,new Loaisp(0, "Home", "https://truongthinh96.000webhostapp.com/devicestore/image/baseline_home_black_48dp.png")); //http://192.168.1.103:8888/devicestore/image/baseline_home_black_48dp.png
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

        if (mangCart != null){

        } else {
            mangCart = new ArrayList<>();
        }
    }
}
