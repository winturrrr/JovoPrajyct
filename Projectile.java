package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Projectile extends Actor {
    private Texture texture;
    private Sprite sprite;
    private Player player;
    private float speed;
    private float damage;

    public Projectile(Player player, float speed, float damage) {
        this.player = player;
        this.speed = speed;
        this.damage = damage;
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

    public void move(){
        if (player.getDirection().equals(Direction.NORTH))
            setY(getY() + speed);
        else if (player.getDirection().equals(Direction.SOUTH))
            setY(getY() - speed);
        else if (player.getDirection().equals(Direction.EAST))
            setX(getX() + speed);
        else if (player.getDirection().equals(Direction.WEST))
            setX(getX() - speed);
    }
}
