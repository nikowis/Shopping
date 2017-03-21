package pl.nikowis.shopping;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the main activity.
 * Created by Nikodem on 3/17/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainFragment extends Fragment {

    private List<ShoppingItem> list;
    private EditorPopupWindow popupEditor;
    private RecyclerView recyclerView;
    private ShoppingAdapter shoppingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainFragment = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) mainFragment.findViewById(R.id.shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        popupEditor = new EditorPopupWindow(getActivity());
        popupEditor.setAddButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(popupEditor.commitFields());
                popupEditor.dismiss();
                popupEditor.clearFields();
                shoppingAdapter.notifyDataSetChanged();
            }
        });
        list = new ArrayList<>();
        list.add(new ShoppingItem("tytul", "opis", R.drawable.carrot));
        list.add(new ShoppingItem("tytul2", "opis2", R.drawable.groceries));
        shoppingAdapter = new ShoppingAdapter(list, getActivity());
        recyclerView.setAdapter(shoppingAdapter);
        return mainFragment;
    }

    public void showAddPopupEditor() {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10);
    }
}
