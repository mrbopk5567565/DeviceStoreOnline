package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Responseapi;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit.Retrofitinit;

public class SanphamRepository {
    private static SanphamRepository sanphamRepository = null;
    private Responseapi responseapi;

    private SanphamRepository() {
        responseapi = Retrofitinit.getApi();
    }

    public static SanphamRepository getInstance(){
        if (sanphamRepository == null){
            sanphamRepository = new SanphamRepository();
        }
        return sanphamRepository;
    }

    public MutableLiveData<List<ResponseSanpham>> getDataSanpham(){
        final MutableLiveData<List<ResponseSanpham>> mutableLiveData = new MutableLiveData<>();
        responseapi.getSanpham().enqueue(new Callback<List<ResponseSanpham>>() {
            @Override
            public void onResponse(Call<List<ResponseSanpham>> call, Response<List<ResponseSanpham>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseSanpham>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<ResponseSanpham>> getDataLoaiSanPhamMobile(String page, int idsp){
        final MutableLiveData<List<ResponseSanpham>> mutableLiveData = new MutableLiveData<>();
        responseapi.getLoaiSanPhamMobile(page,idsp).enqueue(new Callback<List<ResponseSanpham>>() {
            @Override
            public void onResponse(Call<List<ResponseSanpham>> call, Response<List<ResponseSanpham>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseSanpham>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<ResponseSanpham>> getDataLoaiSanPhamlaptop (String page, int idsp){
        final MutableLiveData<List<ResponseSanpham>> mutableLiveData = new MutableLiveData<>();
        responseapi.getLoaiSanPhamLaptop(page, idsp).enqueue(new Callback<List<ResponseSanpham>>() {
            @Override
            public void onResponse(Call<List<ResponseSanpham>> call, Response<List<ResponseSanpham>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseSanpham>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
