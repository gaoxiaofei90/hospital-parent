package com.llw.hospital.api.controller;

import com.llw.hospital.api.annotation.LogIgnore;
import com.llw.hospital.api.aspect.AccessLog;
import com.llw.hospital.api.aspect.LogAspect;
import com.llw.hospital.api.util.CircleQueue;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.log.LogQueryRequest;
import com.llw.hospital.common.util.StringUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wendellpeng
 * @Title: ApiController
 * @ProjectName gov-parent
 * @Description: 测试接口类
 * @date 2018/8/27 20:40
 */
@RestController
@RequestMapping("/api/log")
@Api(tags="调用日志查询")
public class LogController {

    @PostMapping("/list")
    @ApiOperation(value="查询接口调用日志",notes="调用日志")
    @ApiResponses({@ApiResponse(code = 1001,message = "参数不正确")})
    @LogIgnore
    public @ApiParam
    ResponseParam<List<AccessLog>> preDeduction(@RequestBody @ApiParam final LogQueryRequest request){
        CircleQueue<AccessLog> queue = LogAspect.queue;

        List<AccessLog> logs = queue.getQueueReverse(new CircleQueue.Filter() {
            int pageNo = request.getPageNo();
            int pageSize = request.getPageSize();
            int startSize = (pageNo-1)*pageSize + 1;
            int endSize = startSize + pageSize;

            @Override
            public boolean doFilter(Object o) {
                AccessLog accessLog = (AccessLog) o;

                //超过条数不再返回数据了
                if(startSize >= endSize){
                    return false;
                }

                //按功能号查询
                if(!StringUtils.isEmpty(request.getFn()) && !accessLog.getFn().contains(request.getFn())){
                    return false;
                }

                //开始时间
                if(null != request.getStartTime() && accessLog.getRequestTime().before(request.getStartTime())){
                    return false;
                }

                //结束时间
                if(null != request.getEndTime() && accessLog.getResponseTime().after(request.getEndTime()))
                {
                    return false;
                }

                startSize++;
                return true;
            }
        });

        return ResponseParam.ok().setData(logs);
    }

}
