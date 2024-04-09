package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.viewpager2.widget.ViewPager2;

import com.example.foodapp.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ẩn nút back
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        // Khởi tạo các Fragment và thêm vào Adapter
        adapter.addFragment(new Menu1Fragment());
        adapter.addFragment(new Menu2Fragment());
        adapter.addFragment(new Menu3Fragment());
        adapter.addFragment(new Menu4Fragment());
        adapter.addFragment(new Menu5Fragment());


        viewPager.setAdapter(adapter);


        int[] tabIcons = {R.drawable.ic_home, R.drawable.bangtin, R.drawable.ic_search, R.drawable.ic_notification, R.drawable.ic_taikhoan};
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Xác định hình ảnh cho tab dựa trên vị trí
            tab.setIcon(tabIcons[position]);
        }).attach();



        // Thiết lập bộ lắng nghe cho sự kiện khi người dùng chọn tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Lấy vị trí của tab được chọn
                int position = tab.getPosition();
                // Di chuyển viewPager đến tab tương ứng
                viewPager.setCurrentItem(position, true);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Không cần xử lý khi tab bị bỏ chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Không cần xử lý khi tab được chọn lại
            }
        });
    }

}
