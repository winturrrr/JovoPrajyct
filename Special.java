package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

public class Special extends Actor {
    public static float cost = 20;
    private Player player;
    private float sides;
    private float damage;
    private float delay;
    private float cooldown;

    public Special(Player player, float sides, float damage, float delay, float cooldown) {
        this.player = player;
        this.sides = sides;
        this.damage = damage;
        this.delay = delay;
        this.cooldown = cooldown;
    }

    public boolean checkCollision(Player player){
        if (player.getX() < getX() + getWidth() && player.getY() + player.getHeight() > getY())
            if (!player.equals(this.player)){
                player.setHealth(player.getHealth() - damage);
                return true;
            }
            else if (player.getX() + player.getWidth() > getX() && player.getY() < getY() + getHeight())
                if (!player.equals(this.player)) {
                    player.setHealth(player.getHealth() - damage);
                    return true;
                }
        return false;
    }
}
