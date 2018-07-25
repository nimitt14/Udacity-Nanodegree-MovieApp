package com.example.nimitt.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

//Adapter class for Recycler View containing
//MyViewHolder as an inner class as a View Holder
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Result> mData;

    public RecyclerViewAdapter(Context mContext, List<Result> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.cardview_item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Result item = mData.get(position);
        holder.movieTitle.setText(item.getTitle());
//        holder.imgThumbnail.setImageResource(item.getPoster());

        ImageView poster = holder.imgThumbnail;

        Picasso.with(mContext)
                .load(PosterUtils.buildImagePath(item.getPosterPath(), "w185"))
                .into(poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Movie_Activity.class);
                intent.putExtra("Passed", item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //Updates Movie List
    public void updateMovieList(List<Result> movieList) {
        this.mData.clear();
        this.mData.addAll(movieList);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView imgThumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title_id);
            imgThumbnail = itemView.findViewById(R.id.movie_img_id);
        }
    }
}
