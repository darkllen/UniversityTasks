<?php

		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM categories";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));

$rows_num = mysqli_num_rows($result);
$arr;
header('Content-Type: application/json');
for ($i = 0; $i < $rows_num; $i++) {
    $row = mysqli_fetch_row($result);
    $categories = array('id'=>$row[0], 'name'=>$row[1], 'description'=>$row[2]);
  

    $arr[$i] = $categories;
  
}
echo json_encode($arr);
?>