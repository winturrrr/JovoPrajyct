package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Goblin extends Enemy {
    public Goblin(float health, float damage, float speed, Player player) {
        super("badlogic.jpg", health, damage, speed, player);
    }

    @Override
    public void act(float delta) {
        move();
        attack();
        super.act(delta);
    }

    @Override
    public void move(){
        if (player.getX() < getX())
            setX(getX() - speed);
        else
            setX(getX() + speed);
        if (player.getY() < getY())
            setY(getY() - speed);
        else
            setY(getY() + speed);
    }

    @Override
    public void attack() {
        if (player.getX() < getX() + getWidth() && player.getY() + player.getHeight() > getY())
        {
            ((Player)player).setHealth(((Player)player).getHealth() - damage);
            remove();
        }

        else if (player.getX() + player.getWidth() > getX() && player.getY() < getY() + getHeight())
        {
            ((Player)player).setHealth(((Player)player).getHealth() - damage);
            remove();
        }
    }
}
