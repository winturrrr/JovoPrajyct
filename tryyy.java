package com.mygdx.game;

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


public class tryyy extends ApplicationAdapter {
    float CAM_WIDTH = 1000;
    float CAM_HEIGHT = 500;
    float aspectRatio;
    private OrthographicCamera cam;

    private float screenWidth;
    private float screenHeight;


    private Sprite map;


    private float lastX;
    private float lastY;
    private Viewport viewport;


    private Stage maingame;
    private Stage mainmenu;

    boolean inGame = false;

    private float p_x;
    private float p_y;
    private int p_d;
    private int is_atk;
    private float s_x;
    private float s_y;



    @Override
    public void create() {

        mainmenu = new MainMenu();
        Gdx.input.setInputProcessor(mainmenu);


//
        map = new Sprite(new Texture("tate-arena.png"));
        map.setPosition(0, 0);


        float CAM_WIDTH_ZOOM = 4;
        float CAM_HEIGHT_ZOOM = 3;
        CAM_HEIGHT = map.getTexture().getHeight() / CAM_HEIGHT_ZOOM;
        CAM_WIDTH = map.getTexture().getWidth() / CAM_WIDTH_ZOOM;


        aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

        cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT);
//        cam.translate(cam.viewportWidth/2f,cam.viewportHeight/2f);
        cam.position.set(map.getWidth() / 2f, map.getHeight() / 2f, 0);

        viewport = new StretchViewport(CAM_WIDTH, CAM_HEIGHT, cam);
        viewport.apply();


        maingame = new MyStage(viewport);
////        Gdx.input.setInputProcessor(maingame);
        ((MyStage) maingame).setZoom(CAM_WIDTH_ZOOM, CAM_HEIGHT_ZOOM);


    }

    //
//    @Override
//    public void resize(int width, int height){
//        viewport.update(width,height);
////        cam.position.set(CAM_WIDTH*aspectRatio/2f,CAM_HEIGHT/2f,0);
//    }
    @Override
    public void render() {
//        cam.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//        batch.begin();
//        batch.setProjectionMatrix(cam.combined);
//        batch.draw(map,0,0);
//        batch.end();
        if (inGame){
            if (((MainMenu) mainmenu).getHost() == 1 && !(((MainMenu)mainmenu).isInUsed())){
                ThreadedServer threadedServer = new ThreadedServer();
                Player hostPlayer = ((MyStage)maingame).getPlayer();
                Client hostClient = new Client(hostPlayer.getX(), hostPlayer.getY(), hostPlayer.getDirection().ordinal(), hostPlayer.get_projectile_fired(), 0f, 0f, 0);
                hostClient.go();
            }
            if (((MainMenu) mainmenu).getHost() == 0 && !(((MainMenu)mainmenu).isInUsed())){
                Player joinPlayer = ((MyStage)maingame).getPlayer();
                Client joinClient = new Client(joinPlayer.getX(), joinPlayer.getY(), joinPlayer.getDirection().ordinal(), joinPlayer.get_projectile_fired(), 0f, 0f, 0);
                joinClient.go();
            }
        }

        inGame = ((MainMenu) mainmenu).pressed;
        if (!inGame) {
            mainmenu.act(Gdx.graphics.getDeltaTime());
            mainmenu.draw();
            Gdx.input.setInputProcessor(mainmenu);

        } else {
            maingame.act(Gdx.graphics.getDeltaTime());
            maingame.draw();
            Gdx.input.setInputProcessor(maingame);
        }


    }


    @Override
    public void dispose() {
//        batch.dispose();
//        maingame.dispose();
    }

}