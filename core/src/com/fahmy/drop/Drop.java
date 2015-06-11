package com.fahmy.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Iterator;


public class Drop extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music backgroundMusic;

	private OrthographicCamera camera;//to render using 800x480 resolution
	private final Integer cameraWidth = 800;
	private final Integer cameraHeight = 480;
	private SpriteBatch batch;//to draw 2D objects

	private Rectangle bucket;

	private Vector3 touchPos = new Vector3();

	private Array<Rectangle> rainDrops;
	private long lastDropTime;

	@Override
	public void create () {
		//load the images of the droplet and the bucket (64x64)
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		//load the sound effect of the drop
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));

		//load the background music and play it
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Barbarian.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		//creating the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, cameraWidth, cameraHeight);

		//creating the batch
		batch = new SpriteBatch();

		//the bucket rectangle
		bucket = new Rectangle();
		bucket.x = cameraWidth/2 - bucketImage.getWidth()/2;//center the bucket
		bucket.y = 20;//a little above the bottom edge
		bucket.width = bucketImage.getWidth();
		bucket.height = bucketImage.getHeight();

		rainDrops = new Array<Rectangle>();
		spawnRainDrop();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);//dark blue
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		//rendering the bucket
		batch.setProjectionMatrix(camera.combined);//use the camera's coordinate sys
		batch.begin();//start a new batch
			batch.draw(bucketImage, bucket.x, bucket.y);
			for(Rectangle rainDrop: rainDrops){
				batch.draw(dropImage , rainDrop.x , rainDrop.y);
			}
		batch.end();//start drawing

		if(Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(touchPos);//transform coordinates to our camera's coordinate system
			bucket.x = touchPos.x - bucket.getWidth()/2;
		}

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000)//every one second drop a rain drop
			spawnRainDrop();

		//move all rainDrops at a rate 200 pixels/second
		Iterator<Rectangle> iter = rainDrops.iterator();
		while(iter.hasNext()){
			Rectangle rainDrop = iter.next();
			rainDrop.y -= 200 * Gdx.graphics.getDeltaTime();

			if(rainDrop.overlaps(bucket)) {//if drop in the bucket
				dropSound.play();
				iter.remove();
			}else if(rainDrop.y + dropImage.getHeight() < 0) {
				iter.remove();
			}
		}
	}

	@Override
	public void dispose(){
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		backgroundMusic.dispose();
		batch.dispose();
	}

	private void spawnRainDrop(){
		Rectangle rainDrop = new Rectangle();
		rainDrop.x = MathUtils.random(0 , cameraWidth - dropImage.getWidth());
		rainDrop.y = cameraHeight;
		rainDrop.width = dropImage.getWidth();
		rainDrop.height = dropImage.getHeight();
		rainDrops.add(rainDrop);
		lastDropTime = TimeUtils.nanoTime();
	}

}
