package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    private String name;
    private float health;
    private float mana;
    private float damage;
    private float projectile_speed;
    private float speed;
    private Direction direction = Direction.SOUTH;


    public Player(String name, float health, float mana, float damage, float speed, float projectile_speed) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.damage = damage;
        this.speed = speed;
        this.projectile_speed = projectile_speed;
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
}
