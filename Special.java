package com.tryagain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

public class Special extends Image {
    public static float cost = 20;
    private Player player;
    private float radius = 10f;
    private float damage;
    private float spawn_time;
    private float delay;
    private float cooldown;

    public Special(Player player, float target_x, float target_y, float damage, float delay, float cooldown) {
        super(new Texture(Gdx.files.internal("badlogic.jpg")));
        this.player = player;
        setPosition(target_x, target_y);
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

    @Override
    public void act(float delta) {
        if (System.currentTimeMillis() - spawn_time >= delay){

        }
        super.act(delta);
    }
}