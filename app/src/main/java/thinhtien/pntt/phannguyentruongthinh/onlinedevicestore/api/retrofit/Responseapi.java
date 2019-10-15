package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseLoaisp;

public interface Responseapi {

    @GET("getloaisanpham.php")
    Call<List<ResponseLoaisp>> getLoaisp();
}
