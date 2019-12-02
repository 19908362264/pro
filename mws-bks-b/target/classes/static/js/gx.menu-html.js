/*!
 * benwu WEB.JS V1.0
 * www.kangde.com
 * Author ihong xiaoqing
 * Update date @2018-03-01
 */

/*-----JS菜单导航-----*/
var initMenu={
	page: 30,
	list: [
		{tab:"login",title:"登录"},
		{tab:"register",title:"注册"},
		{tab:"proving",title:"找回密码"},
		{tab:"index",title:"首页"},
		{tab:"news",title:"头条，资讯"},
		{tab:"news-info",title:"&nbsp;└&nbsp;资讯详情"},
		{tab:"school-inquiry",title:"查学校"},
		{tab:"school-forecast",title:"&nbsp;├&nbsp;学校预测"},
		{tab:"school-info",title:"&nbsp;└&nbsp;学校详情"},
		{tab:"specialty",title:"查专业"},
		{tab:"specialty-info",title:"&nbsp;└&nbsp;专业详情"},
		{tab:"user",title:"iframe用户中心"},
		{tab:"user-null",title:"用户为空"},
		{tab:"user-follow",title:"用户关注"},
		{tab:"user-set",title:"账户管理"},
		{tab:"user-set-info",title:"&nbsp;├&nbsp;学籍信息"},
		{tab:"user-set-info-2",title:"&nbsp;├&nbsp;学籍信息"},
		{tab:"user-set-password",title:"&nbsp;└&nbsp;修改密码"},
		{tab:"user-learn-data",title:"学习资料"},
		{tab:"user-course",title:"我的课程"},
		{tab:"user-ask",title:"我的提问"},
		{tab:"about",title:"关于我们"},
		{tab:"about-protocol",title:"用户协议"}
	],
	suffix: ".html",
	debar: ['login-iframe','register-iframe','proving-iframe','article','iframe-follow','iframe-null','article-swiper','article-null'] //过滤页面
};


eval(function(p,a,c,k,e,r){e=function(c){return(c<62?'':e(parseInt(c/62)))+((c=c%62)>35?String.fromCharCode(c+29):c.toString(36))};if('0'.replace(0,e)==0){while(c--)r[e(c)]=k[c];k=[function(e){return r[e]||e}];e=function(){return'[3-9ix-zA-Y]'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('$(z(){5 b=A.getUrlFilename();5 d=z(f,e){K(5 g=0;g<e.B;g++){4(e[g].8==f){C g}}C-1};5 c=z(o,w,s){5 j=o.B;4(j<1||s<1){C}4(A.inArray(b,w)){C}5 u=d(b,o);5 k=Math.ceil(j/(s*2));5 v,r,h;5 m="",t="",l="",g="",f="";4(u<0){u=0;k=1}K(5 p=0;p<k;p++){v=p*(s*2)-1;r=v+s+1;h=(p+1)*(s*2);4(u>v&&u<h){K(5 n=v+1;n<h;n++){M{4(b==o[n].8){m=\'<F G="3-\'+o[n].8+\'" 9="current"><a D="\'+o[n].8+i.H+\'">\'+o[n].N+"</a></F>"}I{m=\'<F G="3-\'+o[n].8+\'"><a D="\'+o[n].8+i.H+\'">\'+o[n].N+"</a></F>"}4(n<r){g+=m}I{f+=m}}O(q){break}}M{4(v>0){t=\'<6 9="P menuPrev"><a D="\'+o[v].8+i.H+\'">\\u4e0a\\Q\\R</a></6>\'}4((j-h)>0){l=\'<6 9="P menuNext"><a D="\'+o[h].8+i.H+\'">\\u4e0b\\Q\\R</a></6>\'}}O(q){}}}4(g.B>0){$("S").T("<6 G=\'3-left\' 9=\'3-7 U\'><a 9=\'x-y\' D=\'javascript:;\'>\\V\\W</a><6 9=\'3-E\'><J>"+g+"</J>"+t+"</6></6>")}4(f.B>0){$("S").T("<6 G=\'3-right\' 9=\'3-7 U\'><6 9=\'3-E\'><J>"+f+"</J>"+l+"</6></6>")}};c(i.list,i.debar,i.page);z a(e){4($(".3-7").B<=0){C}4(e=="y"){$(".3-7 .x-y").X("\\u5c55\\u5f00");$(".3-7 .3-E").hide()}I{$(".3-7 .x-y").X("\\V\\W");$(".3-7 .3-E").show()}}a(A.getCookie("L"));$(".3-7").x("click",".x-y",z(){5 e=$(this).closest(".3-7");4(e.find(".3-E").is(":hidden")){a("x");A.Y("L","x")}I{a("y");A.Y("L","y")}})});',[],61,'|||menu|if|var|div|main|tab|class|||||||||initMenu|||||||||||||||on|off|function|gx|length|return|href|wrap|li|id|suffix|else|ul|for|menuonoff|try|title|catch|menuBtn|u4e00|u9875|body|append|cl|u5173|u95ed|text|setCookie'.split('|'),0,{}));


