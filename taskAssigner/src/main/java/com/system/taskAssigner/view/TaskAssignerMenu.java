package com.system.taskAssigner.view;

import com.system.taskAssigner.controller.TaskAssignerController;
import com.system.taskAssigner.dao.TaskAssignerDao;
import com.system.taskAssigner.model.TaskAssigner;

/**
 * List various operations available for
 * asssigning task to assignee.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskAssignerMenu {

    /**
     * Displays all the options available for editing task,
     */
    public void displayOptions() {
        final TaskAssignerController controller = new TaskAssignerController();
        final TaskAssignerDetails details = new TaskAssignerDetails();

        TaskAssignerDao.LOGGER.info("\n Enter 1 assigning task"
                + "\n Enter 2 for displaying task assigned to assignee");

        switch (TaskAssignerDetails.INPUT.nextLine()) {
            case "1":
                controller.assignTask(new TaskAssigner(details.getTaskId(), details.getListOfTaskId()));
                break;
            case "2":
                details.printAllTask(controller.searchTaskByAssigneeId(details.getAssigneeId()));
                break;
            default:
                TaskAssignerDao.LOGGER.warning("You have entered wrong choice");
                break;
        }
    }
}
