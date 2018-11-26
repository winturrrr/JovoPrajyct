package com.tryagain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyStage extends Stage  implements InputProcessor {

    private boolean moving;
    private Vector2 pos;
    private boolean temp;

    private float zoomWidth;
    private float zoomHeight;
    private float mapWidth;
    private float mapHeight;

    private Player player;
    public float bar_heightMod = 35;
    public Image hp_bar;
    public Image mp_bar;
    public Image hp_red;
    public Image mp_red;

    private int touchpadIndex = -1;
    private Touchpad touchpad;

    private BitmapFont font;
    private TextButton.TextButtonStyle tb;
    private TextButton teleportButton;
    private TextButton specialButton;
    private TextButton attackButton;

    private Vector2 textbuttonPosition;

    private Vector2 move;
    private float speedMod = 2;

    private Image bg;
    private Image arena;

    float CAM_WIDTH_ZOOM = 4;
    float CAM_HEIGHT_ZOOM = 3;
    float CAM_WIDTH;
    float CAM_HEIGHT;

    int projectile_num = 0;



    public MyStage(Viewport viewport) {

        super(viewport);

        this.moving = false;
        this.pos = new Vector2();

        bg = new Image(new Texture("bg.png"));
        arena = new Image(new Texture("tate-grassland.png"));
        CAM_HEIGHT = arena.getImageHeight()/CAM_HEIGHT_ZOOM ;
        CAM_WIDTH = arena.getImageWidth()/CAM_WIDTH_ZOOM;

        bg.setSize(arena.getWidth()*2f, arena.getHeight()*2f);
        bg.setPosition(-arena.getWidth()/2f,-arena.getHeight()/2f);

        bg.setName("bg");
        arena.setName("arena");
        this.addActor(bg);
        this.addActor(arena);

        //touchpad initialize
        Skin skin = new Skin();
        skin.add("background",new Texture("touchpad_background.png"));
        skin.add("knob",new Texture("touchpad_knob.png"));
        Touchpad.TouchpadStyle tp = new Touchpad.TouchpadStyle();
        tp.background = skin.getDrawable("background");
        tp.knob = skin.getDrawable("knob");
        touchpad = new Touchpad(0,tp);
        touchpad.setName("touchpad");
        touchpad.setSize(getCamera().viewportWidth/5f,getCamera().viewportWidth/5f);
        touchpad.getStyle().knob.setMinWidth(touchpad.getWidth()/2f);
        touchpad.getStyle().knob.setMinHeight(touchpad.getHeight()/2f);

        this.addActor(touchpad);

        //buttons initialize
        font = new BitmapFont(Gdx.files.internal("font1.fnt"));
        tb = new TextButton.TextButtonStyle();
        tb.font = font;
        font.getData().setScale(0.3f);
        font.setUseIntegerPositions(false);
        tb.up = skin.getDrawable("background");
        tb.down = skin.getDrawable("knob");

        teleportButton = new TextButton("B",tb);
        attackButton = new TextButton("A",tb);
        specialButton = new TextButton("S",tb);

//        textButton.setWidth(touchpad.getWidth());
//        textButton.setHeight(touchpad.getHeight());

        attackButton.setSize(touchpad.getWidth(),touchpad.getHeight());
        textbuttonPosition = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        textbuttonPosition = screenToStageCoordinates(textbuttonPosition);
        attackButton.setPosition(textbuttonPosition.x - attackButton.getWidth(),textbuttonPosition.y);

        this.addActor(attackButton);

        teleportButton.setSize(touchpad.getWidth()*2/3f,touchpad.getHeight()*2/3f);
//        textbuttonPosition = new Vector2(Gdx.graphics.getWidth()*6f/7, Gdx.graphics.getHeight() * 3f/4);
//        textbuttonPosition = screenToStageCoordinates(textbuttonPosition);
        teleportButton.setPosition(attackButton.getX() + attackButton.getWidth()/2f - teleportButton.getWidth()/2f,attackButton.getY()+attackButton.getHeight());

        this.addActor(teleportButton);

        specialButton.setSize(touchpad.getWidth()*2/3f,touchpad.getHeight()*2/3f);
//        textbuttonPosition = new Vector2(Gdx.graphics.getWidth()*7f/10, Gdx.graphics.getHeight());
//        textbuttonPosition = screenToStageCoordinates(textbuttonPosition);
        specialButton.setPosition(attackButton.getX() - specialButton.getWidth(),attackButton.getY() +attackButton.getHeight()/2f - specialButton.getHeight()/2f);

        this.addActor(specialButton);

        player = new Player("Player1",100f,100f,0.3f,10f,3f);
        player.setSize(getCamera().viewportWidth/5f,getCamera().viewportHeight/4f);
        player.setPosition(getCamera().position.x - player.getWidth()/2f,getCamera().position.y - player.getHeight()/2f);
        move = new Vector2(0,0);

//        player.setPosition(getCamera().position.x ,getCamera().position.y);
//        player.setPosition(0,0);

        //player hp mp initialize
        this.addActor(player);
        player.setTeleport_distace();

        hp_bar = new Image(new Texture("health_green.png"));
        hp_bar.setSize(this.getCamera().viewportWidth,this.getCamera().viewportHeight/bar_heightMod);
        hp_bar.setPosition(0,this.getCamera().viewportHeight - hp_bar.getHeight());


        mp_bar = new Image(new Texture("mana_blue.png"));
        mp_bar.setSize(this.getCamera().viewportWidth,this.getCamera().viewportHeight/bar_heightMod);
        mp_bar.setPosition(0,this.getCamera().viewportHeight - hp_bar.getHeight()*2);

        hp_red = new Image(new Texture("health_red.png"));
        hp_red.setSize(this.getCamera().viewportWidth,this.getCamera().viewportHeight/bar_heightMod);
        hp_red.setPosition(0,this.getCamera().viewportHeight - hp_bar.getHeight());

        mp_red = new Image(new Texture("health_red.png"));
        mp_red.setSize(this.getCamera().viewportWidth,this.getCamera().viewportHeight/bar_heightMod);
        mp_red.setPosition(0,this.getCamera().viewportHeight - hp_bar.getHeight()*2);

        this.addActor(hp_red);
        this.addActor(mp_red);

        this.addActor(hp_bar);
        this.addActor(mp_bar);
    }

    //touch
    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }

    //american football
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

//        pos.set(screenX,screenY);
//        pos = this.screenToStageCoordinates(pos);

        //actor select
        Vector2 temp = screenToStageCoordinates(new Vector2(screenX,screenY));
        Actor actor = hit(temp.x,temp.y,false);

        //set touchpad
        if( actor == null || actor.getName() == "bg" || actor.getName() == "arena" || actor.getName() == "touchpad"){
            pos.set(screenX,screenY);
            pos = this.screenToStageCoordinates(pos);

            moving = true;
            touchpadIndex = pointer;
            touchpad.setColor(touchpad.getColor().r,touchpad.getColor().g,touchpad.getColor().b, 1);
            touchpad.setPosition(pos.x - touchpad.getWidth()/2f,pos.y - touchpad.getHeight()/2f);
        }

        //ultra american football
        boolean bool =  super.touchDown(screenX, screenY, pointer, button);

        //read, dumb bitch
        if(teleportButton.isPressed()){
            player.teleport(move);

            if (player.getX() + move.x <= 0){
                move.x = -player.getX();
            }
            if (player.getY() + move.y <= 0){
                move.y = -player.getY();
            }
            if(player.getX() + move.x >= mapWidth - player.getWidth()){
                move.x = mapWidth - player.getX() - player.getWidth();
            }
            if(player.getY() + move.y >= mapHeight - player.getHeight()){
                move.y = mapHeight - player.getY() - player.getHeight();
            }
        }

        //read, dumb bitch
        if(attackButton.isPressed() && player.attakable())
        {
                addActor(player.attack());
                projectile_num += 1;
        }
        return bool;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        temp = super.touchDragged(screenX, screenY, pointer);
        return temp;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        temp =  super.touchUp(screenX, screenY, pointer, button);
        if(moving == true && pointer == touchpadIndex){
            moving = false;
            touchpadIndex = -1;
            touchpad.setColor(touchpad.getColor().r,touchpad.getColor().g,touchpad.getColor().b,0);
        }
        return temp;
    }

    @Override
    public void draw() {
        //touchpad direction (flips and shits)
        if(moving == true)
        {
            float x = speedMod,y = speedMod;

            x *= touchpad.getKnobPercentX();
            y *= touchpad.getKnobPercentY();
            if(Math.abs(x) > Math.abs(y) )
            {
                if(x > 0){
//                    if(player.getDirection() != Direction.EAST)
                    if(player.getDirection() != Direction.EAST){
                        player.flip();
                    }
                    player.setDirection(Direction.EAST);
                }
                else{
                    if(player.getDirection() != Direction.WEST) {
                        player.flip();
                    }
                    player.setDirection(Direction.WEST);
                }
            }
            else{
                if(y >0){
                    player.setDirection(Direction.NORTH);
                }
                else{
                    player.setDirection(Direction.SOUTH);
                }
            }

            //borders
            if (x < 0 && player.getX() <= 0){
                x = 0;
            }
            if (y < 0 && player.getY() <= 0){
                y = 0;
            }
            if( x > 0 &&  player.getX() >= mapWidth - player.getWidth()){
                x = 0;
            }
            if( y > 0 && player.getY() >= mapHeight - player.getHeight()){
                y = 0;
            }

            //everything jinga bell that moves with screen
            move.x += x;
            move.y += y;

            //touchpad
            pos.x += move.x;
            pos.y += move.y;
            touchpad.setPosition(pos.x - touchpad.getWidth()/2f ,pos.y - touchpad.getHeight()/2f );

//            mp_bar.setSize(mp_bar.getWidth()-1,mp_bar.getHeight());
//            player.setMana(player.getMana() - 1);
        }

        //ui position
        player.setPosition(player.getX() + move.x,player.getY() + move.y);
        ((OrthographicCamera)this.getCamera()).position.x = player.getX() + player.getWidth()/2f;
        ((OrthographicCamera)this.getCamera()).position.y = player.getY() + player.getHeight()/2f;

        teleportButton.setPosition(teleportButton.getX() + move.x, teleportButton.getY() + move.y);
        attackButton.setPosition(attackButton.getX() + move.x, attackButton.getY() + move.y);
            specialButton.setPosition(specialButton.getX() + move.x, specialButton.getY() + move.y);
        hp_bar.setPosition(hp_bar.getX() + move.x, hp_bar.getY() + move.y);
        mp_bar.setPosition(mp_bar.getX() + move.x, mp_bar.getY() + move.y);

        hp_red.setPosition(hp_bar.getX(), hp_bar.getY());
        mp_red.setPosition(mp_bar.getX(), mp_bar.getY() );
        mp_bar.setSize(player.getMana()/player.getMana_cap() * this.getCamera().viewportWidth, mp_bar.getHeight());

        hp_bar.setZIndex(10 + projectile_num);
        hp_red.setZIndex(hp_bar.getZIndex()-1);

        mp_bar.setZIndex(10 + projectile_num);
        mp_red.setZIndex(mp_bar.getZIndex()-1);

        move.x = 0;
        move.y = 0;
        player.regen_mana();

        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        player.getSprite().getTexture().dispose();
    }

    public void setZoom(float width, float height){
        zoomWidth = width;
        zoomHeight = height;
        mapHeight = this.getCamera().viewportHeight * zoomHeight;
        mapWidth = this.getCamera().viewportWidth * zoomWidth;
    }

}
