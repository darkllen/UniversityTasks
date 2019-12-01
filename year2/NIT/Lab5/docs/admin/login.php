<?php
$login = $_POST['login'];
$pass = $_POST['password'];
		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM users where `name`=$login and `pass`=$pass";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));

$rows_num = mysqli_num_rows($result);
if($rows_num==1){
	echo '<button class="full" id="add_item">Add Item</button>
	<button class="full" id="add_category">Add category</button>
	<button class="full" id="associate">Associate item to category</button>
	<button class="full" id="get_orders">Get Orders</button>

	<div class="container"></div>';
}else{
	echo 0;
}

?>