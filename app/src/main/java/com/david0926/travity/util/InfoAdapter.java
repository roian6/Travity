package com.david0926.travity.util;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.travity.R;
import com.david0926.travity.model.InfoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private ArrayList<InfoModel> list;

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.info_title);
            iv = itemView.findViewById(R.id.info_img);
        }
    }

    public InfoAdapter(ArrayList<InfoModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main1_info, parent, false);
        InfoAdapter.ViewHolder vh = new InfoAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(InfoAdapter.ViewHolder holder, int position) {
        Picasso.get().load(Uri.parse(list.get(position).getImgsrc())).into(holder.iv);
        holder.tv.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
