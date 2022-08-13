package com.system.taskAssigner.view;

import com.system.task.dao.TaskDao;
import com.system.task.model.Task;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Get details about the task from the user like
 * task id, task name,task description,task status,
 * task start date, task due date.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskAssignerDetails {
    public static final Scanner INPUT = new Scanner(System.in);

    /**
     * Gets the assignee id from the user.
     *
     * @return id of the assignee.
     * @throws InputMismatchException when invalid input is entered.
     */
    int getAssigneeId() {
        int assigneeId = 0;

        try {
            TaskDao.LOGGER.info("Enter task id : ");
            assigneeId = INPUT.nextInt();
            INPUT.nextLine();
        } catch (InputMismatchException exception) {
            TaskDao.LOGGER.warning("Only integer value is accepted");
            TaskDao.LOGGER.info("Enter task id : ");
            assigneeId = INPUT.nextInt();
            INPUT.nextLine();
        }
        return assigneeId;
    }

    /**
     * Gets the task id from the user.
     *
     * @return id of the task.
     * @throws InputMismatchException when invalid input is entered.
     */
    public int getTaskId() {
        int taskId = 0;

        try {
            TaskDao.LOGGER.info("Enter task id : ");
            taskId = INPUT.nextInt();
            INPUT.nextLine();
        } catch (InputMismatchException exception) {
            TaskDao.LOGGER.warning("Only integer value is accepted");
            TaskDao.LOGGER.info("Enter task id : ");
            taskId = INPUT.nextInt();
            INPUT.nextLine();
        }
        return taskId;
    }

    /**
     * Prints all the available task.
     *
     * @param taskCollection List of all available tasks.
     */
    void printAllTask(final ArrayList<Task> taskCollection) {
        if (taskCollection.isEmpty()) {
            TaskDao.LOGGER.warning("No task found");
        } else {
            for (final Task task : taskCollection) {
                TaskDao.LOGGER.info("Task details");
                TaskDao.LOGGER.info("task id : " + task.getTaskId());
                TaskDao.LOGGER.info("Task name is : " + task.getTaskName());
                TaskDao.LOGGER.info("Task Description is : " + task.getTaskDescription());
                TaskDao.LOGGER.info("Task start date is : " + task.getTaskStartDate());
                TaskDao.LOGGER.info("Task due date is : " + task.getTaskDueDate());
                TaskDao.LOGGER.info("Task status is : " + task.getTaskStatus());
            }
        }
    }

    /**
     * Gets the list of task id from the user.
     *
     * @return list of task id.
     */
    int[] getListOfTaskId() {
        TaskDao.LOGGER.info("Enter the total number of tasks");
        int[] taskIdList = new int[INPUT.nextInt()];
        INPUT.nextLine();

        for (int initialValue = 0; initialValue < taskIdList.length; initialValue++) {
            TaskDao.LOGGER.info("Enter the task id");
            taskIdList[initialValue] = INPUT.nextInt();
            INPUT.nextLine();
        }
        return taskIdList;
    }
}
