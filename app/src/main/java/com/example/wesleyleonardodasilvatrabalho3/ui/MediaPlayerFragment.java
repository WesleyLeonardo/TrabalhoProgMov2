package com.example.wesleyleonardodasilvatrabalho3.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.wesleyleonardodasilvatrabalho3.R;
import com.example.wesleyleonardodasilvatrabalho3.adapters.MediaPlayerGridAdapter;
import com.example.wesleyleonardodasilvatrabalho3.databinding.FragmentMediaPlayerBinding;

public class MediaPlayerFragment extends Fragment {

    private FragmentMediaPlayerBinding binding;

    private MediaPlayer mediaPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMediaPlayerBinding.inflate(inflater, container, false);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.musicas,
                android.R.layout.simple_spinner_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                switch (position) {
                    case 0:
                        return;
                    case 1:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.canon_in_e);
                        break;

                    case 2:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.mitorama_mestres_araucarias);
                        break;

                    case 3:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.greensleeves_music_box);
                        break;
                }


                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    binding.switchPausePlay.setChecked(true);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            binding.switchPausePlay.setChecked(false);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.switchPausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer == null) {
                    binding.switchPausePlay.setChecked(false);
                } else if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    binding.switchPausePlay.setChecked(false);
                } else {
                    mediaPlayer.start();
                    binding.switchPausePlay.setChecked(true);
                }
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                    binding.switchPausePlay.setChecked(false);
                }
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                onDestroyView();
                getActivity().onBackPressed();

            }
        });

        int[] images = new int[]{R.drawable.entao_agora_voce_esta_sabendo,
                R.drawable.windows_xp, R.drawable.brasil};

        binding.gridViewMediaPlayer.setAdapter(new MediaPlayerGridAdapter(getContext(), images));

        binding.gridViewMediaPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                switch (position) {
                    case 0:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.entao_agora_voce_esta_sabendo_sound_effect);
                        binding.txtImageSoundLegenda.setText(R.string.entao_agora_voce_esta_sabendo);
                        break;

                    case 1:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.windows_xp_sound_effect);
                        binding.txtImageSoundLegenda.setText("Windows XP - Start Up Sound Effect");
                        break;

                    case 2:
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.brasil_sound_effect);
                        binding.txtImageSoundLegenda.setText("Brasil sil sil!");
                        break;
                }

                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            binding.txtImageSoundLegenda.setText("");
                        }
                    });
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