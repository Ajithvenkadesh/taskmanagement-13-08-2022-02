package com.system.taskAssigner.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import java.util.ArrayList;
import com.system.task.model.Task;
import com.system.taskAssigner.model.TaskAssigner;
import com.system.taskAssigner.service.TaskAssignerService;

/**
 * Invokes the method of task assigner class.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskAssignerController {
	final TaskAssignerService ASSIGNER = new com.system.taskAssigner.service.implementation.TaskAssigner();
	
	/**
	 * Calls the assign task date method from
	 * task assigner class.
	 * 
	 * @param taskAssigner Model of task assigner.
	 */
	@Path("/")
	@Consumes("application/json")
	@PUT
	public void assignTask(TaskAssigner taskAssigner) {
		ASSIGNER.assignTask(taskAssigner);
	}
	
	/**
	 * Calls the search task by assignee id from 
	 * task assigner class.
	 * 
	 * @param assigneeId Id of the assignee.
	 * @return List of tasks.
	 */
	@Path("/{id}")
	@Produces("application/json")
	@GET
	public ArrayList<Task> searchTaskByAssigneeId(@PathParam("id") final int assigneeId) {
		return ASSIGNER.searchTaskByAssigneeId(assigneeId);
	}
}
