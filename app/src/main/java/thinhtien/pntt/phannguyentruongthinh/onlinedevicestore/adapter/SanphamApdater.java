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

import java.text.DecimalFormat;
import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;

public class SanphamApdater extends RecyclerView.Adapter<SanphamApdater.SanphamHolder> {

    ArrayList<Sanpham> mangSanpham;
    OnItemClickListener onItemClickListener;
    Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SanphamApdater(ArrayList<Sanpham> mangSanpham, Context context) {
        this.mangSanpham = mangSanpham;
        this.context = context;
    }

    @NonNull
    @Override
    public SanphamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_sanphammoinhat,null);
        return new SanphamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanphamHolder holder, int position) {
        holder.txtTensp.setText(mangSanpham.get(position).getTensp());
        Glide.with(context)
                    .load(mangSanpham.get(position).getHinhanhsp())
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.ic_image_error)
                    .into(holder.imgHinhanhsp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiasp.setText("Giá : " + decimalFormat.format(mangSanpham.get(position).getGiasp()) + " Đ");
    }

    @Override
    public int getItemCount() {
        return mangSanpham != null ? mangSanpham.size() : 0;
    }

    class SanphamHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhanhsp;
        TextView txtTensp, txtGiasp;

        public SanphamHolder(@NonNull final View itemView) {
            super(itemView);

            imgHinhanhsp = itemView.findViewById(R.id.imagesanpham);
            txtTensp = itemView.findViewById(R.id.textviewtensanpham);
            txtGiasp = itemView.findViewById(R.id.textviewgiasanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClickItem(itemView, getLayoutPosition());
                }
            });
        }
    }
}
