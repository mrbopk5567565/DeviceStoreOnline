package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseConsumer;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Responseapi;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Retrofitinit;

public class ConsumerRepository {

    private static ConsumerRepository consumerRepository = null;
    private Responseapi responseapi;

    private ConsumerRepository() {
        responseapi = Retrofitinit.getApi();
    }

    public static ConsumerRepository getInstance(){
        if (consumerRepository == null){
            consumerRepository = new ConsumerRepository();
        }
        return consumerRepository;
    }

    public MutableLiveData<ResponseConsumer> getDataConsumer(String tenkhachhang, String sodienthoai, String email){
        final MutableLiveData<ResponseConsumer> mutableLiveData = new MutableLiveData<>();
        responseapi.getConsumer(tenkhachhang, sodienthoai, email).enqueue(new Callback<ResponseConsumer>() {
            @Override
            public void onResponse(Call<ResponseConsumer> call, Response<ResponseConsumer> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseConsumer> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
