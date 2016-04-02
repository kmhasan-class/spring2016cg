
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
public class DrawShapes {
    public static void fillRectangle(float x, float y, float width, float height) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(x, y, 0f);
        GL11.glVertex3f(x + width, y, 0f);
        GL11.glVertex3f(x + width, y + height, 0f);
        GL11.glVertex3f(x, y + height, 0f);
        GL11.glEnd();
    }
    
    public static void strokeRectangle(float x, float y, float width, float height) {
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3f(x, y, 0f);
        GL11.glVertex3f(x + width, y, 0f);
        GL11.glVertex3f(x + width, y + height, 0f);
        GL11.glVertex3f(x, y + height, 0f);
        GL11.glEnd();
    }
    
    public static void strokeCirlce(float radius, int sides) {
        float dtheta = (float) (2 * Math.PI / sides);
        GL11.glBegin(GL11.GL_LINES);
        for (int i = 0; i < sides; i++) {
            float x0 = radius * (float) Math.cos(i * dtheta);
            float y0 = radius * (float) Math.sin(i * dtheta);
            float x1 = radius * (float) Math.cos((i + 1) * dtheta);
            float y1 = radius * (float) Math.sin((i + 1) * dtheta);;
            
            GL11.glVertex3f(x0, y0, 0);
            GL11.glVertex3f(x1, y1, 0);
        }
        GL11.glEnd();
    }

    public static void fillCirlce(float radius, int sides) {
        float dtheta = (float) (2 * Math.PI / sides);
        GL11.glBegin(GL11.GL_TRIANGLES);
        for (int i = 0; i < sides; i++) {
            float x0 = radius * (float) Math.cos(i * dtheta);
            float y0 = radius * (float) Math.sin(i * dtheta);
            float x1 = radius * (float) Math.cos((i + 1) * dtheta);
            float y1 = radius * (float) Math.sin((i + 1) * dtheta);;

            GL11.glVertex3f(0, 0, 0);
            GL11.glVertex3f(x0, y0, 0);
            GL11.glVertex3f(x1, y1, 0);
        }
        GL11.glEnd();
    }
    
    public static void strokeGrid() {
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(-1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(+1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, -1.0f, 0.0f);
        GL11.glVertex3f(0.0f, +1.0f, 0.0f);
        GL11.glEnd();
    }
}
