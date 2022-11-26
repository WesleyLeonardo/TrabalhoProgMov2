package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wesleyleonardodasilvatrabalho3.R;
import com.example.wesleyleonardodasilvatrabalho3.adapters.UsersAdapter;
import com.example.wesleyleonardodasilvatrabalho3.database.AppDatabase;
import com.example.wesleyleonardodasilvatrabalho3.databinding.FragmentUsersListBinding;
import com.example.wesleyleonardodasilvatrabalho3.entidades.User;

import java.util.List;


public class UsersListFragment extends Fragment {

    private FragmentUsersListBinding binding;
    private AppDatabase db;
    private UsersAdapter adapter;
    private List<User> users;
    int ordem = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUsersListBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(getContext());
        users = db.userDAO().getAll();
        adapter = new UsersAdapter(getContext(), users);
        binding.listUsers.setAdapter(adapter);

        binding.btnOrdenarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ordem == 0) {
                    users = db.userDAO().getOrganizado();
                    adapter = new UsersAdapter(getContext(), users);
                    binding.listUsers.setAdapter(adapter);
                    binding.btnOrdenarUser.setText("Por Cadastro");
                    ordem = 1;
                } else {
                    users = db.userDAO().getAll();
                    adapter = new UsersAdapter(getContext(), users);
                    binding.listUsers.setAdapter(adapter);
                    binding.btnOrdenarUser.setText("Por Nome");
                    ordem = 0;
                }
            }
        });

        binding.btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UsersListFragment.this)
                        .navigate(R.id.action_usersListFragment_to_loginFormFragment);
            }
        });

        binding.btnUsersVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                onDestroyView();



            }
        });




        return binding.getRoot();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}