package com.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.Todo;

public class ServiceTodoJPA {

    //EntityManagerFactory factory;
    //EntityManager manager;
	
    public ServiceTodoJPA(){	    
    	//factory = Persistence.createEntityManagerFactory("myunit2");
	    //manager = factory.createEntityManager();	    	    	   	
    }
    
    public List<Todo> getTodos(){
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("myunit2");
    	EntityManager manager = factory.createEntityManager();
	    
    	List<Todo> lista = manager.createNativeQuery("select * from todo", Todo.class).getResultList();
	    
    	manager.close();
	    
	    return lista;
	}

    public void deleteTodo(int id){
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("myunit2");
    	EntityManager manager = factory.createEntityManager();
    	EntityTransaction txn = manager.getTransaction();
    	txn.begin();
    	manager.createNativeQuery("delete from todo where id =  :id").setParameter("id", id).executeUpdate();
    	txn.commit();
    	manager.close();
    }
    
    public void completeTodo(int id){
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("myunit2");
    	EntityManager manager = factory.createEntityManager();
    	EntityTransaction txn = manager.getTransaction();
    	txn.begin();
    	manager.createNativeQuery("update todo set completed = 1 where id =  :id").setParameter("id", id).executeUpdate();
    	txn.commit();    	
    	manager.close();
    }

    public void createTodo(String text){
    	Todo t = new Todo();    	
    	t.setTodo(text);
    	t.setCompleted(false);
    	
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("myunit2");
    	EntityManager manager = factory.createEntityManager();
    	EntityTransaction txn = manager.getTransaction();
    	txn.begin();
    	manager.persist(t);
    	txn.commit();
    	manager.close();
    }
 	
	public Todo getTodo(){
		//id hardcoded
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myunit2");
		EntityManager manager = factory.createEntityManager();
	    Todo todo = (Todo)manager.createNativeQuery("select * from todo where id=1", Todo.class).getSingleResult();
	    manager.close();
	    return todo;
	}	
}
