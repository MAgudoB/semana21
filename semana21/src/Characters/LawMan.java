package Characters;

import java.util.Random;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Bertum
 */
public class LawMan extends BranchGroup implements CharacterMovement {

    private boolean turnRight, turnLeft, goFront, goBack;
    private double angleY = Math.toRadians(15);
    private double speed = 0.1;
    private Matrix4d matrix = new Matrix4d();

    private void getRandomMove() {
        Random rnd = new Random();
        int num = rnd.nextInt(3 - 0 + 1) + 0;
        turnRight = false;
        turnLeft = false;
        goFront = false;
        goBack = false;
        switch (num) {
            case 0:
                turnRight = true;
                break;
            case 1:
                turnLeft = true;
                break;
            case 2:
                goFront = true;
                break;
            case 3:
                goBack = true;
                break;
        }
    }

    @Override
    public void move() {
        getRandomMove();
        TransformGroup tg = (TransformGroup) this.getChild(0);
        Transform3D t3d = new Transform3D();
        Transform3D t3dStep = new Transform3D();
        tg.getTransform(t3d);
        if (this.turnLeft) {
            t3dStep.rotY(angleY);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dStep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        } else if (this.turnRight) {
            t3dStep.rotY(-angleY);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dStep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        }
        if (this.goFront) {
            t3dStep.set(new Vector3d(0.0, 0.0, speed));
            tg.getTransform(t3d);
            t3d.mul(t3dStep);
            tg.setTransform(t3d);
        } else if (this.goBack) {
            t3dStep.set(new Vector3d(0.0, 0.0, -speed));
            tg.getTransform(t3d);
            t3d.mul(t3dStep);
            tg.setTransform(t3d);
        }
    }
}
