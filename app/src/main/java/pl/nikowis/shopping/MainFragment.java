package pl.nikowis.shopping;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the main activity.
 * Created by Nikodem on 3/17/2017.
 */

public class MainFragment extends Fragment {

    private List<ShoppingItem> list;
    private PopupWindow popupEditor;
    private View popupLayout;
    private Button popupButton;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainFragment = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) mainFragment.findViewById(R.id.shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        list.add(new ShoppingItem("tytul", "opis"));
        list.add(new ShoppingItem("tytul2", "opis2"));
        ShoppingAdapter adapter = new ShoppingAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        setupPopupEditor(inflater);
        return mainFragment;
    }

    private void setupPopupEditor(LayoutInflater inflater) {
        popupLayout = inflater.inflate(R.layout.layout_edit_popup, null);
        popupEditor = new PopupWindow(getActivity());
        popupEditor.setContentView(popupLayout);
        popupButton = (Button) popupLayout.findViewById(R.id.popup_button_add);
        popupEditor.setFocusable(true);
        final EditText title = (EditText) popupLayout.findViewById(R.id.popup_text_title);
        final EditText description = (EditText) popupLayout.findViewById(R.id.popup_text_description);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new ShoppingItem(title.getText().toString(), description.getText().toString()));
                popupEditor.dismiss();
                title.setText("");
                description.setText("");
            }
        });
    }

    public PopupWindow getPopupEditor() {
        return popupEditor;
    }

    public void showAddPopupEditor() {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10);
    }
}
