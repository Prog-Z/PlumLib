package fr.progz.plumlib.maths.generator;

import java.util.ArrayList;

import org.bukkit.Location;

import fr.progz.plumlib.maths.shape.Shape;
import fr.progz.plumlib.maths.utils.Vec3;
/**
 * Wrapper for geometrical calcul
 */
public class ShapeGenerator {

    protected ArrayList<Vec3> _points = new ArrayList<>();
    protected ArrayList<Shape> _shapesGroups = new ArrayList<>();
    protected Location _loc;

    // #####################################################
    // SECTION Parameter
    // #####################################################
    public final ShapeGenerator addShape(Shape shape) {
        _shapesGroups.add(shape);
        return this;
    }
    // !SECTION

    // #####################################################
    // SECTION Calc
    // #####################################################
    public final void launch() {
        for (Shape s : _shapesGroups) s.launch();
    }
    // !SECTION
}