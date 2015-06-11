package com.fahmy.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Drop extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music backgroundMusic;

	private OrthographicCamera camera;//to render using 800x480 resolution
	private SpriteBatch batch;//to draw 2D objects

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
		camera.setToOrtho(false,800,480);

		//creating the batch
		batch = new SpriteBatch();
		

	}

	@Override
	public void render () {
	}
}
