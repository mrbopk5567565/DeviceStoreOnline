package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.LaptopActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.LaptopAdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnListenId;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.SanphamViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaptopFragment extends Fragment {

    View view;
    Toolbar toolbarLaptop;
    RecyclerView recyclerViewLaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> mangLaptop;
    SanphamViewModel loaiSPViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_laptop, container, false);

        mangLaptop = new ArrayList<>();
        toolbarLaptop = view.findViewById(R.id.toolbarLaptop);
        recyclerViewLaptop = view.findViewById(R.id.recyclerviewLaptop);
        laptopAdapter = new LaptopAdapter(mangLaptop,getActivity());
        recyclerViewLaptop.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewLaptop.setAdapter(laptopAdapter);

        if (CheckConnection.isNetworkAvailable(getActivity())){
            ActionToolbar();
            ((LaptopActivity)getActivity()).setListenId(new OnListenId() {
                @Override
                public void onChangeId(Integer idsp) {
                    getData("1",idsp);
                }
            });
        } else {
            CheckConnection.showToast_Short(getActivity(),"Xin kiểm tra lại kết nối");
            getActivity().finish();
        }

        return view;
    }

    private void getData(String page, Integer id_laptop) {
        loaiSPViewModel = new SanphamViewModel();
        loaiSPViewModel.checkLoaiSanPhamLaptop(page,id_laptop).observe(getActivity(), new Observer<List<ResponseSanpham>>() {
            @Override
            public void onChanged(List<ResponseSanpham> responseSanphams) {
                if (responseSanphams != null){
                    int id = 0;
                    String tensp = "";
                    int giasp = 0;
                    String hinhanhsp = "";
                    String motasp = "";
                    int idsplt = 0;
                    for (int i = 0; i < responseSanphams.size(); i++){
                        id = responseSanphams.get(0).getId();
                        tensp = responseSanphams.get(i).getTensp();
                        giasp = responseSanphams.get(i).getGiasp();
                        hinhanhsp = responseSanphams.get(i).getHinhanhsp();
                        motasp = responseSanphams.get(i).getMotasp();
                        idsplt = responseSanphams.get(i).getIdsp();
                        mangLaptop.add(new Sanpham(id,tensp,giasp,hinhanhsp,motasp,idsplt));
                        laptopAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarLaptop);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

}
