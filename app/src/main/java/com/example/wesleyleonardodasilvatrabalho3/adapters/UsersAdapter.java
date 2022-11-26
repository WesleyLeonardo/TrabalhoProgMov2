package com.example.wesleyleonardodasilvatrabalho3.adapters;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wesleyleonardodasilvatrabalho3.R;
import com.example.wesleyleonardodasilvatrabalho3.database.AppDatabase;
import com.example.wesleyleonardodasilvatrabalho3.entidades.User;

import java.util.List;

public class UsersAdapter extends BaseAdapter {
    private Context context;
    private List<User> users;
    private AppDatabase db;

    public UsersAdapter(Context context, List<User> users) {
        setContext(context);
        setUsers(users);
        db = AppDatabase.getInstance(context);
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getIdLogin();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        TextView nome = v.findViewById(R.id.txtNome);
        TextView login = v.findViewById(R.id.txtLogin);
        nome.setText(users.get(position).getNome());
        login.setText(users.get(position).getLogin());
        return v;
    }

    //Getters e Setters


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
