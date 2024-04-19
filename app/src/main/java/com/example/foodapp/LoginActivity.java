package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.viewpager2.widget.ViewPager2;


import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.model.User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ẩn nút back
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            loggedInUser = (User) intent.getSerializableExtra("user");
        }


        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        // Khởi tạo các Fragment và thêm vào Adapter
        adapter.addFragment(new Menu1Fragment());


        if (loggedInUser != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", loggedInUser);
            Menu2Fragment menu2Fragment = new Menu2Fragment();
            menu2Fragment.setArguments(bundle);
            adapter.addFragment(menu2Fragment);
            adapter.addFragment(new Menu3Fragment());
            adapter.addFragment(new Menu4Fragment());

            Menu5Fragment menu5Fragment = new Menu5Fragment();
            menu5Fragment.setArguments(bundle);
            adapter.addFragment(menu5Fragment);
        }else{
            adapter.addFragment(new Menu2Fragment());
            adapter.addFragment(new Menu3Fragment());
            adapter.addFragment(new Menu4Fragment());
            adapter.addFragment(new Menu5Fragment());
        }

//        if (loggedInUser == null) {
//            Log.e("loggedin ","null");
//        }

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

    public void switchToMenu3Fragment(int groupId) {
        ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
        Menu3Fragment menu3Fragment = (Menu3Fragment) adapter.createFragment(2);

        // Perform the Fragment transition
        if (menu3Fragment != null) {
            // Perform any necessary processing before transitioning Fragment (if needed)
            menu3Fragment.refreshPostsByGroupFromDatabase(groupId);

            // Move to Menu3Fragment
            viewPager.setCurrentItem(2, true);
        }
    }

}
