package com.fahmy.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


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
		camera.setToOrtho(false,cameraWidth,cameraHeight);

		//creating the batch
		batch = new SpriteBatch();

		//the bucket rectangle
		bucket = new Rectangle();
		bucket.x = cameraWidth/2 - bucketImage.getWidth()/2;//center the bucket
		bucket.y = 20;//a little above the bottom edge
		bucket.width = bucketImage.getWidth();
		bucket.height = bucketImage.getHeight();


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0.2f,1);//dark blue
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		//rendering the bucket
		batch.setProjectionMatrix(camera.combined);//use the camera's coordinate sys
		batch.begin();//start a new batch
			batch.draw(bucketImage,bucket.x, bucket.y);
		batch.end();//start drawing
	}
}
