package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel;


import androidx.lifecycle.MutableLiveData;


import org.json.JSONArray;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseOrder;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.repository.OrderRepository;

public class OrderViewModel {
    public OrderViewModel() {
    }

    public MutableLiveData<ResponseOrder> checkOrder(JSONArray json){
        return OrderRepository.getInstance().getDataOrder(json);
    }
}
