package com.fahmy.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by fahmy on 6/13/15.
 */
public class MyGestureListener implements GestureDetector.GestureListener {
    private GameScreen scrn;
    public MyGestureListener(GameScreen screen){
        scrn = screen;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        scrn.setToWrite("touchDown");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        scrn.setToWrite("tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        scrn.setToWrite("longPress");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        scrn.setToWrite("fling");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        scrn.setToWrite("pan");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        scrn.setToWrite("panStop");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        scrn.setToWrite("zoom");
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        scrn.setToWrite("pinch");
        return false;
    }
}
