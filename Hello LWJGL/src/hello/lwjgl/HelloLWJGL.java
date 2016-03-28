/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.lwjgl;

import org.lwjgl.Version;


/**
 *
 * @author kmhasan
 */
public class HelloLWJGL {

    public HelloLWJGL() {
        System.out.println("LWJGL version " + Version.getVersion());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HelloLWJGL();
    }
    
}
