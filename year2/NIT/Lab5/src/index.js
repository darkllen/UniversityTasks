import './scss/mainStyles.scss';
var $ = require("jquery");


var itemsToBuy = [];


$.ajax({
	url: "http://193.111.0.203/Lab5/api/category/list",
	success: function (data) {

		for(var i = data.length-1; i>=0; i--){
			$(".menu").prepend("<a href='#"+data[i].name.replace(" ","")+"', id='category"+data[i].id+"' class ='categoryOpen'>"+data[i].name+"</a><br><br><br>");
		}
		$(".menu").prepend("<a href='#AllProducts', id='category-1' class ='categoryOpen'>All Products</a><br><br><br>");


	}
});

$.ajax({
	url: "http://193.111.0.203/Lab5/api/product/list",
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
		url:"http://193.111.0.203/Lab5/api/product/"+id,
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
		url: "http://193.111.0.203/Lab5/api/list/"+id,
		success:function function_name(data) {
				

				$.ajax({
					url: "http://193.111.0.203/Lab5/api/category/"+id,
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
	url: "http://193.111.0.203/Lab5/api/product/list",
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
		url: "http://193.111.0.203/Lab5/api/order/add",
		data: dataSend,
		success:function function_name(data) {
			console.log(data);
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

String.prototype.hashCode = function() {
  var hash = 0, i, chr;
  if (this.length === 0) return hash;
  for (i = 0; i < this.length; i++) {
    chr   = this.charCodeAt(i);
    hash  = ((hash << 5) - hash) + chr;
    hash |= 0; // Convert to 32bit integer
  }
  return hash;
};

$(document).on('click', '#log_in', function (e) {
				var log = "login="+$("input[name=login]").val().hashCode()+"&password="+$("input[name=password]").val().hashCode();
				console.log($("input[name=login]").val().hashCode());
				console.log($("input[name=password]").val().hashCode());
				$.ajax({
					type:"POST",
					url: "http://193.111.0.203/Lab5/admin/login.php",
					data: log,
					success: function (resp) {
					 if(resp==0){
					 	alert("incorrect login or password");
					 }else{
					 	$("body").html(resp);
					 }
					}
				});
});

$(document).on('click', '#add_item', function (e) {
	var item = $(".container");
	item.children().remove();
			item.css("display","block");	
     		item.prepend('<button id="addItem">add</button>');
     		item.prepend('<button class="exit">cancell</button>');
			item.prepend('<div><div>prev price:</div> <input type="text" name="special_price"></div>');
			item.prepend('<div><div>price:</div> <input type="text" name="price"></div>');
			item.prepend('<div><div>image url:</div> <input type="text" name="image_url"></div>');
			item.prepend('<div><div>description:</div> <input type="text" name="description"></div>');
			item.prepend('<div><div>name:</div> <input type="text" name="name"></div>');
});
$(document).on('click', '#addItem', function (e) {
	var item = "name="+$("input[name=name]").val();
	item += "&description="+$("input[name=description]").val();
	item += "&image_url="+$("input[name=image_url]").val();
	item += "&price="+$("input[name=price]").val();
	item += "&special_price="+$("input[name=special_price]").val();
		$.ajax({
					type:"POST",
					url: "http://193.111.0.203/Lab5/admin/addItem.php",
					data: item,
					success: function (resp) {
					 if(resp==1){
					 	alert("success");
					 }else{
					 	alert("something is wrong");
					 }
					}
				});
});
$(document).on('click', '#add_category', function (e) {
	var item = $(".container");
	item.children().remove();
			item.css("display","block");	
     		item.prepend('<button id="addCategory">add</button>');
     		item.prepend('<button class="exit">cancell</button>');
			item.prepend('<div><div>description:</div> <input type="text" name="description"></div>');
			item.prepend('<div><div>name:</div> <input type="text" name="name"></div>');
});
$(document).on('click', '#addCategory', function (e) {
	var item = "name="+$("input[name=name]").val();
	item += "&description="+$("input[name=description]").val();
		$.ajax({
					type:"POST",
					url: "http://193.111.0.203/Lab5/admin/addCategory.php",
					data: item,
					success: function (resp) {
					 if(resp==1){
					 	alert("success");
					 }else{
					 	alert("something is wrong");
					 }
					}
				});
});

$(document).on('click', '#associate', function (e) {
	var item = $(".container");
	item.children().remove();
	var sel1 = "<select class='selItems'  multiple name='items[]'>";
	var sel2 = "<select class='selCategories'  multiple name='categories[]'>";
				$.ajax({
					url: "http://193.111.0.203/Lab5/api/product/list",
					success: function (resp) {
						for(var l = 0; l<resp.length; l++){
							sel1+=" <option value='"+resp[l].id+"'>"+resp[l].name+"</option>";
						}
						sel1+="</select>";

						$.ajax({
					url: "http://193.111.0.203/Lab5/api/category/list",
					success: function (resp) {
						for(var l = 0; l<resp.length; l++){
							sel2+=" <option value='"+resp[l].id+"'>"+resp[l].name+"</option>";
						}
						sel2+="</select>";

						

								item.css("display","block");	
     		item.prepend('<button id="add_association">associate</button>');
     		item.prepend('<button class="exit">cancell</button>');
			item.prepend('<div><div>choose items:</div>'+sel1+'</div>');
			item.prepend('<div><div>choose categories:</div> '+sel2+'</div>');
					}
				});
					} 
				});
	
});
$(document).on('click', '#add_association', function (e) {
	var items = [];
        $.each($(".selItems option:selected"), function(){            
            items.push($(this).val());
        });;
     var categories = [];
       $.each($(".selCategories option:selected"), function(){            
            categories.push($(this).val());
        });;
	var res = "items="+items.join(" ");
	res+="&categories="+categories.join(" ");
	console.log(res);
		$.ajax({
					type:"POST",
					url: "http://193.111.0.203/Lab5/admin/associate.php",
					data: res,
					success: function (resp) {
					 		 if(resp==1){
					 	alert("success");
					 }else{
					 	alert("something is wrong");
					 }
					}
				});
});

$(document).on('click', '.exit', function (e) {
			$(".container").css("display","none");	
});

$(document).on('click', '#get_orders', function (e) {
	var item = $(".container");
	item.children().remove();
			item.css("display","block");	
     				$.ajax({
					url: "http://193.111.0.203/Lab5/admin/getOrders.php",
					success: function (resp) {
			for (var i = 0; i < resp.length; i++) {
				item.append('<div><div>name:</div> '+resp[i].name+'</div>');
				item.append('<div><div>phone:</div> '+resp[i].phone+'</div>');
				item.append('<div><div>email:</div> '+resp[i].email+'</div>');
				item.append('<div><div>items:</div>'+resp[i].items+'</div>');
				item.append('<div><div>sum:</div>'+resp[i].sum+'</div>');
				item.append('<hr>');
			}
     		item.append('<button class="exit">cancell</button>');
			
			
					}
				});
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


