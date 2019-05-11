package semana21;

import Characters.LawMan;
import Characters.Raptor;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author Bertum
 */
public class Functions {

    //Get distance
    private static double getDistance(Vector3d vector1, Vector3d vector2) {

        double d = Math.pow((Math.pow(vector2.x - vector1.x, 2)
                + Math.pow(vector2.y - vector1.y, 2)
                + Math.pow(vector2.z - vector1.z, 2)
                * 1.0), 0.5);
        return d;
    }

    public static boolean raptorCapturetLawMan(Raptor raptor, LawMan lawMan) {
        boolean capture = false;
        Vector3d raptorVector = getVector(raptor);
        Vector3d lawManVector = getVector(lawMan);
        if (getDistance(raptorVector, lawManVector) <= 1) {
            capture = true;
        }
        return capture;
    }

    public static Vector3d getVector(BranchGroup obj) {
        TransformGroup tg = (TransformGroup) obj.getChild(0);
        Transform3D t3d = new Transform3D();
        tg.getTransform(t3d);
        Vector3d vector = new Vector3d();
        t3d.get(vector);
        return vector;
    }
}
