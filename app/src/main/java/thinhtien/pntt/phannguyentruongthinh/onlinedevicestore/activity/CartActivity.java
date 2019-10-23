package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.CartAdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Cart;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    CartAdapter cartAdapter;
    TextView txtCartEmpty;
    static TextView txtTotalPrice;
    Button btnCompletePayment, btnContinueShopping;
    Toolbar toolbarCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        AnhXa();
        ActionToolbar();
        CheckData();
        EvenUtil();
        CatchOnItemRecyclerview();
        EventButton();
    }

    private void EventButton() {
        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        btnCompletePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mangCart.size() > 0){
                    Intent intent = new Intent(CartActivity.this, InfoConsumerActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnection.showToast_Short(CartActivity.this,"Your cart is empty. Continue shopping to find a product");
                }
            }
        });
    }

    private void CatchOnItemRecyclerview() {
        ((CartAdapter)recyclerViewCart.getAdapter()).setOnItemLongClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, final int position) {
//                Toast.makeText(CartActivity.this, "OK", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Detele Product")
                        .setMessage("Do you want to delete this product?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (MainActivity.mangCart.size() <= 0){
                                    txtCartEmpty.setVisibility(View.VISIBLE);
                                } else {
                                    MainActivity.mangCart.remove(position);
                                    cartAdapter.notifyDataSetChanged();
                                    EvenUtil();
                                    if (MainActivity.mangCart.size() <= 0){
                                        txtCartEmpty.setVisibility(View.VISIBLE);
                                    } else {
                                        txtCartEmpty.setVisibility(View.INVISIBLE);
                                        cartAdapter.notifyDataSetChanged();
                                        EvenUtil();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cartAdapter.notifyDataSetChanged();
                                EvenUtil();
                            }
                        })
                        .show();
            }
        });
    }

    public static void EvenUtil() {
        long totalPrice = 0;
        for (int i = 0; i < MainActivity.mangCart.size(); i++){
            totalPrice += MainActivity.mangCart.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalPrice.setText(decimalFormat.format(totalPrice) + " Đ");
    }

    private void CheckData() {
        if (MainActivity.mangCart.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            txtCartEmpty.setVisibility(View.VISIBLE);
            recyclerViewCart.setVisibility(View.INVISIBLE);
        } else {
            txtCartEmpty.setVisibility(View.INVISIBLE);
            recyclerViewCart.setVisibility(View.VISIBLE);
            cartAdapter.notifyDataSetChanged();
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        recyclerViewCart = findViewById(R.id.recyclerviewCart);
        txtCartEmpty = findViewById(R.id.textviewCartEmpty);
        txtTotalPrice = findViewById(R.id.textviewTotalPrice);
        btnCompletePayment = findViewById(R.id.buttonCompletePayment);
        btnContinueShopping = findViewById(R.id.buttonContinueShopping);
        toolbarCart = findViewById(R.id.toolbarCart);
        cartAdapter = new CartAdapter(MainActivity.mangCart, CartActivity.this);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        recyclerViewCart.setAdapter(cartAdapter);
    }
}
