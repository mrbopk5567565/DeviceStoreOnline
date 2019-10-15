package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseLoaisp;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository.LoaispRepository;

public class LoaispViewModel {
    public LoaispViewModel() {
    }

    public MutableLiveData<List<ResponseLoaisp>> checkLoaisp(){
        return LoaispRepository.getInstance().getDataLoaisp();
    }
}
