package pl.nikowis.shopping.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Nikodem on 3/19/2017.
 */

public class ImageArrayAdapter extends ArrayAdapter<Integer> {

    private Integer[] images;

    public ImageArrayAdapter(Context context, Integer[] images) {
        super(context, android.R.layout.simple_spinner_item, images);
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position);
    }

    private View getImageForPosition(int position) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(images[position]);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}
