package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseLoaisp;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;

public interface Responseapi {

    @GET("getloaisanpham.php")
    Call<List<ResponseLoaisp>> getLoaisp();

    @GET("getsanphammoinhat.php")
    Call<List<ResponseSanpham>> getSanpham();

    @FormUrlEncoded
    @POST("getsanpham.php")
    Call<List<ResponseSanpham>> getLoaiSanPhamMobile(@Query ("page") String page,@Field("idsp") int idsp);

    @FormUrlEncoded
    @POST("getsanpham.php")
    Call<List<ResponseSanpham>> getLoaiSanPhamLaptop(@Query("page") String page, @Field("idsp") int idsp);
}
