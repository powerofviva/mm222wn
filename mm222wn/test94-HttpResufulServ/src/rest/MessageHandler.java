package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("lnu")
public class MessageHandler {

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createDataInJSON(String data) { 
    public void createDataInJSON(String data) {
        //  	jedis.set("key"+(counter++), data);
//    	System.out.println("sixe db ine ----> " + jedis.dbSize()+"     text ine ---> " + data);
//        return Response.status(201).entity(result).build(); 
        return;
    }
}