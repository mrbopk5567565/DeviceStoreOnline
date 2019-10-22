package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;

public class DetailProductActivity extends AppCompatActivity {

    Toolbar toolbarDetail;
    ImageView imgDetail;
    TextView txtNameDetail, txtPriceDetail, txtDesDetail;
    Spinner spinnerDetail;
    Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        AnhXa();
        ActionToolBar();
        GetInformation();
        CatchEventSppinner();
    }

    private void CatchEventSppinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,
                                            android.R.layout.simple_spinner_dropdown_item,
                                            soluong);
        spinnerDetail.setAdapter(arrayAdapter);
    }

    private void GetInformation() {
        int id  = 0;
        String tenchitiet = "";
        int giachitiet = 0;
        String hinhanhchitiet = "";
        String mota = "";
        int idsp = 0;

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
