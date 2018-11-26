package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Projectile extends com.badlogic.gdx.scenes.scene2d.ui.Image {
    public static float cost = 10;
    private Player player;
    private float speed;
    private float acceleration;
    private float damage;

    public Projectile(Player player, float speed, float acceleration, float damage) {
        super(new Texture(Gdx.files.internal("badlogic.jpg")));
        this.player = player;
        this.speed = speed;
        this.damage = damage;
        this.acceleration = acceleration;

        if (player.getDirection().equals(Direction.NORTH))
            setPosition((player.getX() + player.getWidth()) / 2, player.getY() + player.getHeight() + 10);
        else if (player.getDirection().equals(Direction.SOUTH))
            setPosition((player.getX() + player.getWidth()) / 2, player.getY() - 10);
        else if (player.getDirection().equals(Direction.EAST))
            setPosition(player.getX() + player.getWidth() + 10, (player.getY() + player.getHeight()) / 2);
        else if (player.getDirection().equals(Direction.WEST))
            setPosition(player.getX() - 10, (player.getY() + player.getHeight()) / 2);
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
        if (player.getDirection().equals(Direction.NORTH)){
            setY(getY() + speed);

        }

        else if (player.getDirection().equals(Direction.SOUTH)){
            setY(getY() - speed);

        }
        else if (player.getDirection().equals(Direction.EAST)){
            setX(getX() + speed);

        }
        else if (player.getDirection().equals(Direction.WEST)){
            setX(getX() - speed);
        }
        speed += acceleration;

    }

    @Override
    public void act(float delta) {
        move();
        super.act(delta);
    }
}
