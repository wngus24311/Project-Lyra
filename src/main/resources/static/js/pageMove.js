
function membership(){
	let auth = localStorage.getItem("Authorization");
		$.ajax({
			url: '/member/membership',
			type:"post",
			contentType:"application/json; charset=UTF-8",
			headers: {
				"Authorization" : auth
			},
			dataType:"text",
			success: function(result){
				console.log("success result membership ");
				let url = "/member/membership?name=" + result;
				console.log(url);
				location.replace(url);
			}
		});
	}
	
function main(){
	let auth = localStorage.getItem("Authorization");
		$.ajax({
			url: '/main/main',
			type:"post",
			contentType:"application/json; charset=UTF-8",
			headers: {
				"Authorization" : auth
			},
			dataType:"text",
			success: function(result){
				console.log("success result : " + result);
				let url = "/main/main?name=" + result;
				console.log(url);
				location.replace(url);
			}
		});
	}
	
function category(){
	let auth = localStorage.getItem("Authorization");
		$.ajax({
			url: '/main/genrepage',
			type:"post",
			contentType:"application/json; charset=UTF-8",
			headers: {
				"Authorization" : auth
			},
			dataType:"text",
			success: function(result){
				console.log("success result : " + result);
				let url = "/main/genrepage?name=" + result;
				console.log(url);
				location.replace(url);
			}
		});
	}
	
function mypage(){
	let auth = localStorage.getItem("Authorization");
		$.ajax({
			url: '/member/mypage',
			type:"post",
			contentType:"application/json; charset=UTF-8",
			headers: {
				"Authorization" : auth
			},
			dataType:"text",
			success: function(result){
				console.log("success result : " + result);
				let url = "/member/mypage?name=" + result;
				console.log(url);
				location.replace(url);
			}
		});
	}