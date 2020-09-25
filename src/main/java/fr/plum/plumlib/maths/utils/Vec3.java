package fr.plum.plumlib.maths.utils;

import org.bukkit.Location;

public final class Vec3 {
    private double x,y,z;

    public Vec3(double x, double y, double z) { this.set(x,y,z); }
    public Vec3() {this.set(0,0,0);}
    public Vec3(Location loc) {this.set(loc.getX(),loc.getY(), loc.getZ());}

    public final double getX() {return this.x;}
    public final double getY() {return this.y;}
    public final double getZ() {return this.z;}

    public final void setX(double x) {this.x = x;}
    public final void setY(double y) {this.y = y;}
    public final void setZ(double z) {this.z = z;}
    public final void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final void addX(double x) {this.x += x;}
    public final void addY(double y) {this.y += y;}
    public final void addZ(double z) {this.z += z;}
    public final void add(double x, double y, double z) { addX(x); addY(y); addZ(z); }
    public final void add(Vec3 point) {addX(point.getX()); addY(point.getY()); addZ(point.getZ());}

    public final double getSquaredLength() { return x*x + y*y + z*z; }
    public final double getLength() { return Math.sqrt(getSquaredLength()); }

    public final String toString() {return "Vec("+Double.valueOf(x)+","+Double.valueOf(y)+","+Double.valueOf(z)+")";}
}