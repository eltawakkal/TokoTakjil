package com.eltawakkal.tokotakjil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by eltawakkal on 6/5/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    String[] nama, harga;
    int[] imgProd;
    Context context;

    public RecyclerAdapter(String[] nama, String[] harga, int[] imgProd, Context context) {
        this.nama = nama;
        this.harga = harga;
        this.imgProd = imgProd;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtNama.setText(nama[position]);
        holder.txtHarga.setText("Rp."+harga[position]);

        Picasso.with(context).load(imgProd[position]).resize(200, 200).into(holder.imgProduk);

        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper.totalHarga+=Integer.parseInt(harga[position]);
                DataHelper.dataBarang.add(nama[position] + "\t\t" + harga[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nama.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtHarga;
        ImageView imgProduk, imgAdd;

        public ViewHolder(View itemView) {
            super(itemView);

            txtHarga = (TextView) itemView.findViewById(R.id.txtHarga);
            txtNama = (TextView) itemView.findViewById(R.id.txtNama);

            imgProduk = (ImageView) itemView.findViewById(R.id.imgP);
            imgAdd = (ImageView) itemView.findViewById(R.id.imgAdd);

        }
    }
}
