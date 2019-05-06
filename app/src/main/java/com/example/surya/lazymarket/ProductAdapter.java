package com.example.surya.lazymarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;

    public ProductAdapter(Context context,List<Upload> uploads){
        mContext=context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_item,parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
//        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
//        holder.itemView.startAnimation(animation);
        setAnimation(holder.itemView, position);

        Upload uploadCurrent = mUploads.get(position);
        holder.textViewNumber.setText(uploadCurrent.getUsername());
        holder.descriptionText.setText(uploadCurrent.getDescription());
        holder.dateTextView.setText(uploadCurrent.getDate());
        Picasso.get().load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.cc)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        Picasso.get().load(uploadCurrent.getUploaderPic())
                .placeholder(R.mipmap.cc)
                .fit()
                .centerCrop()
                .into(holder.uploaderImage);

        holder.menuOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.menuOpener);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_send:
                                mListener.onWhatEverClick(position);
                                return true;
                            case R.id.menu_item_remove:
                                mListener.onDeleteClick(position);
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {

        public TextView textViewNumber;
        public ImageView imageView;
        public ImageView uploaderImage;
        public TextView descriptionText;
        public TextView dateTextView;
        public TextView menuOpener;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNumber = itemView.findViewById(R.id.text_view_whatsappNo);
            imageView = itemView.findViewById(R.id.product_view_upload);
            descriptionText=itemView.findViewById(R.id.text_view_description);
            uploaderImage=itemView.findViewById(R.id.uploader_pic);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            menuOpener = itemView.findViewById(R.id.menu_opener);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }



        @Override
        public void onClick(View v) {
            if(mListener!=null){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            menu.setHeaderTitle("Select Action");
//            MenuItem doWhatever = menu.add(menu.NONE,1,1,"Get this Product");
//            MenuItem delete = menu.add(menu.NONE,2,2,"Delete");
//
//            doWhatever.setOnMenuItemClickListener(this);
//            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
//            if(mListener!=null){
//                int position = getAdapterPosition();
//                if(position!=RecyclerView.NO_POSITION){
//                    switch (item.getItemId()){
//                        case 1:
//                            mListener.onWhatEverClick(position);
//                            return true;
//                        case 2:
//                            mListener.onDeleteClick(position);
//                            return true;
//                    }
//                }
//            }
            return false;
        }
    }

    public interface OnItemClickListener {

        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(1500));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }
}

