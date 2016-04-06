
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
    private Robot robots[];
    private int selectedRobot;
    
    public RobotDemo() {
        init();
        loop();
    }

    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Robot Demo", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        robots = new Robot[2];
        robots[0] = new Robot();
        robots[0].setColor(0.4f, 0.1f, 0.0f);
        robots[1] = new Robot();
        robots[1].setColor(0.0f, 0.1f, 0.4f);
        selectedRobot = 0;
        
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW.GLFW_KEY_ESCAPE)
                    GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
                if (key == GLFW.GLFW_KEY_P && action == GLFW.GLFW_PRESS)
                    robots[selectedRobot].toggleBouncing();
                if (key == GLFW.GLFW_KEY_H && action == GLFW.GLFW_PRESS)
                    robots[selectedRobot].toggleSelected("HEAD");
                if (key == GLFW.GLFW_KEY_N && action == GLFW.GLFW_PRESS)
                    robots[selectedRobot].toggleSelected("NECK");
                if (key == GLFW.GLFW_KEY_T && action == GLFW.GLFW_PRESS)
                    robots[selectedRobot].toggleSelected("TORSO");
                if (key == GLFW.GLFW_KEY_U && action == GLFW.GLFW_PRESS && mods != GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("UPPER_RIGHT_ARM");
                if (key == GLFW.GLFW_KEY_U && action == GLFW.GLFW_PRESS && mods == GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("UPPER_LEFT_ARM");
                if (key == GLFW.GLFW_KEY_L && action == GLFW.GLFW_PRESS && mods != GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("LOWER_RIGHT_ARM");
                if (key == GLFW.GLFW_KEY_L && action == GLFW.GLFW_PRESS && mods == GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("LOWER_LEFT_ARM");
                if (key == GLFW.GLFW_KEY_G && action == GLFW.GLFW_PRESS && mods != GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("RIGHT_THIGH");
                if (key == GLFW.GLFW_KEY_G && action == GLFW.GLFW_PRESS && mods == GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("LEFT_THIGH");
                if (key == GLFW.GLFW_KEY_C && action == GLFW.GLFW_PRESS && mods != GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("RIGHT_CALF");
                if (key == GLFW.GLFW_KEY_C && action == GLFW.GLFW_PRESS && mods == GLFW.GLFW_MOD_SHIFT)
                    robots[selectedRobot].toggleSelected("LEFT_CALF");
                if (key == GLFW.GLFW_KEY_RIGHT && action != GLFW.GLFW_RELEASE)
                    robots[selectedRobot].moveRight();
                if (key == GLFW.GLFW_KEY_LEFT && action != GLFW.GLFW_RELEASE)
                    robots[selectedRobot].moveLeft();
                if (key == GLFW.GLFW_KEY_1 && action == GLFW.GLFW_PRESS)
                    selectedRobot = 0;
                if (key == GLFW.GLFW_KEY_2 && action == GLFW.GLFW_PRESS)
                    selectedRobot = 1;
            }
        });
    }

    private void loop() {
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        
        while (GLFW.glfwWindowShouldClose(window) != GL11.GL_TRUE) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glPushMatrix();
            for (Robot r: robots)
                r.draw();
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
