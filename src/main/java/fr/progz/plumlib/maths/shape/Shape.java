package fr.progz.plumlib.maths.shape;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import fr.progz.plumlib.maths.animator.Animator;
import fr.progz.plumlib.maths.animator.ShapeRunnable;
import fr.progz.plumlib.maths.utils.Referentiel;
import fr.progz.plumlib.maths.utils.Vec3;

public abstract class Shape {

    // #####################################################
    // SECTION Fields
    // #####################################################

    protected final ArrayList<Vec3> _basePoints = new ArrayList<>();
    protected final ArrayList<Vec3> _points = new ArrayList<>();
    protected Referentiel _ref = new Referentiel();
    protected ShapeRunnable _runnable;
    protected final Vec3 _offset = new Vec3(); 

    public void setReferentiel(Referentiel ref) {_ref = ref;}
    public final Referentiel getReferentiel() {return _ref;}
    public final Vec3 getOffset() {return _offset;}
    public final ArrayList<Vec3> getPoints() {return _points;}

    // !SECTION

    // #####################################################
    // SECTION Init
    // #####################################################
    public final void launch() {
        calcPoints();
        launchAnimation();
    }
    protected abstract void calcPoints();
    //!SECTION

    // #####################################################
    // SECTION Running
    // #####################################################
    public final void projectToOriginRef() {
        _points.clear();
        for (Vec3 pt : _basePoints) {
            Vec3 newPt = new Vec3();
            newPt.setX(pt.getX()*_ref.getAxeX().getX() + pt.getY()*_ref.getAxeY().getX() + pt.getZ()*_ref.getAxeZ().getX());
            newPt.setY(pt.getX()*_ref.getAxeX().getY() + pt.getY()*_ref.getAxeY().getY() + pt.getZ()*_ref.getAxeZ().getY());
            newPt.setZ(pt.getX()*_ref.getAxeX().getZ() + pt.getY()*_ref.getAxeY().getZ() + pt.getZ()*_ref.getAxeZ().getZ());
            _points.add(newPt);
        }
    }

    protected final void offsetAllPoints(ArrayList<Vec3> points) {for (Vec3 pts : points) offsetPoint(pts);}
    protected final void offsetPoint(Vec3 point) {point.add(_offset);point.add(_ref.getLocationVec());}

    public ArrayList<Vec3> getRealPoints() { 
        ArrayList<Vec3> clonedPoints = new ArrayList<>();
        for (Vec3 pt : _points) {
            Vec3 vec = new Vec3();
            vec.add(pt);
            clonedPoints.add(vec);
        }
        offsetAllPoints(clonedPoints);
        return clonedPoints;
    }
    // !SECTION

    // #####################################################
    // SECTION Animator
    // #####################################################

    public final void registerRunnable(@NotNull ShapeRunnable run) { _runnable = run; }

    public final void createAnimation(@NotNull Animator anim) { _runnable = new ShapeRunnable(this, anim); }

    protected boolean launchAnimation() {
        if (_runnable == null) return false;
        _runnable.runTaskTimerAsynchronously(_runnable.getAnim().getPlugin() , _runnable.getAnim().getDelay(), _runnable.getAnim().getRefreshTime());
        return true;
    }
    public final void stopAnimation() {
        if (_runnable != null) _runnable.cancel();
    }
    // !SECTION
}