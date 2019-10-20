package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopHolder> {

    ArrayList<Sanpham> mangSanphamLaptop;
    Context context;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public LaptopAdapter(ArrayList<Sanpham> mangSanphamLaptop, Context context) {
        this.mangSanphamLaptop = mangSanphamLaptop;
        this.context = context;
    }

    @NonNull
    @Override
    public LaptopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_laptop,null);
        return new LaptopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopHolder holder, int position) {
        holder.txtNameLaptop.setText(mangSanphamLaptop.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceLaptop.setText("Giá : " + decimalFormat.format(mangSanphamLaptop.get(position).getGiasp()) + " Đ");
        holder.txtDesLaptop.setMaxLines(2);
        holder.txtDesLaptop.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtDesLaptop.setText(mangSanphamLaptop.get(position).getMotasp());
        Glide.with(context)
                .load(mangSanphamLaptop.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgLaptop);
    }

    @Override
    public int getItemCount() {
        return mangSanphamLaptop != null ? mangSanphamLaptop.size() : 0;
    }

    class LaptopHolder extends RecyclerView.ViewHolder {

        ImageView imgLaptop;
        TextView txtNameLaptop, txtPriceLaptop, txtDesLaptop;

        public LaptopHolder(@NonNull final View itemView) {
            super(itemView);

            imgLaptop = itemView.findViewById(R.id.imageLaptop);
            txtNameLaptop = itemView.findViewById(R.id.textviewNameLaptop);
            txtPriceLaptop = itemView.findViewById(R.id.textviewPriceLaptop);
            txtDesLaptop = itemView.findViewById(R.id.textviewDesLaptop);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClickItem(itemView,getLayoutPosition());
                }
            });
        }
    }
}
