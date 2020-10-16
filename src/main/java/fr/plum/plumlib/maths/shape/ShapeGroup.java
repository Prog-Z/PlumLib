package fr.plum.plumlib.maths.shape;

import java.util.ArrayList;

import fr.plum.plumlib.maths.utils.Referentiel;
import fr.plum.plumlib.maths.utils.Vec3;

/**
 * Wrapper for multiple shapes wich have a common referentiel & runnable 
 */
public class ShapeGroup extends Shape {

    // #####################################################
    // SECTION Fields
    // #####################################################
    protected final ArrayList<Shape> _shapes = new ArrayList<>();

    public final void setReferentiel(Referentiel ref) {
        this._ref = ref;
        for (Shape s : _shapes) s.setReferentiel(this._ref);
    }

    /** Get the stored shapes */
    public final ArrayList<Shape> getShapes() {return _shapes;}
    // !SECTION


    // #####################################################
    // SECTION Init
    // #####################################################
    /**
     * Add a shape into the group
     * @param s the shape to add
     */
    public final void addShape(Shape s) {
        s.setReferentiel(_ref);
        _shapes.add(s);
    }

    /**
     * Launch the calculation of the base points of the base
     */
    protected final void calcPoints() {
        for (Shape s : _shapes) {
            s.calcPoints();
            s.projectToOriginRef();
        }
    }
    // !SECTION


    // #####################################################
    // SECTION Run
    // #####################################################
    /**
     * Get the a copy of the points
     */
    @Override
    public final ArrayList<Vec3> getRealPoints() {
        ArrayList<Vec3> list = new ArrayList<Vec3>();
        for (Shape s : _shapes)
            list.addAll(s.getRealPoints());
        offsetAllPoints(list);
        return list;
    }

    // !SECTION

    // #####################################################
    //
    //                  SECTION Animator
    //
    // #####################################################

    /** Launch the runnables (group & children shape) */
    @Override
    protected final boolean launchAnimation() { return launchSelfAnim() && launchChildAnim(); }

    private boolean launchSelfAnim() {
        if (_runnable == null) return false;
        _runnable.runTaskTimerAsynchronously(_runnable.getAnim().getPlugin() , _runnable.getAnim().getDelay(), _runnable.getAnim().getRefreshTime());
        return true;
    }
    private boolean launchChildAnim() {
        boolean b = true;
        for (Shape s : _shapes) b = b && s.launchAnimation();
        return b;
    }
    // !SECTION
}