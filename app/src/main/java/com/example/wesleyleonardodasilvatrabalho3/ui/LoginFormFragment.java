package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesleyleonardodasilvatrabalho3.database.AppDatabase;
import com.example.wesleyleonardodasilvatrabalho3.databinding.FragmentLoginFormBinding;
import com.example.wesleyleonardodasilvatrabalho3.entidades.User;


public class LoginFormFragment extends Fragment {

    private FragmentLoginFormBinding binding;
    private AppDatabase db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginFormBinding.inflate(inflater, container, false);

        db = AppDatabase.getInstance(getContext());

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
                onDestroyView();

            }
        });

        binding.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = binding.edtLogin.getText().toString();
                String senha = binding.edtSenha.getText().toString();
                String nome = binding.edtNome.getText().toString();

                if (login.isEmpty() || nome.isEmpty()) {
                    binding.edtLogin.setError("Preencha todos os campos");
                    binding.edtNome.setError("Preencha todos os campos");
                    return;
                }

                User user = new User(login, senha, nome);
                if (db.userDAO().getByLogin(login) != null) {
                    binding.edtLogin.setError("Login já cadastrado");
                    return;
                } else {
                    db.userDAO().insert(user);
                }

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