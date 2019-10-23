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
import thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    ArrayList<Cart> mangCart;
    Context  context;

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
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.txtNameCart.setText(mangCart.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceCart.setText(decimalFormat.format(mangCart.get(position).getGiasp()));
        Glide.with(context)
                .load(mangCart.get(position).getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_image_error)
                .into(holder.imgCart);
        holder.btnValues.setText(mangCart.get(position).getSoluong());
    }

    @Override
    public int getItemCount() {
        return mangCart != null ? mangCart.size() : 0;
    }

    class CartHolder extends RecyclerView.ViewHolder {

        ImageView imgCart;
        TextView txtNameCart, txtPriceCart;
        Button btnMinus, btnValues, btnPlus;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imageCart);
            txtNameCart = itemView.findViewById(R.id.textviewNameCart);
            txtPriceCart = itemView.findViewById(R.id.textviewPriceCart);
            btnMinus = itemView.findViewById(R.id.buttonMinus);
            btnValues = itemView.findViewById(R.id.buttonValues);
            btnPlus = itemView.findViewById(R.id.buttonPlus);
        }
    }
}
