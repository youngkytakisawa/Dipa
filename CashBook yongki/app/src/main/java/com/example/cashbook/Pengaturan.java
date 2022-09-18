package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class Pengaturan extends AppCompatActivity implements View.OnClickListener {

    Button simpan_password, kembali;
    EditText passwordBaru, passwordSekarang;
    DBHelper DB;
    Cursor cursor;
    String queryGetUser;

    TextView text_ganti_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        DB = new DBHelper(this);
        queryGetUser="";

        text_ganti_password = findViewById(R.id.text_gantipassword);
        text_ganti_password.setPaintFlags(text_ganti_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        passwordBaru = findViewById(R.id.passwordBaru);
        passwordSekarang = findViewById(R.id.passwordSekarang);
        simpan_password = findViewById(R.id.simpan_password);
        kembali = findViewById(R.id.kembali);

        simpan_password.setOnClickListener(this);
        kembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.simpan_password :
                SQLiteDatabase db = DB.getWritableDatabase();
                queryGetUser =
                        "SELECT password FROM users";
                cursor = db.rawQuery(queryGetUser, null);
                cursor.moveToFirst();

                int i;

                for (i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    if(!passwordSekarang.getText().toString().equals(cursor.getString(0))){
                        Toast.makeText(this, "Silahkan Cek Kembali Password Anda", Toast.LENGTH_LONG).show();
                    }else{
                        db.execSQL("UPDATE users SET password="+"'"+passwordBaru.getText().toString()+"'");
                        Toast.makeText(this, "Password berhasil diubah", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                }
                break;
            case R.id.kembali :
                onBackPressed();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}