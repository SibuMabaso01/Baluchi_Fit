package com.example.baluchi;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchFrag extends Fragment {

    private EditText search;
    private RecyclerView rvSearch;
    private SearchAdapter itemAdapter;
    public List<SearchClassGetter> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.search);
        //Declare RecyclerView
        rvSearch = view.findViewById(R.id.rvSearch);
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));




        getdata();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchVal;
                searchVal = String.valueOf(search.getText());
                searchList(searchVal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void getdata () {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        itemList = new ArrayList<>();

        db.collection("Baluchi").document("Meal-Data")
                .collection("Meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String mName, mCategory, mPrice;
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                mName = String.valueOf(document.get("name"));
                                mCategory = String.valueOf(document.get("category"));
                                mPrice = String.valueOf(document.get("price"));

                                itemList.add(new SearchClassGetter(mName, mCategory,mPrice));

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                    }
                });

        itemAdapter = new SearchAdapter(itemList);
        rvSearch.setAdapter(itemAdapter);
    }

    private void searchList (String searchVal) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Baluchi").document("Meal-Data")
                .collection("Meals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String sName, sCategory, sPrice;
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                sName = String.valueOf(document.get("name"));
                                sCategory = String.valueOf(document.get("category"));
                                sPrice = String.valueOf(document.get("price"));
                                if(searchVal.contains(sCategory)){
                                    itemList.add(new SearchClassGetter(sName, sCategory, sPrice));
                                }
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        itemAdapter = new SearchAdapter(itemList);
        rvSearch.setAdapter(itemAdapter);
    }
}