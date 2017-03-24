package pl.nikowis.shopping.ui;

import android.app.Fragment;
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
import java.util.Arrays;
import java.util.List;

import pl.nikowis.shopping.R;
import pl.nikowis.shopping.ShoppingItem;
import pl.nikowis.shopping.db.ItemQueryUtil;
import pl.nikowis.shopping.db.ShoppingDbHelper;

/**
 * Fragment of the main activity.
 * Created by Nikodem on 3/17/2017.
 */

public class MainFragment extends Fragment {

    private static final Integer[] IMAGES = new Integer[]{R.drawable.carrot, R.drawable.groceries
            , R.drawable.doughnut, R.drawable.turkey, R.drawable.washing_machine};

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
        database = dbHelper.getWritableDatabase();
        ;
        final ItemQueryUtil queryUtil = new ItemQueryUtil(database);

        list = new ArrayList<>();
        list.addAll(queryUtil.getAllItems());

        View mainFragment = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) mainFragment.findViewById(R.id.shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        popupEditor = new EditorPopupWindow(getActivity(), IMAGES);
        popupEditor.setDeleteButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingItem item = list.get(popupEditor.editedItemIndex);
                list.remove(item);
                queryUtil.deleteItem(item);
                popupEditor.dismiss();
                shoppingAdapter.notifyDataSetChanged();
            }
        });
        popupEditor.setButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingItem newItem = popupEditor.commitFields();
                if (popupEditor.workMode.equals(EditorPopupWindow.WorkMode.ADDER)) {
                    queryUtil.addNewItem(newItem);
                    list.add(newItem);
                } else {
                    ShoppingItem oldItem = list.get(popupEditor.editedItemIndex);
                    oldItem.setDescription(newItem.getDescription());
                    oldItem.setTitle(newItem.getTitle());
                    oldItem.setImage(newItem.getImage());
                    queryUtil.saveItem(oldItem);
                }
                popupEditor.dismiss();
                shoppingAdapter.notifyDataSetChanged();
            }
        });

        final List<Integer> images = Arrays.asList(IMAGES);
        shoppingAdapter = new ShoppingAdapter(list, getActivity(), new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                ShoppingItem item = list.get(itemPosition);
                int index = images.indexOf(item.getImage());
                popupEditor.title.setText(item.getTitle());
                popupEditor.description.setText(item.getDescription());
                popupEditor.image.setSelection(index);
                popupEditor.editedItemIndex = itemPosition;
                showEditPopupEditor();
                return true;
            }
        });
        recyclerView.setAdapter(shoppingAdapter);
        return mainFragment;
    }

    public void showAddPopupEditor() {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10, EditorPopupWindow.WorkMode.ADDER);
    }

    public void showEditPopupEditor() {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10, EditorPopupWindow.WorkMode.EDITOR);
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
