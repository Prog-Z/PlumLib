package fr.plum.plumlib.maths.animator;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.plum.plumlib.maths.curves.Curve;
import fr.plum.plumlib.maths.utils.Rotator;

public final class Animator {

    private final Plugin _plug;
    private final Rotator _rot;
    private final Curve _mvnt;
    
    private final long _delay;
    private final long _refresh_time;
/**
 * 
 * @param plug the calling plugin 
 * @param rot describe the rotation
 * @param curve describe the path (translation)
 * @param delay
 * @param refresh_time
 */
    public Animator(@NotNull Plugin plug, @Nullable Rotator rot, @Nullable Curve curve, long delay, long refresh_time) {
        _plug = plug;
        _rot = rot;
        _mvnt = curve;

        _delay = delay;
        _refresh_time = refresh_time;
    }

    /**
     * @return the Plugin
     */
    public Plugin getPlugin() { return _plug; }

    /**
     * @return the Rotator
     */
    public Rotator getRotator() { return _rot; }

    /**
     * @return the Curve
     */
    public Curve getCurve() { return _mvnt; }

    /**
     * @return the delay before the animation start
     */
    public long getDelay() { return _delay; }

    /**
     * @return the time bewteen each execution in tick
     */
    public long getRefreshTime() { return _refresh_time; }
}