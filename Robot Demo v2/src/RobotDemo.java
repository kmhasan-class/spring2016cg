
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kmhasan
 */
public class RobotDemo {
    private long window;
    private int WIDTH = 800;
    private int HEIGHT = 800;
    private Robot robot;
    
    public RobotDemo() {
        init();
        loop();
    }

    private void init() {
        robot = new Robot();
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Robot Demo", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        
        GLFW.glfwSetWindowCloseCallback(window, new GLFWWindowCloseCallback() {
            @Override
            public void invoke(long window) {
                GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
            }
        });
        
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW.GLFW_KEY_ESCAPE)
                    GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
            }
        });
    }

    private void loop() {
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        
        while (GLFW.glfwWindowShouldClose(window) != GL11.GL_TRUE) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glPushMatrix();
            robot.draw();
            GL11.glPopMatrix();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new RobotDemo();
    }

}
