package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel;

import androidx.lifecycle.MutableLiveData;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseConsumer;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository.ConsumerRepository;

public class ConsumerViewModel {
    public ConsumerViewModel() {
    }

    public MutableLiveData<ResponseConsumer> checkConsumer(String tenkhachhang, String sodienthoai, String email){
        return ConsumerRepository.getInstance().getDataConsumer(tenkhachhang, sodienthoai, email);
    }
}
