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
    private OnPostClickListener onPostClickListener; // Interface

    public interface OnPostClickListener {
        void onPostClick(int position);
    }

    public adapterPostMenu2(Context context, List<Post> posts, OnPostClickListener listener) {
        this.context = context;
        this.posts = posts;
        this.onPostClickListener = listener;
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
        holder.textViewTitle.setText(post.getTitle());

        holder.txtRecipe.setVisibility(post.getIsRecipe() == 1 ? View.VISIBLE : View.GONE);

        Picasso.get().load(post.getAvatarUrl()).into(holder.imageViewAvatar);

        if (!post.getImageUrls().isEmpty()) {
            String imageUrl = post.getImageUrls().get(0); // Lấy URL ảnh đầu tiên trong danh sách
            Picasso.get().load(imageUrl).into(holder.imageViewPost);
            holder.imageViewPost.setVisibility(View.VISIBLE);
        } else {
            // Nếu không có ảnh, ẩn ImageView
            holder.imageViewPost.setVisibility(View.GONE);
        }
        int currentPosition = position;
        // Gán sự kiện onClickListener cho itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPostClickListener != null) {
                    onPostClickListener.onPostClick(currentPosition);    // Gọi phương thức trong interface
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAvatar;
        TextView textViewUsername, textViewDateTime, textViewTitle, txtRecipe;
        ImageView imageViewPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewPost = itemView.findViewById(R.id.imageViewPost);
            txtRecipe = itemView.findViewById(R.id.txtRecipe);
        }
    }
}
