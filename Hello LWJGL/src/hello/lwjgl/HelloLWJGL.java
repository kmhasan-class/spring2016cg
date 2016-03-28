/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.lwjgl;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author kmhasan
 */
public class HelloLWJGL {

    private int WIDTH = 800;
    private int HEIGHT = 600;
    private long window;

    public HelloLWJGL() {
        init();
        loop();
    }

    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Hello LWJGL", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    private void loop() {
        while (true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex3f(+1.0f, 0.0f, 0.0f);
            GL11.glVertex3f(+0.0f, 1.0f, 0.0f);
            GL11.glVertex3f(-1.0f, 0.0f, 0.0f);
            GL11.glEnd();
            
            GLFW.glfwSwapBuffers(window);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HelloLWJGL();
    }

}
