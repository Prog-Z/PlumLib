package fr.progz.plumlib.maths.generator;

import java.util.ArrayList;

import org.bukkit.Location;

import fr.progz.plumlib.maths.shape.Shape;
import fr.progz.plumlib.maths.utils.Referentiel;
import fr.progz.plumlib.maths.utils.Rotator;
import fr.progz.plumlib.maths.utils.Vec3;

public abstract class ShapeGenerator<T extends ShapeGenerator<T>> {

    protected ArrayList<Shape> _shapes = new ArrayList<>();
    protected Location _loc;
    protected Referentiel _ref = new Referentiel();

    protected ArrayList<Vec3> _points = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private T getThis() {return (T) this;}

    // #####################################################
    // SECTION Parameter
    // #####################################################
    public final T addShape(Shape s) {
        s.setReferentiel(_ref);
        _shapes.add(s);
        return getThis();
    }

    public final T setRotation(Rotator rot) {
        _ref.rotate(rot);
        return getThis();
    }

    public final T setLocation(Location loc) {
        _loc = loc;
        return getThis();
    }

    // !SECTION

    // #####################################################
    // SECTION Calc
    // #####################################################
    public final void calcPoints() {
        for (Shape s : _shapes) {
            s.calcPoints();
            s.rotate();
        }
    }
    public final void calc() {
        // Copy & translate points
        for (Shape s : _shapes) _points.addAll(s.getCopiedPoints());
        for (Vec3 pt : _points) pt.add(_loc.getX(),_loc.getY(), _loc.getZ());

        execute();
    }

    protected abstract void execute();
    // !SECTION
}