package com.example.baluchi;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuFrag extends Fragment {

    TextView show;
    private RecyclerView rvMenu;
    private MenuAdapter itemAdapter;
    private List<MenuClassGetter> itemList;

    private List<MenuClassGetter> demoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        itemList = new ArrayList<>();
        demoList = new ArrayList<>();

        show = view.findViewById(R.id.show);

        rvMenu = view.findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

                                show.append(mName + mCategory +  mPrice + "\n\n");
                                itemList.add(new MenuClassGetter(mName, mCategory,mPrice));

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                           show.append("Data not found");
                        }
                    }
                });

//        for (int i = 0; i < demoList.size(); i++ ){
//            itemList.add(demoList.get(i));
//        }
//        show.append(itemList.toString());


        itemAdapter = new MenuAdapter(itemList);
        rvMenu.setAdapter(itemAdapter);


        return view;
    }

}