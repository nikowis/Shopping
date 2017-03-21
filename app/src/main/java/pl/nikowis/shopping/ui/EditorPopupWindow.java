package pl.nikowis.shopping.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;

import pl.nikowis.shopping.R;
import pl.nikowis.shopping.ShoppingItem;

/**
 * Created by Nikodem on 3/21/2017.
 */

public class EditorPopupWindow extends PopupWindow {

    private View popupLayout;
    private Button popupButton;
    private Spinner popupSpinner;
    private Integer[] images = new Integer[]{R.drawable.carrot, R.drawable.groceries
            , R.drawable.doughnut, R.drawable.turkey, R.drawable.washing_machine};
    final EditText title, description;

    public EditorPopupWindow(Context context) {
        super(context);
        popupLayout = LayoutInflater.from(context).inflate(R.layout.layout_edit_popup, null);
        setContentView(popupLayout);
        setFocusable(true);
        popupButton = (Button) popupLayout.findViewById(R.id.popup_button_add);
        popupSpinner = (Spinner) popupLayout.findViewById(R.id.popup_image_spinner);

        popupSpinner.setAdapter(new ImageArrayAdapter(context, images));
        title = (EditText) popupLayout.findViewById(R.id.popup_text_title);
        description = (EditText) popupLayout.findViewById(R.id.popup_text_description);
    }

    public void setAddButtonListener(View.OnClickListener listener) {
        popupButton.setOnClickListener(listener);
    }

    public void clearFields() {
        title.setText("");
        description.setText("");
    }

    public ShoppingItem commitFields() {
        return new ShoppingItem(title.getText().toString(), description.getText().toString(), (Integer) popupSpinner.getSelectedItem());
    }
}
