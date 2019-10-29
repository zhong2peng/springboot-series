package com.dnight.activiti.controller;

import com.dnight.activiti.common.BizResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHONGPENG769
 * @date 2019/10/29
 */
@RestController
@Api(tags = "启动流程实例")
public class StartController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping(path = "start")
    @ApiOperation(value = "根据流程key启动流程",notes = "每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey",value = "流程key",dataType = "String",paramType = "query",example = ""),
            @ApiImplicitParam(name = "user",value = "启动流程的用户",dataType = "String",paramType = "query",example = "")
    })
    public BizResponseEntity start(@RequestParam("user") String userKey,
                                   @RequestParam("processKey") String processKey) {
        Map<String, Object> variables=new HashMap<>(1);
        variables.put("userKey", userKey);

        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey(processKey, variables);

        Map<String, String> result = new HashMap<>(2);
        if (instance != null) {
            // 流程实例ID
            result.put("processID", instance.getId());
            // 流程定义ID
            result.put("processDefinitionKey", instance.getProcessDefinitionId());
        }
        return BizResponseEntity.ok(result);
    }


    @PostMapping(path = "searchByKey")
    @ApiOperation(value = "根据流程key查询流程实例",notes = "查询流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey",value = "流程key",dataType = "String",paramType = "query",example = ""),
    })
    public BizResponseEntity searchProcessInstance(@RequestParam("processKey") String processDefinitionKey){
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> runningList = processInstanceQuery.processDefinitionKey(processDefinitionKey).list();

        int size = runningList.size();
        List<Map<String, String>> resultList = new ArrayList<>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ProcessInstance pi = runningList.get(i);
                Map<String, String> resultMap = new HashMap<>(2);
                // 流程实例ID
                resultMap.put("processID", pi.getId());
                // 流程定义ID
                resultMap.put("processDefinitionKey", pi.getProcessDefinitionId());
                resultList.add(resultMap);
            }
        }
        return BizResponseEntity.ok(resultList);
    }


    @PostMapping(path = "searchByID")
    @ApiOperation(value = "根据流程key查询流程实例",notes = "查询流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processID",value = "流程实例ID",dataType = "String",paramType = "query",example = ""),
    })
    public BizResponseEntity searchByID(@RequestParam("processID") String processDefinitionID){
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processDefinitionID)
                .singleResult();

        Map<String, String> resultMap = new HashMap<>(2);
        if (pi != null) {
            // 流程实例ID
            resultMap.put("processID", pi.getId());
            // 流程定义ID
            resultMap.put("processDefinitionKey", pi.getProcessDefinitionId());
        }
        return BizResponseEntity.ok(resultMap);
    }

    @PostMapping(path = "deleteProcessInstanceByKey")
    @ApiOperation(value = "根据流程实例key删除流程实例",notes = "根据流程实例key删除流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey",value = "流程实例Key",dataType = "String",paramType = "query",example = ""),
    })
    public BizResponseEntity deleteProcessInstanceByKey(@RequestParam("processKey") String processDefinitionKey){
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> runningList = processInstanceQuery.processDefinitionKey(processDefinitionKey).list();
        int size = runningList.size();
        List<Map<String, String>> resultList = new ArrayList<>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ProcessInstance pi = runningList.get(i);
                runtimeService.deleteProcessInstance(pi.getId(),"删除");
            }
        }
        return  BizResponseEntity.ok(resultList);
    }

    @PostMapping(path = "deleteProcessInstanceByID")
    @ApiOperation(value = "根据流程实例ID删除流程实例",notes = "根据流程实例ID删除流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processID",value = "流程实例ID",dataType = "String",paramType = "query",example = ""),
    })
    public BizResponseEntity deleteProcessInstanceByID(@RequestParam("processID") String processDefinitionID){
        runtimeService.deleteProcessInstance(processDefinitionID,"删除" + processDefinitionID);
        return  BizResponseEntity.ok();
    }
}
