package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository.SanphamRepository;
//implements LifecycleObserver
public class SanphamViewModel {

//    public MutableLiveData<List<ResponseSanpham>> mutableLiveData = new MutableLiveData<>();

    public SanphamViewModel() {
    }
    public MutableLiveData<List<ResponseSanpham>> checkSanpham(){
        return SanphamRepository.getInstance().getDataSanpham();
    }

    public MutableLiveData<List<ResponseSanpham>> checkLoaiSanPhamMobile(String page,int idsp){
        return SanphamRepository.getInstance().getDataLoaiSanPhamMobile(page, idsp);
    }

    public MutableLiveData<List<ResponseSanpham>> checkLoaiSanPhamLaptop(String page,int idsp){
        return SanphamRepository.getInstance().getDataLoaiSanPhamlaptop(page, idsp);
    }
}
