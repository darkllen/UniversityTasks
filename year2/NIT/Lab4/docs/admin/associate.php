<?php
		$items = explode(" ", $_POST['items']);
		$categories = explode(" ", $_POST['categories']);

		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		foreach ($items as $key1 => $value1) {
			foreach ($categories as $key2 => $value2) {
					$query ="INSERT INTO `itemstocategories`(`item_id`, `category_id`) VALUES ( $value1,$value2)";
		mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
			}
		}
	
		echo 1;
?>