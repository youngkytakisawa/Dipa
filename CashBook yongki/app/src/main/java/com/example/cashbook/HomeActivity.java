package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView tambahPemasukkan, tambahPengeluaran, detailCashFlow, pengaturan;
    TextView text_pemasukkan, text_pengeluaran;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DB = new DBHelper(this);

        tambahPemasukkan = (CardView) findViewById(R.id.tambahPemasukkan);
        tambahPengeluaran = (CardView) findViewById(R.id.tambahPengeluaran);
        detailCashFlow = (CardView) findViewById(R.id.detailCashFlow);
        pengaturan = (CardView) findViewById(R.id.pengaturan);
        text_pemasukkan = findViewById(R.id.text_pemasukkan);
        text_pengeluaran = findViewById(R.id.text_pengeluaran);

        totalMasukKeluar();

        tambahPemasukkan.setOnClickListener(this);
        tambahPengeluaran.setOnClickListener(this);
        detailCashFlow.setOnClickListener(this);
        pengaturan.setOnClickListener(this);
    }

    private void totalMasukKeluar() {
        NumberFormat formatRp = NumberFormat.getInstance(Locale.GERMANY);
        SQLiteDatabase db = DB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(total_kas) AS total, " +
                "(SELECT SUM(total_kas) FROM kas WHERE tipe_kas='Pemasukkan') AS masuk, " +
                "(SELECT SUM(total_kas) FROM kas WHERE tipe_kas='Pengeluaran') AS keluar " +
                "FROM kas", null);
        cursor.moveToFirst();

        int i;
        for (i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            text_pemasukkan.setText( "Rp. " + formatRp.format(cursor.getDouble(1)) );
            text_pengeluaran.setText( "Rp. " + formatRp.format(cursor.getDouble(2)) );
        }

        refresh(1000);
    }

    private void refresh(int timer) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                totalMasukKeluar();
            }
        };
        handler.postDelayed(runnable, timer);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.tambahPemasukkan :
                i = new Intent(this, TambahPemasukkan.class);
                startActivity(i);
                break;

            case R.id.tambahPengeluaran :
                i = new Intent(this, TambahPengeluaran.class);
                startActivity(i);
                break;

            case R.id.detailCashFlow :
                i = new Intent(this, DetailCashFlow.class);
                startActivity(i);
                break;

            case R.id.pengaturan :
                i = new Intent(this, Pengaturan.class);
                startActivity(i);
                break;
        }
    }
}