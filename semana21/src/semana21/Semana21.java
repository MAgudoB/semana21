package semana21;

import Characters.LawMan;
import Characters.Raptor;
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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import static semana21.Functions.getVector;

public class Semana21 extends MouseAdapter implements ActionListener, KeyListener {

    private int numLawMans = 3;
    private PickCanvas pickCanvas;
    private Timer timer;
    private Raptor raptor;
    private TransformGroup viewingTransform;
    private ArrayList<LawMan> lawMans = new ArrayList<LawMan>();
    private SimpleUniverse universe;
    private BranchGroup group;

    public Semana21() {
        Frame frame = new Frame("Jurasic World");
        timer = new Timer(100, this);

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        canvas.setSize(800, 800);
        universe = new SimpleUniverse(canvas);
        group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        raptor = createRaptor();
        group.addChild(raptor);

        for (int i = 0; i < numLawMans; i++) {
            Random rnd = new Random();
            LawMan newLawMan = createLawMan(rnd.nextFloat() * i * 10, rnd.nextFloat() * i * 10);
            lawMans.add(newLawMan);
            group.addChild(newLawMan);
        }

        
        group.addChild(createScenary());
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
        canvas.addKeyListener(this);

        frame.pack();
        frame.setVisible(true);
        timer.start();
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        Semana21 game = new Semana21();
    }

    public void mouseClicked(MouseEvent e) {
        pickCanvas.setShapeLocation(e);
        PickResult result = pickCanvas.pickClosest();

        if (result == null) {
            System.out.println("Nothing picked");
        } else {
            Primitive p = (Primitive) result.getNode(PickResult.PRIMITIVE);
            Shape3D s = (Shape3D) result.getNode(PickResult.SHAPE3D);

            if (p != null) {
                System.out.println(p.getClass().getName());
            } else if (s != null) {
                System.out.println(s.getClass().getName());
            } else {
                System.out.println("null");
            }
        }
    }

    private BranchGroup createScenary () {
    	BranchGroup objRoot = new BranchGroup();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        objRoot.setCapability(BranchGroup.ALLOW_DETACH);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        t3d.setTranslation(new Vector3f(-0.3f, 0.0f, 0.0f));
        t3d.setRotation(new AxisAngle4f(0.0f, 0.0f, 0.0f, 0.0f));
        t3d.setScale(1.0);

        tg.setTransform(t3d);

        ObjectFile loader = new ObjectFile(ObjectFile.RESIZE);
        Scene s = null;

        File file = new java.io.File("src//semana21//roundabout.obj");

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
    
    private Raptor createRaptor() {

        Raptor objRoot = new Raptor();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        objRoot.setCapability(BranchGroup.ALLOW_DETACH);
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

    private LawMan createLawMan(float x, float z) {
        LawMan objRoot = new LawMan();
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        objRoot.setCapability(BranchGroup.ALLOW_DETACH);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t3d.setTranslation(new Vector3f(x, 0.0f, z));
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
                1.0f, 1.0f), new Vector3f(0.3f, -10, 0));
        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

        return light;
    }

    private void moveCamera() {
        TransformGroup viewingTransformGroup;
        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        Transform3D viewingTransform = new Transform3D();

        Vector3d vector = getVector(raptor);
        Point3d point = new Point3d();
        point.x = vector.x;
        point.y = vector.y;
        point.z = vector.z;
        viewingTransform.lookAt(new Point3d(point.x, point.y + 1, point.z - 2), point,
                new Vector3d(0, 1, 0));

        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }

//Bucle
    @Override
    public void actionPerformed(ActionEvent ae) {
        raptor.move();
        moveCamera();
        boolean removeLawMan = false;
        LawMan toBeRemoved = null;
        for (LawMan lawMan : lawMans) {
            lawMan.move();
            if (Functions.raptorCapturetLawMan(raptor, lawMan)) {
                lawMan.detach();
                removeLawMan = true;
                toBeRemoved = lawMan;
            }
        }
        if (removeLawMan) {
            lawMans.remove(toBeRemoved);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    //Pulsar una tecla
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == 'a') {
            raptor.setTurnLeft(true);
            raptor.setTurnRight(false);
        }
        if (ke.getKeyChar() == 'w') {
            raptor.setGoFront(true);
            raptor.setGoBack(false);
        }
        if (ke.getKeyChar() == 's') {
            raptor.setGoFront(false);
            raptor.setGoBack(true);
        }
        if (ke.getKeyChar() == 'd') {
            raptor.setTurnLeft(false);
            raptor.setTurnRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyChar() == 'a') {
            raptor.setTurnLeft(false);
        }
        if (ke.getKeyChar() == 'w') {
            raptor.setGoFront(false);
        }
        if (ke.getKeyChar() == 's') {
            raptor.setGoBack(false);
        }
        if (ke.getKeyChar() == 'd') {
            raptor.setTurnRight(false);
        }
    }
}
