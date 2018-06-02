/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class HelloRestService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting getGreeting(){
       return new Greeting("Mr.Tiger", "You suck!");
    }
}