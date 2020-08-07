package fr.progz.plumlib.maths.collisions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.progz.plumlib.maths.utils.Pos;

public abstract class CollideBox implements ICollider {

    protected String world_name;
    protected Pos posA;
    protected Pos posB;

    public CollideBox(String wname, Pos posA, Pos posB) {
        this.world_name = wname; this.posA = posA; this.posB = posB;
    }

    @Override
    public void isColliding(Player p) {
        Location loc = p.getLocation();
        if (!(posA.getX()<=loc.getX() && loc.getX()<=posB.getX()) && !(posB.getX()<=loc.getX() && loc.getX()<=posA.getX())) return;
        if (!(posA.getY()<=loc.getY() && loc.getY()<=posB.getY()) && !(posB.getY()<=loc.getY() && loc.getY()<=posA.getY())) return;
        if (!(posA.getZ()<=loc.getZ() && loc.getZ()<=posB.getZ()) && !(posB.getZ()<=loc.getZ() && loc.getZ()<=posA.getZ())) return;
        this.execute(p);
    }

    public String getWorldName() {return this.world_name;}
}