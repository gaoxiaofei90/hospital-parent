layui.config({
    base: '../../static/layui/' //静态资源所在路径
}).extend({
    index: 'modules/index' //主入口模块
}).use(['index','form'], function() {
    var $ = layui.$,form = layui.form;
    /*
    layui.util.ajax({
		url: "./../../medical/consoleStatistics/list.jspx",
		success: function(res) {
			console.log(res.respData);
			//住院人数+抽样人数
			var samplingReportMap = res.respData.samplingReportMap;
			var samplingReportx = samplingReportMap.samplingReportx;
		    var srCheckAmount = samplingReportMap.srCheckAmount;
		    var srNotFinishAmount = samplingReportMap.srNotFinishAmount;
		    var srHospitalAmount = samplingReportMap.srHospitalAmount;
		    var srPersonAmount = samplingReportMap.srPersonAmount;
		    var srFinishAmount = samplingReportMap.srFinishAmount;
		    var srHospitalDays = samplingReportMap.srHospitalDays;
		    
			var myChart0 = echarts.init(document.getElementById('hl-f1'),'customed');
		    var option0 = {
		    	    tooltip : {
		    	        trigger: 'axis',
		    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		    	        }
		    	    },
		    	    legend: {
		    	        data:['住院人数','抽查人数','住院天数','抽查次数','已完成','未完成']
		    	    },
		    	    grid: {
		    	        left: '3%',
		    	        right: '4%',
		    	        bottom: '3%',
		    	        containLabel: true
		    	    },
		    	    xAxis : [
		    	        {
		    	            type : 'category',
		    	            data : samplingReportx
		    	        }
		    	    ],
		    	    yAxis : [
		    	        {
		    	            type : 'value'
		    	        }
		    	    ],
		    	    series : [
		    	        {
		    	            name:'住院人数',
		    	            type:'bar',
		    	            barWidth:20,
		    	            stack: 'type1',
		    	            data:srHospitalAmount
		    	        },        
		    	        {
		    	            name:'抽查人数',
		    	            type:'bar',
		    	            stack: 'type1',
		    	            data:srPersonAmount
		    	        },
		    	    
		    	        
		    	        {
		    	            name:'住院天数',
		    	            type:'bar',
		    	            barWidth:25,
		    	            stack: 'type2',
		    	            data:srHospitalDays
		    	        },
		    	        {
		    	            name:'抽查次数',
		    	            type:'bar',
		    	            stack: 'type2',
		    	            data:srCheckAmount
		    	        },
		    	   

		    	        {
		    	            name:'已完成',
		    	            type:'bar',
		    	            barWidth:20,
		    	            stack: 'type3',
		    	            data:srFinishAmount
		    	        },
		    	        {
		    	            name:'未完成',
		    	            type:'bar',
		    	            stack: 'type3',
		    	            data:srNotFinishAmount
		    	        } 
		    	        
		    	    ]
		    	};
		    myChart0.hideLoading();
		    myChart0.setOption(option0);
		    
			//住院人数+抽样人数
			var inhospitalSamplingMap = res.respData.inhospitalSamplingMap;
			var daysx = inhospitalSamplingMap.daysx;
		    var hsHospitalAmount = inhospitalSamplingMap.hsHospitalAmount;
		    var hsCheckAmount = inhospitalSamplingMap.hsCheckAmount;
		    var myChart2 = echarts.init(document.getElementById('hl-tt'),'customed');
		    var option2;
		    option2 = {
		    	    tooltip : {
		    	        trigger: 'axis'
		    	    },
		    	    legend: {
		    	        data:['住院人数','抽查人数']
		    	    },
		    	    toolbox: {
		    	        show : false,
		    	        feature : {
		    	            mark : {show: true},
		    	            dataZoom : {show: true},
		    	            dataView : {show: true},
		    	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		    	            restore : {show: true},
		    	            saveAsImage : {show: true}
		    	        }
		    	    },
		    	    calculable : true,  
		    	    xAxis : [
		    	        {
		    	            type : 'category',
		    	            boundaryGap : false,
		    	            data : daysx
		    	        }
		    	    ],
		    	    yAxis : [
		    	        {
		    	            type : 'value'
		    	        }
		    	    ],
		    	    series : [
		    	        {
		    	            name:'住院人数',
		    	            type:'line',
		    	            data:hsHospitalAmount
		    	        },
		    	        {
		    	            name:'抽查人数',
		    	            type:'line',
		    	            data:hsCheckAmount
		    	        }
		    	    ]
		    	};
		    myChart2.hideLoading();
		    myChart2.setOption(option2);
		    
			//抽样完成情况
			var samplingFinishMap = res.respData.samplingFinishMap;
			var samplingFinishx = samplingFinishMap.samplingFinishx;
		    var finishCheckAmount = samplingFinishMap.finishCheckAmount;
		    var finishNotFinishAmount = samplingFinishMap.finishNotFinishAmount;
		    var tempNum = finishCheckAmount;
		    if(finishCheckAmount){
		    	for (var i=0; i<finishCheckAmount.length; i++){
		    		tempNum[i] = finishCheckAmount[i] - finishNotFinishAmount[i];
		    	}
		    }
		    var myChart1 = echarts.init(document.getElementById('hl-dj'),'customed');
			var option1 = {
		    	    tooltip : {
		    	        trigger: 'axis',
		    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		    	        },
		    	        formatter: function (params){
		    	            return params[0].name + '<br/>'
		    	                   + params[1].seriesName + ' : ' + params[1].value + '<br/>'
		    	                   + params[0].seriesName + ' : ' + (params[1].value + params[0].value);
		    	        }
		    	    },
		    	    legend: {
		    	        selectedMode:false,
		    	        data:['抽查人次', '未完成人次']
		    	    },
		    	    toolbox: {
		    	        show : false,
		    	        feature : {
		    	            mark : {show: true},
		    	            dataView : {show: true, readOnly: false},
		    	            restore : {show: true},
		    	            saveAsImage : {show: true}
		    	        }
		    	    },
		    	    calculable : true,
		    	    xAxis : [
		    	        {
		    	            type : 'category',
		    	            data : samplingFinishx
		    	        }
		    	    ],
		    	    yAxis : [
		    	        {
		    	            type : 'value',
		    	            boundaryGap: [0, 0.1]
		    	        }
		    	    ],
		    	    series : [
		    	        {
		    	            name:'抽查人次',
		    	            type:'bar',
		    	            stack: 'sum',
		    	            barCategoryGap: '50%',
		    	            itemStyle: {
		    	                normal: {
		    	                    color: 'tomato',
		    	                    barBorderColor: 'tomato',
		    	                    barBorderWidth: 6,
		    	                    barBorderRadius:0,
		    	                    label : {
		    	                        show: true, position: 'insideTop'
		    	                    }
		    	                }
		    	            },
		    	            data:tempNum
		    	        },
		    	        {
		    	            name:'未完成人次',
		    	            type:'bar',
		    	            stack: 'sum',
		    	            itemStyle: {
		    	                normal: {
		    	                    color: '#ffff00',
		    	                    barBorderColor: 'tomato',
		    	                    barBorderWidth: 6,
		    	                    barBorderRadius:0,
		    	                    label : {
		    	                        show: true, 
		    	                        position: 'top',
		    	                        formatter: function (params) {
		    	                            for (var i = 0, l = option1.xAxis[0].data.length; i < l; i++) {
		    	                                if (option1.xAxis[0].data[i] == params.name) {
		    	                                    return option1.series[0].data[i] + params.value;
		    	                                }
		    	                            }
		    	                        },
		    	                        textStyle: {
		    	                            color: 'tomato'
		    	                        }
		    	                    }
		    	                }
		    	            },
		    	            data: finishNotFinishAmount
		    	        }
		    	    ]
		    	};
			myChart1.hideLoading();
		    myChart1.setOption(option1);
		    
		    
		    //今日抽查情况
		    var planReportMap = res.respData.planReportMap;
			var finishRate = planReportMap.finishRate;
		    var myChart4 = echarts.init(document.getElementById('hl-r1'),'customed');
		    var option = {
		    	    tooltip : {
		    	        formatter: "{a} <br/>{b} : {c}%"
		    	    },
		    	    series : [
		    	        {
		    	            name:'',
		    	            type:'gauge',
		    	            splitNumber: 10,       // 分割段数，默认为5
		    	            axisLine: {            // 坐标轴线
		    	                lineStyle: {       // 属性lineStyle控制线条样式
		    	                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']], 
		    	                    width: 8
		    	                }
		    	            },
		    	            axisTick: {            // 坐标轴小标记
		    	                splitNumber: 10,   // 每份split细分多少段
		    	                length :12,        // 属性length控制线长
		    	                lineStyle: {       // 属性lineStyle控制线条样式
		    	                    color: 'auto'
		    	                }
		    	            },
		    	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
		    	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		    	                    color: 'auto'
		    	                }
		    	            },
		    	            splitLine: {           // 分隔线
		    	                show: true,        // 默认显示，属性show控制显示与否
		    	                length :30,         // 属性length控制线长
		    	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		    	                    color: 'auto'
		    	                }
		    	            },
		    	            pointer : {
		    	                width : 5
		    	            },
		    	            title : {
		    	                show : true,
		    	                offsetCenter: [0, '-40%'],       // x, y，单位px
		    	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		    	                    fontWeight: 'bolder'
		    	                }
		    	            },
		    	            detail : {
		    	                formatter:'{value}%',
		    	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		    	                    color: 'auto',
		    	                    fontWeight: 'bolder',
		    	                    fontSize : '20'
		    	                }
		    	            },
		    	            data:[{value: finishRate, name: ''}]
		    	        }
		    	    ]
		    	};

//		    	clearInterval(timeTicket);
//		    	var timeTicket = setInterval(function (){
//		    	    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
//		    	    myChart4.setOption(option,true);
//		    	},2000)
		    	                    
		    myChart4.hideLoading();
		    myChart4.setOption(option);
		}
	});
    */
})