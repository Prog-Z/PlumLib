package fr.progz.plumlib.maths.generator;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.progz.plumlib.maths.utils.Rotator;
import fr.progz.plumlib.maths.utils.Vec3;

public class ParticleGenerator extends ShapeGenerator<ParticleGenerator> {

    private class ParticleRunnable extends BukkitRunnable {

        private ArrayList<Vec3> pts;
        private World w;

        public ParticleRunnable(ArrayList<Vec3> pts, World w) {
            this.pts = pts;
            this.w = w;
        }
        
        public void run() {
            for (Vec3 pt : pts) {
               //w.spawnParticle(particule, new Location(w, pt.getX(), pt.getY(), pt.getZ()), 1,0,0,0);
               Particle.DustOptions opt = new Particle.DustOptions(Color.PURPLE, 1);
               w.spawnParticle(Particle.REDSTONE, new Location(w, pt.getX(), pt.getY(), pt.getZ()), 1,0,0,0, opt);
            }
        }
    }

    private JavaPlugin plug;
    private Particle particule;
    private ParticleRunnable runnable;
    private long refreshTime;

    public ParticleGenerator(JavaPlugin plug, Particle part, Rotator rot, Location loc, long refreshTime) {
        this.plug = plug;
        particule = part;
        this.setRotation(rot);
        this.setLocation(loc);
        runnable = new ParticleRunnable(_points, _loc.getWorld());
    }

    @Override
    protected void execute() {
        runnable.runTaskTimerAsynchronously(plug, 0L, refreshTime);
    }
    public void stop() {runnable.cancel();}

    
}