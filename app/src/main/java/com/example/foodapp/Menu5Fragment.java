package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.model.User;
import com.squareup.picasso.Picasso;

public class Menu5Fragment extends Fragment {
    private TextView usernameTextView;
    private TextView emailTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu5fragment, container, false);
        TextView logoutTextView = view.findViewById(R.id.textViewLogout);



        Bundle bundle = getArguments();
        if (bundle != null) {
            User user = (User) bundle.getSerializable("user");
            if (user != null) {
                TextView usernameTextView = view.findViewById(R.id.Text_Name);
                TextView emailTextView = view.findViewById(R.id.text_gmail);
                ImageView imgviewMyAvt = view.findViewById(R.id.imageViewMyAvt);
                ImageView imgviewCover = view.findViewById(R.id.imgviewcoverimg);
                usernameTextView.setText(user.getUsername());
                emailTextView.setText(user.getEmail());
                Picasso.get().load(user.getAvatarImage()).into(imgviewMyAvt);
                Picasso.get().load(user.getCoverImage()).into(imgviewCover);
                Log.e("Menu5Fragment", "Username: " + user.getUsername());
                Log.e("Menu5Fragment", "Email: " + user.getEmail());
            }
        }

        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất ở đây
                // Ví dụ: Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish(); // Đóng Activity hiện tại
            }
        });
        return view;
    }

}
