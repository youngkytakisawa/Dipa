package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailCashFlow extends AppCompatActivity {

    public static String id_kas;
    DBHelper DB;
    Cursor cursor;
    String queryGetAllKas;
    ArrayList<Kas> kasList;
    RecyclerView recyclerView;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash_flow);
        id_kas = "";
        queryGetAllKas = "";
        DB   = new DBHelper(this);
        recyclerView = findViewById(R.id.list_semua_kas);
        btn_back = findViewById(R.id.btn_back);
        kasList = new ArrayList<>();

        KasAdapter();
        setAdapter();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void KasAdapter() {
        queryGetAllKas  =
        "SELECT id_kas, total_kas, tipe_kas, keterangan, tanggal " +
                "FROM kas ORDER BY id_kas DESC";
        SQLiteDatabase db = DB.getReadableDatabase();
        cursor = db.rawQuery(queryGetAllKas, null);
        cursor.moveToFirst();

        int i;

        for (i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            kasList.add(new Kas(cursor.getString(0), cursor.getString(2), cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }

        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Tidak ada kas untuk saat ini", Toast.LENGTH_LONG).show();
        }
    }

    private void setAdapter(){
        KasAdapter adapter = new KasAdapter(kasList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}