package com.example.s1.privat;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by S1 on 2/19/2017.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<CurrencyModel> posts;

    public PostsAdapter(ArrayList<CurrencyModel> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrencyModel post = posts.get(position);
        holder.curr.setText(post.getCurrency());
        holder.buy.setText(String.format("%.2f", post.getPurchaseRateNb()));
        holder.sell.setText(String.format("%.2f", post.getSaleRateNb()));
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView curr;
        TextView sell,buy;

        public ViewHolder(View itemView) {
            super(itemView);
            curr = (TextView) itemView.findViewById(R.id.curr_view);
            buy = (TextView) itemView.findViewById(R.id.buy_view);
            sell = (TextView) itemView.findViewById(R.id.sell_view);
        }
    }
}