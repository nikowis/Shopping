package pl.nikowis.shopping.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.nikowis.shopping.R;
import pl.nikowis.shopping.ShoppingItem;
import pl.nikowis.shopping.ui.ShoppingViewHolder;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingAdapter  extends RecyclerView.Adapter<ShoppingViewHolder> {


    private List<ShoppingItem> list;
    private Context context;
    private View.OnLongClickListener itemLongClickListener;

    public ShoppingAdapter(List<ShoppingItem> list, Context context, View.OnLongClickListener itemLongClickListener) {
        this.list = list;
        this.context = context;
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shopping_item, null);
        ShoppingViewHolder shoppingViewHolder = new ShoppingViewHolder(view);
        return shoppingViewHolder;
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, int position) {
        ShoppingItem shoppingItem = list.get(position);
        holder.itemView.setOnLongClickListener(itemLongClickListener);
        holder.mTitleView.setText(shoppingItem.getTitle());
        holder.mDescriptionView.setText(shoppingItem.getDescription());
        holder.imageView.setImageResource(shoppingItem.getResId());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
