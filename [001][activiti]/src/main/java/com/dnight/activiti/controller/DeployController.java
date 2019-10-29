package com.dnight.activiti.controller;

import com.dnight.activiti.common.BizResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author ZHONGPENG769
 * @date 2019/10/29
 */
@RestController
@Api(tags="部署流程、删除流程")
public class DeployController {

    @Autowired
    private RepositoryService repositoryService;

    @PostMapping("deploy")
    @ApiOperation(value = "根据bpmnName部署流程",notes = "根据bpmnName部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bpmnName",value = "设计的流程图名称",dataType = "String",paramType = "query",example = "myProcess")
    })
    public BizResponseEntity deploy(@RequestParam("bpmnName") String bpmnName){

        //创建一个部署对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("请假流程");
        Deployment deployment = deploymentBuilder
                .addClasspathResource("processes/"+ bpmnName +".bpmn")
                .addClasspathResource("processes/"+ bpmnName +".png")
                .deploy();

        Map<String, String> result = new HashMap<>(2);
        if (deployment != null) {

            result.put("deployID", deployment.getId());
            result.put("deployName", deployment.getName());
        }
        return BizResponseEntity.ok(result);
    }

    @PostMapping(path = "deployZIP")
    @ApiOperation(value = "根据ZIP压缩包部署流程",notes = "根据ZIP压缩包部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zipName",value = "设计的流程图和图片的压缩包名称",dataType = "String",paramType = "query",example = "myProcess")
    })
    public BizResponseEntity deployZIP(@RequestParam("zipName") String zipName) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("processes/leaveProcess.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程2")
                //指定zip格式的文件完成部署
                .addZipInputStream(zipInputStream)
                .deploy();//完成部署
        zipInputStream.close();
        Map<String, String> result = new HashMap<>(2);
        if (deployment != null) {
            result.put("deployID", deployment.getId());
            result.put("deployName", deployment.getName());
        }
        return BizResponseEntity.ok(result);
    }

    @PostMapping(path = "deleteProcess")
    @ApiOperation(value = "根据部署ID删除流程",notes = "根据部署ID删除流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId",value = "部署ID",dataType = "String",paramType = "query",example = "")
    })
    public BizResponseEntity deleteProcess(@RequestParam("deploymentId") String deploymentId){
        /**不带级联的删除：只能删除没有启动的流程，如果流程启动，就会抛出异常*/
        repositoryService.deleteDeployment(deploymentId);

        /**级联删除：不管流程是否启动，都能可以删除（emmm大概是一锅端）*/
//        repositoryService.deleteDeployment(deploymentId, true);
        return  BizResponseEntity.ok();
    }
}
