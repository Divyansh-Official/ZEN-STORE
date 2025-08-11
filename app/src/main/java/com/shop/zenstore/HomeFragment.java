package com.shop.zenstore;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.shop.zenstore.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private boolean isShrunk = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.menuButton.setOnClickListener(v -> {
            if (!isShrunk) {
                // Shrink and move
                binding.homeFragment.animate()
                        .scaleX(0.6f)
                        .scaleY(0.6f)
                        .translationX(-400f)  // left shift
                        .translationY(100f)   // down shift
                        .setDuration(300)
                        .start();

                binding.homeFragment.setBackground(
                        ContextCompat.getDrawable(getContext(), R.drawable.home_fragment_bg)
                );

                binding.statusBarContainer.setBackground(
                        ContextCompat.getDrawable(getContext(), R.drawable.home_dummy_top_bar_bg)
                );

                binding.dummyContainer.animate()
                        .scaleX(0.5f)
                        .scaleY(0.5f)
                        .translationX(-300f)  // left shift
                        .translationY(100f)   // down shift
                        .setDuration(300)
                        .start();

                binding.menuButton.setVisibility(GONE);
                binding.cancelButton.setVisibility(VISIBLE);
            }
        });

        binding.cancelButton.setOnClickListener(v -> {
            if(isShrunk) {
                // Restore
                binding.homeFragment.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .translationX(0f)
                        .translationY(0f)
                        .setDuration(300)
                        .start();

                binding.dummyContainer.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .translationX(0f)
                        .translationY(0f)
                        .setDuration(300)
                        .start();

                binding.menuButton.setVisibility(VISIBLE);
                binding.cancelButton.setVisibility(GONE);
            }
            isShrunk = !isShrunk;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Apply native blur effect
            float blurRadius = 25f;
            RenderEffect blurEffect = RenderEffect.createBlurEffect(blurRadius, blurRadius, Shader.TileMode.CLAMP);
            binding.glassEffectLayout.setRenderEffect(blurEffect);
        }

    }

}










//package com.shop.zenstore;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//public class HomeFragment extends Fragment {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//}