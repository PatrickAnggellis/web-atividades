package br.com.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.nashorn.internal.objects.annotations.Getter;
import sun.security.util.ManifestEntryVerifier;

@Path("class")
public class ClassResource {

    private ClassRepository classRepository = new ClassRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasse(){
        return Response.ok(classRepository.getAllClasses()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Class getByIdClasse(@PathParam("id")int id){
        return classRepository.getClasse(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClass(Class classe){
        try {
            classRepository.addClass(classe);
            return Response.status(Response.Status.CREATED).entity(classe).build();
        } catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editClass(@PathParam("id") int id, Class classe){
        Class cl = classRepository.getClasse(id);
        if (cl == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            classe.setId(id);
            classRepository.editClass(classe);
            return Response.status(Response.Status.OK).entity(classe).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteClass(@PathParam("id") int id){
        Class cl = classRepository.getClasse(id);
        if (cl == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            classRepository.deleteClass(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
