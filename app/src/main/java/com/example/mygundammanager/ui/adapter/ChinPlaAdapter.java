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
import com.example.mygundammanager.thirdparty.model.ChinPla;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChinPlaAdapter extends RecyclerView.Adapter<ChinPlaAdapter.ChinPlaViewHolder> {

    private List<? extends ChinPla> chinPlaList = new ArrayList<>();
    private final Context context;

    public ChinPlaAdapter(Context context) {
        this.context = context;
    }

    public void setChinPlaList(List<? extends ChinPla> chinPlaList) {
        this.chinPlaList = chinPlaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChinPlaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chinpla, parent, false);
        return new ChinPlaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChinPlaViewHolder holder, int position) {
        ChinPla kit = chinPlaList.get(position);

        holder.tvName.setText(kit.getName());
        holder.tvBrand.setText("Brand: " + kit.getBrand());

        if (kit.getPrice() != null) {
            holder.tvPrice.setText(String.format(Locale.getDefault(), "Prezzo: € %.2f", kit.getPrice()));
        } else {
            holder.tvPrice.setText("Prezzo: N/D");
        }

        if (kit.hasSpareRunners() != null && kit.hasSpareRunners()) {
            holder.tvSpareRunners.setText("Runner avanzati: Sì");
        } else {
            holder.tvSpareRunners.setText("Runner avanzati: No");
        }

        // Caricamento sicuro dell'immagine con fallback in caso di errore
        if (kit.getPhotoUri() != null && !kit.getPhotoUri().isEmpty()) {
            try {
                holder.imgChinPla.setImageURI(Uri.parse(kit.getPhotoUri()));
            } catch (Exception e) {
                holder.imgChinPla.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.imgChinPla.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }

    @Override
    public int getItemCount() {
        return chinPlaList != null ? chinPlaList.size() : 0;
    }

    static class ChinPlaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgChinPla;
        TextView tvName, tvBrand, tvPrice, tvSpareRunners;

        public ChinPlaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChinPla = itemView.findViewById(R.id.img_chinpla);
            tvName = itemView.findViewById(R.id.tv_chinpla_name);
            tvBrand = itemView.findViewById(R.id.tv_chinpla_brand);
            tvPrice = itemView.findViewById(R.id.tv_chinpla_price);
            tvSpareRunners = itemView.findViewById(R.id.tv_chinpla_spare_runners);
        }
    }
}