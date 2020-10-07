package br.com.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("student")
public class StudentResource {

    private StudentRepository studentRepository = new StudentRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(){
        return Response.ok(studentRepository.getAllStudent()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getById(@PathParam("id") int id){
        return studentRepository.getStudent(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student){
        try{
            studentRepository.addStudent(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editStudent(@PathParam("id") int id, Student student){
        Student t = studentRepository.getStudent(id);
        if (t == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            student.setId(id);
            studentRepository.editStudent(student);
            return Response.status(Response.Status.OK).entity(student).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delStudent(@PathParam("id") int id){
        Student t = studentRepository.getStudent(id);
        if (t == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            studentRepository.deleteStudent(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
