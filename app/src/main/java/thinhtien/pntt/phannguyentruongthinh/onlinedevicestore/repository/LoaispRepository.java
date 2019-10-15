package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseLoaisp;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Responseapi;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Retrofitinit;

public class LoaispRepository {
    private static LoaispRepository loaispRepository = null;
    private Responseapi responseapi;

    private LoaispRepository() {
        responseapi = Retrofitinit.getApi();
    }

    public static LoaispRepository getInstance(){
        if (loaispRepository == null){
            loaispRepository = new LoaispRepository();
        }
        return loaispRepository;
    }

    public MutableLiveData<List<ResponseLoaisp>> getDataLoaisp(){
        final MutableLiveData<List<ResponseLoaisp>> mutableLiveData = new MutableLiveData<>();

        responseapi.getLoaisp().enqueue(new Callback<List<ResponseLoaisp>>() {
            @Override
            public void onResponse(Call<List<ResponseLoaisp>> call, Response<List<ResponseLoaisp>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseLoaisp>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
