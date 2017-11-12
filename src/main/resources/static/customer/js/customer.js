
//页面加载时完成
$(document).ready(function(){
	var codeType = '30101004';
	$("#region").autocomplete({
	    // 静态的数据源
	    source:'dict/getValues/30101004/10',
	    dataType: 'json',
	    select: function(event, ui){
	        // 这里的this指向当前输入框的DOM元素
	        // event参数是事件对象
	        // ui对象只有一个item属性，对应数据源中被选中的对象
	        $(this).value = ui.item.label;
	        //alert(ui.item.code);
	        // 必须阻止事件的默认行为，否则autocomplete默认会把ui.item.value设为输入框的value值
	        event.preventDefault();     
	    }
	});
});

function loadData(){
	 	$("#user-list").html("");
	    // 显示loading.gif
	    $("#loadingDiv").show();
	    $.ajax({
	        type:"post",
	        url: "userList",
	        dataType:"json",
	        data:queryBean,
	        success:function(data){
	            var users = data.list;
	            for (var i = 0; i < users.length; i++) {
	                userArray.push(users[i]);
	                $("#user-list").append(
	                    '<tr>' +
	                    '<td style="text-align: center"><input id="checkbox_' + i + '" type="checkbox" value="' + users[i].id + '"></td>' +
	                    '<td style="text-align: center">' + users[i].name + '</td>' +      // 用户名
	                    '<td style="text-align: center">' + users[i].realName + '</td>' +   // 昵称
	                    '<td>' + users[i].corpId + '</td>' +                 // 单位
	                    '<td>' + users[i].deptId + '</td>' +                 // 部门
	                    '</tr>'
	                );
	            }
	            // 隐藏loading.gif
	            $("#loadingDiv").hide();
	        }
	    });
}

function listDataHtml(data){
	//
	$("#data-list").html("");
}

function auth(){
	//权限 控制按钮是否显示
	//所有有 auth 样式的元素隐藏
	$(".auth").css("display","none");
	//得到用的权限点
	var user_auth = ['bms.sys.user.add'];
	//遍历所有有  auth 样式的 元素
	$(".auth").each(function(index,element){
		var auth = $(this).attr("auth");
		for ( var i in user_auth) {
			if(auth == user_auth[i]){
				$(this).css("display","block");
				break;
			}
		}
	});
}