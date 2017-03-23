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

    protected WorkMode workMode;
    private Context context;
    private View popupLayout;
    private Button popupButton;
    protected Spinner image;
    private Integer[] images;
    final protected EditText title, description;
    protected Integer editedItemIndex;

    public EditorPopupWindow(Context context, Integer[] images) {
        super(context);
        this.images = images;
        this.context = context;
        popupLayout = LayoutInflater.from(context).inflate(R.layout.layout_edit_popup, null);
        setContentView(popupLayout);
        setFocusable(true);
        popupButton = (Button) popupLayout.findViewById(R.id.popup_button_add);
        image = (Spinner) popupLayout.findViewById(R.id.popup_image_spinner);
        workMode = WorkMode.ADDER;
        image.setAdapter(new ImageArrayAdapter(context, images));
        title = (EditText) popupLayout.findViewById(R.id.popup_text_title);
        description = (EditText) popupLayout.findViewById(R.id.popup_text_description);
    }

    public void setButtonListener(View.OnClickListener listener) {
        popupButton.setOnClickListener(listener);
    }

    private void clearFields() {
        title.setText("");
        description.setText("");
        image.setSelection(0);
        editedItemIndex = null;
    }

    public ShoppingItem commitFields() {
        return new ShoppingItem(title.getText().toString(), description.getText().toString(), (Integer) image.getSelectedItem());
    }

    public void showAtLocation(View parent, int gravity, int x, int y, WorkMode workMode) {
        this.workMode = workMode;
        if(WorkMode.ADDER.equals(workMode)) {
            popupButton.setText(context.getResources().getText(R.string.popup_button_add));
            clearFields();
        } else {
            popupButton.setText(context.getResources().getText(R.string.popup_button_edit));
        }
        super.showAtLocation(parent, gravity, x, y);
    }

    public enum WorkMode {
        EDITOR,
        ADDER
    }
}
