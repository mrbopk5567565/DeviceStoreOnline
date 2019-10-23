package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseConsumer;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.ConsumerViewModel;

public class InfoConsumerActivity extends AppCompatActivity {

    EditText edtNameConsumer, edtTelConsumer, edtEmailConsumer;
    Button btnConfirm, btnReturnCart;
    ConsumerViewModel consumerViewModel;

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
                                    Toast.makeText(InfoConsumerActivity.this, "Confirm Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(InfoConsumerActivity.this, "Confirm Fail", Toast.LENGTH_SHORT).show();
                                }
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
