//package com.example.myapplication.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import com.example.myapplication.R;
//import com.example.myapplication.model.Truyen;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//
//public class adapterPost extends BaseAdapter  {
//
//
//    private Context context;;
//
//    private ArrayList<Truyen> listtruyen;
//
//
//    public adapterPost(Context context, ArrayList<Truyen> listtruyen) {
//        this.context = context;
//        this.listtruyen = listtruyen;
//    }
//
//    @Override
//    public int getCount() {
//        return listtruyen.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listtruyen.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    public class ViewHolder{
//        TextView txtPostName;
//        ImageView imgtruyen;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//
//         viewHolder = new ViewHolder();
//
//          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//          convertView = inflater.inflate(R.layout.new_post,null);
//
//
//          viewHolder.txtPostName = convertView.findViewById(R.id.textviewTenTruyen);
//          viewHolder.imgtruyen = convertView.findViewById(R.id.imgNewTruyen);
//          convertView.setTag(viewHolder);
//
//        Truyen truyen = (Truyen) getItem(position);
//        viewHolder.txtPostName.setText(truyen.getTenTruyen());
//
//        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_not_supported_24).into(viewHolder.imgtruyen);
//
//        return convertView;
//    }
//
//
//
//    public void filterList(ArrayList<Truyen> filteredList){
//        listtruyen = filteredList;
//        notifyDataSetChanged();
//    }
//
//}
