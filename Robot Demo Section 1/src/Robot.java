
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

    public Robot() {
    }
    
    public void draw() {
        // drawing the torso
        DrawShapes.strokeRectangle(-0.2f, 0, 0.4f, 0.5f);
        
        // drawing the neck
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.5f, 0);
        GL11.glRotatef(-10, 0, 0, 1);
        DrawShapes.strokeRectangle(-0.05f, 0.0f, 0.1f, 0.2f);

        // drawing the head
        GL11.glTranslatef(0, 0.2f + 0.1f, 0);
        DrawShapes.strokeCircle(0.1f, 8);

        GL11.glPopMatrix();
        
    }
}
