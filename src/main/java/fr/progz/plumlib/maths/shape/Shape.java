package fr.progz.plumlib.maths.shape;

import java.util.ArrayList;

import fr.progz.plumlib.maths.utils.Referentiel;
import fr.progz.plumlib.maths.utils.Vec3;

public abstract class Shape {

    protected ArrayList<Vec3> _points = new ArrayList<>();
    protected Referentiel _ref = new Referentiel();

    public final void setReferentiel(Referentiel ref) {_ref = ref;}
    public abstract void calcPoints();
    public final void rotate() {
        for (Vec3 pt : _points) {
            pt.setX(pt.getX()*_ref.getAxeX().getX() + pt.getY()*_ref.getAxeY().getX() + pt.getZ()*_ref.getAxeZ().getX());
            pt.setY(pt.getX()*_ref.getAxeX().getY() + pt.getY()*_ref.getAxeY().getY() + pt.getZ()*_ref.getAxeZ().getY());
            pt.setZ(pt.getX()*_ref.getAxeX().getZ() + pt.getY()*_ref.getAxeY().getZ() + pt.getZ()*_ref.getAxeZ().getZ());
        }
    }

    public final ArrayList<Vec3> getPoints() {return _points;}
    @SuppressWarnings("unchecked")
    public final ArrayList<Vec3> getCopiedPoints() { return (ArrayList<Vec3>) _points.clone(); }
    
}