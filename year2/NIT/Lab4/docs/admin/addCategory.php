<?php
		$name = $_POST['name'];
		$description = $_POST['description'];
		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="INSERT INTO `categories`(`name`, `description`) VALUES ('$name','$description')";
		mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
		echo 1;
?>