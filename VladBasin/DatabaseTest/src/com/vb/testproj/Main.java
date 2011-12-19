package com.vb.testproj;

import static java.lang.System.out;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vb.testproj.helpers.CryptoHelper;
import com.vb.testproj.model.Notification;
import com.vb.testproj.model.Task;
import com.vb.testproj.model.TaskStatus;
import com.vb.testproj.model.User;
import com.vb.testproj.model.UserRole;
import com.vb.testproj.service.NotificationService;
import com.vb.testproj.service.TaskService;
import com.vb.testproj.service.UserService;

public class Main {


	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		TaskService service = new TaskService();
		
		//service.addTask("title 1", "content 1", "description 1", 1, 0, new Date());
	
		//service.beginTask(1);
		
		//service.updateTask(2, 10);
		
		//service.deleteTask(1);
		
		for (Task t : service.getTasks(1, 0, 10, TaskStatus.CREATED)){
			out.println(t);
		}
		out.println("-----");
		for (Task t : service.getUncompletedTasks(1, 0, 10)){
			out.println(t);
		}
		//out.println(service.getTask(1));
	}

}
