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

    private int WIDTH = 600;
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
        float angle = 0;

        float r = 0.8f;
        float lastX = r;
        float lastY = 0f;

        float nextX, nextY;

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // draw the dial
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINES);
        int n = 100;
        float dtheta = (float) (2 * Math.PI / n);
        for (int i = 0; i < n; i++) {
            GL11.glVertex3f((float) (1 * Math.cos(i * dtheta)), (float) (1 * Math.sin(i * dtheta)), 0f);
            GL11.glVertex3f((float) (1 * Math.cos((i + 1) * dtheta)), (float) (1 * Math.sin((i + 1) * dtheta)), 0f);
        }
        GL11.glEnd();
        GL11.glPopMatrix();

        while (true) {
            double t = GLFW.glfwGetTime();

            // all our calculations are in radian (NOT degree)
            angle = (float) (2 * Math.PI / 60.0 * t);

            nextX = (float) (r * Math.cos(angle));
            nextY = (float) (r * Math.sin(angle));
            GL11.glPushMatrix();
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex3f(+0.0f, 0.0f, 0.0f); // origin
            GL11.glVertex3f(lastX, lastY, 0.0f); // p1
            GL11.glVertex3f(nextX, nextY, 0.0f); // p2
            GL11.glEnd();
            GL11.glPopMatrix();

            lastX = nextX;
            lastY = nextY;

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
