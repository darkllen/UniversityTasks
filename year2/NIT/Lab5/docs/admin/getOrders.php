<?php

		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM ordered";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));

$rows_num = mysqli_num_rows($result);
$arr;
header('Content-Type: application/json');
for ($i = 0; $i < $rows_num; $i++) {
    $row = mysqli_fetch_row($result);
    $products = explode(" ", $row[4]);
     unset($products[count($products)-1]);

    $sum = 0;
    $categories = array('name'=>$row[1], 'phone'=>$row[2], 'email'=>$row[3], 'items'=>$products);
 
    $arr[$i] = $categories;
  
}

$newarr = [];
$m = 0;
foreach ($arr as $key => $value) {
	$sum = 0; 
		 $prods = [];
	foreach ($value['items'] as $key => $value) {
		$id = explode(":",  $value)[0];
		$num = explode(":",  $value)[1];

		$query ="SELECT * FROM `items` where `id`=$id";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
	 	$row = mysqli_fetch_row($result);
	 	$name = $row[1];
	 	$sum+=(intval($row[4])*intval($num));
	 	$prods[$id] = $name . ": " .$num . "шт." ;
	}
	
	$categories =join(", ",  $prods);
	$arr[$m]['items'] = $categories;
		$arr[$m]['sum'] = $sum;
	$m++;
}
echo json_encode($arr);
?>