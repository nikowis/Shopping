package pl.nikowis.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the main activity.
 * Created by Nikodem on 3/17/2017.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ShoppingItem> list = new ArrayList<>();
        list.add(new ShoppingItem("tytul", "opis"));
        list.add(new ShoppingItem("tytul2", "opis2"));
        ShoppingAdapter adapter = new ShoppingAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
