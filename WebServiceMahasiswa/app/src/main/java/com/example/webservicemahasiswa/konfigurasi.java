package com.example.webservicemahasiswa;

public class konfigurasi {

    public static final String URL_ADD="http://192.168.1.7/PHP/tambahMHS.php";
    public static final String URL_GET_ALL = "http://192.168.1.7/PHP/tampilMHS.php";
    public static final String URL_GET_EMP = "http://192.168.1.7/PHP/tampilDetail.php?nrp=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.7/PHP/ubahMHS.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.7/PHP/hapusMHS.php?nrp=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_MHS_NRP = "nrp";
    public static final String KEY_MHS_NAMA = "nama";
    public static final String KEY_MHS_JURUSAN = "jurusan"; //desg itu variabel untuk posisi
    public static final String KEY_MHS_SEMESTER = "semester";
    public static final String KEY_MHS_KELAMIN = "kelamin";//salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_NRP = "nrp";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_JURUSAN = "jurusan";
    public static final String TAG_SEMESTER = "semester";
    public static final String TAG_KELAMIN = "kelamin";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_NRP = "nrp";
    public static String TAG_ID;
}
