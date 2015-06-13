package com.fahmy.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Iterator;


public class GameScreen implements Screen {
    final Drop game;


    private OrthographicCamera camera;//to render using 800x480 resolution
    private final Integer cameraWidth = 800;
    private final Integer cameraHeight = 480;

    private Rectangle minion;
    private Texture minionImage;

    private Vector3 touchPos = new Vector3();

    private String toWrite;

    public GameScreen(final Drop gam) {
        toWrite = new String("");
        game = gam;

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(this)));

        //creating the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);

        minionImage = new Texture(Gdx.files.internal("minion.png"));

        //the minion rectangle
        minion = new Rectangle();
        minion.x = cameraWidth/2 - minionImage.getWidth()/2;//center the minion
        minion.y = 20;//a little above the bottom edge
        minion.width = minionImage.getWidth();
        minion.height = minionImage.getHeight();

    }

    public void setToWrite(String s) {
        toWrite = s;
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);//dark blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        //rendering the minion
        game.batch.setProjectionMatrix(camera.combined);//use the camera's coordinate sys
        game.batch.begin();//start a new batch
            game.batch.draw(minionImage , minion.getX() , minion.getY());
            game.font.draw(game.batch , toWrite , 100 , 100);
        game.batch.end();//start drawing

        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);//transform coordinates to our camera's coordinate system
        }
    }

    @Override
    public void dispose(){
    }


    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}