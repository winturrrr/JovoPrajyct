

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
public class tryyy extends ApplicationAdapter {
    float CAM_WIDTH = 1000;
    float CAM_HEIGHT =  500;
    float aspectRatio;
    private OrthographicCamera cam;

    private float screenWidth;
    private float screenHeight;

    private Batch batch ;

    private Sprite map;


    private float lastX;
    private float lastY;
    private Viewport viewport;


    private Stage maingame;

    @Override
    public void create() {

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();


        batch = new SpriteBatch();



        map = new Sprite(new Texture("tate-arena.png"));
        map.setPosition(0,0);


        float CAM_WIDTH_ZOOM = 4;
        float CAM_HEIGHT_ZOOM = 3;
        CAM_HEIGHT = map.getTexture().getHeight()/CAM_HEIGHT_ZOOM ;
        CAM_WIDTH = map.getTexture().getWidth()/CAM_WIDTH_ZOOM;



        aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

        cam = new OrthographicCamera(CAM_WIDTH,CAM_HEIGHT);
//        cam.translate(cam.viewportWidth/2f,cam.viewportHeight/2f);
        cam.position.set(map.getWidth()/2f,map.getHeight()/2f,0);

        viewport = new StretchViewport(CAM_WIDTH,CAM_HEIGHT,cam);
        viewport.apply();



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


        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        batch.draw(map,0,0);
//        batch.draw(sprite,pos.x,pos.y);

//    batch.draw(sprite,cam.position.x - sprite.getWidth()/2f,cam.position.y - sprite.getHeight()/2f) ;
//        touchpad.draw(batch,1f);
        batch.end();

        maingame.act(Gdx.graphics.getDeltaTime());
        maingame.draw();

    }



    @Override
    public void dispose() {
        batch.dispose();
        maingame.dispose();
    }

}

