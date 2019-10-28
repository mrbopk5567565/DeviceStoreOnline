package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Sanpham;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;

public class MoblieAdapter extends RecyclerView.Adapter<MoblieAdapter.MoblieHolder> implements Filterable {

    ArrayList<Sanpham> mangSanphamMoblie;
    ArrayList<Sanpham> mangSanphamMoblieFiltered;
    Context context;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MoblieAdapter(ArrayList<Sanpham> mangSanphamMoblie, Context context) {
        this.mangSanphamMoblie = mangSanphamMoblie;
        this.context = context;
        this.mangSanphamMoblieFiltered = mangSanphamMoblie;
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

        // setAnimation
        holder.imgMoblie.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        // set linearlayout_animation_item_mobile
        holder.linearlayout_animation_item_mobile.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        holder.txtNameMoblie.setText(mangSanphamMoblieFiltered.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceMobile.setText("Giá : " + decimalFormat.format(mangSanphamMoblieFiltered.get(position).getGiasp()) + " Đ");
        holder.txtDesMobile.setMaxLines(2);
        holder.txtDesMobile.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtDesMobile.setText(mangSanphamMoblieFiltered.get(position).getMotasp());
        Glide.with(context)
                .load(mangSanphamMoblieFiltered.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgMoblie);
    }

    @Override
    public int getItemCount() {
        return mangSanphamMoblieFiltered != null ? mangSanphamMoblieFiltered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if (key.isEmpty()){
                    mangSanphamMoblieFiltered = mangSanphamMoblie;
                } else {
                    ArrayList<Sanpham> mangFiltered = new ArrayList<>();
                    for (Sanpham sp : mangSanphamMoblie){
                        if (sp.getTensp().toLowerCase().contains(key.toLowerCase())){
                            mangFiltered.add(sp);
                        }
                    }
                    mangSanphamMoblieFiltered = mangFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mangSanphamMoblieFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                mangSanphamMoblieFiltered = (ArrayList<Sanpham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class MoblieHolder extends RecyclerView.ViewHolder{

        ImageView imgMoblie;
        TextView txtNameMoblie, txtPriceMobile, txtDesMobile;
        LinearLayout linearlayout_animation_item_mobile;

        public MoblieHolder(@NonNull final View itemView) {
            super(itemView);
            linearlayout_animation_item_mobile = itemView.findViewById(R.id.linearlayout_animation_item_mobile);
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
