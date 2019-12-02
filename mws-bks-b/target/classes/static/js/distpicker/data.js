
(function(w){
	var a = w.app = w.app || {};
	//学校地址
	w.schoolArea=[
		{"py": "bj","name": "北京","code": "11"},
		{"py": "tj","name": "天津","code": "12"},
		{"py": "hb","name": "河北","code": "13"},
		{"py": "sx","name": "山西","code": "14"},
		{"py": "nmg","name": "内蒙古","code": "15"},
		{"py": "ln","name": "辽宁","code": "21"},
		{"py": "jl","name": "吉林","code": "22"},
		{"py": "hlj","name": "黑龙江","code": "23"},
		{"py": "sh","name": "上海","code": "31"},
		{"py": "js","name": "江苏","code": "32"},
		{"py": "zj","name": "浙江","code": "33"},
		{"py": "ah","name": "安徽","code": "34"},
		{"py": "fj","name": "福建","code": "35"},
		{"py": "jx","name": "江西","code": "36"},
		{"py": "sd","name": "山东","code": "37"},
		{"py": "hb","name": "河南","code": "41"},
		{"py": "hb","name": "湖北","code": "42"},
		{"py": "hn","name": "湖南","code": "43"},
		{"py": "gd","name": "广东","code": "44"},
		{"py": "gx","name": "广西","code": "45"},
		{"py": "hn","name": "海南","code": "46"},
		{"py": "cq","name": "重庆","code": "50"},
		{"py": "sc","name": "四川","code": "51"},
		{"py": "gz","name": "贵州","code": "52"},
		{"py": "yn","name": "云南","code": "53"},
		{"py": "xz","name": "西藏","code": "54"},
		{"py": "sx","name": "陕西","code": "61"},
		{"py": "gs","name": "甘肃","code": "62"},
		{"py": "qh","name": "青海","code": "63"},
		{"py": "nx","name": "宁夏","code": "64"},
		{"py": "xj","name": "新疆","code": "65"},
		{"py": "tw","name": "台湾","code": "71"},
		{"py": "xg","name": "香港","code": "81"},
		{"py": "am","name": "澳门","code": "82"}
	];
	//自动匹配学校地址
	w.autoMatch={
		box:'autoMatchBox',
		list:'AMList',
		input:'AMInput',
		init(){
			var box=$("."+this.box),
				input=$("."+this.input);
			if(!box)return false;
			var dlist=[],html='',keyDownList='';
			input.keyup(function () {
				dlist=[],html='',keyDownList='';
				console.log($(this).val());
				if($(this).val()!==''){
					for (let i in schoolArea){
						var spy=schoolArea[i].py,sname=schoolArea[i].name;
						if(spy.indexOf($(this).val())>-1 || sname.indexOf($(this).val())>-1){
							dlist.push(schoolArea[i]);
						}
					}
					if(dlist.length>0){
						for(let i in dlist){
							html+="<li data-code="+dlist[i].code+">"+dlist[i].name+"</li>";
						}
						keyDownList="<div class='AMList'><ul>"+html+"</ul></div>"
						box.append(keyDownList);
					}else{
						$("."+autoMatch.list).remove()
					}
				}else{
					$("."+autoMatch.list).remove()
				}
			});
		}
	};
	// 学校item
	w.change_school_list = function(data){
		var school=data,school_list='',fHtml='';
		for(let i in school){
			// 判断关注是否
			if(school.follow){
				fHtml='<button class="btn btn-gray btn-follow " data-id="'+school[i].id+'">关注</button>';
			}else{
				fHtml='<button class="btn btn-blue btn-border btn-follow" data-id="'+school[i].id+'">关注</button>';
			}
			school_list+='<li class="school-item"><div class="pic"><a href="'+school[i].url+'"><img src="'+school[i].img+'" alt="" /></a></div>'+
					'<div class="detail"><div class="tit"><a href="'+school[i].url+'">'+school[i].name+'</a></div>'+
					'<div class="row"><span class="space">院校类型：<span>'+school[i].type+'</span></span>'+
					'<span class="space">所在省：<span>'+school[i].address+' </span></span>'+
					'<span class="space">录取概率：<span class="orange">'+school[i].fx+'</span></span></div>'+
					'<div class="row"><span class="space">'+school[i].year+'录取分数线</span>'+
					'<span class="space">最低分：<span class="orange">'+school[i].lower+'</span></span>'+
					'<span class="space">平均分：<span class="orange">'+school[i].average+'</span></span></div>'+
					'<div class="row"><span class="space">部分开设专业：<span class="gray">'+school[i].major+'</span></span><a href="'+school[i].type+'">&gt;&gt;</a></div></div>'+
					'<div class="right">'+fHtml+'</div></li>';
		}
		$(".school-list").html(school_list);
		$(".school-wrap .card-hd").find(".num").html(school.length);
		pageClick.pageHtml(page,data.count);
	};
	// 专业item
	w.change_specialty_list = function(data){
		var school=data.majors,school_list='',fHtml='';
		for(let i in school){
			// 判断关注是否
			if(school[i].is_attention){
				fHtml='<button class="btn btn-blue btn-gray btn-follow " data-id="'+school[i].major_category_id+'">关注</button>';
			}else{
				fHtml='<button class="btn btn-blue btn-follow" data-id="'+school[i].major_category_id+'">关注</button>';
			}
			school_list+='<li class="specialty-item"><div class="detail">\n' +
					'<div class="tit"><a href="javascript:;">'+school[i].level1_name+'</a><span class="label"><span>'+school[i].level2_name+'</span><span>'+school[i].level3_name+'</span></span></div>\n' +
					'<div class="row">\n' +
					'<span class="space">开设高校数量：<span>'+school[i].num_school+'所</span></span>\n' +
					'<span class="space">学历层次：<span>'+school[i].major_level+' </span></span>\n' +
					'<span class="space">授予学位：<span>'+school[i].bachelor_degree+'</span></span>\n' +
					'<span class="space">专业代码：<span>'+school[i].major_category_id+'</span></span>\n' +
					'</div><div class="row course">\n' +
					'<span class="space">主要课程：<span>'+school[i].course+'</span></span>\n' +
					'</div></div><div class="right">'+fHtml+'</div></li>';
		}
		$(".specialty-list").html(school_list);
		console.log(data)
		pageClick.pageHtml(data.cur,data.total);
	};
	// 点击翻页进行数据提交
	w.pageClick={
		//翻页class
		pageBox:$(".pagelist"),
		//重置翻页HTML
		pageHtml(cur,count){
			if(count<=1)return false;
			var pageHtml,perv=1,next=count;
			// if(cur>5 && count>9){pageHtml='<a class="home" title="1" href="javascript:;">首页</a>';}
			if(cur>1 && count>9){pageHtml+='<a class="prev" title="上一页" href="javascript:;">上一页</a>';}
			if(count-cur>4 && cur>=6){
				next=cur+4
			}else if(cur<=5 && count>10 ){
				next=9
			}
			if(cur>5 && count-cur>=4){
				perv=cur-4
			}else if(count-cur<9 && count>10 && cur>5){
				perv=count-8
			}
			for(let i=perv; i<next+1; i++){
				if(i===cur){
					pageHtml+='<span class="current active" title="'+i+'">'+i+'</span>'
				}else{
					pageHtml+='<a class="num" title="'+i+'" href="javascript:;">'+i+'</a>'
				}
			}
			if(cur<count && count>9){pageHtml+='<a class="next" title="下一页" href="javascript:;">下一页</a>';}
			// if(count-cur>4 && count>9){pageHtml+='<a class="back" title="'+count+'" href="javascript:;">尾页</a>';}
			this.pageBox.html(pageHtml);
		},
		sub:{
			url:'http://10.10.0.68:8080/api-bks/bks-anon/school/',
			data:'',
			type:'post',
			list:'list'
		},
		//初始化
		init(data){
			//判断是否有翻页
			if(!this.pageBox.length)return false;

			if(data){
				$.extend(this.sub,data)
				this.pageHtml(data.cur,data.count);
			}
			pageClick.request();
		},
		request(data){
			if(data){
				$.extend(this.sub,data)
			}
			console.log(this.sub);
			$.ajax({
				url: this.sub.url,
				type: this.sub.type,
				data: this.sub.data,
				success: function(data) {
					if(pageClick.sub.list == 'specialty'){
						change_specialty_list(data)
					}else{
						change_school_list(data)
					}
				},
				error: function(error) {
					console.log(error);
				}
			});
		}
	};
	// 关注请求
	w.follow = {
		btn:'.btn-follow',
		init () {
			if(!$(this.btn)){return false}
			$(document).on("click",".btn-follow",function () {
				var id=$(this).attr("data-id");
				var thisID=$(this);
				// var thisID=$(".btn-follow[data-id="+id+"]");
				if(!thisID.hasClass("btn-gray")){
					thisID.text('已关注');
					thisID.addClass("btn-gray");
					console.log(id);
					/*$.ajax({
						url: '',
						type: 'post',
						data: {school:id},
						success: function(data) {
							gx.msg('关注成功');
						},
						error: function() {

						}
					});*/
				}else{
					thisID.text('关注');
					thisID.removeClass("btn-gray");
					console.log(id);
					/*$.ajax({
						url: '',
						type: 'post',
						data: {school:id},
						success: function(data) {
							gx.msg('已取消关注');
						},
						error: function() {

						}
					});*/
				}
			});
		}
	}

})(window);
$(function () {
	autoMatch.init();
	follow.init();
	$(document).on("click",".AMList li",function (e) {
		e.preventDefault(), e.stopPropagation();
		//console.log($(this).attr("data-code"));
		var box=$(this).closest("."+autoMatch.box);
		box.find("."+autoMatch.input).val($(this).text());
		box.find("input[name='areaCode']").val($(this).attr("data-code"));
		$("."+autoMatch.list).remove()
	});
	$(document).click(function () {
		$("."+autoMatch.list).remove()
	});
	$(document).on("click",".pagelist a",function () {
		var thisCur=pageClick.pageBox.find(".current").attr("title"),page=0;
		var thisClass=$(this).attr("class"),
			thisNum=$(this).attr("title");
		if(thisClass === "prev"){
			page=parseInt(thisCur)-1;
		}else if(thisClass === "next"){
			page=parseInt(thisCur)+1;
		}else{
			page=parseInt(thisNum);
		}
		$.extend(pageClick.sub.data, {page:page});
		//console.log(pageClick.data);
		//pageClick.pageHtml(page,data.count);
		pageClick.request()
	});
});
