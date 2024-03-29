package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitinit {
    private static Retrofit retrofit = null;

    public Retrofitinit() {
    }

    public static Responseapi getApi(){
        if (retrofit == null){
            retrofit = getInstance();
        }
        return retrofit.create(Responseapi.class);
    }

    private static Retrofit getInstance(){

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                                    .readTimeout(10, TimeUnit.SECONDS)
                                                    .writeTimeout(10,TimeUnit.SECONDS)
                                                    .connectTimeout(10,TimeUnit.SECONDS)
                                                    .retryOnConnectionFailure(true)
                                                    .build();

        retrofit = new Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .baseUrl("https://truongthinh96.000webhostapp.com/devicestore/")  //http://172.16.1.72:8888/devicestore/ //192.168.1.103  //https://truongthinh96.000webhostapp.com/devicestore/
                                .client(okHttpClient)
                                .build();
        return retrofit;
    }
}
