package com.tryagain.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Projectile extends com.badlogic.gdx.scenes.scene2d.ui.Image {
    public static float cost = 10;
//    private Player player;
    Group enermyGroup;
    private float speed;
    private float acceleration;
    private float damage;
    private Direction direction;

    public Projectile(Player player, float speed, float acceleration, float damage, Group group) {
        super(new Texture(Gdx.files.internal("badlogic.jpg")));
//        this.player = player;
        this.speed = speed;
        this.damage = damage;
        this.acceleration = acceleration;
        this.direction = player.getDirection();
        this.setSize(player.getWidth()/5f,player.getHeight()/5f);
        enermyGroup = group;

        if (direction.equals(Direction.NORTH))
            setPosition(player.getX() + player.getWidth() / 2f - getWidth()/2f, player.getY() + player.getHeight()/2f);
        else if (direction.equals(Direction.SOUTH))
            setPosition(player.getX() + player.getWidth() / 2f - getWidth()/2f, player.getY());
        else if (direction.equals(Direction.EAST))
            setPosition(player.getX() + player.getWidth() , player.getY() + player.getHeight() / 2f - getHeight()/2f);
        else if (direction.equals(Direction.WEST))
            setPosition(player.getX(), player.getY() + player.getHeight() / 2f - getHeight()/2f);
    }



    public boolean checkCollision(){
        if( enermyGroup.hasChildren()){
            return false;
        }

        for (Actor player: enermyGroup.getChildren()){
            if (player.getX() < getX() + getWidth() && player.getY() + player.getHeight() > getY())
            {
                ((Player)player).setHealth(((Player)player).getHealth() - damage);
                return true;
            }

            else if (player.getX() + player.getWidth() > getX() && player.getY() < getY() + getHeight())
            {
                ((Player)player).setHealth(((Player)player).getHealth() - damage);
                return true;
            }

        }



        return false;
    }

    public void move(){
        if (direction.equals(Direction.NORTH)){
            setY(getY() + speed);

        }

        else if (direction.equals(Direction.SOUTH)){
            setY(getY() - speed);

        }
        else if (direction.equals(Direction.EAST)){
            setX(getX() + speed);

        }
        else if (direction.equals(Direction.WEST)){
            setX(getX() - speed);
        }
        speed += acceleration;

    }

    @Override
    public void act(float delta) {

        super.act(delta);
        move();
        checkCollision();
    }
}