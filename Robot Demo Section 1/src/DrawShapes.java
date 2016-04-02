
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
        GL11.glVertex3f(x, y, 0);
        GL11.glVertex3f(x + width, y, 0);
        GL11.glVertex3f(x + width, y + height, 0);
        GL11.glVertex3f(x, y + height, 0);
        GL11.glEnd();
    }

    public static void strokeRectangle(float x, float y, float width, float height) {
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3f(x, y, 0);
        GL11.glVertex3f(x + width, y, 0);
        GL11.glVertex3f(x + width, y + height, 0);
        GL11.glVertex3f(x, y + height, 0);
        GL11.glEnd();
    }
    
    public static void strokeCircle(float radius, int sides) {
        float dtheta = (float) Math.PI * 2 / sides;
        GL11.glBegin(GL11.GL_LINES);
        for (int i = 0; i < sides; i++) {
            float x0, y0;
            float x1, y1;
            x0 = radius * (float) Math.cos(i * dtheta);
            y0 = radius * (float) Math.sin(i * dtheta);
            x1 = radius * (float) Math.cos((i + 1) * dtheta);
            y1 = radius * (float) Math.sin((i + 1) * dtheta);
            
            GL11.glVertex3f(x0, y0, 0);
            GL11.glVertex3f(x1, y1, 0);
        }
        GL11.glEnd();
    }
    
    public static void strokeCircle(float x, float y, float z, float radius, int sides) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        strokeCircle(radius, sides);
        GL11.glPopMatrix();
    }
}
