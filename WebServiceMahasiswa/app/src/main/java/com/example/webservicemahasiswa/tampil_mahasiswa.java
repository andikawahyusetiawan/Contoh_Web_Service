package com.example.webservicemahasiswa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class tampil_mahasiswa extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNRP;
    private EditText editTextNama;
    private EditText editTextJurusan;
    private EditText editTextSemester;
    private EditText editTextKelamin;

    private Button buttonUbah;
    private Button buttonHapus;

    private String NRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_mahasiswa);

        Intent intent = getIntent();

        NRP = intent.getStringExtra(konfigurasi.KEY_MHS_NRP);

        editTextNRP = (EditText) findViewById(R.id.editTextNRP);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJurusan = (EditText) findViewById(R.id.editTextJurusan);
        editTextSemester = (EditText) findViewById(R.id.editTextSemester);
        editTextKelamin = (EditText) findViewById(R.id.editTextKelamin);

        buttonUbah = (Button) findViewById(R.id.buttonUbah);
        buttonHapus = (Button) findViewById(R.id.buttonHapus);

        buttonUbah.setOnClickListener(this);
        buttonHapus.setOnClickListener(this);

        editTextNRP.setText(NRP);

        getMahasiswa();
    }

    private void getMahasiswa(){
        class GetMahasiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampil_mahasiswa.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMahasiswa(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                request mhs = new request();
                String s = mhs.sendGetRequestParam(konfigurasi.URL_GET_EMP,NRP);
                return s;
            }
        }
        GetMahasiswa ge = new GetMahasiswa();
        ge.execute();
    }

    private void tampilakanMahasiswa(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nrp = c.getString(konfigurasi.TAG_NRP);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String jurusan = c.getString(konfigurasi.TAG_JURUSAN);
            String semester = c.getString(konfigurasi.TAG_SEMESTER);
            String kelamin = c.getString(konfigurasi.TAG_KELAMIN);

            editTextNRP.setText(nrp);
            editTextNama.setText(nama);
            editTextJurusan.setText(jurusan);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void UbahMahasiswa(){
        final String nrp = editTextNRP.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String jurusan = editTextJurusan.getText().toString().trim();
        final String semester = editTextSemester.getText().toString().trim();
        final String kelamin = editTextKelamin.getText().toString().trim();

        class UbahMahasiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampil_mahasiswa.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                try {
                    Toast.makeText(tampil_mahasiswa.this, "", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                };(tampil_mahasiswa.this,s,Toast.LENGTH_LONG).ShowableListMenu();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_MHS_NRP,nrp);
                hashMap.put(konfigurasi.KEY_MHS_NAMA,nama);
                hashMap.put(konfigurasi.KEY_MHS_JURUSAN,jurusan);
                hashMap.put(konfigurasi.KEY_MHS_SEMESTER,semester);
                hashMap.put(konfigurasi.KEY_MHS_KELAMIN,kelamin);

                request rh = new request();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UbahMahasiswa ue = new UbahMahasiswa();
        ue.execute();
    }

    private void HapusMahasiwa(){
        class HapusMahasiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampil_mahasiswa.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampil_mahasiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                request rh = new request();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, NRP);
                return s;
            }
        }
    }

    private void HapusMahasiswa(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pegawai ini?");

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface().OnClickListener() {
                    @Override
                    public void onClick(DialogInterface int arg1) {
                        HapusMahasiwa();
                        startActivity(new Intent(tampil_mahasiswa.this,tampilakanMahasiswa().class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUbah){
            UbahMahasiswa();
        }

        if(v == buttonHapus){
            HapusMahasiwa();
        }
    }
}
