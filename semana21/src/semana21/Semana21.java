package semana21;

import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import javax.media.j3d.*;

import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;
import java.io.File;

public class Semana21 extends MouseAdapter {

private PickCanvas pickCanvas;

public Semana21() {

    Frame frame = new Frame("Jurasic World");

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas = new Canvas3D(config);

    canvas.setSize(800, 800);

    SimpleUniverse universe = new SimpleUniverse(canvas);

    BranchGroup group = new BranchGroup();

    // create a color cube
    
    //createCube(group);

    //create a sphere

    //createSphere(group);
    
    group.addChild(createRaptor());
    
    group.addChild(createLawMan());

    universe.getViewingPlatform().setNominalViewingTransform();

    universe.addBranchGraph(group);

    frame.addWindowListener(new WindowAdapter() {

       public void windowClosing(WindowEvent winEvent) {

          System.exit(0);

       }

    });

    frame.add(canvas);

    pickCanvas = new PickCanvas(canvas, group);

    pickCanvas.setMode(PickCanvas.BOUNDS);

    canvas.addMouseListener(this);

    frame.pack();

    frame.show();

}

public static void main( String[] args ) {

    System.setProperty("sun.awt.noerasebackground", "true");

    new Semana21();
    
    

}

public void mouseClicked(MouseEvent e)  {

    pickCanvas.setShapeLocation(e);

    PickResult result = pickCanvas.pickClosest();

    if (result == null) {

       System.out.println("Nothing picked");

    } else {

       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

       if (p != null) {

          System.out.println(p.getClass().getName());

       } else if (s != null) {

             System.out.println(s.getClass().getName());

       } else{

          System.out.println("null");

       }
    }
}

    private void createCube(BranchGroup group) {
        Vector3f vector = new Vector3f(-0.3f, 0.0f, 0.0f);

        Transform3D transform = new Transform3D();

        transform.setTranslation(vector);

        TransformGroup transformGroup = new TransformGroup(transform);

        ColorCube cube = new ColorCube(0.3);

        transformGroup.addChild(cube);

        group.addChild(transformGroup);
    }

    private void createSphere(BranchGroup group) {
        Vector3f vector2 = new Vector3f(+0.3f, 0.0f, 0.0f);

        Transform3D transform2 = new Transform3D();

        transform2.setTranslation(vector2);

        TransformGroup transformGroup2 = new TransformGroup(transform2);

        Appearance appearance = new Appearance();

        appearance.setPolygonAttributes(

           new PolygonAttributes(PolygonAttributes.POLYGON_LINE,

           PolygonAttributes.CULL_BACK,0.0f));

        Sphere sphere = new Sphere(0.3f,appearance);

        transformGroup2.addChild(sphere);

        group.addChild(transformGroup2);
    }

    private BranchGroup createRaptor() {
        
        BranchGroup objRoot = new BranchGroup();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        t3d.setTranslation(new Vector3f(-0.3f, 0.0f, 0.0f));
        t3d.setRotation(new AxisAngle4f(0.0f, 0.0f, 0.0f, 0.0f));
        t3d.setScale(1.0);

        tg.setTransform(t3d);

        ObjectFile loader = new ObjectFile(ObjectFile.RESIZE);
        Scene s = null;

        File file = new java.io.File("src//semana21//Irex_obj.obj");

        try {
         s = loader.load(file.toURI().toURL());
        } catch (Exception e) {
         System.err.println(e);
         System.exit(1);
        }

        tg.addChild(s.getSceneGroup());

        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.compile();

        return objRoot;
    }
    
    private BranchGroup createLawMan() {
        
        BranchGroup objRoot = new BranchGroup();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        t3d.setTranslation(new Vector3f(+0.3f, 0.0f, 0.0f));
        t3d.setRotation(new AxisAngle4f(0.0f, 0.0f, 0.0f, 0.0f));
        t3d.setScale(1.0);

        tg.setTransform(t3d);

        ObjectFile loader = new ObjectFile(ObjectFile.RESIZE);
        Scene s = null;

        File file = new java.io.File("src//semana21//lawMan.obj");

        try {
         s = loader.load(file.toURI().toURL());
        } catch (Exception e) {
         System.err.println(e);
         System.exit(1);
        }

        tg.addChild(s.getSceneGroup());

        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.compile();

        return objRoot;
    }
    
    private Light createLight() {
      DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f,
        1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));

      light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

      return light;
     }
}