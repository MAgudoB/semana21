/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//https://sites.google.com/site/java3dapplets/home/source-code-move-tinkerbell

package Characters;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Miguel
 */
public class Raptor extends BranchGroup implements CharacterMovement {
    private double x,y,z;
    private double angleY = Math.toRadians(15);
    private Matrix4d matrix = new Matrix4d();
    
    public Raptor(){
        super();        
    }

    @Override
    public void move(boolean turnLeft,boolean turnRight,boolean goFront,boolean goBack) {
        TransformGroup tg = (TransformGroup) this.getChild(0);
        Transform3D t3d = new Transform3D();
        Transform3D t3dAux = new Transform3D();
        tg.getTransform(t3d);
        Vector3f translation = new Vector3f();
        t3d.get(translation);
        if(turnLeft){
            
        }
        if(turnRight){
            t3dAux.set(new Vector3d(0d,-angleY,0d));
            t3d.mul(t3dAux);
            tg.setTransform(t3d);
        }
        if(goFront){
            t3dAux.set(new Vector3d(0d,angleY,0d));
            t3d.mul(t3dAux);
            tg.setTransform(t3d);           
        }
        if(goBack){
            t3dAux.set(new Vector3d(0d,angleY,0d));
            t3d.mul(t3dAux);
            tg.setTransform(t3d);    
        }
        tg.setTransform(t3d);
    }
    
}
