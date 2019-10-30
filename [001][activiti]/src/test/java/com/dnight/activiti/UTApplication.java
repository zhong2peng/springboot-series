package com.dnight.activiti;

import com.dnight.activiti.util.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;


/**
 * @author ZHONGPENG769
 * @date 2019/10/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UTApplication {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void contextLoads(){
        securityUtil.logInAs("salaboy");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        processDefinitionPage.getContent().forEach(System.out::println);
    }


    /**启动流程实例分配任务给个人*/
    @Test
    public void start() {

        String userKey="PTM";//脑补一下这个是从前台传过来的数据
        String processDefinitionKey ="myProcess_2";//每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的
        HashMap<String, Object> variables=new HashMap<>();
        variables.put("userKey", userKey);//userKey在上文的流程变量中指定了

        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey,variables);

        System.out.println("流程实例ID:"+instance.getId());
        System.out.println("流程定义ID:"+instance.getProcessDefinitionId());
    }

    /**查询流程实例*/
    @Test
    public void searchProcessInstance(){
        String processDefinitionKey = "oneTaskProcess";
        String processDefinitionId = "b51ddbae-f9f4-11e9-bd26-82869256f240";
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
//                .processDefinitionKey(processDefinitionKey)
                .processInstanceId(processDefinitionId)
                .singleResult();
        System.out.println("流程实例ID:"+pi.getId());
        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());
        //验证是否启动成功
        //通过查询正在运行的流程实例来判断

//        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
//        //根据流程实例ID来查询
//        List<ProcessInstance> runningList = processInstanceQuery.processInstanceId(processDefinitionId).list();
//        System.out.println("根据流程ID查询条数:{}"+runningList.size());
    }

    /**查询当前人的个人任务*/
    @Test
    public void findTask(){
        String assignee = "PTM";
        List<Task> list = taskService.createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询
                .list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
            }
        }
    }
}
