<?php

$num = explode("/",$_SERVER['REQUEST_URI'])[4];

		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM categories where `id`=$num";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));

$rows_num = mysqli_num_rows($result);
$arr;
header('Content-Type: application/json');

    $row = mysqli_fetch_row($result);
    $categories = array('id'=>$row[0], 'name'=>$row[1], 'description'=>$row[2]);
  if($row[0]>0)
echo json_encode ($categories);
else{
		header('HTTP/1.1 500 Internal Server Booboo');
        header('Content-Type: application/json; charset=UTF-8');
        die(json_encode(array('status'=> 'error', 'statusCode' => 404, 'statusText' => 'Not Found')));
}
?>