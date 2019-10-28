package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseConsumer;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseOrder;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.ConsumerViewModel;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.OrderViewModel;

public class InfoConsumerActivity extends AppCompatActivity {

    EditText edtNameConsumer, edtTelConsumer, edtEmailConsumer;
    Button btnConfirm, btnReturnCart;
    ConsumerViewModel consumerViewModel;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_consumer);

        AnhXa();

        EventButtonReturnCart();

        if (CheckConnection.isNetworkAvailable(InfoConsumerActivity.this)){
            EventButtonConfirm();
        } else {
            CheckConnection.showToast_Short(InfoConsumerActivity.this, "Please check the connection");
        }
    }

    private void EventButtonConfirm() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameConsumer = edtNameConsumer.getText().toString().trim();
                String telConsumer = edtTelConsumer.getText().toString().trim();
                String emailConsumer = edtEmailConsumer.getText().toString().trim();
                if (nameConsumer.length() > 0 && telConsumer.length() > 0 & emailConsumer.length() > 0){
                    consumerViewModel = new ConsumerViewModel();
                    consumerViewModel.checkConsumer(nameConsumer,telConsumer,emailConsumer)
                            .observe(InfoConsumerActivity.this, new Observer<ResponseConsumer>() {
                        @Override
                        public void onChanged(ResponseConsumer responseConsumer) {
                            if (responseConsumer != null){
                                if (responseConsumer.getSuccess()){
//                                    Toast.makeText(InfoConsumerActivity.this, "Confirm Successfully", Toast.LENGTH_SHORT).show();

                                    JSONArray jsonArray = new JSONArray();
                                    for (int i = 0; i < MainActivity.mangCart.size(); i++){
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang",responseConsumer.getIduser());
                                            jsonObject.put("masanppham",MainActivity.mangCart.get(i).getId());
                                            jsonObject.put("tensanpham",MainActivity.mangCart.get(i).getTensp());
                                            jsonObject.put("giasanpham",MainActivity.mangCart.get(i).getGiasp());
                                            jsonObject.put("soluongsanpham",MainActivity.mangCart.get(i).getSoluong());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }
                                    orderViewModel = new OrderViewModel();
                                    orderViewModel.checkOrder(jsonArray).observe(InfoConsumerActivity.this, new Observer<ResponseOrder>() {
                                        @Override
                                        public void onChanged(ResponseOrder responseOrder) {
                                            if (responseOrder != null){
                                                if (responseOrder.getSuccess()){
                                                    MainActivity.mangCart.clear();
                                                    Toast.makeText(InfoConsumerActivity.this, responseOrder.getMessage(), Toast.LENGTH_SHORT).show();

                                                    Toast.makeText(InfoConsumerActivity.this, "Do you want anything else", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(InfoConsumerActivity.this, responseOrder.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });

                                    // of screen information Consumer
//                                    startActivity(new Intent(InfoConsumerActivity.this, MainActivity.class));
//                                    Toast.makeText(InfoConsumerActivity.this, "Do you want anything else", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(InfoConsumerActivity.this, "Confirm Fail", Toast.LENGTH_SHORT).show();
                                }
                                startActivity(new Intent(InfoConsumerActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
                } else {
                    CheckConnection.showToast_Short(InfoConsumerActivity.this, "Please check the data");
                }
            }
        });
    }

    private void EventButtonReturnCart() {
        btnReturnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        edtNameConsumer = findViewById(R.id.edittextNameConsumer);
        edtTelConsumer = findViewById(R.id.edittextTelConsumer);
        edtEmailConsumer = findViewById(R.id.edittextEmailConsumer);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReturnCart = findViewById(R.id.buttonReturnCart);
    }
}
