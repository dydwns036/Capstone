package com.example.foodapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.adapter.ItemData;
import com.example.foodapp.adapter.adapterPost;
import com.example.foodapp.data.DatabaseAll;
import com.example.foodapp.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Menu1Fragment extends Fragment {

    ViewFlipper viewFlipper;


    ListView listViewNew;

    ArrayList<Truyen> TruyenArrayList;
    adapterPost adaptertruyen;

    DatabaseAll databaseDocTruyen;

    private List<ItemData> itemgridList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu1fragment, container, false);

        //home menu itemlist
        itemgridList = createItemgridList();
        GridLayout gridLayout = view.findViewById(R.id.gridLayout);
        for (final ItemData item : itemgridList) {
            View itemView = getLayoutInflater().inflate(R.layout.item_grid, gridLayout, false);
            ImageView imageView = itemView.findViewById(R.id.itemgridImage);
            TextView textView = itemView.findViewById(R.id.itemgridText);

            // Set image and text for each item
            imageView.setImageResource(item.getImageResId());
            textView.setText(item.getItemgridName());

            // Add click listener to each item
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // add activitty/intent here
                    Toast.makeText(requireContext(), item.getItemgridName(), Toast.LENGTH_SHORT).show();
                }
            });

            gridLayout.addView(itemView);
        }

        //
        databaseDocTruyen = new DatabaseAll(requireContext());

        Mapping(view);

        ActionViewFlipper();

        //listview Truyen
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(requireActivity(), Content.class);
                String tent =   TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("postname",tent);
                intent.putExtra("content",noidungt);
                startActivity(intent);
            }
        });

        return view;
    }


    // Flipper 광고
    private void ActionViewFlipper() {
        ArrayList<String> advertisement = new ArrayList<>();
        // Thêm các URL hình ảnh quảng cáo vào danh sách
        advertisement.add("https://img2.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202303/28/dailylife/20230328110003580sufq.jpg");
        advertisement.add("https://static.hubzum.zumst.com/hubzum/2022/02/10/14/3ea39a0f140c44b8b26761a93d11aa18.jpg");
        advertisement.add("https://dulichlive.com/han-quoc/wp-content/uploads/sites/7/2020/02/10-mon-an-Han-Quoc-khong-cay-ngon-noi-tieng.jpg");
        advertisement.add("https://forza.com.vn/wp-content/uploads/2021/07/cach-lam-mi-y-thom-ngon-chuan-vi-tai-nha-6.jpeg");


        //Gán link ảnh vào imageView, rồi gán gán image ra app
        for(int i =0; i <advertisement.size(); i++){
            ImageView imageView = new ImageView(requireContext());
            //Hàm thư viện của picasso. lấy ảnh từ internet về cho vào imageview
            Picasso.get().load(advertisement.get(i)).into(imageView);
            //phương thức căn chỉnh tấm hình vừa với khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }
        //Thiết lập tự chạy cho viewFlipper chạy trong 5 giây
        viewFlipper.setFlipInterval(5000);
        //viewFlipper run
        viewFlipper.setAutoStart(true);
        //Gọi animation cho in và out . Animation giúp cho nó biến dổi giữa các giao diện hình ảnh
        Animation animation_slide_in = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_out_right);
        //Gọi animation vào ViewFlippẻ
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void Mapping(View view){

        viewFlipper = view.findViewById(R.id.viewflipper);
        listViewNew = view.findViewById(R.id.listviewNew);


        //New Post
        TruyenArrayList = new ArrayList<>();

        Cursor cursor1 = databaseDocTruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String postname = cursor1.getString(1);
            String content = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArrayList.add(new Truyen(id,postname,content,anh,id_tk));
        }
        cursor1.close();

        adaptertruyen = new adapterPost(requireContext(), TruyenArrayList);
        listViewNew.setAdapter(adaptertruyen);
    }


    private List<ItemData> createItemgridList() {
        List<ItemData> itemgridList = new ArrayList<>();
        // Add data for each item
        itemgridList.add(new ItemData(R.drawable.meat, "고기"));
        itemgridList.add(new ItemData(R.drawable.seafood, "생선"));
        itemgridList.add(new ItemData(R.drawable.cereal, "곡류"));
        itemgridList.add(new ItemData(R.drawable.vegetable, "채소"));
//        itemgridList.add(new ItemData(R.drawable.botmi, "간식"));
        itemgridList.add(new ItemData(R.drawable.dessert, "디저트"));
        itemgridList.add(new ItemData(R.drawable.cooking, " 끓임"));
        itemgridList.add(new ItemData(R.drawable.deep_fried, "튀김"));
        itemgridList.add(new ItemData(R.drawable.soup, " 국"));
        itemgridList.add(new ItemData(R.drawable.grill, "구워"));
        itemgridList.add(new ItemData(R.drawable.fried, "볶음"));
        itemgridList.add(new ItemData(R.drawable.smoothie, "스무티"));
        itemgridList.add(new ItemData(R.drawable.ic_delete, "Item 13"));
        itemgridList.add(new ItemData(R.drawable.ic_delete, "Item 14"));
        itemgridList.add(new ItemData(R.drawable.ic_delete, "Item 15"));

        // Add data for other items here

        return itemgridList;
    }
}
