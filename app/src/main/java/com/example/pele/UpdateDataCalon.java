package com.example.pele;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataCalon extends AppCompatActivity {
    DBHelper dbHelper;
    EditText txNamaKetua, txNamaWakil, txVisi, txMisi, txGambar, txNourut;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_calon);

        dbHelper = new DBHelper(this);
        id = getIntent().getLongExtra(DBHelper.user_id, 0);

        txNamaKetua = (EditText) findViewById(R.id.txNamaKetua_edit);
        txNamaWakil = (EditText) findViewById(R.id.txNamaWakil_edit);
        txVisi = (EditText) findViewById(R.id.txVisi_edit);
        txMisi = (EditText) findViewById(R.id.txMisi_edit);
        txGambar = (EditText) findViewById(R.id.txGambar_edit);
        txNourut = (EditText) findViewById(R.id.txNourut_edit);

    }

    private void getData() {
        Cursor cursor = dbHelper.ambilSatuDataUser(id);
        if (cursor.moveToFirst()) {
            String namaketua = cursor.getString(cursor.getColumnIndex(DBHelper.calon_namaketua));
            String namawakil = cursor.getString(cursor.getColumnIndex(DBHelper.calon_namawakil));
            String visi = cursor.getString(cursor.getColumnIndex(DBHelper.calon_visi));
            String misi = cursor.getString(cursor.getColumnIndex(DBHelper.calon_misi));
            String gambar = cursor.getString(cursor.getColumnIndex(DBHelper.calon_gambar));
            String nourut = cursor.getString(cursor.getColumnIndex(DBHelper.calon_nourut));

            txNamaKetua.setText(namaketua);
            txNamaWakil.setText(namawakil);
            txVisi.setText(visi);
            txMisi.setText(misi);
            txGambar.setText(gambar);
            txNourut.setText(nourut);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu_calon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_edit:
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
                    Toast.makeText(UpdateDataCalon.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{

                    dbHelper.updateDataUser(values, id);
                    Toast.makeText(UpdateDataCalon.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();

                }
        }
        switch (item.getItemId()){
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDataCalon.this);
                builder.setMessage("Data ini akan dihapus.");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteDataUser(id);
                        Toast.makeText(UpdateDataCalon.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

}