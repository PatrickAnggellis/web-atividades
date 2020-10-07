package br.com.web;

import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ClassRepository {

    private static HashMap<Integer, Class> classes = new HashMap<>();

    //Add student in a class
   /* public void addStudentClass(Student student){
        if (classes.containsKey(student) == false)
    }*/

    private  int generetadIdClass (final int values) {
        if (classes.containsKey(values))
            return generetadIdClass(values + 1);
        return values;
    }

    public List<Class> getAllClasses(){
        return new ArrayList<Class>(classes.values());
    }

    public Class getClasse(final int id){
        return classes.get(id);
    }

    public void addClass(final Class classe){
        if (classe.getId()== 0)
            classe.setId(generetadIdClass(classes.size() + 1));
        classes.put(classe.getId(), classe);
    }

    public void editClass(final Class classe){
        classes.remove(classe.getId());
        classes.put(classe.getId(), classe);
    }

    public void deleteClass(final int id){
        classes.remove(id);
    }

}
