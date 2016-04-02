
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
public class Robot {
    public void draw() {
        // drawing the torso
        DrawShapes.strokeRectangle(-0.2f, -0.35f, 0.4f, 0.7f);
        
        GL11.glPushMatrix();
        // drawing the neck
        GL11.glTranslatef(0, 0.35f, 0);
        GL11.glRotatef(45, 0, 0, 1);
        GL11.glTranslatef(0, 0.1f, 0);
        DrawShapes.strokeRectangle(-0.05f, -0.10f, 0.1f, 0.2f);
        GL11.glPopMatrix();
    }
}
