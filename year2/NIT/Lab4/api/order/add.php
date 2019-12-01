<?php
$name = $_POST['name'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$products = $_POST['products'];
$errors =[];

$i = 0;

if($name==''){
	$errors[$i] = "Name shouldn`t be empty.";
	$i++;
}
$pattern = '/^[0-9\-\(\)\/\+\s]*$/';
if($phone==''){
	$errors[$i] = "Phone number shouldn`t be empty.";
	$i++;
} else if ( !preg_match($pattern, $phone)){
	$errors[$i] = "Phone number isn`t valid.";
	$i++;
}
if($email==''){
	$errors[$i] = "Email shouldn`t be empty.";
	$i++;
}else if(!preg_match("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)", $email)){
	$errors[$i] = "Email isn`t valid.";
	$i++;
}
$t = 0;
$num = 0;

$prodList = '';
foreach($products as $key=>$value){
	if(!$value<0){
		$t = 1;
	}
	$num++;
	$prodList.=strval($key) .":" . strval($value) . " " ;
}
if($t==1 || $num<=0){
	$errors[$i] = "Items number isn`t valid.";
	$i++;
}

		
		if($i>0){
		header('Content-Type: application/json; charset=UTF-8');
        echo (json_encode(array('status'=> 'error', 'errors' => $errors, 's'=>$products)));
		}else{
		echo (json_encode(array('status'=> 'success')));
		$query = "INSERT INTO `ordered`(`name`, `phone`, `email`, `products`) VALUES ('$name','$phone','$email','$prodList')";
		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
		}
		
	
?>