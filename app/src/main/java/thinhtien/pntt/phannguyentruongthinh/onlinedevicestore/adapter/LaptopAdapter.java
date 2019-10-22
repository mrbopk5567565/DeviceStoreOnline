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

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopHolder> implements Filterable {

    ArrayList<Sanpham> mangSanphamLaptop;
    ArrayList<Sanpham> mangSanphamLaptopFiltered;
    Context context;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public LaptopAdapter(ArrayList<Sanpham> mangSanphamLaptop, Context context) {
        this.mangSanphamLaptop = mangSanphamLaptop;
        this.context = context;
        this.mangSanphamLaptopFiltered =  mangSanphamLaptop;
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

        // setAnimation
        holder.imgLaptop.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        // set linearlayout_animation_item_mobile
        holder.linearlayout_animation_item_laptop.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        holder.txtNameLaptop.setText(mangSanphamLaptopFiltered.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceLaptop.setText("Giá : " + decimalFormat.format(mangSanphamLaptopFiltered.get(position).getGiasp()) + " Đ");
        holder.txtDesLaptop.setMaxLines(2);
        holder.txtDesLaptop.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtDesLaptop.setText(mangSanphamLaptopFiltered.get(position).getMotasp());
        Glide.with(context)
                .load(mangSanphamLaptopFiltered.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgLaptop);
    }

    @Override
    public int getItemCount() {
        return mangSanphamLaptopFiltered != null ? mangSanphamLaptopFiltered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if (key.isEmpty()){
                    mangSanphamLaptopFiltered = mangSanphamLaptop;
                } else {
                    ArrayList<Sanpham> mangFilterd = new ArrayList<>();
                    for (Sanpham sp : mangSanphamLaptop){
                        if (sp.getTensp().toLowerCase().contains(key.toLowerCase())){
                            mangFilterd.add(sp);
                        }
                    }
                    mangSanphamLaptopFiltered = mangFilterd;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mangSanphamLaptopFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mangSanphamLaptopFiltered = (ArrayList<Sanpham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class LaptopHolder extends RecyclerView.ViewHolder {

        ImageView imgLaptop;
        TextView txtNameLaptop, txtPriceLaptop, txtDesLaptop;
        LinearLayout linearlayout_animation_item_laptop;

        public LaptopHolder(@NonNull final View itemView) {
            super(itemView);
            linearlayout_animation_item_laptop = itemView.findViewById(R.id.linearlayout_animation_item_laptop);
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
