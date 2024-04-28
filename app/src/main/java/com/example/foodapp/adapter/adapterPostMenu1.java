package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.Post;
import com.squareup.picasso.Picasso;
import java.util.List;

public class adapterPostMenu1 extends RecyclerView.Adapter<adapterPostMenu1.ViewHolder> {
    private List<Post> postList;
    private Context context;
    private OnPostClickListener onPostClickListener;

    public interface OnPostClickListener {
        void onPostClick(int position);
    }

    public adapterPostMenu1(Context context, List<Post> postList, OnPostClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.onPostClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNewTruyen);
            textViewTitle = itemView.findViewById(R.id.textviewTenTruyen);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_popular_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Post post = postList.get(position);

        if (!post.getImageUrls().isEmpty()) {
            String imageUrl = post.getImageUrls().get(0); // Lấy URL ảnh đầu tiên trong danh sách
            Picasso.get().load(imageUrl).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.food_bg);
        }

        holder.textViewTitle.setText(post.getTitle());

        // Handle click event on image
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPostClickListener != null) {
                    onPostClickListener.onPostClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
