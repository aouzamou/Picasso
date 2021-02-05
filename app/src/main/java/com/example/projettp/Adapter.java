package com.example.projettp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.widget.ImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ImageViewHolder>{

        public Adapter(Context mContext, List<row> mdata) {
            this.mContext = mContext;
            this.mdata = mdata;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.image.setImageResource(mdata.get(position).getImg());
        }


        @Override
        public int getItemCount() {
            return mdata.size();
        }

        Context mContext;
        List<row> mdata;

        public class ImageViewHolder extends RecyclerView.ViewHolder {

            ImageView image;

            public ImageViewHolder(@NonNull View itemView){
                super(itemView);
                image = itemView.findViewById(R.id.imageView);
            }
        }
}

