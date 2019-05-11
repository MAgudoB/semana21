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
	private boolean turnRight,turnLeft,goFront,goBack;
    private double angleY = Math.toRadians(15);
    private double speed = 0.1;
    private Matrix4d matrix = new Matrix4d();
    
    public Raptor(){
        super();        
    }

    @Override
    public void move() {
        TransformGroup tg = (TransformGroup) this.getChild(0);
        Transform3D t3d = new Transform3D();
        Transform3D t3dStep = new Transform3D();
        tg.getTransform(t3d);
        if(this.turnLeft){
        	t3dStep.rotY(angleY);
        	tg.getTransform(t3d);
        	t3d.get(matrix);
			t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d.mul(t3dStep);
			t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg.setTransform(t3d);
        } else if(this.turnRight){
        	t3dStep.rotY(-angleY);
        	tg.getTransform(t3d);
        	t3d.get(matrix);
        	t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d.mul(t3dStep);
			t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg.setTransform(t3d);
        }
        if(this.goFront){
        	t3dStep.set(new Vector3d(0.0, 0.0, speed));
        	tg.getTransform(t3d);
        	t3d.mul(t3dStep);
        	tg.setTransform(t3d);          
        } else if(this.goBack){
        	t3dStep.set(new Vector3d(0.0, 0.0, -speed));
        	tg.getTransform(t3d);
        	t3d.mul(t3dStep);
        	tg.setTransform(t3d);    
        }
    }

	public void setTurnRight(boolean turnRight) {
		this.turnRight = turnRight;
	}

	public void setTurnLeft(boolean turnLeft) {
		this.turnLeft = turnLeft;
	}

	public void setGoFront(boolean goFront) {
		this.goFront = goFront;
	}

	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}
}
