package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesleyleonardodasilvatrabalho3.R;
import com.example.wesleyleonardodasilvatrabalho3.database.AppDatabase;
import com.example.wesleyleonardodasilvatrabalho3.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        db = AppDatabase.getInstance(getContext());

        if (db.userDAO().getAll().size() == 0) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Nenhum usuário cadastrado")
                    .setMessage("Cadastre um usuário para continuar")
                    .setPositiveButton("OK", null)
                    .show();
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_loginFragment_to_loginFormFragment);
        }

        binding.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = binding.edtLoginScreen.getText().toString();
                String senha = binding.edtSenhaScreen.getText().toString();

                if (db.userDAO().login(login, senha) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("login", login);
                    bundle.putString("senha", senha);
                    bundle.putString("nome", db.userDAO().login(login, senha).getNome());

                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_loginFragment_to_inicioFragment, bundle);
                    binding.edtLoginScreen.setText("");
                    binding.edtSenhaScreen.setText("");
                    onDestroyView();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Erro");
                    builder.setMessage("Login ou senha incorretos");
                    builder.setPositiveButton("OK", null);
                    builder.show();

                }

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

