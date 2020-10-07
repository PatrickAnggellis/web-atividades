package br.com.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepository<addStudent> {

    // Save the personal dates in memory
    private static HashMap<Integer, Student> students = new HashMap<>();

    //generated id for each student
    private int generatedId(final int values){
        if(students.containsKey(values))
            return generatedId(values + 1);
        return values;
    }

    //List all students
    public List<Student> getAllStudent(){
        return new ArrayList<Student>(students.values());
    }

    //List each student by id
    public Student getStudent(final int id){
        return students.get(id);
    }

    //Add the student
    public void addStudent(final Student student){
        if(student.getId() == 0)
            student.setId(generatedId(students.size() + 1));
        students.put(student.getId(), student);
    }

    //Edition an student
    public void editStudent(final Student student){
        students.remove(student.getId());
        students.put(student.getId(), student);
    }

    //Remove an student
    public void deleteStudent(final int id){
        students.remove(id);
    }
}
