package pl.nikowis.shopping.ui;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.PopupWindow;

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
                deleteItem(queryUtil);
            }
        });
        popupEditor.setActionButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupActionOnClick(queryUtil);
            }
        });

        popupEditor.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                clearDim((ViewGroup) getActivity().getWindow().getDecorView().getRootView());
            }
        });

        final List<Integer> images = Arrays.asList(IMAGES);

        shoppingAdapter = new ShoppingAdapter(list, getActivity(), new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditPopup(v, images);
                return true;
            }
        });

        recyclerView.setAdapter(shoppingAdapter);
        return mainFragment;
    }

    private void popupActionOnClick(ItemQueryUtil queryUtil) {
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
        hidePopupEditor();
        shoppingAdapter.notifyDataSetChanged();
    }

    private void showEditPopup(View v, List<Integer> images) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        ShoppingItem item = list.get(itemPosition);
        int index = images.indexOf(item.getImage());
        popupEditor.title.setText(item.getTitle());
        popupEditor.description.setText(item.getDescription());
        popupEditor.image.setSelection(index);
        popupEditor.editedItemIndex = itemPosition;
        showPopupEditor(EditorPopupWindow.WorkMode.EDITOR);
    }

    private void deleteItem(ItemQueryUtil queryUtil) {
        ShoppingItem item = list.get(popupEditor.editedItemIndex);
        list.remove(item);
        queryUtil.deleteItem(item);
        hidePopupEditor();
        shoppingAdapter.notifyDataSetChanged();
    }

    public void hidePopupEditor() {
        popupEditor.dismiss();
    }

    public void showPopupEditor(EditorPopupWindow.WorkMode mode) {
        popupEditor.showAtLocation(getActivity().findViewById(R.id.main_container), Gravity.CENTER, 10, 10, mode);
        applyDim((ViewGroup) getActivity().getWindow().getDecorView().getRootView(), 0.7f);
    }

    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        shoppingAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
