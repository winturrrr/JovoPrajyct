package com.tryagain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyStage extends Stage  implements InputProcessor {

    private boolean moving;
    private Vector2 pos;
    private int touchpadIndex = -1;
    private Touchpad touchpad;
    private boolean temp;
    private Sprite map;

    private float zoomWidth;
    private float zoomHeight;

    private Player player;

    public MyStage(Viewport viewport) {
        super(viewport);
        this.moving = false;
        this.pos = new Vector2();

        Skin skin = new Skin();
        skin.add("shit",new Texture("badlogic.jpg"));
        skin.add("shittier",new Texture("Sprite.png"));
        Touchpad.TouchpadStyle tp = new Touchpad.TouchpadStyle();
        tp.background = skin.getDrawable("shit");
        tp.knob = skin.getDrawable("shittier");
        touchpad = new Touchpad(0,tp);
        touchpad.setName("touchpad");
        touchpad.setSize(getCamera().viewportWidth/5f,getCamera().viewportWidth/5f);
        touchpad.getStyle().knob.setMinWidth(touchpad.getWidth()/3f);
        touchpad.getStyle().knob.setMinHeight(touchpad.getHeight()/3f);


        this.addActor(touchpad);

        player = new Player("Player1",100f,100f,10f,3f,6);
        player.setSize(getCamera().viewportWidth/4f,getCamera().viewportHeight/3f);
        player.setPosition(getCamera().position.x - player.getWidth()/2f,getCamera().position.y - player.getHeight()/2f);

//        player.setPosition(getCamera().position.x ,getCamera().position.y);
//        player.setPosition(0,0);

        this.addActor(player);


    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {

        return super.hit(stageX, stageY, touchable);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenX,screenY);
        pos = this.screenToStageCoordinates(pos);
        Actor actor = hit(pos.x,pos.y,false);

        moving = true;
        touchpadIndex = pointer;

        touchpad.setPosition(pos.x - touchpad.getWidth()/2f,pos.y - touchpad.getHeight()/2f);


        temp = super.touchDown(screenX, screenY, pointer, button);
        return temp;

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
        }
        return temp;
    }

    @Override
    public void draw() {

        if(moving == true)
        {
            float x = 2,y = 2;

            x *= touchpad.getKnobPercentX();
            y *= touchpad.getKnobPercentY();

            if (x < 0 && this.getCamera().position.x < this.getCamera().viewportWidth/2f){
                x = 0;
            }
            if (y < 0 && this.getCamera().position.y < this.getCamera().viewportHeight/2f){
                y = 0;
            }

            if( x > 0 &&  this.getCamera().position.x >= this.getCamera().viewportWidth * zoomWidth - this.getCamera().viewportWidth/2f){
                x = 0;
            }

            if( y > 0 && this.getCamera().position.y >= this.getCamera().viewportHeight * zoomHeight - this.getCamera().viewportHeight/2f){
                y = 0;
            }




            ((OrthographicCamera)this.getCamera()).translate(x ,y);

            pos.x += x;
            pos.y += y;
            touchpad.setPosition(pos.x - touchpad.getWidth()/2f ,pos.y - touchpad.getHeight()/2f );
            player.setPosition(player.getX() + x,player.getY() + y);


        }


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
    }
}
