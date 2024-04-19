package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.adapter.adapterPostMenu1;
import com.example.myapplication.data.DatabaseHelper;
import com.example.myapplication.model.Post;

import java.util.ArrayList;
import java.util.List;
public class Menu3Fragment extends Fragment {
    private RecyclerView recyclerView;
    private adapterPostMenu1 adapter;
    private List<Post> postList;
    private int groupID;
    private TextView emptyView,textView3;
    private EditText searchEditText;
    private ImageView searchButton,filterButton,searchButtonL;
    private HorizontalScrollView horizontalScrollView;
    private String searchText = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu3fragment, container, false);

        // Nhận groupID từ bundle
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("GROUP_ID")) {
            groupID = bundle.getInt("GROUP_ID");
            Log.e("groupid nhận được:", String.valueOf(groupID));

        }
        ViewPager2 viewPager2 = getActivity().findViewById(R.id.viewPager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    // Nếu Fragment 3 được hiển thị, reset dữ liệu
                    resetData();
                }
            }
        });
        View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy loại mã nhận dạng của mục được chọn
                int groupId = (int) v.getTag();

                // Thực hiện các hoạt động tương ứng với loại dữ liệu
                fetchDataFromDatabase(groupId,searchText);
                LinearLayout linearLayout = view.findViewById(R.id.filterLayout);
                // Đặt trạng thái chọn cho TextView được chọn và huỷ chọn các TextView khác
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    View childView = linearLayout.getChildAt(i);
                    if (childView == v) {
                        // TextView được chọn
                        childView.setSelected(true);
                        Log.e("groupid", String.valueOf(groupId));
                    } else {
                        // TextView khác
                        childView.setSelected(false);
                    }
                }
            }
        };
        searchButton = view.findViewById(R.id.search_button);

        // Thêm sự kiện lắng nghe cho nút filter
        filterButton = view.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Mở dialog khi nút filter được nhấn
//                FilterDialogFragment dialogFragment = new FilterDialogFragment();
//                dialogFragment.show(getChildFragmentManager(), "FilterDialog");
            }
        });
        // Gắn sự kiện lắng nghe và loại mã nhận dạng cho mỗi TextView trong thanh cuộn ngang
        LinearLayout linearLayout = view.findViewById(R.id.filterLayout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);
            childView.setTag(getGroupIdForPosition(i)); // Đặt loại mã nhận dạng cho mỗi mục
            childView.setOnClickListener(itemClickListener);
        }
        searchButtonL = view.findViewById(R.id.search_button_left);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        textView3 = view.findViewById(R.id.textview3);
        searchEditText = view.findViewById(R.id.search_editText);
        emptyView = view.findViewById(R.id.emptyView);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postList = new ArrayList<>();
        adapter = new adapterPostMenu1(getActivity(), postList, new MyOnPostClickListener());
        recyclerView.setAdapter(adapter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Xử lý trong quá trình text thay đổi
//                String query = charSequence.toString().trim();
//                if (!query.isEmpty()) {
//                    // Thực hiện tìm kiếm dựa trên query
//                    List<Post> searchResults = searchData(query);
//                    // Cập nhật dữ liệu trong RecyclerView
//                    adapter.updateData(searchResults);
//                    // Hiển thị hoặc ẩn emptyView tùy thuộc vào kết quả tìm kiếm
//                    if (searchResults.isEmpty()) {
//                        recyclerView.setVisibility(View.GONE);
//                        emptyView.setVisibility(View.VISIBLE);
//                    } else {
//                        recyclerView.setVisibility(View.VISIBLE);
//                        emptyView.setVisibility(View.GONE);
//                    }
//                } else {
//                    // Nếu query trống, hiển thị tất cả các bài đăng
//                    List<Post> allPosts = getAllPosts();
//                    adapter.updateData(allPosts);
//                    // Hiển thị hoặc ẩn emptyView tùy thuộc vào có bài đăng hay không
//                    if (allPosts.isEmpty()) {
//                        recyclerView.setVisibility(View.GONE);
//                        emptyView.setVisibility(View.VISIBLE);
//                    } else {
//                        recyclerView.setVisibility(View.VISIBLE);
//                        emptyView.setVisibility(View.GONE);
//                    }
//                }


                searchText = s.toString();
                Log.e("GroupId hiện tại:", String.valueOf(groupID));
                // Gọi fetchDataFromDatabase để lấy dữ liệu mới
                fetchDataFromDatabase(groupID, searchText);
//                searchData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Xử lý sau khi text thay đổi
                if (editable.length() > 0) {
                    // Nếu có chữ được nhập vào, ẩn nút tìm kiếm
                    searchButton.setVisibility(View.GONE);
                    filterButton.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.GONE);
                    horizontalScrollView.setVisibility(View.VISIBLE);
                    searchButtonL.setVisibility(View.VISIBLE);

                } else {
                    // Nếu không có chữ nào được nhập, hiển thị nút tìm kiếm
                    searchButton.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    horizontalScrollView.setVisibility(View.GONE);
                    filterButton.setVisibility(View.GONE);
                    searchButtonL.setVisibility(View.GONE);
                }
            }
        });
        retrievePostsByGroupFromDatabase();

        return view;
    }

    private void resetData() {
        // Xóa nội dung của EditText và reset dữ liệu cần thiết ở đây
        if (searchEditText != null) {
            searchEditText.setText("");
        }
        retrievePostsByGroupFromDatabase();
        groupID=0;
    }
    // Phương thức để lấy loại mã nhận dạng (groupId) cho mỗi vị trí trong thanh cuộn ngang
    private int getGroupIdForPosition(int position) {

        if (position == 0) {
            return 0; // Mục All
        } else {
            return position ; // Các mục khác
        }
    }
    // Phương thức để lấy dữ liệu từ cơ sở dữ liệu dựa trên groupId
    private void fetchDataFromDatabase(int groupId, String searchText) {
        // Thực hiện truy vấn cơ sở dữ liệu dựa trên groupId và từ được tìm kiếm
        // Tạo một đối tượng DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        // Xóa dữ liệu cũ từ postList
        postList.clear();

        // Thực hiện truy vấn cơ sở dữ liệu và lấy dữ liệu dựa trên groupId và từ được tìm kiếm
        if (groupId == 0) {
            // Trường hợp groupId là 0 (mục "All"): lấy tất cả bài đăng có chứa từ được tìm kiếm
            postList.addAll(databaseHelper.searchPost(searchText));
        } else {
            // Trường hợp groupId khác 0: lấy bài đăng theo groupId và chứa từ được tìm kiếm
            postList.addAll(databaseHelper.getPostsByGroupIdAndSearchText(groupId, searchText));
        }

        // Cập nhật dữ liệu trong adapter
        adapter.notifyDataSetChanged();

        // Hiển thị hoặc ẩn emptyView tùy thuộc vào kết quả truy vấn
        if (postList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        this.groupID = groupId;
    }

    //    private void searchData(String searchText) {
//        if (getActivity() != null) {
//            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
//            if (postList == null) {
//                postList = new ArrayList<>();
//            } else {
//                postList.clear();
//            }
//            postList.addAll(databaseHelper.searchPost(searchText));
//            adapter.notifyDataSetChanged();
//
//            if (postList.isEmpty()) {
//                recyclerView.setVisibility(View.GONE);
//                emptyView.setVisibility(View.VISIBLE);
//            } else {
//                recyclerView.se          emptyView.setVisibility(View.GONE);
//            }
//
//       tVisibility(View.VISIBLE);
////       }
//    }
    public void refreshPostsByGroupFromDatabase(int groupId) {
        this.groupID = groupId;
        retrievePostsByGroupFromDatabase();
//        retrieveDataCalled = true;
    }
    private void retrievePostsByGroupFromDatabase() {
        if (getActivity() != null) {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            if (postList == null) {
                postList = new ArrayList<>();
            } else {
                postList.clear();
            }
            postList.addAll(databaseHelper.getPostsByGroupId(groupID));
            adapter.notifyDataSetChanged();

            if (postList.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    private class MyOnPostClickListener implements adapterPostMenu1.OnPostClickListener {
        @Override
        public void onPostClick(int position) {
            Post clickedPost = postList.get(position);
            Intent intent = new Intent(getActivity(), PostDetail.class);
            intent.putExtra("POST_DETAIL", clickedPost);
            startActivity(intent);
        }
    }

}
