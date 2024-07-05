package com.example.baluchi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class CategoryFrag extends Fragment {

    private FirebaseAuth mAuth;
    private RecyclerView rvCategory;
    private SearchAdapter itemAdapter;
    public List<SearchClassGetter> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        //Declare RecyclerView
        rvCategory = view.findViewById(R.id.rvCategory);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}