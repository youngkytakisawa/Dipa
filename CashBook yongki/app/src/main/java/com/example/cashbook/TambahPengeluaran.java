package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TambahPengeluaran extends AppCompatActivity implements View.OnClickListener{

    Button simpan_pengeluaran, kembali;
    EditText txTanggal2, total_kas2, keterangan2;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String tipeKas;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengeluaran);

        DB = new DBHelper(this);

        txTanggal2 =  findViewById(R.id.txTanggal2);
        total_kas2 =  findViewById(R.id.total_kas2);
        keterangan2 =  findViewById(R.id.keterangan2);
        tipeKas = "Pengeluaran";
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        simpan_pengeluaran = findViewById(R.id.simpan_pengeluaran);
        kembali = findViewById(R.id.kembali);

        txTanggal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }

        });

        simpan_pengeluaran.setOnClickListener(this);
        kembali.setOnClickListener(this);
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                txTanggal2.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.simpan_pengeluaran :
                if (txTanggal2.getText().toString().equals("") || total_kas2.getText().toString().equals("") || keterangan2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = DB.getWritableDatabase();
                    db.execSQL("INSERT INTO kas (tipe_kas, total_kas, keterangan, tanggal) VALUES('" + tipeKas + "','" + total_kas2.getText().toString() + "','" + keterangan2.getText().toString() + "','" + txTanggal2.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case R.id.kembali :
                i = new Intent(this, HomeActivity.class);
                startActivity(i);
                break;
        }
    }
}