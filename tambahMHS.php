<?php 

if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$nrp = $_POST['nrp'];
		$nama = $_POST['nama'];
		$jurusan = $_POST['jurusan'];
		$semester = $_POST['semester'];
		$kelamin = $_POST['kelamin'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO mahasiswa (nrp,nama,jurusan,semester,kelamin) VALUES ('$nrp','$nama','$jurusan','$semester','$kelamin')";
		
		//Import File Koneksi database
		require_once('koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Pegawai';
		}else{
			echo 'Gagal Menambahkan Pegawai';
		}
		
		mysqli_close($con);
	}

 ?>