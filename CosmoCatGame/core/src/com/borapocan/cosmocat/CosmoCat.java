package com.borapocan.cosmocat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class CosmoCat extends ApplicationAdapter {

	SpriteBatch batch;
	Texture galaxy;
	Texture cat;
	Texture alien;
	Texture alien2;
	Texture alien3;

	float catX = 0;
	float catY = 0;

	int gameState = 0;

	float velocity = 0;
	float gravity = 0.4f;

	//float enemyX = 0;
	int enemySet = 4;
	float [] enemyX = new float[enemySet];
	float [] enemyOffSet = new float[enemySet];
	float [] enemyOffSet2 = new float[enemySet];
	float [] enemyOffSet3 = new float[enemySet];


	float distance = 0;
	float enemyVelocity = 2;
	Random random;

	int score = 0;
	int scoredEnemy = 0;
	BitmapFont font;
	BitmapFont font2;

	ShapeRenderer shapeRenderer;

	Circle catCircle;
	Circle[] enemyCircle;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;

	@Override
	public void create () {
		batch = new SpriteBatch();
		galaxy = new Texture("galaxy2.jpg");
		cat = new Texture("sergentcat.png");
		alien = new Texture("idiotalien.png");
		alien2 = new Texture("idiotalien.png");
		alien3 = new Texture("idiotalien.png");

		distance = Gdx.graphics.getWidth() / 2;
		random = new Random();

		catX = Gdx.graphics.getWidth() / 2 - cat.getHeight() / 2;
		catY = Gdx.graphics.getHeight() / 3;

		shapeRenderer = new ShapeRenderer();

		catCircle = new Circle();
		enemyCircle = new Circle[enemySet];
		enemyCircle2 = new Circle[enemySet];
		enemyCircle3 = new Circle[enemySet];

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);

		font2 = new BitmapFont();
		font2.setColor(Color.ORANGE);
		font2.getData().setScale(4);

		//enemyX = 900;

		for (int i = 0; i<enemySet; i++ ) {

			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);


			enemyX[i] = Gdx.graphics.getWidth() -  alien.getWidth() / 2 + i * distance;

			enemyCircle[i] = new Circle();
			enemyCircle2[i] = new Circle();
			enemyCircle3[i] = new Circle();

		}
	}

	@Override
	public void render () {

		batch.begin();
		batch.draw(galaxy, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if (gameState == 1) {


			if (enemyX[scoredEnemy] < catX) {
				score++;

				if (scoredEnemy < enemySet -1 ) {
					scoredEnemy++;

				} else {
					scoredEnemy = 0;
				}
			}

			if (Gdx.input.justTouched()) {
				velocity = -8;
			}

			for (int i = 0; i<enemySet; i++ ) {

				if (enemyX[i] < 0 ) {
					enemyX[i] = enemyX[i] + enemySet * distance;

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

				} else {
					enemyX[i] = enemyX[i] - enemyVelocity;
				}

				//enemyX[i] = enemyX[i] - enemyVelocity;

				batch.draw(alien,enemyX[i],Gdx.graphics.getHeight() /2 + enemyOffSet[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);
				batch.draw(alien2,enemyX[i],Gdx.graphics.getHeight() /2 + enemyOffSet2[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);
				batch.draw(alien3,enemyX[i],Gdx.graphics.getHeight() /2 + enemyOffSet3[i],Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);

				enemyCircle[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() /2 + enemyOffSet[i] + Gdx.graphics.getWidth() / 20, Gdx.graphics.getWidth() / 24);
				enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() /2 + enemyOffSet2[i] + Gdx.graphics.getWidth() / 20, Gdx.graphics.getWidth() / 24);
				enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() /2 + enemyOffSet3[i] + Gdx.graphics.getWidth() / 20, Gdx.graphics.getWidth() / 24);


			}


//			batch.draw(alien,enemyX[],50,Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);
//			batch.draw(alien2,enemyX,150,Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);
//			batch.draw(alien3,enemyX,350,Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);

			//enemyX -= 3;

//			if (Gdx.input.justTouched()) {
//				velocity = -8;
//			}

			if (catY > 0) {
				velocity = velocity + gravity;
				catY = catY - velocity;
			} else {
				gameState = 2;
			}

		} else if (gameState == 0){

			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState == 2) {

			font2.draw(batch, "GAME OVER! Tap To Play Again!",50,Gdx.graphics.getHeight() / 2);

			if (Gdx.input.justTouched()) {
				gameState = 1;
				catY = Gdx.graphics.getHeight() / 3;

				for (int i = 0; i<enemySet; i++ ) {

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);


					enemyX[i] = Gdx.graphics.getWidth() -  alien.getWidth() / 2 + i * distance;

					enemyCircle[i] = new Circle();
					enemyCircle2[i] = new Circle();
					enemyCircle3[i] = new Circle();

				}

				velocity = 0;
				scoredEnemy = 0;
				score = 0;
			}

		}

		//batch.begin();
		//batch.draw(galaxy, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(cat, catX , catY, Gdx.graphics.getWidth() / 12,Gdx.graphics.getHeight() / 8);

		font.draw(batch, String.valueOf(score),100,100);

		batch.end();

		catCircle.set(catX + Gdx.graphics.getWidth() / 24,catY + Gdx.graphics.getHeight() / 16 ,Gdx.graphics.getWidth() / 24);

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(Color.BLACK);
//		shapeRenderer.circle(catCircle.x,catCircle.y,catCircle.radius);
		//shapeRenderer.end();

		for ( int i = 0; i < enemySet; i ++) {
//			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() /2 + enemyOffSet[i] + Gdx.graphics.getWidth() / 16, Gdx.graphics.getWidth() / 24);
//			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() /2 + enemyOffSet2[i] + Gdx.graphics.getWidth() / 16, Gdx.graphics.getWidth() / 24);
//			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() /2 + enemyOffSet3[i] + Gdx.graphics.getWidth() / 16, Gdx.graphics.getWidth() / 24);

			if (Intersector.overlaps(catCircle,enemyCircle[i]) || Intersector.overlaps(catCircle, enemyCircle2[i]) || Intersector.overlaps( catCircle,enemyCircle3[i])) {
				gameState = 2;

			}
		}

		shapeRenderer.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
