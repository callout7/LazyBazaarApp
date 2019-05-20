package com.example.surya.lazymarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.UserItemViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public UserItemAdapter(Context context, List<Upload> uploads){
        mContext=context;
        mUploads=uploads;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.useritem,parent,false);
        return new UserItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        Upload uploadCurr = mUploads.get(position);
        holder.itemName.setText(uploadCurr.getDescription());
        holder.uploadDate.setText(uploadCurr.getDate());
        Picasso.get().load(uploadCurr.getImageUrl()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView uploadDate;
        public ImageView itemImage;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            
            itemName=itemView.findViewById(R.id.itemName);
            uploadDate=itemView.findViewById(R.id.itemDesc);
            itemImage=itemView.findViewById(R.id.uploaderItemPic);
        }
    }
}
