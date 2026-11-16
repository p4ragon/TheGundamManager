package com.example.mygundammanager.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygundammanager.R;
import com.example.mygundammanager.bandai.model.Gundam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GundamAdapter extends RecyclerView.Adapter<GundamAdapter.GundamViewHolder> {

    private List<? extends Gundam> gundamList = new ArrayList<>();
    private final Context context;

    public GundamAdapter(Context context) {
        this.context = context;
    }

    public void setGundamList(List<? extends Gundam> gundamList) {
        this.gundamList = gundamList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GundamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gundam, parent, false);
        return new GundamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GundamViewHolder holder, int position) {
        Gundam gundam = gundamList.get(position);

        holder.tvName.setText(gundam.getName());

        // Formattazione prezzo
        if (gundam.getPrice() != null) {
            holder.tvPrice.setText(String.format(Locale.getDefault(), "Prezzo: € %.2f", gundam.getPrice()));
        } else {
            holder.tvPrice.setText("Prezzo: Non Inserito");
        }

        // Gestione booleano runner avanzati
        if (gundam.hasSpareRunners() != null && gundam.hasSpareRunners()) {
            holder.tvSpareRunners.setText("Runner avanzati: Sì");
        } else {
            holder.tvSpareRunners.setText("Runner avanzati: No");
        }

        // Caricamento sicuro dell'immagine con fallback in caso di errore
        if (gundam.getPhotoUri() != null && !gundam.getPhotoUri().isEmpty()) {
            try {
                holder.imgGundam.setImageURI(Uri.parse(gundam.getPhotoUri()));
            } catch (Exception e) {
                holder.imgGundam.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.imgGundam.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }

    @Override
    public int getItemCount() {
        return gundamList != null ? gundamList.size() : 0;
    }

    static class GundamViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGundam;
        TextView tvName, tvPrice, tvSpareRunners;

        public GundamViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGundam = itemView.findViewById(R.id.img_gundam);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvSpareRunners = itemView.findViewById(R.id.tv_spare_runners);
        }
    }
}