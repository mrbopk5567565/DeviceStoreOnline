package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.R;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.CartActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.activity.MainActivity;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Cart;
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.util.OnItemClickListener;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    ArrayList<Cart> mangCart;
    Context  context;
    OnItemClickListener onItemClickListener;

    public void setOnItemLongClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public CartAdapter(ArrayList<Cart> mangCart, Context context) {
        this.mangCart = mangCart;
        this.context = context;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View  view = inflater.inflate(R.layout.item_cart,null);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartHolder holder, final int position) {
        holder.txtNameCart.setText(mangCart.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceCart.setText(decimalFormat.format(mangCart.get(position).getGiasp()));
        Glide.with(context)
                .load(mangCart.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgCart);
        holder.btnValues.setText(mangCart.get(position).getSoluong() + "");
        final int quantity = Integer.parseInt(holder.btnValues.getText().toString());
        if (quantity >= 10){
            holder.btnPlus.setVisibility(View.INVISIBLE);
            holder.btnMinus.setVisibility(View.VISIBLE);
        } else if (quantity <= 1){
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinus.setVisibility(View.INVISIBLE);
        } else if (quantity >= 1) {
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinus.setVisibility(View.VISIBLE);
        }
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_new = Integer.parseInt(holder.btnValues.getText().toString()) + 1;
                int quantity_current = MainActivity.mangCart.get(position).getSoluong();
                long price_current = MainActivity.mangCart.get(position).getGiasp();
                MainActivity.mangCart.get(position).setSoluong(quantity_new);
                long price_new = (price_current * quantity_new) / quantity_current;
                MainActivity.mangCart.get(position).setGiasp(price_new);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtPriceCart.setText(decimalFormat.format(mangCart.get(position).getGiasp()) + " Đ");

                // update totalprice all procducts
                CartActivity.EvenUtil();

                if (quantity_new > 9){
                    holder.btnPlus.setVisibility(View.INVISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnValues.setText(String.valueOf(quantity_new));
                } else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnValues.setText(String.valueOf(quantity_new));
                }
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_new = Integer.parseInt(holder.btnValues.getText().toString()) - 1;
                int quantity_current = MainActivity.mangCart.get(position).getSoluong();
                long price_current = MainActivity.mangCart.get(position).getGiasp();
                MainActivity.mangCart.get(position).setSoluong(quantity_new);
                long price_new = (price_current * quantity_new) / quantity_current;
                MainActivity.mangCart.get(position).setGiasp(price_new);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtPriceCart.setText(decimalFormat.format(mangCart.get(position).getGiasp()) + " Đ");

                // update totalprice all procducts
                CartActivity.EvenUtil();

                if (quantity_new < 2){
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.INVISIBLE);
                    holder.btnValues.setText(String.valueOf(quantity_new));
                } else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnValues.setText(String.valueOf(quantity_new));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangCart != null ? mangCart.size() : 0;
    }

    class CartHolder extends RecyclerView.ViewHolder {

        ImageView imgCart;
        TextView txtNameCart, txtPriceCart;
        Button btnMinus, btnValues, btnPlus;

        public CartHolder(@NonNull final View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imageCart);
            txtNameCart = itemView.findViewById(R.id.textviewNameCart);
            txtPriceCart = itemView.findViewById(R.id.textviewPriceCart);
            btnMinus = itemView.findViewById(R.id.buttonMinus);
            btnValues = itemView.findViewById(R.id.buttonValues);
            btnPlus = itemView.findViewById(R.id.buttonPlus);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onClickItem(itemView,getLayoutPosition());
                    return false;
                }
            });
        }
    }
}
