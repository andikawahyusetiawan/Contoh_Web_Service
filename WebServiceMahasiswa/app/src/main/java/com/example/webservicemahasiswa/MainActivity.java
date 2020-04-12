package com.example.webservicemahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextNRP;
    private EditText editTextNama;
    private EditText editTextJurusan;
    private EditText editTextSemester;
    private EditText editTextKelamin;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        editTextNRP = (EditText) findViewById(R.id.editTextNRP);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJurusan = (EditText) findViewById(R.id.editTextJurusan);
        editTextSemester = (EditText) findViewById(R.id.editTextSemester);
        editTextKelamin = (EditText) findViewById(R.id.editTextKelamin);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan mahasiswa (CREATE)
    private void addEmployee(){

        final String nrp = editTextNRP.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String jurusan = editTextJurusan.getText().toString().trim();
        final String semester = editTextSemester.getText().toString().trim();
        final String kelamin = editTextKelamin.getText().toString().trim();

        class AddMHS extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();.Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_MHS_NRP,nrp);
                params.put(konfigurasi.KEY_MHS_NAMA,nama);
                params.put(konfigurasi.KEY_MHS_JURUSAN,jurusan);
                params.put(konfigurasi.KEY_MHS_SEMESTER,semester);
                params.put(konfigurasi.KEY_MHS_KELAMIN,kelamin);

                request mhs = new request();
                String res = mhs.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddMHS ae = new AddMHS();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,Button.class));
        }
    }
}
