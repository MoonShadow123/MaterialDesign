package com.example.materialdesign.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialdesign.bean.Fruit;
import com.example.materialdesign.R;

import java.util.List;

/**
 * The type Fruit adapter.
 *
 * @author PengLiang
 * Time: 2019/8/27
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;
    private onItemClick listener;

    /**
     * Instantiates a new Fruit adapter.
     *
     * @param list the list
     */
    public FruitAdapter(List<Fruit> list) {
        mFruitList = list;
    }

    /**
     * On click listener.
     *
     * @param listener the listener
     */
    public void onClickListener(onItemClick listener) {
        this.listener = listener;
    }

    /**
     * The type View holder.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView fruitImage;
        private TextView fruitName;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                listener.onClick(v, holder.getAdapterPosition(), fruit.getName(), fruit.getImageId());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int i) {
        Fruit fruit = mFruitList.get(i);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    /**
     * The interface On item click.
     */
    public interface onItemClick {
        /**
         * On click.
         *
         * @param view     the view
         * @param position the position
         * @param name     the name
         * @param id       the id
         */
        void onClick(View view, int position, String name, int id);
    }


    /**
     * Remove data.
     *
     * @param position the position
     */
    public void removeData(int position) {
        mFruitList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    /**
     * Add data.
     *
     * @param position the position
     */
    public void addData(int position) {
        //在list中添加数据，并通知条目加入一条
        mFruitList.add(position, Fruit.getFruitElement());
        //添加动画
        notifyItemInserted(position);
    }
}
