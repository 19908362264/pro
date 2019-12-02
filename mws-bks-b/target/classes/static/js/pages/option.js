(function(w){
	var a = w.app = w.app || {};
	//创建验证码
	w.eData={
		id(e){
			return echarts.init(document.getElementById(e));
		},
		hSeries(t,x,y,c) {
			var data=[]
			for(var i in y){
				data.push({
					name: x[i],
					type: t,
					stack: 'heat',
					itemStyle: {
						normal: {color: c[i]}
					},
					data: y[i]
				})
			}
			return data
		},
		rSeries(x,y,c) {
			var data=[]
			for(var i in x){
				data.push({
					name: x[i],
					itemStyle: {
						normal: {color: c[i]}
					},
					value: y[i]
				})
			}
			return data
		},
		radarIndicator (x,max) {
			var data=[]
			for(var i in x){
				if(x[i] == '总分'){
					data.push({ name: x[i], max: max, color: '#fff000'})
				}else{
					data.push({ name: x[i], max: max})
				}
			}
			return data
		},
		legend (data){
			return  {
				data:data,
				orient: 'horizontal', // 'vertical'
				x: 'center', // 'center' | 'left' | {number},
				y: 'bottom', // 'center' | 'bottom' | {number}
			}
		},
		YAData (y,u){
			var data=[]
			if(u){
				for(var i in y){
					if(y[i] == u){
						data.push({
							value: y[i],
							// 突出周一
							textStyle: {
								color: 'red'
							}})

					}else{
						data.push(y[i])
					}
				}
			}else{
				data = y
			}
			return data
		},
		ranking(oL,oX,oMax,oS){
			var oC=['#3fa7dc','#f6ad2d'],
					oP = {
						tooltip: {},
						legend: eData.legend(oL),
						radar: {
							// shape: 'circle',
							name: {
								textStyle: {
									color: '#fff',
									backgroundColor: '#3fa7dc',
									borderRadius: 3,
									padding: [3, 5]
								}
							},
							indicator: eData.radarIndicator(oX,oMax)
						},
						series:[{
							type: 'radar',
							data : eData.rSeries(oL,oS,oC)
						}]
					}
			return oP
		},
		subject(oL,oX,oS){
			var oP = {
				tooltip : {
					trigger: 'axis'
				},
				legend:eData.legend(oL),
				toolbox: {
					show: true,
					feature: {
						dataZoom: {
							yAxisIndex: 'none'
						},
						dataView: {readOnly: false},
						magicType: {type: ['line', 'bar']},
						restore: {},
						saveAsImage: {}
					}
				},
				xAxis:  {
					type: 'category',
					boundaryGap: false,
					data: oX
				},
				yAxis: {
					type: 'value',
				},
				series: [
					{
						name:oL[0],
						type:'line',
						data:oS[0],
						itemStyle:{
							color: '#26ddcd'
						},
						markLine: {
							data: [
								{type: 'average', name: '平均值'}

							]
						}
					},
					{
						name:oL[1],
						type:'line',
						itemStyle:{
							color: '#f6ad2d'
						},
						data:oS[1]
					}
				]
			}
			return oP
		},
		heat(oX,oY,uY,oS){
			var oC=['#3fa7dc','#ffb716','#ff0000'],
				oP = {
					tooltip : {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					toolbox: {
						show: true,
						feature: {
							dataZoom: {
								yAxisIndex: 'none'
							},
							dataView: {readOnly: false},
							magicType: {type: ['line', 'bar']},
							restore: {},
							saveAsImage: {}
						}
					},
					legend: eData.legend(oX),
					grid: {
						left: '3%',
						right: '4%',
						bottom: '10%',
						containLabel: true
					},
					xAxis:  {
						type: 'value'
					},
					yAxis: {
						type: 'category',
						data:eData.YAData(oY,uY),

					},
					// series:eData.hSeries('bar',oX,oS,oC)
					series:[{type: 'bar',
							itemStyle: { normal: { color: '#3fa7dc' } },
							silent: true,
							stack: 'heat',
							data: oS[0]
						},
						{
							type: 'bar',
							itemStyle: { normal: { color: '#ff0000' } },
							stack: 'heat',
							// z: 9,
							data:oS[1]
						},
						{
							type: 'line',
							name: '当前',
							symbol:'rect',
							symbolSize:20,
							itemStyle:{
								color:'#ffb716'
							},
							data: oS[2],
							z: 10
						}
					]
				}
				console.log(oP)
			return oP
		},
		promote(oL,oX,oS){
			var oP = {
				tooltip: {
					trigger: 'axis'
				},
				legend: {show:false},
				toolbox: {
					show: true,
					feature: {
						dataZoom: {
							yAxisIndex: 'none'
						},
						dataView: {readOnly: false},
						magicType: {type: ['line', 'bar']},
						restore: {},
						saveAsImage: {}
					}
				},
				xAxis:  {
					type: 'category',
					boundaryGap: true,
					data: oX,
					axisLabel: {
						rotate: -50, interval: 0
					}
				},
				yAxis: {
					type: 'value',
				},
				series:[{name:oL[0],
					type:'line',
					data:oS,
					itemStyle:{
						color: '#26ddcd',
						normal: {
							borderWidth: 3,
							borderColor: '#26decd',
							color: '#3fa7dc'
						}
					},
					label: {
						normal: {
							show: true,
							position: 'top'
						}
					},
					markLine: {
						data: [
							{type: 'average', name: '平均值'}

						]
					},
					lineStyle: {
						normal: {
							color: 'green',
							type: 'dashed'
						}
					},
					smooth: true
				}]
			}
			return oP
		}
	}
})(window);

$(function () {



});
