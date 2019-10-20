package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository.SanphamRepository;

public class SanphamViewModel {
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
