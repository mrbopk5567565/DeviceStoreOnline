package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.DetailProductActivity;
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
    Toolbar toolbarMobile;
    RecyclerView recyclerViewMoblie;
    MoblieAdapter moblieAdapter;
    ArrayList<Sanpham> mangMoblie;
    SanphamViewModel sanphamViewModel;
    Boolean isLoading = false;
    Boolean limitData = false;
    MutableLiveData<Boolean> enableLoadmore = new MutableLiveData<>();
    int page = 1;
    LinearLayoutManager linearLayoutManager;
    int firstItem, visibleItem, totalItem;
    ProgressBar progressBar;
    int id_mobile = 0;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mobile, container, false);

        Anhxa();

        if (CheckConnection.isNetworkAvailable(getActivity())){
            ActionToolbar();
            ((MobileActivity)getActivity()).setListenId(new OnListenId() {
                @Override
                public void onChangeId(Integer idsp) {
                    id_mobile = idsp;
                    getData(page + "", idsp);
                }
            });
            SearchMobile();
            if (recyclerViewMoblie != null){
                LoadMoreData();
            }

//            enableLoadmore.observe(this, new Observer<Boolean>() {
//                @Override
//                public void onChanged(Boolean aBoolean) {
//                    if (aBoolean){
//                        LoadMoreData();
//                    }
//                }
//            });
        } else {
            CheckConnection.showToast_Short(getActivity(),"Check your connection !!!");
            getActivity().finish();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void SearchMobile() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((MoblieAdapter)recyclerViewMoblie.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });
    }

    private void LoadMoreData() {

        ((MoblieAdapter)recyclerViewMoblie.getAdapter()).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                intent.putExtra("InformationProduct",mangMoblie.get(position));
                startActivity(intent);
            }
        });

        recyclerViewMoblie.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                firstItem = ((LinearLayoutManager)(recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                visibleItem = ((LinearLayoutManager)(recyclerView.getLayoutManager())).getChildCount();
                totalItem = ((LinearLayoutManager)(recyclerView.getLayoutManager())).getItemCount();
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
                getData(++page + "", id_mobile);
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    private void getData(String page, Integer id_mobile) {
        sanphamViewModel.checkLoaiSanPhamMobile(page,id_mobile).observe(getActivity(), new Observer<List<ResponseSanpham>>() {
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
                        id = responseSanphams.get(0).getId();
                        tensp = responseSanphams.get(i).getTensp();
                        giasp = responseSanphams.get(i).getGiasp();
                        hinhanhsp = responseSanphams.get(i).getHinhanhsp();
                        motasp = responseSanphams.get(i).getMotasp();
                        idspdt = responseSanphams.get(i).getIdsp();
                        mangMoblie.add(new Sanpham(id,tensp,giasp,hinhanhsp,motasp,idspdt));
                        moblieAdapter.notifyDataSetChanged();
                    }
                    enableLoadmore.setValue(true);
                } else {
                    limitData = true;
//                    Toast.makeText(getActivity(), "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarMobile);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMobile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void Anhxa() {
        toolbarMobile = view.findViewById(R.id.toolbarMoblie);
        recyclerViewMoblie = view.findViewById(R.id.recyclerviewMobile);
        mangMoblie = new ArrayList<>();
        moblieAdapter = new MoblieAdapter(mangMoblie,getActivity());
        sanphamViewModel = new SanphamViewModel();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMoblie.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMoblie.setAdapter(moblieAdapter);
        progressBar = view.findViewById(R.id.progressbarMobile);
        searchView = view.findViewById(R.id.search_mobile);
    }
}