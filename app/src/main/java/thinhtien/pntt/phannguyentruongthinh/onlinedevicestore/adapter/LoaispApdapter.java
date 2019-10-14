package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.OnItemClickListener;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Loaisp;

public class LoaispApdapter extends RecyclerView.Adapter<LoaispApdapter.MenuHolder> {

    ArrayList<Loaisp> mangLoaiSanPham;
    Context context;
    private OnItemClickListener onItemClickListener;

    public LoaispApdapter(ArrayList<Loaisp> mangLoaiSanPham, Context context) {
        this.mangLoaiSanPham = mangLoaiSanPham;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu,null);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        holder.txtLoaisp.setText(mangLoaiSanPham.get(position).getTenLoaiSp());
        Glide.with(context)
                .load(mangLoaiSanPham.get(position).getHinhAnhLoaiSp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgLoaisp);
    }

    @Override
    public int getItemCount() {
        if (mangLoaiSanPham != null){
            return mangLoaiSanPham.size();
        }
        return 0;
    }

    class MenuHolder extends RecyclerView.ViewHolder {

        ImageView imgLoaisp;
        TextView txtLoaisp;
        public MenuHolder(@NonNull final View itemView) {
            super(itemView);

            imgLoaisp = itemView.findViewById(R.id.imageviewLoaisp);
            txtLoaisp = itemView.findViewById(R.id.textviewLoaisp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClickItem(itemView,getLayoutPosition());
                }
            });
        }
    }
}
