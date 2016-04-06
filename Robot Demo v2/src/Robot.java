
import java.util.HashMap;
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
    private float TORSO_HEIGHT = 0.3f;

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
    private float upperRightArmAngle[] = {0, -90, +50, +1};
    private float upperLeftArmAngle[] = {0, -90, +50, +1};

    private float lowerRightArmAngle[] = {0, 0, +120, +1};
    private float lowerLeftArmAngle[] = {0, 0, +120, +1};

    private float rightThighAngle[] = {0, -5, +30, +1};
    private float leftThighAngle[] = {0, -5, +30, +1};

    private float rightCalfAngle[] = {0, -10, +5, +1};
    private float leftCalfAngle[] = {0, -10, +5, +1};

    private float neckAngle[] = {0, -20, +20, +1};
    private float headAngle[] = {0, -10, +10, +1};

    private float torsoAngle[] = {0, -5, +5, +1};
    private float torsoX = 0;
    private float torsoY = 0;

    private final int CURRENT_ANGLE = 0;
    private final int MIN_ANGLE = 1;
    private final int MAX_ANGLE = 2;
    private final int ROTATION_SIGN = 3;

    private boolean bouncing;

    private float robotColor[];
    
    private HashMap<String, Boolean> selected;

    public Robot() {
        bouncing = true;
        selected = new HashMap<>();
        selected.put("TORSO", false);
        selected.put("NECK", false);
        selected.put("HEAD", false);
        selected.put("UPPER_RIGHT_ARM", false);
        selected.put("UPPER_LEFT_ARM", false);
        selected.put("LOWER_RIGHT_ARM", false);
        selected.put("LOWER_LEFT_ARM", false);
        selected.put("RIGHT_THIGH", false);
        selected.put("LEFT_THIGH", false);
        selected.put("RIGHT_CALF", false);
        selected.put("LEFT_CALF", false);
        
        // by default set it to be a red robot
        robotColor = new float[3];
        robotColor[0] = 1;
        robotColor[1] = 0;
        robotColor[2] = 0;
    }

    public void setColor(float r, float g, float b) {
        robotColor[0] = r;
        robotColor[1] = g;
        robotColor[2] = b;
        GL11.glColor4f(r, g, b, 1);
    }
    
    public void setBouncing(boolean bouncing) {
        this.bouncing = bouncing;
    }

    public void toggleBouncing() {
        this.bouncing = !this.bouncing;
    }

    public void moveRight() {
        torsoX += 0.01;
    }

    public void moveLeft() {
        torsoX -= 0.01;
    }
    
    private void drawTorso() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("TORSO")) {
            DrawShapes.fillRectangle(-TORSO_WIDTH / 2, 0, TORSO_WIDTH, TORSO_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(-TORSO_WIDTH / 2, 0, TORSO_WIDTH, TORSO_HEIGHT);
        }
    }

    private void drawNeck() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("NECK")) {
            DrawShapes.fillRectangle(-NECK_WIDTH / 2, 0, NECK_WIDTH, NECK_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(-NECK_WIDTH / 2, 0, NECK_WIDTH, NECK_HEIGHT);
        }
    }

    private void drawHead() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        float EYE_RADIUS = HEAD_RADIUS / 5;
        float MOUTH_WIDTH = HEAD_RADIUS * 0.7f;
        // the circular face
        if (selected.get("HEAD")) {
            DrawShapes.fillCircle(HEAD_RADIUS, HEAD_SIDES);
        } else {
            DrawShapes.strokeCircle(HEAD_RADIUS, HEAD_SIDES);
        }
        // draw the right eye
        GL11.glPushMatrix();
        GL11.glTranslatef(HEAD_RADIUS / 2 - EYE_RADIUS / 2, HEAD_RADIUS * 0.1f, 0);
        if (selected.get("HEAD")) {
            GL11.glColor4f(1, 1, 0, 1);
            DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
            GL11.glColor4f(1, 1, 1, 1);
            DrawShapes.fillCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
            setColor(robotColor[0], robotColor[1], robotColor[2]);
        } else {
            DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
            DrawShapes.strokeCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
        }

        GL11.glPopMatrix();
        // draw the left eye
        GL11.glPushMatrix();
        GL11.glTranslatef(-HEAD_RADIUS / 2 + EYE_RADIUS / 2, HEAD_RADIUS * 0.1f, 0);
        if (selected.get("HEAD")) {
            GL11.glColor4f(1, 1, 0, 1);
            DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
            GL11.glColor4f(1, 1, 1, 1);
            DrawShapes.fillCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
            setColor(robotColor[0], robotColor[1], robotColor[2]);
        } else {
            DrawShapes.strokeCircle(EYE_RADIUS, HEAD_SIDES);
            DrawShapes.strokeCircle(EYE_RADIUS * 0.8f, HEAD_SIDES);
        }
        GL11.glPopMatrix();
        // draw the mouth
        GL11.glPushMatrix();
        GL11.glTranslatef(0, -HEAD_RADIUS / 2, 0);
        if (selected.get("HEAD")) {
            GL11.glColor4f(1, 1, 1, 1);
            DrawShapes.fillRectangle(-MOUTH_WIDTH * 0.5f, 0, MOUTH_WIDTH, MOUTH_WIDTH * 0.1f);
            setColor(robotColor[0], robotColor[1], robotColor[2]);
        } else {
            DrawShapes.strokeRectangle(-MOUTH_WIDTH * 0.5f, 0, MOUTH_WIDTH, MOUTH_WIDTH * 0.1f);
        }
        GL11.glPopMatrix();
    }

    public void toggleSelected(String bodyPart) {
        selected.replace(bodyPart, !selected.get(bodyPart));
    }

    private void drawUpperRightArm() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("UPPER_RIGHT_ARM")) {
            DrawShapes.fillRectangle(0, 0, UPPER_ARM_WIDTH, UPPER_ARM_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, UPPER_ARM_WIDTH, UPPER_ARM_HEIGHT);
        }
    }

    private void drawUpperLeftArm() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("UPPER_LEFT_ARM")) {
            DrawShapes.fillRectangle(0, 0, UPPER_ARM_WIDTH, UPPER_ARM_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, UPPER_ARM_WIDTH, UPPER_ARM_HEIGHT);
        }
    }

    private void drawLowerRightArm() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("LOWER_RIGHT_ARM")) {
            DrawShapes.fillRectangle(0, 0, LOWER_ARM_WIDTH, -LOWER_ARM_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, LOWER_ARM_WIDTH, -LOWER_ARM_HEIGHT);
        }
    }

    private void drawLowerLeftArm() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("LOWER_LEFT_ARM")) {
            DrawShapes.fillRectangle(0, 0, LOWER_ARM_WIDTH, -LOWER_ARM_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, LOWER_ARM_WIDTH, -LOWER_ARM_HEIGHT);
        }
    }

    private void drawRightThigh() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("RIGHT_THIGH")) {
            DrawShapes.fillRectangle(0, 0, THIGH_WIDTH, -THIGH_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, THIGH_WIDTH, -THIGH_HEIGHT);
        }
    }

    private void drawLeftThigh() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("LEFT_THIGH")) {
            DrawShapes.fillRectangle(0, 0, THIGH_WIDTH, -THIGH_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, THIGH_WIDTH, -THIGH_HEIGHT);
        }
    }

    private void drawRightCalf() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("RIGHT_CALF")) {
            DrawShapes.fillRectangle(0, 0, CALF_WIDTH, -CALF_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, CALF_WIDTH, -CALF_HEIGHT);
        }
    }
    
    private void drawLeftCalf() {
        GL11.glColor4f(robotColor[0], robotColor[1], robotColor[2], 1);
        if (selected.get("LEFT_CALF")) {
            DrawShapes.fillRectangle(0, 0, CALF_WIDTH, -CALF_HEIGHT);
        } else {
            DrawShapes.strokeRectangle(0, 0, CALF_WIDTH, -CALF_HEIGHT);
        }
    }
    

    private void updateAngles() {
        float t = (float) GLFW.glfwGetTime();

        if (selected.get("HEAD")) {
            headAngle[CURRENT_ANGLE] += headAngle[ROTATION_SIGN];
            if (headAngle[CURRENT_ANGLE] < headAngle[MIN_ANGLE] || headAngle[CURRENT_ANGLE] > headAngle[MAX_ANGLE]) {
                headAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("NECK")) {
            neckAngle[CURRENT_ANGLE] += neckAngle[ROTATION_SIGN];
            if (neckAngle[CURRENT_ANGLE] < neckAngle[MIN_ANGLE] || neckAngle[CURRENT_ANGLE] > neckAngle[MAX_ANGLE]) {
                neckAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("TORSO")) {
            torsoAngle[CURRENT_ANGLE] += torsoAngle[ROTATION_SIGN] * 0.1;
            if (torsoAngle[CURRENT_ANGLE] < torsoAngle[MIN_ANGLE] || torsoAngle[CURRENT_ANGLE] > torsoAngle[MAX_ANGLE]) {
                torsoAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("UPPER_RIGHT_ARM")) {
            upperRightArmAngle[CURRENT_ANGLE] += upperRightArmAngle[ROTATION_SIGN];
            if (upperRightArmAngle[CURRENT_ANGLE] < upperRightArmAngle[MIN_ANGLE] || upperRightArmAngle[CURRENT_ANGLE] > upperRightArmAngle[MAX_ANGLE]) {
                upperRightArmAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("LOWER_RIGHT_ARM")) {
            lowerRightArmAngle[CURRENT_ANGLE] += lowerRightArmAngle[ROTATION_SIGN];
            if (lowerRightArmAngle[CURRENT_ANGLE] < lowerRightArmAngle[MIN_ANGLE] || lowerRightArmAngle[CURRENT_ANGLE] > lowerRightArmAngle[MAX_ANGLE]) {
                lowerRightArmAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("UPPER_LEFT_ARM")) {
            upperLeftArmAngle[CURRENT_ANGLE] += upperLeftArmAngle[ROTATION_SIGN];
            if (upperLeftArmAngle[CURRENT_ANGLE] < upperLeftArmAngle[MIN_ANGLE] || upperLeftArmAngle[CURRENT_ANGLE] > upperLeftArmAngle[MAX_ANGLE]) {
                upperLeftArmAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("LOWER_LEFT_ARM")) {
            lowerLeftArmAngle[CURRENT_ANGLE] += lowerLeftArmAngle[ROTATION_SIGN];
            if (lowerLeftArmAngle[CURRENT_ANGLE] < lowerLeftArmAngle[MIN_ANGLE] || lowerLeftArmAngle[CURRENT_ANGLE] > lowerLeftArmAngle[MAX_ANGLE]) {
                lowerLeftArmAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("RIGHT_THIGH")) {
            rightThighAngle[CURRENT_ANGLE] += rightThighAngle[ROTATION_SIGN];
            if (rightThighAngle[CURRENT_ANGLE] < rightThighAngle[MIN_ANGLE] || rightThighAngle[CURRENT_ANGLE] > rightThighAngle[MAX_ANGLE]) {
                rightThighAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("LEFT_THIGH")) {
            leftThighAngle[CURRENT_ANGLE] += leftThighAngle[ROTATION_SIGN];
            if (leftThighAngle[CURRENT_ANGLE] < leftThighAngle[MIN_ANGLE] || leftThighAngle[CURRENT_ANGLE] > leftThighAngle[MAX_ANGLE]) {
                leftThighAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("RIGHT_CALF")) {
            rightCalfAngle[CURRENT_ANGLE] += rightCalfAngle[ROTATION_SIGN];
            if (rightCalfAngle[CURRENT_ANGLE] < rightCalfAngle[MIN_ANGLE] || rightCalfAngle[CURRENT_ANGLE] > rightCalfAngle[MAX_ANGLE]) {
                rightCalfAngle[ROTATION_SIGN] *= -1;
            }
        }
        if (selected.get("LEFT_CALF")) {
            leftCalfAngle[CURRENT_ANGLE] += leftCalfAngle[ROTATION_SIGN];
            if (leftCalfAngle[CURRENT_ANGLE] < leftCalfAngle[MIN_ANGLE] || leftCalfAngle[CURRENT_ANGLE] > leftCalfAngle[MAX_ANGLE]) {
                leftCalfAngle[ROTATION_SIGN] *= -1;
            }
        }
    }

    private void updateLocation() {
        if (bouncing) {
            float t = (float) GLFW.glfwGetTime();
            torsoY = (float) (0.1 * Math.sin(t));
        }
    }

    public void draw() {
        updateAngles();
        updateLocation();
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        // drawing the torso
        GL11.glTranslatef(torsoX, torsoY, 0);
        GL11.glRotatef(torsoAngle[CURRENT_ANGLE], 0, 0, 1);
        drawTorso();

        // drawing the neck
        GL11.glPushMatrix();
        GL11.glTranslatef(0, TORSO_HEIGHT, 0);
        GL11.glRotatef(neckAngle[CURRENT_ANGLE], 0, 0, 1);
        drawNeck();

        // drawing the head
        GL11.glTranslatef(0, NECK_HEIGHT + HEAD_RADIUS, 0);
        GL11.glRotatef(headAngle[CURRENT_ANGLE], 0, 0, 1);
        drawHead();
        GL11.glPopMatrix();

        // drawing the right arms
        GL11.glPushMatrix();
        GL11.glTranslatef(TORSO_WIDTH / 2, TORSO_HEIGHT - UPPER_ARM_HEIGHT, 0);
        GL11.glRotatef(upperRightArmAngle[CURRENT_ANGLE], 0, 0, 1);
        drawUpperRightArm();
        GL11.glTranslatef(UPPER_ARM_WIDTH, LOWER_ARM_HEIGHT, 0);
        GL11.glRotatef(lowerRightArmAngle[CURRENT_ANGLE], 0, 0, 1);
        drawLowerRightArm();
        GL11.glPopMatrix();

        // drawing the left arms
        GL11.glPushMatrix();
        GL11.glScalef(-1, 1, 1); // same as the right arm but on the other side
        GL11.glTranslatef(TORSO_WIDTH / 2, TORSO_HEIGHT - UPPER_ARM_HEIGHT, 0);
        GL11.glRotatef(upperLeftArmAngle[CURRENT_ANGLE], 0, 0, 1);
        drawUpperLeftArm();
        GL11.glTranslatef(UPPER_ARM_WIDTH, LOWER_ARM_HEIGHT, 0);
        GL11.glRotatef(lowerLeftArmAngle[CURRENT_ANGLE], 0, 0, 1);
        drawLowerLeftArm();
        GL11.glPopMatrix();

        // drawing the right leg
        GL11.glPushMatrix();
        GL11.glTranslatef(TORSO_WIDTH / 4 - THIGH_WIDTH / 2, 0, 0);
        GL11.glRotatef(rightThighAngle[CURRENT_ANGLE], 0, 0, 1);
        drawRightThigh();
        GL11.glTranslatef(0, -THIGH_HEIGHT, 0);
        GL11.glRotatef(rightCalfAngle[CURRENT_ANGLE], 0, 0, 1);
        drawRightCalf();
        GL11.glPopMatrix();

        // drawing the left leg
        GL11.glPushMatrix();
        GL11.glScalef(-1, 1, 1); // same as the right arm but on the other side
        GL11.glTranslatef(TORSO_WIDTH / 4 - THIGH_WIDTH / 2, 0, 0);
        GL11.glRotatef(leftThighAngle[CURRENT_ANGLE], 0, 0, 1);
        drawLeftThigh();
        GL11.glTranslatef(0, -THIGH_HEIGHT, 0);
        GL11.glRotatef(leftCalfAngle[CURRENT_ANGLE], 0, 0, 1);
        drawLeftCalf();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
