package com.shop.zenstore;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.shop.zenstore.R;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shop.zenstore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private int previousIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.CustomColor2));


//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupBottomNavigation();

    }

    private void setupBottomNavigation() {
        loadFragment(new HomeFragment(), false, previousIndex, 0);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int newIndex = getMenuItemIndex(item.getItemId());
            int itemId = item.getItemId();
            Fragment fragment;

            if (itemId == R.id.home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.cart) {
                fragment = new CartFragment();
            } else if (itemId == R.id.categories) {
                fragment = new CategoriesFragment();
            } else if (itemId == R.id.account) {
                fragment = new AccountFragment();
            } else {
                return false;
            }

            loadFragment(fragment, true, previousIndex, newIndex);
            previousIndex = newIndex;
            return true;
        });
    }

    private int getMenuItemIndex(int itemId) {
        if (itemId == R.id.home) return 0;
        if (itemId == R.id.cart) return 1;
        if (itemId == R.id.categories) return 2;
        if (itemId == R.id.account) return 3;
        return 0;
    }

    private void loadFragment(Fragment fragment, boolean animate, int oldIndex, int newIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            if (newIndex > oldIndex) {
                // Slide in from right
                transaction.setCustomAnimations(
                        R.anim.main_slide_in_right_anim,
                        R.anim.main_slide_out_left_anim,
                        R.anim.main_slide_in_left_anim,
                        R.anim.main_slide_out_right_anim
                );
            } else if (newIndex < oldIndex) {
                // Slide in from left
                transaction.setCustomAnimations(
                        R.anim.main_slide_in_left_anim,
                        R.anim.main_slide_out_right_anim,
                        R.anim.main_slide_in_right_anim,
                        R.anim.main_slide_out_left_anim
                );
            }
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}