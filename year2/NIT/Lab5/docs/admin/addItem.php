<?php
		$name = $_POST['name'];
		$description = $_POST['description'];
		$image_url = $_POST['image_url'];
		$price = $_POST['price'];
		$special_price = $_POST['special_price'];
		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="INSERT INTO `items`(`name`, `description`, `image_url`, `price`, `special_price`) VALUES ('$name','$description','$image_url',$price,$special_price)";
		mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
		echo 1;
?>