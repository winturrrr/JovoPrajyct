package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Enemy extends Image {
    protected float health;
    protected float damage;
    protected float speed;
    protected Player player;
//    private Sprite sprite;

    public Enemy(String file_path, float health, float damage, float speed, Player player) {
        super(new Texture(Gdx.files.internal(file_path)));
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.player = player;
//        this.sprite = new Sprite(new Texture(Gdx.files.internal(file_path)));
    }

    public void move(){
        System.out.println("This is an abstract class");
    }

    public void attack(){
        System.out.println("This is an abstract class");
    }
}
