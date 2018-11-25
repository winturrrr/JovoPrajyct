package com.tryagain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


class TouchCoord{
    public static float getX(){
        return Gdx.input.getX();
    }
    public static float getY(){
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }
}
public class tryyy extends ApplicationAdapter implements InputProcessor {
    float CAM_WIDTH = 1000;
    float CAM_HEIGHT =  500;
    float aspectRatio;
    private OrthographicCamera cam;

    private float screenWidth;
    private float screenHeight;

    private Batch batch ;
    private Sprite sprite;
    private Sprite map;

    private Vector3 pos;

    private float lastX;
    private float lastY;
    private Viewport viewport;

    private Touchpad touchpad;
    private Skin skin;

    private Stage maingame;

    @Override
    public void create() {

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();


        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("Sprite.png"));
        pos = new Vector3(screenWidth/2f - sprite.getWidth()/2,
                screenHeight/2f - sprite.getHeight()/2,0);



        map = new Sprite(new Texture("tate-arena.png"));
        map.setPosition(0,0);


        float CAM_WIDTH_ZOOM = 4;
        float CAM_HEIGHT_ZOOM = 4;
        CAM_HEIGHT = map.getTexture().getHeight()/CAM_HEIGHT_ZOOM ;
        CAM_WIDTH = map.getTexture().getWidth()/CAM_WIDTH_ZOOM;



        aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

        cam = new OrthographicCamera(CAM_WIDTH,CAM_HEIGHT);
//        cam.translate(cam.viewportWidth/2f,cam.viewportHeight/2f);
        cam.position.set(map.getWidth()/2f,map.getHeight()/2f,0);

        viewport = new StretchViewport(CAM_WIDTH,CAM_HEIGHT,cam);
        viewport.apply();
        Gdx.input.setInputProcessor(this);


        sprite.setPosition(cam.position.x - sprite.getWidth()/2f ,cam.position.y - sprite.getHeight()/2f);
        sprite.setSize(CAM_WIDTH/10,CAM_HEIGHT/10);

//        skin = new Skin();
//        skin.add("shit",new Texture("badlogic.jpg"));
//        skin.add("shittier",new Texture("Sprite.png"));
//        Touchpad.TouchpadStyle tp = new Touchpad.TouchpadStyle();
//        tp.background = skin.getDrawable("shit");
//        tp.knob = skin.getDrawable("shittier");
//
//        touchpad = new Touchpad(20,tp);

//
//        maingame = new Stage(viewport);
//        maingame.addActor(touchpad);
//        Gdx.input.setInputProcessor(maingame);

        maingame = new MyStage(viewport);
        Gdx.input.setInputProcessor(maingame);
        ((MyStage)maingame).setZoom(CAM_WIDTH_ZOOM,CAM_HEIGHT_ZOOM);



    }


    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
//        cam.position.set(CAM_WIDTH*aspectRatio/2f,CAM_HEIGHT/2f,0);
    }
    @Override
    public void render() {
        cam.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isTouched()){
            pos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            cam.unproject(pos);
            }

        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        batch.draw(map,0,0);
//        batch.draw(sprite,pos.x,pos.y);

//    batch.draw(sprite,cam.position.x - sprite.getWidth()/2f,cam.position.y - sprite.getHeight()/2f) ;
        batch.draw(sprite,sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
//        touchpad.draw(batch,1f);
        batch.end();

        maingame.act(Gdx.graphics.getDeltaTime());
        maingame.draw();

    }



    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        batch.dispose();
        maingame.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if( sprite.getX() + screenX - lastX < 0 || sprite.getX() + screenX - lastX > map.getTexture().getWidth() - sprite.getWidth()){
           lastX = screenX ;

        }
//        if( sprite.getY() - screenY + lastY < 0 ){
            if( sprite.getY() - screenY + lastY < 0 || sprite.getY() - screenY + lastY > map.getTexture().getHeight() - sprite.getHeight()){

                lastY = screenY;
        }
        cam.translate(screenX - lastX,lastY - screenY);

        sprite.translate(screenX-lastX,lastY-screenY);
        lastX = screenX;
        lastY = screenY;

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
