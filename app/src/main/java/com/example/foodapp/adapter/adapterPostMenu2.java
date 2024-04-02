package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterPostMenu2 extends RecyclerView.Adapter<adapterPostMenu2.ViewHolder> {
private List<Post> posts;
private Context context;
private final int MAX_LINES = 2;
public adapterPostMenu2(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.textViewUsername.setText(post.getUsername());
        holder.textViewDateTime.setText(post.getDate());
        holder.textViewContent.setText(post.getContent());
        // Load ảnh avatar và ảnh bài đăng nếu cần
        // Picasso.with(context).load(post.getAvatarUrl()).into(holder.imageViewAvatar);
        Picasso.get().load(post.getAvatarUrl()).into(holder.imageViewAvatar);
    // Kiểm tra xem bài đăng có ảnh hay không
    List<String> imageUrls = post.getImageUrls();
    if (post.getImageUrls() != null && !post.getImageUrls().isEmpty()) {
        String imageUrl = post.getImageUrls().get(0); // Lấy URL ảnh đầu tiên trong danh sách
        Picasso.get().load(imageUrl).into(holder.imageViewPost);
    } else {
        // Nếu không có ảnh, ẩn ImageView
        holder.imageViewPost.setVisibility(View.GONE);
    }
        }

@Override
public int getItemCount() {
        return posts.size();
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAvatar;
        TextView textViewUsername, textViewDateTime, textViewContent;
        ImageView imageViewPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewContent = itemView.findViewById(R.id.textViewContent);
            imageViewPost = itemView.findViewById(R.id.imageViewPost);
        }
    }
}
