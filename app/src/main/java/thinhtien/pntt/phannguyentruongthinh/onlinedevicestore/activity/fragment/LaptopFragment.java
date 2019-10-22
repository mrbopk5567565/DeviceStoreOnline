package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.LaptopActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.LaptopAdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;
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
    Boolean isLoading = false;
    Boolean limitData = false;
    int page = 1;
    LinearLayoutManager linearLayoutManager;
    int firstItem, visibleItem, totalItem;
    ProgressBar progressBar;
    int id_laptop = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_laptop, container, false);

        AnhXa();

        if (CheckConnection.isNetworkAvailable(getActivity())){
            ActionToolbar();
            ((LaptopActivity)getActivity()).setListenId(new OnListenId() {
                @Override
                public void onChangeId(Integer idsp) {
                    id_laptop = idsp;
                    getData(page + "",idsp);
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

        //  su kien click item
        ((LaptopAdapter)recyclerViewLaptop.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {

            }
        });

        recyclerViewLaptop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isLoading = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstItem = linearLayoutManager.findFirstVisibleItemPosition();
                visibleItem = linearLayoutManager.getChildCount();
                totalItem = linearLayoutManager.getItemCount();
                if ((firstItem + visibleItem == totalItem) && isLoading && (limitData == false)){
                    isLoading = false;
                    FetchData();
                }
            }
        });
    }

    private void FetchData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(++page + "",id_laptop);
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    private void getData(String page, Integer id_laptop) {
        loaiSPViewModel.checkLoaiSanPhamLaptop(page,id_laptop).observe(getActivity(), new Observer<List<ResponseSanpham>>() {
            @Override
            public void onChanged(List<ResponseSanpham> responseSanphams) {
                if (responseSanphams != null && responseSanphams.size() > 0){
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
                } else {
                    limitData = true;
                    Toast.makeText(getActivity(), "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
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

    private void AnhXa() {
        mangLaptop = new ArrayList<>();
        toolbarLaptop = view.findViewById(R.id.toolbarLaptop);
        recyclerViewLaptop = view.findViewById(R.id.recyclerviewLaptop);
        laptopAdapter = new LaptopAdapter(mangLaptop,getActivity());
        recyclerViewLaptop.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewLaptop.setAdapter(laptopAdapter);
        loaiSPViewModel = new SanphamViewModel();
        linearLayoutManager = (LinearLayoutManager) recyclerViewLaptop.getLayoutManager();
        progressBar = view.findViewById(R.id.progressbarLaptop);
    }
}
