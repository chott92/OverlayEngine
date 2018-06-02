/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.rest;

import java.io.Serializable;

/**
 *
 * @author CHOTT
 */
public class Greeting implements Serializable{
        
        private static final long serialVersionUID = 1L;
        
       private String name;
       private String message;

        public Greeting(String name, String message) {
            this.name = name;
            this.message = message;
        }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
        
        
}
