package com.example.foodapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.adapter.adapterPostMenu3;
import com.example.foodapp.data.DatabaseHelper;
import com.example.foodapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class Menu3Fragment extends Fragment {
    private RecyclerView recyclerView;
    private adapterPostMenu3 adapter;
    private List<Post> postList;
    private DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu3fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Sử dụng context của activity hoặc activity gốc
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        postList = new ArrayList<>();

        // Sử dụng context của activity hoặc activity gốc
        adapter = new adapterPostMenu3(getActivity(), postList);
        recyclerView.setAdapter(adapter);

        // Sử dụng context của activity hoặc activity gốc
        databaseHelper = new DatabaseHelper(getActivity());

        retrievePostsFromDatabase();

        return view;
    }

    private void retrievePostsFromDatabase() {
        postList.clear();
        postList.addAll(databaseHelper.getAllPosts());
        adapter.notifyDataSetChanged();
    }

}
