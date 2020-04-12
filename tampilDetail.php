<?php 

//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id = $_GET['nrp'];
	
	//Importing database
	require_once('koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM mahasiswa WHERE nrp=$nrp";
	
	//Mendapatkan Hasil 
	$mhs = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($mhs);
	array_push($result,array(
			"nrp"=>$row['nrp'],
			"nama"=>$row['nama'],
			"jurusan"=>$row['jurusan'],
			"semester"=>$row['semester'],
			"kelamin"=>$row['kelamin']
		));
 
	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

 ?>