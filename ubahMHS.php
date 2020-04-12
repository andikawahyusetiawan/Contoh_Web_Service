<?php 

if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable 
		$nrp = $_POST['nrp'];
		$nama = $_POST['nama'];
		$jurusan = $_POST['jurusan'];
		$semester = $_POST['semester'];
		$kelamin = $_POST['kelamin'];
		
		//import file koneksi database 
		require_once('koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE mahasiswa SET nrp = '$nrp', nama = '$nama', jurusan = '$jurusan', semester = '$semester', kelamin = '$kelamin' WHERE nrp = $nrp;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Pegawai';
		}else{
			echo 'Gagal Update Data Pegawai';
		}
		
		mysqli_close($con);
	}

 ?>