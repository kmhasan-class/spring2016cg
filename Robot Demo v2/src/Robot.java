
import org.lwjgl.glfw.GLFW;
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

    private float TORSO_WIDTH = 0.4f;
    private float TORSO_HEIGHT = 0.5f;

    private float NECK_WIDTH = 0.1f;
    private float NECK_HEIGHT = 0.08f;

    private float HEAD_RADIUS = 0.15f;
    private int HEAD_SIDES = 20;

    private float UPPER_ARM_WIDTH = 0.25f;
    private float UPPER_ARM_HEIGHT = 0.1f;

    private float LOWER_ARM_WIDTH = 0.35f;
    private float LOWER_ARM_HEIGHT = 0.1f;

    private float THIGH_WIDTH = 0.1f;
    private float THIGH_HEIGHT = 0.25f;

    private float CALF_WIDTH = 0.1f;
    private float CALF_HEIGHT = 0.35f;

    // rotation angles to control the body parts
    private float upperRightArmAngle = 0;
    private float upperLeftArmAngle = 0;

    private float lowerRightArmAngle = 0;
    private float lowerLeftArmAngle = 0;

    private float rightThighAngle = 0;
    private float leftThighAngle = 0;

    private float rightCalfAngle = 0;
    private float leftCalfAngle = 0;

    private float neckAngle = 0;

    private float torsoAngle = 0;
    private float torsoX = 0;
    private float torsoY = 0;
    
    public Robot() {
    }

    private void drawTorso() {
        DrawShapes.strokeRectangle(-TORSO_WIDTH / 2, 0, TORSO_WIDTH, TORSO_HEIGHT);
    }

    private void drawNeck() {
        DrawShapes.strokeRectangle(-NECK_WIDTH / 2, 0, NECK_WIDTH, NECK_HEIGHT);
    }

    private void drawHead() {
        float EYE_RADIUS = HEAD_RADIUS / 5;
        float MOUTH_WIDTH = HEAD_RADIUS * 0.7f;
        // the circular face
        DrawShapes.strokeCircle(HEAD_RADIUS, HEAD_SIDES);
        // draw the right eye
        GL11.glPushMatrix();
        GL11.glTranslatef(HEAD_RADIUS / 2 - EYE_RADIUS / 2, HEAD_RADIUS * 0.1f, 0);
        DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
        DrawShapes.strokeCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
        GL11.glPopMatrix();
        // draw the left eye
        GL11.glPushMatrix();
        GL11.glTranslatef(-HEAD_RADIUS / 2 + EYE_RADIUS / 2, HEAD_RADIUS * 0.1f, 0);
        DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
        DrawShapes.strokeCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
        GL11.glPopMatrix();
        // draw the mouth
        GL11.glPushMatrix();
        GL11.glTranslatef(0, -HEAD_RADIUS / 2, 0);
        DrawShapes.strokeRectangle(-MOUTH_WIDTH * 0.5f, 0, MOUTH_WIDTH, MOUTH_WIDTH * 0.1f);
        GL11.glPopMatrix();

    }

    private void drawUpperArm() {
        DrawShapes.strokeRectangle(0, 0, UPPER_ARM_WIDTH, UPPER_ARM_HEIGHT);
    }

    private void drawLowerArm() {
        DrawShapes.strokeRectangle(0, 0, LOWER_ARM_WIDTH, LOWER_ARM_HEIGHT);
    }

    private void drawThigh() {
        DrawShapes.strokeRectangle(0, 0, THIGH_WIDTH, -THIGH_HEIGHT);
    }

    private void drawCalf() {
        DrawShapes.strokeRectangle(0, 0, CALF_WIDTH, -CALF_HEIGHT);
    }

    private void updateAngles() {
        float t = (float) GLFW.glfwGetTime();
        
        upperRightArmAngle++;
        lowerRightArmAngle++;
        upperLeftArmAngle += 0.1;
        lowerLeftArmAngle += 0.2;
        torsoAngle = (float) (5.0 * Math.sin(t));
    }
    
    private void updateLocation() {
        float t = (float) GLFW.glfwGetTime();
        torsoY = (float) (0.05 * Math.sin(t));
    }
    
    public void draw() {
        updateAngles();
        updateLocation();
        GL11.glPushMatrix();
        // drawing the torso
        GL11.glTranslatef(torsoX, torsoY, 0);
        GL11.glRotatef(torsoAngle, 0, 0, 1);
        drawTorso();

        // drawing the neck
        GL11.glPushMatrix();
        GL11.glTranslatef(0, TORSO_HEIGHT, 0);
        GL11.glRotatef(neckAngle, 0, 0, 1);
        drawNeck();

        // drawing the head
        GL11.glTranslatef(0, NECK_HEIGHT + HEAD_RADIUS, 0);
        drawHead();
        GL11.glPopMatrix();

        // drawing the right arms
        GL11.glPushMatrix();
        GL11.glTranslatef(TORSO_WIDTH / 2, TORSO_HEIGHT - UPPER_ARM_HEIGHT, 0);
        GL11.glRotatef(upperRightArmAngle, 0, 0, 1);
        drawUpperArm();
        GL11.glTranslatef(UPPER_ARM_WIDTH, 0, 0);
        GL11.glRotatef(lowerRightArmAngle, 0, 0, 1);
        drawLowerArm();
        GL11.glPopMatrix();

        // drawing the left arms
        GL11.glPushMatrix();
        GL11.glScalef(-1, 1, 1); // same as the right arm but on the other side
        GL11.glTranslatef(TORSO_WIDTH / 2, TORSO_HEIGHT - UPPER_ARM_HEIGHT, 0);
        GL11.glRotatef(upperLeftArmAngle, 0, 0, 1);
        drawUpperArm();
        GL11.glTranslatef(UPPER_ARM_WIDTH, 0, 0);
        GL11.glRotatef(lowerLeftArmAngle, 0, 0, 1);
        drawLowerArm();
        GL11.glPopMatrix();

        // drawing the right leg
        GL11.glPushMatrix();
        GL11.glTranslatef(TORSO_WIDTH / 4 - THIGH_WIDTH / 2, 0, 0);
        GL11.glRotatef(rightThighAngle, 0, 0, 1);
        drawThigh();
        GL11.glTranslatef(0, -THIGH_HEIGHT, 0);
        GL11.glRotatef(rightCalfAngle, 0, 0, 1);
        drawCalf();
        GL11.glPopMatrix();

        // drawing the left leg
        GL11.glPushMatrix();
        GL11.glScalef(-1, 1, 1); // same as the right arm but on the other side
        GL11.glTranslatef(TORSO_WIDTH / 4 - THIGH_WIDTH / 2, 0, 0);
        GL11.glRotatef(leftThighAngle, 0, 0, 1);
        drawThigh();
        GL11.glTranslatef(0, -THIGH_HEIGHT, 0);
        GL11.glRotatef(leftCalfAngle, 0, 0, 1);
        drawCalf();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
