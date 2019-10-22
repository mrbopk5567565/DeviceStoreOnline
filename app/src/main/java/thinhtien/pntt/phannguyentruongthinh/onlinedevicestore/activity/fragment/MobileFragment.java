package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.content.Context;
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

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.DetailProductActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.MainActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.MobileActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter.MoblieAdapter;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi.ResponseSanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.CheckConnection;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;
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
    Boolean limitData = false;
    ProgressBar progressBar;
    int firstItem, visibleItem, totalItem;
    LinearLayoutManager linearLayoutManager;
//    mHandler mHandler;

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

        // bat su kien click vao item
        ((MoblieAdapter)recyclerViewMobile.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                intent.putExtra("InformationProduct", mangMobile.get(position));
                startActivity(intent);
            }
        });

        recyclerViewMobile.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                visibleItem = linearLayoutManager.getChildCount();
                firstItem = linearLayoutManager.findFirstVisibleItemPosition();
                totalItem = linearLayoutManager.getItemCount();
                if ((firstItem + visibleItem == totalItem) && isLoading && limitData == false){
                    isLoading = false;
//                    Log.d("BBB","1");
                    fetchData();
//                    GetData(++page + "",1);
                }
            }
        });
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
//        Log.d("BBB","2");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                ((MobileActivity)getActivity()).setListenId(new OnListenId() {
//                    @Override
//                    public void onChangeId(Integer idsp) {
////                        Log.d("BBB","3");
//                        GetData(++page + "",idsp);
//                        progressBar.setVisibility(View.GONE);
////                        Log.d("BBB","4");
//                    }
//                });
                GetData(++page + "",1);
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void GetData(String page, int id_moblie) {
//        loaispViewModel = new SanphamViewModel();
        loaispViewModel.checkLoaiSanPhamMobile(page, id_moblie).observe(getActivity(), new Observer<List<ResponseSanpham>>() {
            @Override
            public void onChanged(List<ResponseSanpham> responseSanphams) {
                if (responseSanphams != null && responseSanphams.size() > 0){
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
                } else {
                    limitData = true;
                    Toast.makeText(getActivity(), "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
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
//        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////                LayoutInflater.from(getActivity());
//        viewfooter = inflater.inflate(R.layout.progressbar,null);
//        mHandler = new mHandler();
        progressBar = view.findViewById(R.id.progressbarMobile);
        linearLayoutManager = (LinearLayoutManager) recyclerViewMobile.getLayoutManager();
        loaispViewModel = new SanphamViewModel();
    }

//    public class mHandler extends Handler{
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    recyclerViewMobile.addView(viewfooter, mangMobile.size());
//                    break;
//                case 1:
//                    ((MobileActivity)getActivity()).setListenId(new OnListenId() {
//                        @Override
//                        public void onChangeId(Integer idsp) {
//                            page++;
//                            GetData(String.valueOf(page),idsp);
//                            isLoading = false;
//                        }
//                    });
//                    break;
//            }
//        }
//    }
//
//    public class ThreadData extends Thread{
//        @Override
//        public void run() {
//            super.run();
//
//        }
//    }
}
