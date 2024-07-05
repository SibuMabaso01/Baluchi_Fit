package com.example.baluchi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionsFrag extends Fragment {

   private Button profile, cart, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        profile = view.findViewById(R.id.profile);
        cart = view.findViewById(R.id.cart);
        logout = view.findViewById(R.id.logout);

        //Go to rpofile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileH = new Intent(view.getContext(), Profile.class);
                startActivity(profileH);
            }
        });

        //Going to cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent cartOut = new Intent(view.getContext(), );
                //startActivity(cartOut);
            }
        });

        //Logging out
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(view.getContext(), Welcome_page.class);
                startActivity(welcome);
            }
        });

        return view;


    }
}