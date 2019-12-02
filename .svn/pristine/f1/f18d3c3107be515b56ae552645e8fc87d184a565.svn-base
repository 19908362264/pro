
(function(w){
	var a = w.app = w.app || {};
	//创建验证码
	w.mCode={
		character: {
			code:'',
			codeLength:4,
			rand:[],
			random: [0,1,2,3,4,5,6,7,8,9,'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
			],
			init(){
				this.rand=[];
				for (var i = 0; i < this.codeLength; i++) {
					this.rand.push(this.rand[i]);
					this.rand[i] = this.random[Math.floor(Math.random() * this.random.length)];
				}
				mCode.character.code = this.rand.join('')
				$(".btn-code").html(this.rand.join('&nbsp;'))
			}
		},
		setTime(){ // 倒计时
			var btn = $(".btn-mcode"),
					num = 60
			var timer = setInterval(function () {
				num--
				btn.text(num+'s，后重新获取')
				if (num <1) {
					btn.text('获取短信验证码').removeClass('btn-dashed');
					clearInterval(timer)
				}
			},1000)
		},
		isPhone(phone){ // 验证是否是手机号
			var pattern = /^[1][3-9][0-9]{9}$/;
			return pattern.test(phone);
		}
	}
})(window);



$(function(){
	var login = $("#loginform").validate({
		//debug:true, //调试状态：只验证不提交表单
		rules:{
			user:{
				required:true
			},
			password:{
				required:true
			},
			code:{
				required:true
			}
		},
		messages:{
			user:{
				required:"请输入学籍号/手机号"
			},
			password:{
				required:"请输入密码"
			},
			code:{
				required:"请输入验证码"
			}
		},
		errorPlacement: function(error, element) {
			element.attr('placeholder',error.text())
		},
		errorElement: "span",
		submitHandler: function(form){
			//本地测试
			console.log("验证通过");
			if ($("#loginform input[name=code]").val() == mCode.character.code) {
				//线上交互
				var arr = $("#loginform").serializeArray();
				$.post('#', arr, function (result) {
					if (result.status == 0) {
						layer.msg(result.msg);
					} else if(result.status == 1){
						//window.location.href = result.url;
					}
				}, 'json');
			}else{
				layer.msg('验证码错误');
			}
		}
	});
	$('.pass-form .btn-login').click(function(e){
		e.preventDefault(), e.stopPropagation();
		$("#loginform").submit();
	});
	var register = $("#registerform").validate({
		//debug:true, //调试状态：只验证不提交表单
		rules:{
			mobile:{
				required:true,
				isMobile:true
			},
			password:{
				required:true,
				minlength:6,
				maxlength:20
			},
			password2:{
				required:true
			},
			code:{
				required:true
			},
			agree:{
				isAgree :true
			}
		},
		messages:{
			mobile:{
				required:"请输入手机号",
				isMobile:"请输入正确的手机号码"
			},
			password:{
				required:"请输入您的新密码",
				minlength:'密码由6-20位字符，区分大小写',
				maxlength:'密码由6-20位字符，区分大小写'
			},
			password2:{
				required:"请再次输入密码",
				equalTo: "#password"
			},
			code:{
				required:"请输入验证码"
			},
			agree:{
				isAgree :""
			}
		},
		errorPlacement: function(error, element) {
			if (element.is(':radio') || element.is(':checkbox')) {
				$(".agreement").addClass("error")
			} else {
				element.attr('placeholder',error.text())
			}
		},
		errorElement: "span",
		submitHandler: function(form){
			console.log("验证通过");
			//线上交互
			var arr = $("#registerform").serializeArray();
			$.post('#', arr, function (result) {
				if (result.status == 0) {
					layer.msg(result.msg);
				} else if(result.status == 1){
					window.location.href = '#';
				}
			}, 'json');
		}
	});
	$('.pass-form .btn-register').click(function(e){
		e.preventDefault(), e.stopPropagation();
		$("#registerform").submit();
	});
	var proving = $("#provingform").validate({
		//debug:true, //调试状态：只验证不提交表单
		rules:{
			mobile:{
				required:true,
				isMobile:true
			},
			password:{
				required:true,
				minlength:6,
				maxlength:20
			},
			password2:{
				required:true
			},
			code:{
				required:true
			}
		},
		messages:{
			mobile:{
				required:"请输入手机号",
				isMobile:"请输入正确的手机号码"
			},
			password:{
				required:"请输入您的新密码",
				minlength:'密码由6-20位字符，区分大小写',
				maxlength:'密码由6-20位字符，区分大小写'
			},
			password2:{
				required:"请再次输入密码",
				equalTo: "#password"
			},
			code:{
				required:"请输入验证码"
			}
		},
		errorPlacement: function(error, element) {
			element.attr('placeholder',error.text())
		},
		errorElement: "span",
		submitHandler: function(form){
			console.log("验证通过");
			//线上交互
			var arr = $("#provingform").serializeArray();
			$.post('#', arr, function (result) {
				if (result.status == 0) {
					layer.msg(result.msg);
				} else if(result.status == 1){
					window.location.href = 'login.html';
				}
			}, 'json');
		}
	});
	$('.pass-form .btn-proving').click(function(e){
		e.preventDefault(), e.stopPropagation();
		$("#provingform").submit();
	});
	// 字母验证码
	$(".btn-code").click(function () {
		mCode.character.init()
	});
	// 手机验证码
	$(".btn-mcode").click(function () {
		if (!$(this).hasClass('btn-dashed')) {
			var mobile = $("#mobile").val()
			if(!mCode.isPhone(mobile)){
				$("#mobile").addClass("error").attr('placeholder','请输入正确的手机号码')
			}else{
				$(this).addClass('btn-dashed');
				// mCode.setTime();
				/*$.post('#', {mobile: mobile,}, function (result) {
					if (result.status == 0) {
						layer.msg(result.msg);
						$(".btn-mcode").addClass('btn-dashed');
					} else if(result.status == 1){
						mCode.setTime();
					}
				}, 'json');*/
			}
		}
	});
	$("#mobile").keydown(function () {
		$("#mobile").removeClass("error")
	})
	// 用户协议
	$(".agreement .checkbox").click(function () {
		$(this).closest('.agreement').removeClass('error')
		if($(this).hasClass('checked')){
			$(this).removeClass('checked')
			$("input[name=agree]").removeAttr('checked')
		}else{
			$(this).addClass('checked')
			$("input[name=agree]").attr('checked','checked')
		}
	})
});