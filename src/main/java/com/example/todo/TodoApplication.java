package com.example.todo;

import com.example.todo.db.TaskDAO;
import com.example.todo.resource.TaskResource;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class TodoApplication extends Application<TodoConfiguration> {


    //Setting up hibernate bundle
    private final HibernateBundle<TodoConfiguration> hibernate = new HibernateBundle<TodoConfiguration>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TodoConfiguration configuration) {
            return configuration.getDatabase();
        }
    };


    //Main method
    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    //Initializing
    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap){
        bootstrap.addBundle(hibernate);
    }

    //Running the Application
    @Override
    public void run(TodoConfiguration configuration, Environment environment) {
        final SessionFactory sessionFactory = hibernate.getSessionFactory();
        final TaskDAO taskDAO = new TaskDAO(sessionFactory);
        environment.jersey().register(new TaskResource(taskDAO));
    }
}
