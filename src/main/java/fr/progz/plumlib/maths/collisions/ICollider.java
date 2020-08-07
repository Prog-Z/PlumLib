package fr.progz.plumlib.maths.collisions;

import org.bukkit.entity.Player;

public interface ICollider {
    public void isColliding(Player p);

    public void execute(Player p);
}