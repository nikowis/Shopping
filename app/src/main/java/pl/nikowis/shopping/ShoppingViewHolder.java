package pl.nikowis.shopping;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingViewHolder extends RecyclerView.ViewHolder {

    protected TextView mTitleView;
    protected TextView mDescriptionView;
    protected ImageView imageView;

    public ShoppingViewHolder(View itemView) {
        super(itemView);
        this.mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        this.mDescriptionView = (TextView) itemView.findViewById(R.id.item_description);
        this.imageView = (ImageView) itemView.findViewById(R.id.item_image);
    }
}
