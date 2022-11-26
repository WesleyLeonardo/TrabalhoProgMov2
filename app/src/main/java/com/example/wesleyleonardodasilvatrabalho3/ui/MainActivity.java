package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.wesleyleonardodasilvatrabalho3.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wesleyleonardodasilvatrabalho3.database.AppDatabase;
import com.example.wesleyleonardodasilvatrabalho3.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        db = AppDatabase.getInstance(getApplicationContext());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.info){

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.infoFragment);
        }

        if (id == R.id.mudarLogin){
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.loginFormFragment);
        }

        if (id == R.id.DropDatabase) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("APAGAR BANCO DE DADOS");
            builder.setMessage("Olha, não vou perguntar duas vezes, poderia, mas não vou. " +
                    "\nVocê tem certeza que quer apagar o banco de dados?");
            builder.setPositiveButton("Sim",(dialog, which) -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("APAGAR BANCO DE DADOS");
                builder1.setMessage("Na verdade, vou perguntar sim, só pra mostrar que eu posso. " +
                        "\nVocê tem certeza que quer apagar o banco de dados?");
                builder1.setPositiveButton("Sim", (dialog1, which1) -> {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    builder2.setTitle("APAGAR BANCO DE DADOS");
                    builder2.setMessage("Viu só, eu poderia perguntar quantas vezes quisesse. " +
                            "\nMas mais do que isso já perde a graça. " +
                            "\nTem certeza que quer apagar o banco de dados?");
                    builder2.setPositiveButton("Sim", (dialog2, which2) -> {
                        db.clearAllTables();
                        Toast.makeText(this, "Banco de dados apagado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    });
                    builder2.setNegativeButton("Não", (dialog2, which2) -> {
                        dialog2.dismiss();
                    });
                    builder2.show();
                });
                builder1.setNegativeButton("Não", (dialog1, which1) -> {
                    dialog1.dismiss();
                });
                builder1.show();
            });
            builder.setNegativeButton("Não", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }










    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}