package com.example.todo.resource;

import com.example.todo.db.TaskDAO;
import com.example.todo.Task;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.errors.ErrorMessage;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/tasks")  //Path of the application
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDAO taskDAO;

    //Constructor
    public TaskResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    //Get All
    @GET
    @UnitOfWork
    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    //Get By ID
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Task getTask(@PathParam("id") Long id) {
        return taskDAO.findById(id)
                .orElseThrow(() -> new WebApplicationException("Task not found", Response.Status.NOT_FOUND));
    }


    //Create task
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Task createTask(Task task) {
        return taskDAO.create(task);
    }

    //Update task
    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Task updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task existingTask = taskDAO.findById(id)
                .orElseThrow(() -> new WebApplicationException("Task not found", Response.Status.NOT_FOUND));

        // Update fields
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setTargetDate(updatedTask.getTargetDate());
        existingTask.setStartDate(updatedTask.getStartDate());


        taskDAO.update(existingTask);

        return existingTask;
    }


    //Delete task
    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response removeTask(@PathParam("id") Long id) {
        Optional<Task> task = taskDAO.findById(id);
        if (task.isPresent()) {
            taskDAO.delete(task.get());
            return Response.ok("Task removed successfully!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessage("Task not found")).build();
        }
    }
}
