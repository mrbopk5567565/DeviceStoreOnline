package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.MainActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.MobileActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.MoblieAdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnListenId;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.viewmodel.SanphamViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileFragment extends Fragment {

    View view;
    Toolbar toolbarmoblie;
    RecyclerView recyclerViewMobile;
    MoblieAdapter moblieAdapter;
    ArrayList<Sanpham> mangMobile;
    int page = 1;
    SanphamViewModel loaispViewModel;
    View viewfooter;
    Boolean isLoading = false;
    int firstItem, visibleItem, totalItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mobile, container, false);

        Anhxa();

        if(CheckConnection.isNetworkAvailable(getActivity())){
            ActionToolbar();
            ((MobileActivity)getActivity()).setListenId(new OnListenId() {
                @Override
                public void onChangeId(Integer idsp) {
                    GetData(String.valueOf(page),idsp);
                }
            });
            LoadMoreData();
        } else {
            CheckConnection.showToast_Short(getActivity(),"Xin kiểm tra lại kết nối");
            getActivity().finish();
        }


        return view;
    }

    private void LoadMoreData() {
        recyclerViewMobile.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItem = (new LinearLayoutManager(getActivity())).getChildCount();
                firstItem = (new LinearLayoutManager(getActivity())).findFirstVisibleItemPosition();
                totalItem = (new LinearLayoutManager(getActivity())).getItemCount();
                if (firstItem + visibleItem == totalItem && totalItem != 0 && isLoading == false){

                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void GetData(String page, int id_moblie) {
        loaispViewModel = new SanphamViewModel();
        loaispViewModel.checkLoaiSanPhamMobile(page, id_moblie).observe(getActivity(), new Observer<List<ResponseSanpham>>() {
            @Override
            public void onChanged(List<ResponseSanpham> responseSanphams) {
                if (responseSanphams != null){
                    int id = 0;
                    String tensp = "";
                    int giasp = 0;
                    String hinhanhsp = "";
                    String motasp = "";
                    int idspdt = 0;
                    for (int i = 0; i < responseSanphams.size(); i++){
                        id = responseSanphams.get(i).getId();
                        tensp = responseSanphams.get(i).getTensp();
                        giasp = responseSanphams.get(i).getGiasp();
                        hinhanhsp = responseSanphams.get(i).getHinhanhsp();
                        motasp = responseSanphams.get(i).getMotasp();
                        idspdt = responseSanphams.get(i).getIdsp();
                        mangMobile.add(new Sanpham(id,tensp,giasp,hinhanhsp,motasp,idspdt));
                        moblieAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarmoblie);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmoblie.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void Anhxa() {
        toolbarmoblie = view.findViewById(R.id.toolbarMoblie);
        recyclerViewMobile = view.findViewById(R.id.recyclerviewMobile);
        mangMobile = new ArrayList<>();
        moblieAdapter = new MoblieAdapter(mangMobile,getContext());
        recyclerViewMobile.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMobile.setAdapter(moblieAdapter);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        viewfooter = inflater.inflate(R.layout.progressbar,null);
    }
}
