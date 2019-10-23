package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Cart;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;

public class DetailProductActivity extends AppCompatActivity {

    Toolbar toolbarDetail;
    ImageView imgDetail;
    TextView txtNameDetail, txtPriceDetail, txtDesDetail;
    Spinner spinnerDetail;
    Button btnAddCart;

    // getInformaton
    int id  = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String mota = "";
    int idsp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        AnhXa();
        ActionToolBar();
        GetInformation();
        CatchEventSppinner();
        EventButtonCart();
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
                Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButtonCart() {
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mangCart.size() > 0){
                    int quantity_new = Integer.parseInt(spinnerDetail.getSelectedItem().toString());
                    Boolean exists = false;
                    for (int i = 0; i < MainActivity.mangCart.size(); i++){
                        if (MainActivity.mangCart.get(i).getId() == id){
                            MainActivity.mangCart.get(i).setSoluong(MainActivity.mangCart.get(i).getSoluong() + quantity_new);
                            if (MainActivity.mangCart.get(i).getSoluong() >= 10){
                                MainActivity.mangCart.get(i).setSoluong(10);
                            }
                            MainActivity.mangCart.get(i).setGiasp(MainActivity.mangCart.get(i).getSoluong() * giachitiet);
                            exists = true;
                        }
                    }
                    if (exists == false){
                        int quantity = Integer.parseInt(spinnerDetail.getSelectedItem().toString());
                        long totalPrice = quantity * giachitiet;
                        MainActivity.mangCart.add(new Cart(id,tenchitiet,totalPrice,hinhanhchitiet,quantity));
                    }
                } else {
                    int quantity = Integer.parseInt(spinnerDetail.getSelectedItem().toString());
                    long totalPrice = quantity * giachitiet;
                    MainActivity.mangCart.add(new Cart(id,tenchitiet,totalPrice,hinhanhchitiet,quantity));
                }
                Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSppinner() {
        Integer[] soluongSpinner = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,
                                            android.R.layout.simple_spinner_dropdown_item,
                                            soluongSpinner);
        spinnerDetail.setAdapter(arrayAdapter);
    }

    private void GetInformation() {
//        int id  = 0;
//        String tenchitiet = "";
//        int giachitiet = 0;
//        String hinhanhchitiet = "";
//        String mota = "";
//        int idsp = 0;

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("InformationProduct");
        id = sanpham.getId();
        tenchitiet = sanpham.getTensp();
        giachitiet = sanpham.getGiasp();
        hinhanhchitiet = sanpham.getHinhanhsp();
        mota = sanpham.getMotasp();
        idsp = sanpham.getIdsp();

        txtNameDetail.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPriceDetail.setText("Price : " + decimalFormat.format(giachitiet) + " Đ");
        txtDesDetail.setText(mota);
        Glide.with(this)
                .load(hinhanhchitiet)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(imgDetail);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarDetail = findViewById(R.id.toolbarDetailProduct);
        imgDetail = findViewById(R.id.imageDetailProduct);
        txtNameDetail = findViewById(R.id.textviewNameDetailProduct);
        txtPriceDetail = findViewById(R.id.textviewPriceDetailProduct);
        txtDesDetail = findViewById(R.id.textviewDescription);
        spinnerDetail = findViewById(R.id.spinnerDetailProduct);
        btnAddCart = findViewById(R.id.buttonAddCart);
    }
}
