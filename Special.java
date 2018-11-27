package com.tryagain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

public class Special extends Image {
    public static float cost = 20;
//    private Player player;
    private float radius ;
    private Player player;

    private float damage;
    private long spawn_time;
    private float delay;
    private float cooldown;
    private Group enermyGroup;


    public Special( Player player, float target_x, float target_y, float damage, float delay, float cooldown, Group group) {
        super(new Texture("badlogic.jpg"));
        this.player = player;
        this.damage = damage;
        this.delay = delay;
        this.cooldown = cooldown;
        this.radius = player.getWidth()* 0.7f;
        this.setSize(radius*2,radius*2);
        this.isTouchable();
        this.spawn_time = System.currentTimeMillis();
        enermyGroup = group;
//        enermyGroup.addActor(player);
        setPosition(target_x - radius, target_y - radius);

    }

    public boolean checkCollision() {
        if (!enermyGroup.hasChildren()) {
            return false;
        }

        for (Actor player : enermyGroup.getChildren())
        {

            if(player.getX() + player.getWidth() >= getX() && player.getY() < getY() + getHeight()){
                return true;
            }
            if(getX() + getWidth() >= player.getX() && player.getY() + player.getHeight() > getX()){
                return true;
            }



        }
        return false;
    }

    @Override
    public void act(float delta) {
        if (System.currentTimeMillis() - spawn_time >= delay){
//            System.out.println(System.currentTimeMillis());
//            System.out.println(spawn_time);
            if(checkCollision()){

            }
            remove();
        }
        super.act(delta);
    }
}