package pl.nikowis.shopping.ui;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.nikowis.shopping.R;
import pl.nikowis.shopping.ShoppingItem;
import pl.nikowis.shopping.db.ItemEntry;
import pl.nikowis.shopping.db.ItemQueryUtil;
import pl.nikowis.shopping.db.ShoppingDbHelper;

/**
 * Fragment of the main activity.
 * Created by Nikodem on 3/17/2017.
 */

public class MainFragment extends Fragment {

    private List<ShoppingItem> list;
    private EditorPopupWindow popupEditor;
    private RecyclerView recyclerView;
    private ShoppingAdapter shoppingAdapter;
    private SQLiteDatabase database;
    private ShoppingDbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbHelper = new ShoppingDbHelper(getActivity());
        database = dbHelper.getWritableDatabase();;
        final ItemQueryUtil queryUtil = new ItemQueryUtil(database);

        list = new ArrayList<>();
        list.addAll(queryUtil.getAllItems());

        View mainFragment = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) mainFragment.findViewById(R.id.shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        popupEditor = new EditorPopupWindow(getActivity());
        popupEditor.setAddButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingItem shoppingItem = popupEditor.commitFields();
                list.add(shoppingItem);
                queryUtil.addNewItem(shoppingItem);
                popupEditor.dismiss();
                popupEditor.clearFields();
                shoppingAdapter.notifyDataSetChanged();
            }
        });

        shoppingAdapter = new ShoppingAdapter(list, getActivity());
        recyclerView.setAdapter(shoppingAdapter);
        return mainFragment;
    }

    public void showAddPopupEditor() {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10);
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
