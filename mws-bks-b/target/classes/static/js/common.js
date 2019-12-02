/*!
 * benwu WEB.JS V1.0
 * www.benwunet.com
 * Author ihong xiaoqing
 * Update date @2018-03-01
 */

(function(w, d){
	var g = w.gx = w.gx || {};
	g.call = function($e, $t){
		var dom = $($t), getType = dom.attr('data-gxon'), dt = dom.attr('data-target');
		if(getType == 'hover') getType = $e.type;
		if(getType == 'fold' || getType=='unfold') getType = 'unfold';
		switch(getType){
			case 'mouseover': //鼠标悬浮
				dom.addClass('hover');
				break;
			case 'mouseout': //鼠标离开
				dom.removeClass('hover');
				break;
			case 'click': //点击事件
				if(!gx.isNull(dt) && gx.isFun(dt)) eval('1,' + dt +'($e, $t)');
				break;
			case 'act': //行为事件
				if(!gx.isNull(dt) && gx.isFun(dt)) eval('1,' + dt +'($e, $t)'); //gx.parse(dt);
				break;
			case 'unfold': //展开折叠
				var b, c, fun = dom.attr('data-callback'), 
				cbfun = function(){
					if(!gx.isNull(fun) && gx.isFun(fun)) eval('1,' + fun +'($e, $t)');
				}
				//if (gx.isNull(dt)) dt = '.gxui-ocbox';
				if (gx.isNull(dt) || dt=='this'){
					b=$($t),b.is('.active')?b.removeClass('active'):(b.addClass('active'),cbfun());
				}else{
					b=$(dt), c=dom.closest(dt);
					c.is('.active')?c.removeClass('active'):(b.removeClass('active'),c.addClass('active'),cbfun());
				}
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
				$(this).is('a') && e.preventDefault(), e.stopPropagation(), gx.call(e, this);
			}).on('mouseover mouseout', '[data-gxon="hover"]', function(e){
				/*gxon鼠标移入移出*/
				gx.call(e, this);
			});

			$(d).on('click', '.gxui-select__input', function(e){ //点击显示下拉框列表
				/*Select下拉框事件*/
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
			});

			return this;
		},
		/*Select下拉框事件*/
		select: {
			init: function($t){
				var el = $($t), dom = el.closest('.gxui-select'),
				v = el.attr('value');
				if (gx.isNull(v)) return false;
				var t = dom.find('li[data-value="'+ v +'"]').text();
				if (!gx.isNull(t)) dom.find('.gxui-select__label').html(t);
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
				v = gx.isNull(v) ? t : v;
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
					if (!gx.isNull(tm)) {
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
		}
	}
})(window, document);

$(function(){

	gxui.init();

});

