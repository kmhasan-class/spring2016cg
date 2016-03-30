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

    private void drawCircle(float r, int n) {
        // draw the dial
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINES);
        float dtheta = (float) (2 * Math.PI / n);
        for (int i = 0; i < n; i++) {
            GL11.glVertex3f((float) (r * Math.cos(i * dtheta)), (float) (r * Math.sin(i * dtheta)), 0f);
            GL11.glVertex3f((float) (r * Math.cos((i + 1) * dtheta)), (float) (r * Math.sin((i + 1) * dtheta)), 0f);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void drawDottedCircle(float r, int n) {
        // draw the dial
        GL11.glColor3f(0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINES);
        float dtheta = (float) (2 * Math.PI / n);
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                GL11.glVertex3f((float) (r * Math.cos(i * dtheta)), (float) (r * Math.sin(i * dtheta)), 0f);
                GL11.glVertex3f((float) (r * Math.cos((i + 1) * dtheta)), (float) (r * Math.sin((i + 1) * dtheta)), 0f);
            }
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void loop() {
        float angle = 0;

        float r = 0.8f;
        float lastX = r;
        float lastY = 0f;

        float nextX, nextY;

        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        while (true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            angle++;

            drawCircle(0.25f, 5);
            drawDottedCircle(0.5f, 50);
            drawDottedCircle(0.8f, 50);
            
            GL11.glPushMatrix();
            GL11.glRotatef(angle, 0, 0, 1);
            GL11.glTranslatef(0.5f, 0.0f, 0.0f);
            GL11.glRotatef(-angle * 4, 0, 0, 1);
            drawCircle(0.1f, 5);
            GL11.glPopMatrix();
            /*
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
             */
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
