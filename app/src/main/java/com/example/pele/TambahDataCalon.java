package com.example.pele;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class TambahDataCalon extends AppCompatActivity {
    DBHelper dbHelper;
    EditText txNamaKetua, txNamaWakil, txVisi, txMisi, txGambar, txNourut;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_calon);

        dbHelper = new DBHelper(this);
        id = getIntent().getLongExtra(DBHelper.calon_id, 0);

        txNamaKetua = (EditText)findViewById(R.id.txNamaKetua_Add);
        txNamaWakil = (EditText)findViewById(R.id.txNamaWakil_Add);
        txVisi = (EditText)findViewById(R.id.txVisi_Add);
        txMisi = (EditText)findViewById(R.id.txMisi_Add);
        txGambar = (EditText)findViewById(R.id.txGambar_Add);
        txNourut = (EditText)findViewById(R.id.txNourut_Add);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu_calon, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_add:
                String namaketua = txNamaKetua.getText().toString().trim();
                String namawakil = txNamaWakil.getText().toString().trim();
                String visi = txVisi.getText().toString().trim();
                String misi = txMisi.getText().toString().trim();
                String gambar = txGambar.getText().toString().trim();
                String nourut = txNourut.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DBHelper.calon_namaketua, namaketua);
                values.put(DBHelper.calon_namawakil, namawakil);
                values.put(DBHelper.calon_visi, visi);
                values.put(DBHelper.calon_misi, misi);
                values.put(DBHelper.calon_gambar, gambar);
                values.put(DBHelper.calon_nourut, nourut);

                if (namaketua.equals("") || namawakil.equals("") || visi.equals("") || misi.equals("") || gambar.equals("") || nourut.equals("")) {
                    Toast.makeText(TambahDataCalon.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {

                    dbHelper.insertDataCalon(values);
                    Toast.makeText(TambahDataCalon.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();

                }
        }
        return super.onOptionsItemSelected(item);
    }
}