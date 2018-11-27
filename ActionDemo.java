package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ActionDemo extends ApplicationAdapter {

    Stage stage;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        stage.addActor(new MyActor(new Texture("badlogic.jpg")));
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(stage.getActors().first());
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}