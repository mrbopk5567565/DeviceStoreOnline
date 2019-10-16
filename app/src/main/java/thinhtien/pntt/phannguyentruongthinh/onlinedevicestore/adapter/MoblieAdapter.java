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

public class MoblieAdapter extends RecyclerView.Adapter<MoblieAdapter.MoblieHolder> {

    ArrayList<Sanpham> mangSanphamMoblie;
    Context context;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MoblieAdapter(ArrayList<Sanpham> mangSanphamMoblie, Context context) {
        this.mangSanphamMoblie = mangSanphamMoblie;
        this.context = context;
    }

    @NonNull
    @Override
    public MoblieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mobile,null);
        return new MoblieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoblieHolder holder, int position) {
        holder.txtNameMoblie.setText(mangSanphamMoblie.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceMobile.setText("Giá : " + decimalFormat.format(mangSanphamMoblie.get(position).getGiasp()) + " Đ");
        holder.txtDesMobile.setMaxLines(2);
        holder.txtDesMobile.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtDesMobile.setText(mangSanphamMoblie.get(position).getMotasp());
        Glide.with(context)
                .load(mangSanphamMoblie.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgMoblie);
    }

    @Override
    public int getItemCount() {
        return mangSanphamMoblie != null ? mangSanphamMoblie.size() : 0;
    }

    class MoblieHolder extends RecyclerView.ViewHolder{

        ImageView imgMoblie;
        TextView txtNameMoblie, txtPriceMobile, txtDesMobile;

        public MoblieHolder(@NonNull final View itemView) {
            super(itemView);

            imgMoblie = itemView.findViewById(R.id.imageMobile);
            txtNameMoblie = itemView.findViewById(R.id.textviewNameMobile);
            txtPriceMobile = itemView.findViewById(R.id.textviewPriceMoblie);
            txtDesMobile = itemView.findViewById(R.id.textviewDesMoblie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClickItem(itemView, getLayoutPosition());
                }
            });
        }
    }
}
