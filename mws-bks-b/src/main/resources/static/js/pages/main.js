
(function(w, d){
	var a = w.app = w.app || {};
	a.call = function($e, $t){
		var dom = $($t), getType = dom.attr('data-gxon');
		if(getType == 'hover') getType = $e.type;
		switch(getType){
			case 'mouseover': //鼠标悬浮
				dom.addClass('hover');
				break;
			case 'mouseout': //鼠标离开
				dom.removeClass('hover');
				break;
			default:
		}
	}
	w.gxui = {
		flag: false,
		init: function(){
			this.ergodic();
			if(!this.flag){
				this.bindEvent().flag = true;
			}
		},
		ergodic: function(){
			var $t = this;
			/*Select下拉框初始化遍历*/
			$('.gxui-select__original').each(function(){
				$t.select.init(this);
			});
			$('.input-select__label').each(function(){
				$t.iSelect.init(this);
			});
			/*Switch开关初始化遍历*/
			$('.gxui-switch__input').each(function(){
				$t.switchs.init(this);
			});
			/*Radio单选框初始化遍历*/
			$('.gxui-radio__original').each(function(){
				$t.radio.init(this);
			});
			/*Checkbox复选框初始化遍历*/
			$('.gxui-checkbox__original').each(function(){
				$t.checkbox.init(this);
			});
		},
		bindEvent: function(){
			var $t = this;

			$(d).on('click', '[data-gxon]:not([data-gxon="hover"])', function(e){
				/*gxon点击事件*/
				$(this).is('a') && e.preventDefault(), e.stopPropagation(), app.call(e, this);
			}).on('mouseover mouseout', '[data-gxon="hover"]', function(e){
				/*gxon鼠标移入移出*/
				app.call(e, this);
			});

			$(d).on('click', '.gxui-select__input', function(e){ //点击显示下拉框列表
				e.stopPropagation(), $t.select.show(e, this);
			}).on('click', '.gxui-select li', function(e){ //选项事件：将内容替换到下拉框中并收起下拉选项
				e.stopPropagation(), $t.select.pick(e, this);
			}).on('click',function(e){ //全局事件点击关闭下拉框
				$t.select.close();
			}).on('click','.gxui-switch__input',function(e){
				/*Switch开关事件*/
				e.stopPropagation(), $t.switchs.check(e, this);
			}).on('click','.gxui-radio__original',function(e){
				/*Radio单选框事件*/
				e.stopPropagation(), $t.radio.check(e, this);
			}).on('click','.gxui-checkbox__original',function(e){
				/*Checkbox复选框事件*/
				e.stopPropagation(), $t.checkbox.check(e, this);
			}).on('focus', '.input-select__original', function(e){ //点击显示下拉框列表
				e.stopPropagation(), $t.iSelect.input(e, this);
			}).on('blur', '.input-select__original', function(e){ //点击显示下拉框列表
				e.stopPropagation(), $t.iSelect.blur(e, this);
			}).on('click', '.input-select__down', function(e){ //点击显示下拉框列表
				e.stopPropagation(), $t.iSelect.show(e, this);
			}).on('click', '.input-select li', function(e){ //选项事件：将内容替换到下拉框中并收起下拉选项
				e.stopPropagation(), $t.iSelect.pick(e, this);
			}).on('click',function(e){ //全局事件点击关闭下拉框
				$t.iSelect.close();
			});

			return this;
		},
		/*Select下拉框事件*/
		select: {
			init: function($t){
				var el = $($t), dom = el.closest('.gxui-select'),
						v = el.attr('value');
				if (isNaN(v)) return false;
				var t = dom.find('li[data-value="'+ v +'"]').text();
				if (!isNaN(t)) dom.find('.gxui-select__label').html(t);
			},
			/*显示下拉框列表事件*/
			show: function($e, $t){
				var el = $($t), dom = el.closest('.gxui-select');
				if(dom.hasClass('disabled')) return;
				if(dom.hasClass('active')){
					dom.removeClass('active');
				}else{
					this.close();
					dom.addClass('active');
				}
			},
			/*关闭选项列表事件*/
			close: function(){
				var dom = $('.gxui-select');
				if(dom.hasClass('active')){
					dom.removeClass('active');
				}
			},
			/*选项事件*/
			pick: function($e, $t){
				var el = $($t), dom = el.closest('.gxui-select'), t = el.text(), v = el.attr('data-value');
				v = isNaN(v) ? t : v;
				dom.find('.gxui-select__label').html(t);
				dom.find('.gxui-select__original').val(v);
				dom.removeClass('active');
			}
		},
		/*Switch开关事件*/
		switchs: {
			init: function($t){
				var el = $($t), dom = el.closest('.gxui-switch');
				if(el.is(':checked')) {
					dom.addClass('is-checked');
					el.prop("checked", true);
				}
				if(el.is(':disabled')) {
					dom.addClass('is-disabled').attr('tabindex','-1');
					el.prop("disabled", true);
				}
			},
			check: function($e, $t){
				var el = $($t), dom = el.closest('.gxui-switch');
				if(el.is(':disabled') || dom.is('.is-disabled')) return false;
				if(!dom.is('.is-checked')){
					dom.addClass('is-checked');
					el.prop("checked", true);
				}else{
					dom.removeClass('is-checked');
					el.prop("checked", false);
				}
			}
		},
		/*Radio单选框事件*/
		radio: {
			init: function($t){
				var el = $($t), dom = el.closest('.gxui-radio'), sib = dom.siblings('.gxui-radio');
				if(el.is(':checked')) {
					var tm = el.attr('name');
					if (!isNaN(tm)) {
						var els = $("input[name='"+tm+"']");
						for (var i = 0; i < els.length; i++) {
							els.eq(i).prop("checked", false);
							els.eq(i).closest('.gxui-radio').removeClass('is-checked');
						}
					} else{
						sib.removeClass('is-checked').find('input').prop("checked", false);
					}
					dom.addClass('is-checked');
					el.prop("checked", true);
				}
				if(el.is(':disabled')) {
					dom.addClass('is-disabled').attr('tabindex','-1');
					el.prop("disabled", true);
				}
			},
			check: function($e, $t){
				var el = $($t), dom = el.closest('.gxui-radio'), sib = dom.siblings('.gxui-radio');
				if(!el.is(':disabled') && el.is(':checked') && !dom.is('.is-checked')){
					dom.addClass('is-checked');
					el.prop("checked", true);
					sib.removeClass('is-checked').find('input').prop("checked", false);
				}
			}
		},
		/*Checkbox复选框事件*/
		checkbox: {
			init: function($t){
				var el = $($t), dom = el.closest('.gxui-checkbox');
				if(el.is(':checked')) {
					dom.addClass('is-checked');
					el.prop("checked", true);
				}
				if(el.is(':disabled')) {
					dom.addClass('is-disabled').attr('tabindex','-1');
					el.prop("disabled", true);
				}
			},
			check: function($e, $t){
				var el = $($t), dom = el.closest('.gxui-checkbox');
				if(el.is(':disabled') || dom.is('.is-disabled')) return false;
				if(!dom.is('.is-checked')){
					dom.addClass('is-checked');
					el.prop("checked", true);
				}else{
					dom.removeClass('is-checked');
					el.prop("checked", false);
				}
			}
		},
		iSelect: {
			init: function($t){
				var el = $($t), dom = el.closest('.input-select'),
					v = el.attr('value');
				if (isNaN(v)) return false;
				var t = dom.find('li[data-value="'+ v +'"]').text();
				if (isNaN(t)){ dom.find('.input-select__original').val(t)};
			},
			/*显示下拉框列表事件*/
			input: function($e, $t){
				var el = $($t), dom = el.closest('.input-select');
				if(dom.hasClass('disabled')) return;
				if(dom.hasClass('active')){
					dom.removeClass('active');
				}else{
					this.close();
					el.val('').keyup(function () {
						if(! dom.hasClass('active')){dom.addClass('active');}
						gxui.iSelect.contrast(el.val(), $t)
					})
				}
			},
			contrast:function(val,$t){
				var el = $($t), dom = el.closest('.input-select');
				dom.find('.input-select__list li').hide()
				dom.find('.input-select__list li').each(function () {
					if($(this).text().indexOf(val) !== -1){ //等于-1表示这个字符串中没有o这个字符
						$(this).show()
					}
				});
			},
			blur: function($e, $t){
				var el = $($t), dom = el.closest('.input-select');
				dom.find('.input-select__list li').each(function () {
					if($(this).attr('data-value') == dom.find('.input-select__label').val()){
						dom.find('.input-select__original').val($(this).text());
					}
				});
			},
			/*显示下拉框列表事件*/
			show: function($e, $t){
				var el = $($t), dom = el.closest('.input-select');
				if(dom.hasClass('disabled')) return;
				if(dom.hasClass('active')){
					dom.removeClass('active');
				}else{
					this.close();
					dom.addClass('active');
				}
			},
			/*关闭选项列表事件*/
			close: function(){
				var dom = $('.input-select');
				if(dom.hasClass('active')){
					dom.removeClass('active');
				}
			},
			/*选项事件*/
			pick: function($e, $t){
				var el = $($t), dom = el.closest('.input-select'), t = el.text(), v = el.attr('data-value');
				v = isNaN(v) ? t : v;
				dom.find('.input-select__label').val(v);
				dom.find('.input-select__original').val(t);
				dom.removeClass('active');
			}
		},

	}

	w.articleHeight ={ // 获取框架文章高度
		href (e) { // 侧栏href地址
			// console.log($(e).find('a').attr('href'))
			let  url = '';
			if($(e).find('a').attr('href') !== '#'){
				url = $(e).find('a').attr('href')
			}else{
				url = 'article-null.html'
			}
			$("#frame_article").attr("src",url)
		},
		self (id,sub){  // 当前页获取子级页高度赋值给自己 必须在服务器环境下执行而且是同一域名
			try{
				var iframe = document.getElementById(id);
				if(iframe.attachEvent){
					iframe.attachEvent("onload", function(){
						iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;
					});
					return;
				}else{
					iframe.onload = function(){
						iframe.height = iframe.contentDocument.body.scrollHeight;
					};
					return;
				}
			}catch(e){
				// throw new Error('setIframeHeight Error');
				console.log('setIframeHeight Error')
			}
		},
		parent (id,sub) {  // 当前页获取子级获取高度传给父级 必须在服务器环境下执行而且是同一域名
			try{
				var parentIframe = parent.document.getElementById(id);
				if(window.attachEvent){
					window.attachEvent("onload", function(){
						parentIframe.height = $(sub).outerHeight();
					});
					return;
				}else{
					parentIframe.height = $(sub).outerHeight();
					return;
				}
			}catch(e){
				// throw new Error('setParentIframeHeight Error');
				console.log('setParentIframeHeight Error')
			}
		}
	}
})(window, document);


$(function(){
	//头部菜单高亮
	var topnav = $('.top-head').attr('data-nav');
	$(".head-nav li").each(function(){
		var id = $(this).attr('data-id');
		if (topnav == id) $(this).addClass('on');
	});
	var personalmenu = $('.personal-menu').attr('data-nav');
	$(".personal-menu li").each(function(){
		var id = $(this).attr('data-id');
		if (personalmenu == id) {
			$(this).addClass('on')
			$(".personal-menu li[data-id="+id+"]").closest('.card').addClass('active')
		}
	});
	$(".personal-menu .card-hd").click(function(){
		var cl=$(this).closest('.card')
		if(cl.hasClass('active')){
			cl.removeClass('active');
		}else{
			cl.addClass('active');
		}
	});
	$(document).on('click', '[data-gxon]:not([data-gxon="hover"])', function(e){
		/*gxon点击事件*/
		$(this).is('a') && e.preventDefault(), e.stopPropagation(), app.call(e, this);
	}).on('mouseover mouseout', '[data-gxon="hover"]', function(e){
		/*gxon鼠标移入移出*/
		app.call(e, this);
	});
	gxui.init();
	/*-----JS菜单导航-----*/
	//
	// var script=document.createElement("script");
	// script.src="js/libs.js";
	// document.getElementsByTagName('head')[0].appendChild(script);
});


