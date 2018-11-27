package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Player extends Actor {

    private String player_name;
    private float health;
    private float mana_cap;
    private float mana;
    private float damage;
    private float speed;
    private float mana_regen;

    private float projectile_speed = 2f;
    private float projectile_acceleration = 0.2f;
    private int projectile_fired = 0;

    private float special_size = 5f;
    private float special_cooldown = 3000f;
    private float special_delay = 1000f;
    private float special_last_casted = 0f;

    private Direction direction = Direction.SOUTH;

    private Sprite sprite;

    private float teleport_distace;

    private Group enermyGrouop;

    Texture img,img2,img3,img4,img5_1,img5_2,img5_3,img5_4;
    TextureRegion[] mage_run,mage_atk,melee_up,melee_down,melee_left,melee_right;
    TextureRegion[] wiz_atk,wiz_die,wiz_fly,wiz_idle;
    Animation mage_runAni,mage_atkAni,melee_upAni,melee_downAni,melee_leftAni,melee_rightAni;
    Animation wiz_atkAni,wiz_dieAni,wiz_flyAni,wiz_idleAni;
    float elaspedTime;
    Sprite mage_runSp,mage_atkSp,melee_upSp,melee_downSp,melee_leftSp,melee_rightSp;//sprite,sprite2
    Sprite wiz_atkSp,wiz_dieSp,wiz_flySp,wiz_idleSp;


    public Player(String name, float health, float mana_cap, float mana_regen, float damage, float speed) {
        enermyGrouop = new Group();
        this.player_name = name;
        this.health = health;
        this.mana_cap = mana_cap;
        this.mana = mana_cap;
        this.mana_regen = mana_regen;
        this.damage = damage;
        this.speed = speed;
        sprite = new Sprite(new Texture("badlogic.jpg"));

        img2 = new Texture("mage_walk.png");

        TextureRegion[][] tempFrame2 = TextureRegion.split(img2,77,50);
        mage_run = new TextureRegion[10];

        int index_mage_run = 0;
        int index_r_mage_run = 9;


        for(int i = 0;i<1;i++){
            for (int j = 0;j<10;j++){
                mage_runSp = new Sprite(tempFrame2[i][j]);
                mage_run[index_mage_run++] = mage_runSp;
            }
        }
    }
    public void setTeleport_distace(){
        teleport_distace = this.getStage().screenToStageCoordinates(new Vector2(Gdx.graphics.getWidth()/2f,0f)).x;

    }

    public void addEnermy(Actor enermy){
        enermyGrouop.addActor(enermy);
    }

    public float getMana_cap() {
        return mana_cap;
    }

    public void setMana_cap(float mana_cap) {
        this.mana_cap = mana_cap;
    }

    public String getPlayerName() {
        return player_name;
    }

    public void setPlayerName(String name) {
        this.player_name = name;
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

    public float get_mana_regen() {
        return mana_regen;
    }

    public void set_mana_regen(float mana_regen) {
        this.mana_regen = mana_regen;
    }

    public void regen_mana(){
        if (mana < mana_cap)
            mana = Math.min(mana + mana_regen, mana_cap);
    }

    public Projectile attack() {
        if (mana > Projectile.cost){
            mana -= Projectile.cost;
            projectile_fired = 1;
            return new Projectile(this, projectile_speed, projectile_acceleration, damage, enermyGrouop);
        }
        return null;
    }
    public boolean attakable(){
        return mana > Projectile.cost;
    }

//    public Special special(){
//        if (mana > Special.cost && Math.min(System.currentTimeMillis() - special_last_casted, special_cooldown) >= special_cooldown){
//            mana -= Special.cost;
//            special_last_casted = System.currentTimeMillis();
//            return new Special(this,getX()-1,getY()-1, damage * 4, special_delay, special_cooldown);
//        }
//        return null;
//    }

    public void teleport(Vector2 pos){
        if(mana < 20)
            return;
        mana -= 20;
        float x,y;
        if(direction == Direction.NORTH){
            x = 0;
            y = teleport_distace;
        }
        else if(direction == Direction.EAST){
            x = teleport_distace;
            y = 0;
        }
        else if(direction == Direction.SOUTH){
            x = 0;
            y = -teleport_distace;
        }
        else {
            x = -teleport_distace;
            y = 0;
        }

        pos.x += x;
        pos.y += y;

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {

        this.sprite = sprite;
    }

    public void flip(){
        sprite.flip(true   ,false   );

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

    @Override
    public void act(float delta) {
        projectile_fired = 0;
        super.act(delta);
    }

    public int get_projectile_fired(){
        return projectile_fired;
    }
}

/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {

    private String player_name;
    private float health;
    private float mana_cap;
    private float mana;
    private float damage;
    private float speed;
    private float mana_regen;

    private float projectile_speed = 3f;

    private float special_size = 5f;
    private float special_cooldown = 3000f;
    private float special_delay = 1000f;
    private float special_last_casted = 0f;

    private Direction direction = Direction.SOUTH;

    private Sprite sprite,sprite2;

    private float teleport_distace;

    Texture img,img2,img3,img4,img5_1,img5_2,img5_3,img5_4;
    TextureRegion[] mage_run,mage_atk,melee_up,melee_down,melee_left,melee_right;
    TextureRegion[] wiz_atk,wiz_die,wiz_fly,wiz_idle;
    Animation mage_runAni,mage_atkAni,melee_upAni,melee_downAni,melee_leftAni,melee_rightAni;
    Animation wiz_atkAni,wiz_dieAni,wiz_flyAni,wiz_idleAni;
    float elaspedTime;
    Sprite mage_runSp,mage_atkSp,melee_upSp,melee_downSp,melee_leftSp,melee_rightSp;//sprite,sprite2
    Sprite wiz_atkSp,wiz_dieSp,wiz_flySp,wiz_idleSp;

    public Player(String name, float health, float mana_cap, float mana_regen, float damage, float speed, float projectile_speed) {
        this.player_name = name;
        this.health = health;
        this.mana_cap = mana_cap;
        this.mana = mana_cap;
        this.mana_regen = mana_regen;
        this.damage = damage;
        this.speed = speed;
        this.projectile_speed = projectile_speed;
        sprite = new Sprite(new Texture("mage_float.png")) ;




        img2 = new Texture("mage_atk.png");
        img3 = new Texture("r_mage_atk.png");

        TextureRegion[][] tempFrame2 = TextureRegion.split(img2,77,50);
        TextureRegion[][] tempFrame3 = TextureRegion.split(img3,77,50);
        mage_run = new TextureRegion[10];

        int index_mage_run = 0;
        int index_r_mage_run = 9;


        for(int i = 0;i<1;i++){
            for (int j = 0;j<10;j++){
                mage_runSp = new Sprite(tempFrame2[i][j]);
                mage_run[index_mage_run++] = mage_runSp;
            }
        }

//        for(int i = 0;i<1;i++){
//            for (int j = 0;j<9;j++){
//                mage_atkSp = new Sprite(tempFrame2[i][j]);
//                mage_atk[index_r_mage_run--] = mage_atkSp;
//            }
//        }

        mage_runAni = new Animation(1f/15f,mage_run);
        //mage_atkAni = new Animation(1f/15f,mage_run);

    }
    public void setTeleport_distace(){
        teleport_distace = this.getStage().screenToStageCoordinates(new Vector2(Gdx.graphics.getWidth()/2f,0f)).x;

    }
    public float getMana_cap() {
        return mana_cap;
    }

    public void setMana_cap(float mana_cap) {
        this.mana_cap = mana_cap;
    }

    public String getPlayerName() {
        return player_name;
    }

    public void setPlayerName(String name) {
        this.player_name = name;
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

    public float get_mana_regen() {
        return mana_regen;
    }

    public void set_mana_regen(float mana_regen) {
        this.mana_regen = mana_regen;
    }

    public void regen_mana(){
        if (mana < mana_cap)
            mana = Math.min(mana + mana_regen, mana_cap);
    }

    public Projectile attack() {
        if (mana > Projectile.cost){
            mana -= Projectile.cost;
            return new Projectile(this, projectile_speed, damage);
        }
        return null;
    }

    public Special special(){
        if (mana > Special.cost && Math.min(System.currentTimeMillis() - special_last_casted, special_cooldown) >= special_cooldown){
            mana -= Special.cost;
            special_last_casted = System.currentTimeMillis();
            return new Special(this, special_size, damage * 4, special_delay, special_cooldown);
        }
        return null;
    }

    public void teleport(Vector2 pos){
        if(mana < 20)
            return;
        mana -= 20;
        float x,y;
        if(direction == Direction.NORTH){
            x = 0;
            y = teleport_distace;
        }
        else if(direction == Direction.EAST){
            x = teleport_distace;
            y = 0;
        }
        else if(direction == Direction.SOUTH){
            x = 0;
            y = -teleport_distace;
        }
        else {
            x = -teleport_distace;
            y = 0;
        }

        pos.x += x;
        pos.y += y;

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void flip(){
        this.sprite.flip(true,false);
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
        elaspedTime += Gdx.graphics.getDeltaTime();
        //sprite.draw(batch);
        batch.draw((TextureRegion)mage_runAni.getKeyFrame(elaspedTime,false),getX(),getY());
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

}*/