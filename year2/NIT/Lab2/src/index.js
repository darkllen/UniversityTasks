import './scss/mainStyles.scss';
var $ = require("jquery");
$(".bucket-icon").click(function() {
	console.log("s");
	$(".bucket").css("display","block");
})
$("#continue").click(function() {
	$(".bucket").css("display","none");
})
$("#bucketFromMenu").click(function() {
	$(".bucket").css("display","block");
})

$(document).on('click', '.plus', function (e) {
		$(".bucket-icon").text(Number($(".bucket-icon").text())+1);
			$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))+1)+')'    );

	var name = e.target.parentElement.parentElement.id;
	var item = $("#"+name.replace(/ /g,""));
	item.children(".count").text(Number(item.children(".count").text().replace("-","").replace("+",""))+1);
	item.children(".count").prepend('<button class="minus">-</button>');
	item.children(".count").append('<button class="plus">+</button>');
	item.children(".sum").text(Number(item.children(".sum").text())/(Number(item.children(".count").text().replace("-","").replace("+",""))-1)*Number(item.children(".count").text().replace("-","").replace("+","")));

		$("#total").text('Total: '+(Number($("#total").text().split(" ")[1])+Number(item.children(".sum").text())/((Number(item.children(".count").text().replace("-","").replace("+",""))))));
});
$(document).on('click', '.minus', function (e) {
		$(".bucket-icon").text(Number($(".bucket-icon").text())-1);
			$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))-1)+')'    );

		var name = e.target.parentElement.parentElement.id;
	var item = $("#"+name.replace(/ /g,""));
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
	$(".bucket-icon").text(Number($(".bucket-icon").text())+1);
	console.log($("#bucketFromMenu").text().split(" ")[1]);
	$("#bucketFromMenu").text('Bucket ('+(Number($("#bucketFromMenu").text().split(" ")[1].replace('(','').replace(')',''))+1)+')'    );
	
    var img = e.target.parentElement.childNodes[1].src;
    var name = e.target.parentElement.childNodes[3].innerHTML.split("<br>")[0];
    var price = e.target.parentElement.childNodes[3].innerHTML.split("<br>")[1];

    if(price.split(" ").length>1){
    	price = price.split(" ")[2];
    }
	
	var item = $("#"+name.replace(/ /g,""));
	if(item.length==0){
		$(".inbucket").prepend('	<div id="'+name.replace(/ /g,"")+'" class="productInfo"><img src="'+img+'" alt=""><div class="name">'+name+'</div><div class="count"><button class="minus">-</button>1<button class="plus">+</button></div><div class="sum">'+price+'</div></div>');
	}else{
		item.children(".count").text(Number(item.children(".count").text().replace("-","").replace("+",""))+1);
		item.children(".count").prepend('<button class="minus">-</button>');
		item.children(".count").append('<button class="plus">+</button>');
		item.children(".sum").text(price*Number(item.children(".count").text().replace("-","").replace("+","")));
	}
	
		$("#total").text('Total: '+(Number($("#total").text().split(" ")[1])+Number(price)));
		$(".bucket").css("display","block");
})
