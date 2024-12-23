package com.example.todo.db;

import com.example.todo.Task;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //Getting all tasks
    public List<Task> findAll() {
        CriteriaQuery<Task> criteriaQuery = currentSession().getCriteriaBuilder().createQuery(Task.class);
        criteriaQuery.from(Task.class);
        return currentSession().createQuery(criteriaQuery).getResultList();
    }

    //Getting tasks by ID
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(get(id));  //Optional to avoid abnormal termination
    }

    //Creating tasks
    public Task create(Task task) {
        currentSession().save(task);
        return task;
    }

    //Updating tasks
    public void update(Task task) {
        currentSession().merge(task); // Use merge instead of update
    }


    //Hard Deleting tasks
    public void delete(Task task) {
        currentSession().delete(task);
    }
}

