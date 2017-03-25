package pl.nikowis.shopping.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.nikowis.shopping.R;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingViewHolder extends RecyclerView.ViewHolder {

    protected TextView mTitleView;
    protected TextView mDescriptionView;
    protected ImageView imageView;

    public ShoppingViewHolder(View itemView, int fontSize, String fontColor) {
        super(itemView);
        this.mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        this.mDescriptionView = (TextView) itemView.findViewById(R.id.item_description);
        this.imageView = (ImageView) itemView.findViewById(R.id.item_image);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        mTitleView.setTextColor(Color.parseColor(fontColor));
        mDescriptionView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        mDescriptionView.setTextColor(Color.parseColor(fontColor));
    }
}
