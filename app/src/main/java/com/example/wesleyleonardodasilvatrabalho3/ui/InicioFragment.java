package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wesleyleonardodasilvatrabalho3.R;
import com.example.wesleyleonardodasilvatrabalho3.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);


        Bundle bundle = getArguments();
        binding.txtNomeUser.setText(bundle.getString("nome"));

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstaceState) {
        super.onViewCreated(view, savedInstaceState);

        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Wesley Leonardo da Silva" +
                        "\nRGA: 2020.1906.052-0" +
                        "\nDemonstração de um floating button";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        });


        binding.btnMediaPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InicioFragment.this)
                        .navigate(R.id.action_inicioFragment_to_mediaPlayerFragment);
            }
        });

        binding.btnExemploDeNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = "Notificação";
                String mensagem = "Exemplo de notificação";
                String canal = "Canal de notificação";
                int icone = R.drawable.ic_launcher_foreground;

                PendingIntent pendingIntent = new NavDeepLinkBuilder(getContext())
                        .setComponentName(MainActivity.class)
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.notificationFragment)
                        .setArguments(getArguments())
                        .createPendingIntent();


                String CHANNEL_ID = "01";
                String CHANNEL_NAME = "Notification Channel";

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                            CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                    final NotificationManager notificationManager = (NotificationManager)
                            view.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                        .setSmallIcon(icone)
                        .setContentTitle(titulo)
                        .setContentText(mensagem)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);


                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(view.getContext());
                notificationManager.notify(1, builder.build());

            }
        });

        binding.btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InicioFragment.this)
                        .navigate(R.id.action_inicioFragment_to_usersListFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}