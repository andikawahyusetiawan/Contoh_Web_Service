<?php 

//Import File Koneksi Database
	require_once('koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT * FROM mahasiswa";
	
	//Mendapatkan Hasil
	$mhs = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($mhs)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"nrp"=>$row['nrp'],
			"nama"=>$row['nama'],
			"jurusan"=>$row['jurusan'],
			"semester"=>$row['semester'],
			"kelamin"=>$row['kelamin']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

 ?>