package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.Gdx.app;

public class MainMenu extends Stage {
    SpriteBatch batch;
    BitmapFont font;
    BitmapFont font1;
    String title;
    Skin skin;
    Stage stage;
    TextButton hostGame;
    TextButton joinGame;
    TextButton wait;
    int host;
    boolean inUsed = false;

    //    TextButton quitGame;
    GlyphLayout layout;

    public boolean pressed = false;

    public MainMenu()  {


        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font1.fnt"));
        font1 = new BitmapFont(Gdx.files.internal("font1.fnt"));
        skin = new Skin();

        Texture texture = new Texture("badlogic.jpg");
        Texture green = new Texture("Sprite.png");
        skin.add("down", texture);
        skin.add("up", green);

        TextButton.TextButtonStyle tb = new TextButton.TextButtonStyle();
        tb.font = font1;
        tb.up = skin.getDrawable("up");
        tb.down = skin.getDrawable("down");

        hostGame = new TextButton("Host Game", tb);
        joinGame = new TextButton("Join Game", tb);
//        quitGame = new TextButton("Quit", tb);

//        quitGame = new TextButton("Quit", tb);
        wait = new TextButton("Waiting for the other player...", tb);

        hostGame.align(Align.center);
        hostGame.setHeight(Gdx.graphics.getHeight() / 8);
        hostGame.setWidth(Gdx.graphics.getWidth() / 4);
        hostGame.setPosition((Gdx.graphics.getWidth() - hostGame.getWidth()) / 2, Gdx.graphics.getHeight() * 3 / 7);

        joinGame.align(Align.center);
        joinGame.setHeight(Gdx.graphics.getHeight() / 8);
        joinGame.setWidth(Gdx.graphics.getWidth() / 4);
        joinGame.setPosition((Gdx.graphics.getWidth() - joinGame.getWidth()) / 2, Gdx.graphics.getHeight() * 2 / 7);

//        quitGame.align(Align.center);
//        quitGame.setHeight(Gdx.graphics.getHeight() / 8);
//        quitGame.setWidth(Gdx.graphics.getWidth() / 4);
//        quitGame.setPosition((Gdx.graphics.getWidth() - quitGame.getWidth()) / 2, Gdx.graphics.getHeight() / 7);

        wait.align(Align.center);
        wait.setHeight(Gdx.graphics.getHeight() / 2);
        wait.setWidth(Gdx.graphics.getWidth() / 2);
        wait.setPosition((Gdx.graphics.getWidth())/2 - wait.getWidth()/2f ,Gdx.graphics.getHeight() / 5);


//        stage = new Stage(new ScreenViewport());
//        stage.addActor(hostGame);
//        stage.addActor(joinGame);
//        stage.addActor(quitGame);


        this.addActor(hostGame);
        this.addActor(joinGame);
//        this.addActor(quitGame);
//        this.addActor(wait);

        title = "Warlock Showdown";
        font.getData().setScale(2.5f);
        font1.getData().setScale(1.0f);
//
////        Gdx.input.setInputProcessor(stage);
        layout = new GlyphLayout(font, title, Color.FIREBRICK, Gdx.graphics.getWidth(), Align.center, false);


    }

    public int getHost(){
        return host;
    }

    @Override
    public void draw () {
        super.draw();

        batch.begin();
        font.draw(batch, layout, 0, (Gdx.graphics.getHeight() + layout.height) * 3 / 4);
        batch.end();

//        if(hostGame.isPressed()){
//            pressed = true;
//        }
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
    }

    @Override
    public void dispose () {
        super.dispose();
//        batch.dispose();
//        font.dispose();
//        stage.dispose();
        if(hostGame.isPressed()){
            pressed = true;
        }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        boolean bool = super.touchDown(screenX, screenY, pointer, button);

//        if (quitGame.isPressed()){
//            dispose();
//        }
        if (hostGame.isPressed()){
            pressed = true;
            host = 1;
            inUsed = true;
        }

        if (joinGame.isPressed()){
            pressed = true;
            inUsed = true;
        }

        return bool;
    }

    public boolean isInUsed(){
        return inUsed;
    }
}
/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import static com.mygdx.game.TouchCoord.getX;
import static com.mygdx.game.TouchCoord.getY;

public class MainMenu extends Stage {
    SpriteBatch batch;
    BitmapFont font;
    BitmapFont font1;
    String title;
    Skin skin;
    Stage stage;
    TextButton hostGame;
    TextButton joinGame;
    TextButton quitGame;
    GlyphLayout layout;

    public boolean pressed = false;

    public MainMenu()  {


        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font1.fnt"));
        font1 = new BitmapFont(Gdx.files.internal("font1.fnt"));
        skin = new Skin();

        Texture texture = new Texture("badlogic.jpg");
        Texture green = new Texture("Sprite.png");
        skin.add("down", texture);
        skin.add("up", green);

        TextButton.TextButtonStyle tb = new TextButton.TextButtonStyle();
        tb.font = font1;
        tb.up = skin.getDrawable("up");
        tb.down = skin.getDrawable("down");

        hostGame = new TextButton("Host Game", tb);
        joinGame = new TextButton("Join Game", tb);
        quitGame = new TextButton("Quit", tb);


        hostGame = new TextButton("Host Game", tb);
        joinGame = new TextButton("Join Game", tb);
        quitGame = new TextButton("Quit", tb);

        hostGame.align(Align.center);
        hostGame.setHeight(Gdx.graphics.getHeight() / 8);
        hostGame.setWidth(Gdx.graphics.getWidth() / 4);
        hostGame.setPosition((Gdx.graphics.getWidth() - hostGame.getWidth()) / 2, Gdx.graphics.getHeight() * 3 / 7);

        joinGame.align(Align.center);
        joinGame.setHeight(Gdx.graphics.getHeight() / 8);
        joinGame.setWidth(Gdx.graphics.getWidth() / 4);
        joinGame.setPosition((Gdx.graphics.getWidth() - joinGame.getWidth()) / 2, Gdx.graphics.getHeight() * 2 / 7);

        quitGame.align(Align.center);
        quitGame.setHeight(Gdx.graphics.getHeight() / 8);
        quitGame.setWidth(Gdx.graphics.getWidth() / 4);
        quitGame.setPosition((Gdx.graphics.getWidth() - quitGame.getWidth()) / 2, Gdx.graphics.getHeight() / 7);

//        stage = new Stage(new ScreenViewport());
//        stage.addActor(hostGame);
//        stage.addActor(joinGame);
//        stage.addActor(quitGame);


        this.addActor(hostGame);
        this.addActor(joinGame);
        this.addActor(quitGame);

        title = "Warlock Showdown";
        font.getData().setScale(2.5f);
        font1.getData().setScale(1.0f);
//
////        Gdx.input.setInputProcessor(stage);
        layout = new GlyphLayout(font, title, Color.FIREBRICK, Gdx.graphics.getWidth(), Align.center, false);


    }

    @Override
    public void draw () {
        super.draw();

        batch.begin();
        font.draw(batch, layout, 0, (Gdx.graphics.getHeight() + layout.height) * 3 / 4);
        batch.end();
        if(hostGame.isPressed()){
            pressed = true;
        }
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
    }

    @Override
    public void dispose () {
        super.dispose();
//        batch.dispose();
//        font.dispose();
//        stage.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (hostGame.isPressed()){
            ThreadedServer threadedServer = new ThreadedServer();
            Client hostClient = new Client(getX(),getY(),0,0,0,100,0);
            hostClient.go();
        }

        if (joinGame.isPressed()){
            Client joinClient = new Client(getX(),getY(),2,1,0,100,1);
            joinClient.go();
        }

        if (quitGame.isPressed()){
            dispose();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

}*/