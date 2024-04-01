package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        // Nếu đã đăng nhập, chuyển hướng sang MainActivity
        // 사용자가 로그인했는지 여부를 확인합니다.
        // 이미 로그인된 경우, MainActivity로 이동합니다.
        if (isUserLoggedIn()) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish(); // Đóng activity hiện tại để ngăn không cho người dùng quay lại màn hình đăng nhập bằng nút back
                      // 현재 액티비티를 종료하여 뒤로가기 버튼으로 로그인 화면으로 돌아갈 수 없도록 합니다.
        }
    }

    // phương thức này kiểm tra xem người dùng đã đăng nhập hay chưa
    // 사용자가 이미 로그인했는지 여부를 확인하는 가상 메소드입니다.
    private boolean isUserLoggedIn() {
        // Code kiểm tra người dùng đã đăng nhập ở đây
        // Trả về true nếu đã đăng nhập, ngược lại trả về false
        // 사용자가 이미 로그인했는지 여부를 확인하는 코드를 여기에 작성합니다.
        // 이미 로그인한 경우 true를 반환하고, 그렇지 않으면 false를 반환합니다.
        return false;
    }

    // Phương thức khi người dùng nhấn nút "Đăng nhập"
    // 사용자가 "로그인" 버튼을 클릭했을 때 호출되는 메소드입니다.
    public void onLoginButtonClick(View view) {
        // Xử lý logic đăng nhập ở đây
        // 여기서 로그인 로직을 처리합니다.


        // 로그인에 성공한 후, MainActivity로 이동합니다.
        // Sau khi đăng nhập thành công, chuyển hướng sang MainActivity
        startActivity(new Intent(Login.this, MainActivity.class));
        finish(); // Đóng activity hiện tại để ngăn không cho người dùng quay lại màn hình đăng nhập bằng nút back
    }
}