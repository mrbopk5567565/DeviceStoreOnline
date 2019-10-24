package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository;


import androidx.lifecycle.MutableLiveData;


import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseOrder;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Responseapi;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Retrofitinit;

public class OrderRepository {
    private static OrderRepository orderRepository = null;
    private Responseapi responseapi;

    private OrderRepository() {
        responseapi = Retrofitinit.getApi();
    }

    public static OrderRepository getInstance(){
        if (orderRepository == null){
            orderRepository = new OrderRepository();
        }
        return orderRepository;
    }

    public MutableLiveData<ResponseOrder> getDataOrder(JSONArray json){
        final MutableLiveData<ResponseOrder> mutableLiveData = new MutableLiveData<>();
        responseapi.getOrder(json).enqueue(new Callback<ResponseOrder>() {
            @Override
            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseOrder> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
