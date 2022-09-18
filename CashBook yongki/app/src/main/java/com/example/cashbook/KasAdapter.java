package com.example.cashbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KasAdapter extends RecyclerView.Adapter<KasAdapter.ViewHolder>{
    private final ArrayList<Kas> listKas;

    public KasAdapter(ArrayList<Kas> listKas) {
        this.listKas    = listKas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView txt_kas_id;
        private final TextView txt_tipe;
        private final TextView txt_tipe_kas;
        private final TextView txt_total_kas;
        private final TextView txt_keterangan;
        private final TextView txt_tanggal;
        private final ImageView img_status;

        public ViewHolder(final View view){
            super(view);
            txt_kas_id = view.findViewById(R.id.text_kas_id);
            txt_tipe = view.findViewById(R.id.text_tipe);
            txt_tipe_kas = view.findViewById(R.id.text_tipe_kas);
            txt_total_kas = view.findViewById(R.id.text_total_kas);
            txt_keterangan = view.findViewById(R.id.text_keterangan);
            txt_tanggal = view.findViewById(R.id.text_tanggal);
            img_status = view.findViewById(R.id.logo_tipe_kas);
        }
    }

    @NonNull
    @Override
    public KasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kas, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KasAdapter.ViewHolder holder, int position) {
        NumberFormat formatRp = NumberFormat.getInstance(Locale.GERMANY);

        String kas_id = listKas.get(position).getId_kas();
        String tipe_kas = listKas.get(position).getTipe_kas();
        String total_kas = listKas.get(position).getTotal_kas();
        String keterangan = listKas.get(position).getKeterangan();
        String tanggal = listKas.get(position).getTanggal();

        String formatRp_total_kas = "Rp. "+formatRp.format(Double.parseDouble(total_kas));
        holder.txt_kas_id.setText(kas_id);
        holder.txt_tipe_kas.setText(tipe_kas);
        holder.txt_total_kas.setText(formatRp_total_kas);
        holder.txt_keterangan.setText(keterangan);
        holder.txt_tanggal.setText(tanggal);

        if(tipe_kas.equals("Pengeluaran")){
            holder.img_status.setImageResource(R.drawable.arrow_pengeluaran);
            holder.txt_tipe.setText("-");
        }else{
            holder.img_status.setImageResource(R.drawable.arrow_pemasukkan);
            holder.txt_tipe.setText("+");
        }
    }

    @Override
    public int getItemCount() {
        return listKas.size();
    }

}
