import './scss/mainStyles.scss';
var $ = require("jquery");


var itemsToBuy = [];

$.ajax({
	url: "https://nit.tron.net.ua/api/category/list",
	success: function (data) {

		for(var i = data.length-1; i>=0; i--){
			$(".menu").prepend("<a href='#"+data[i].name.replace(" ","")+"', id='category"+data[i].id+"' class ='categoryOpen'>"+data[i].name+"</a><br><br><br>");
		}
		$(".menu").prepend("<a href='#AllProducts', id='category-1' class ='categoryOpen'>All Products</a><br><br><br>");


	}
});

$.ajax({
	url: "https://nit.tron.net.ua/api/product/list",
	success: function (data) {
		for(var i = data.length-1; i>=0; i--){
			var specPrice = "";
			if(data[i].special_price!=null) specPrice = "<span class='prevPrice'>"+data[i].special_price+"</span> ";
			$(".content").append("<div class='product' id ='item"+data[i].id+"'><img src='"+data[i].image_url+"'><div class='price'>"+data[i].name+"<br>"+specPrice+data[i].price+"<button class='details'>details</button><button class='buy'>buy</button></div></div");
		}


	}
});





$(document).on('click', '.details', function (e) {
	$(".sendOrder").css("display", "none");
	var id = e.target.parentElement.parentElement.id.replace('item','');
	$(".productDetails").children().remove();
	$(".productDetails").css("display","block");
	$(".bucket").css("display","none");
	$.ajax({
		url:"https://nit.tron.net.ua/api/product/"+id,
		success:function(data){
		$(".productDetails").attr('id',"deteilsOf"+id);
		$(".productDetails").append("<img src="+data.image_url+">");
		$(".productDetails").append("<p>name: "+data.name+"</p>");
		$(".productDetails").append("<p>price: "+data.price+"</p>");
		$(".productDetails").append("<p>description: "+data.description+"</p>");
		$(".productDetails").append("<button id='closeDetails'>continue</button><button id='buyItem' class='buy'>buy</button>");

		}
	});
	});



$(document).on('click', '.categoryOpen', function(event) {
	$(".sendOrder").css("display", "none");
		$(".bucket").css("display","none");
		$(".productDetails").css("display","none");

		var id = event.target.id.replace("category","");
		$(".content").children().remove();
		if(id!='-1'){
				$.ajax({
		url: "https://nit.tron.net.ua/api/product/list/category/"+id,
		success:function function_name(data) {
				

				$.ajax({
					url: "https://nit.tron.net.ua/api/category/"+id,
					success: function (resp) {
					$(".content").prepend("<h1 id = "+resp.name.replace(" ","")+">"+resp.name+"</h1>");
						}
					});

				


		for(var i = 0; i<data.length; i++){
			var specPrice = "";
			if(data[i].special_price!=null) specPrice = "<span class='prevPrice'>"+data[i].special_price+"</span> ";
			$(".content").append("<div class='product' id = 'item"+data[i].id+"'><img src='"+data[i].image_url+"'><div class='price'>"+data[i].name+"<br>"+specPrice+data[i].price+"<button class='details'>details</button><button class='buy'>buy</button></div></div");
		}
		}
	});
			} else{


				$.ajax({
	url: "https://nit.tron.net.ua/api/product/list",
	success: function (data) {
		for(var i = data.length-1; i>=0; i--){
			var specPrice = "";
			if(data[i].special_price!=null) specPrice = "<span class='prevPrice'>"+data[i].special_price+"</span> ";
			$(".content").append("<div class='product' id ='item"+data[i].id+"'><img src='"+data[i].image_url+"'><div class='price'>"+data[i].name+"<br>"+specPrice+data[i].price+"<button class='details'>details</button><button class='buy'>buy</button></div></div");
		}
				$(".content").prepend("<h1 id = 'AllProducts'>All Products</h1>");

	}
});

			}

});


$(document).on('click', '#sendOrderButton', function (e) {
	var products = "";
	for(var i = 0; i< itemsToBuy.length;i++){
		if(itemsToBuy[i]>0){
			products+="&products["+i+"]="+itemsToBuy[i];
		}
		};
	var dataSend = "token=qwfivbYlqIF8tkACUXyx&name="+$("input[name=name]").val()+"&phone="+$("input[name=phone]").val()+"&email="+$("input[name=email]").val()+products;
		$.ajax({
		type:"POST",
		url: "https://nit.tron.net.ua/api/order/add",
		data: dataSend,
		success:function function_name(data) {
			if(data.status=="error"){ 
				var err = "";
						for (var key in data.errors) {
				err+=(data.errors[key])+" ";	
}
				alert(err);
			} else{
				alert("success");
				itemsToBuy = [];
				$(".inbucket").children().remove();
				$(".bucket-icon").text("0");
				$("#total").text("Total: 0");
				$(".sendOrder").css("display", "none");
			}
			
		}
	});
});

$(document).on('click', '#purchase', function (e) {
	$(".bucket").css("display","none");
	$(".sendOrder").css("display", "block");
});

$(document).on('click', '#cancellOrder', function (e) {
	$(".sendOrder").css("display", "none");
});

$(".bucket-icon").click(function() {
	$(".bucket").css("display","block");
	$(".productDetails").css("display","none");
	$(".sendOrder").css("display", "none");
})
$("#continue").click(function() {
	$(".bucket").css("display","none");
})

$(document).on('click', '#closeDetails', function (e) {
	$(".productDetails").css("display","none");
});

	

$("#bucketFromMenu").click(function() {
	$(".bucket").css("display","block");
	$(".productDetails").css("display","none");
	$(".sendOrder").css("display", "none");
})

$(document).on('click', '.plus', function (e) {
		$(".bucket-icon").text(Number($(".bucket-icon").text())+1);
			$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))+1)+')'    );


	var id = e.target.parentElement.parentElement.id.replace("inBucket","");
	itemsToBuy[id]+=1
	var item = $("#inBucket"+id);
	item.children(".count").text(Number(item.children(".count").text().replace("-","").replace("+",""))+1);
	item.children(".count").prepend('<button class="minus">-</button>');
	item.children(".count").append('<button class="plus">+</button>');
	item.children(".sum").text(Number(item.children(".sum").text())/(Number(item.children(".count").text().replace("-","").replace("+",""))-1)*Number(item.children(".count").text().replace("-","").replace("+","")));

		$("#total").text('Total: '+(Number($("#total").text().split(" ")[1])+Number(item.children(".sum").text())/((Number(item.children(".count").text().replace("-","").replace("+",""))))));
});

$(document).on('click', '.minus', function (e) {
		$(".bucket-icon").text(Number($(".bucket-icon").text())-1);
			$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))-1)+')'    );

			var id = e.target.parentElement.parentElement.id.replace("inBucket","");
			itemsToBuy[id]-=1
	var item = $("#inBucket"+id);
		$("#total").text('Total: '+(Number($("#total").text().split(" ")[1])-Number(item.children(".sum").text())/((Number(item.children(".count").text().replace("-","").replace("+",""))))));
	item.children(".count").text(Number(item.children(".count").text().replace("-","").replace("+",""))-1);
	item.children(".count").prepend('<button class="minus">-</button>');
	item.children(".count").append('<button class="plus">+</button>');
	item.children(".sum").text(Number(item.children(".sum").text())/((Number(item.children(".count").text().replace("-","").replace("+","")))+1)*Number(item.children(".count").text().replace("-","").replace("+","")));
	
	if(Number(item.children(".count").text().replace("-","").replace("+","")==0)){
		item.remove();
	}
});


$(document).on('click', '.buy', function (e) {
	$(".sendOrder").css("display", "none");
	$(".productDetails").css("display","none");
	$(".bucket-icon").text(Number($(".bucket-icon").text())+1);
	$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))+1)+')'    );

	var img;
	var name; 
	var price;
	var id;

	
	if(e.target.parentElement.className=="productDetails"){
	id = e.target.parentElement.id.replace('deteilsOf','');	
	img = e.target.parentElement.childNodes[1].src;
	name = e.target.parentElement.childNodes[2].textContent.replace("name: ","");
	price = e.target.parentElement.childNodes[3].textContent.replace("price: ","");
	}else{
	id = e.target.parentElement.parentElement.id.replace('item','');
	img = e.target.parentElement.parentElement.childNodes[0].src;
    name = e.target.parentElement.childNodes[0].textContent;
    price = e.target.parentElement.childNodes[3].textContent;
    
    if(price =='details'){
    	price = e.target.parentElement.childNodes[2].textContent;
    }
	}
   


    price = price.replace(" ","");

    if(price.split(" ").length>1){
    	price = price.split(" ")[2];
    }
	
	var item = $("#inBucket"+id);
	if(item.length==0){
		itemsToBuy[id]=1
		$(".inbucket").prepend('	<div id = "inBucket'+id+'" class="productInfo"><img src="'+img+'" alt=""><div class="name">'+name+'</div><div class="count"><button class="minus">-</button>1<button class="plus">+</button></div><div class="sum">'+price+'</div></div>');
	}else{
		itemsToBuy[id]+=1
		item.children(".count").text(Number(item.children(".count").text().replace("-","").replace("+",""))+1);
		item.children(".count").prepend('<button class="minus">-</button>');
		item.children(".count").append('<button class="plus">+</button>');
		item.children(".sum").text(price*Number(item.children(".count").text().replace("-","").replace("+","")));
	}
	
		$("#total").text('Total: '+(Number($("#total").text().split(" ")[1])+Number(price)));
		$(".bucket").css("display","block");
})


