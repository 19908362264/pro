/*!
 * benwu WEB.JS V1.0
 * www.benwunet.com
 * Author ihong xiaoqing
 * Update date @2018-03-01
 */

var school = {
	flag: 0,
	ipt: '#school_input',
	list: '#schoollist',
	focus: function(){},
	blur: function(){},
	init: function(){
		var t;
		school.hide();
		school.bindInput();
		$(school.list).on('mouseup','li',function(e){
			if (t) {
				clearInterval(t);
			};
			var str = $(this).text();
			var school_id=$(this).attr('data-id');
			$('#school_id').val(school_id);
			school.setValue($(school.ipt), str);
		});
		$(school.list).on('mousemove','li',function(e){
			school.flag = 1;
		});
		$(school.ipt).focus(function(){
			school.flag = 0;
			school.focus();
		});
		$(school.ipt).blur(function(){
			if (!school.flag) {
				school.hide();
			} else {
				t = setTimeout(school.hide,300);
			}
			school.blur();
		});
	},
	bindInput: function(){
		//IE
		if (window.ActiveXObject){
			document.getElementById("school_input").attachEvent("onpropertychange",school.show);
		}else{
			$(school.ipt).on("input",school.show);
		}
	},
	show: function(){
		school.showList();
		var s_value = $(school.ipt).val();
		if (online){
			//ajax获取搜索列表 最多显示10条
			$.ajax({
				type:'post',
				dataType:'json',
				url:'/home/userCenter/selectSchools',
				data:{name:s_value},
				success:function (data) {
					if(data.status==1){
						$('#schoollist').find('ul').html(data.schools);
					}else{
						$('#schoollist').find('ul').empty().html(data.schools);
					}
				}
			});
		}else{
			console.log(s_value);
			$(school.list).children().children("li").hide();
			if(s_value.length >= 1){
				$(school.list).children().children("li").each(function(){
					var tmp = $(this).text().substr(0,$(this).text().length);
				
					if(tmp && s_value==tmp.substr(0,s_value.length)){
						$(this).show();
					}
				});
			}
		}
	},
	show2: function(){
		school.showList();
		var s_value = $(school.ipt).val();
		$(school.list).children().children("li").hide();
		if(s_value.length >= 1){
			$(school.list).children().children("li").each(function(){
				var tmp = $(this).text().substr(0,$(this).text().length);
				
				if(tmp && s_value==tmp.substr(0,s_value.length)){
					$(this).show();
				}
			});
		}
	},
	hide: function(){
		$(school.list).css("display","none");
	},
	setValue: function(obj, str){
		obj.val(str);
		school.hide();
		obj.focus();
	},
	showList: function(){
		var obj = $(school.ipt);
		$(school.list).css({
			'position': 'absolute',
			//"left": parseInt(obj.offset().left),
			//"top": parseInt(obj.offset().top + obj.outerHeight()),
			"top": parseInt(obj.outerHeight()),
			"width": parseInt(obj.outerWidth()),
			'display': 'block'
		});
	}
};


