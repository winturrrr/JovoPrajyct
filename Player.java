package com.tryagain.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    private String name;
    private float health;
    private float mana;
    private float damage;
    private float projectile_speed;
    private float speed;

    private Sprite sprite;
    private Direction direction = Direction.SOUTH;


    public Player(String name, float health, float mana, float damage, float speed, float projectile_speed) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.damage = damage;
        this.speed = speed;
        this.projectile_speed = projectile_speed;
        sprite = new Sprite(new Texture("badlogic.jpg"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getProjectile_speed() {
        return projectile_speed;
    }

    public void setProjectile_speed(float projectile_speed) {
        this.projectile_speed = projectile_speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setHealth(float health) {
        this.health = health;

    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Projectile attack() {
        return new Projectile(this, projectile_speed, damage);
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {

        this.sprite = sprite;
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void setWidth(float width) {
        sprite.setSize(width,sprite.getHeight());

        super.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        sprite.setSize(sprite.getWidth(),height);
        super.setHeight(height);
    }

    @Override
    public void setSize(float width, float height) {
        sprite.setSize(width,height);
        super.setSize(width, height);
    }
}
