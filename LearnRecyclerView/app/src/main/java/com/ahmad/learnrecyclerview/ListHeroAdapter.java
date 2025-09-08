package com.ahmad.learnrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.learnrecyclerview.databinding.ItemRowHeroBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;

    private OnItemClickCallback onItemClickCallback;

    public ListHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowHeroBinding binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Hero hero = listHero.get(position);
        Glide.with(holder.binding.imgItemPhoto.getContext())
            .load(hero.getPhoto()) // URL Gambar
            .into(holder.binding.imgItemPhoto); // imageView mana yang akan diterapkan
        holder.binding.tvItemName.setText(hero.getName());
        holder.binding.tvItemDescription.setText(hero.getDescription());

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ItemRowHeroBinding binding;

        public ListViewHolder(@NonNull ItemRowHeroBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Hero data);
    }
}
