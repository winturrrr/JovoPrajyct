package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3	;

public class MyGdxGame extends ApplicationAdapter {

	//TextureRegion[] animationFrame,animationFrame2;
	//Animation animation,animation2;
	//ShapeRenderer sr;
	//OrthographicCamera cam;
	//Vector3 pos;
	SpriteBatch batch;
	Texture img,img2,img3,img4,img5_1,img5_2,img5_3,img5_4;
	TextureRegion[] mage_run,mage_atk,melee_up,melee_down,melee_left,melee_right;
	TextureRegion[] wiz_atk,wiz_die,wiz_fly,wiz_idle;
	Animation mage_runAni,mage_atkAni,melee_upAni,melee_downAni,melee_leftAni,melee_rightAni;
	Animation wiz_atkAni,wiz_dieAni,wiz_flyAni,wiz_idleAni;
	float elaspedTime;
	Sprite mage_runSp,mage_atkSp,melee_upSp,melee_downSp,melee_leftSp,melee_rightSp;//sprite,sprite2
	Sprite wiz_atkSp,wiz_dieSp,wiz_flySp,wiz_idleSp;
	@Override
	public void create () {
		//sr = new ShapeRenderer();
		//cam = new OrthographicCamera();
		//cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		//pos = new Vector3(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
		batch = new SpriteBatch();
		img = new Texture("ZombieToast2.png");
		img2 = new Texture("mage_run.png");
		img3 = new Texture("mage_atk.png");
		img4 = new Texture("melee.png");
		img5_1 = new Texture("wizard attack.png");
		img5_2 = new Texture("wizard death.png");
		img5_3 = new Texture("wizard fly forward.png");
		img5_4 = new Texture("wizard idle.png");
		//TextureRegion[][] tempFrame = TextureRegion.split(img,379,380);
		TextureRegion[][] tempFrame2 = TextureRegion.split(img2,270,270);
		TextureRegion[][] tempFrame3 = TextureRegion.split(img3,652,270);
		TextureRegion[][] tempFrame4 = TextureRegion.split(img4,270,270);
		TextureRegion[][] tempFrame5_1 = TextureRegion.split(img5_1,265,270);
		TextureRegion[][] tempFrame5_2 = TextureRegion.split(img5_2,270,270);
		TextureRegion[][] tempFrame5_3 = TextureRegion.split(img5_3,270,270);
		TextureRegion[][] tempFrame5_4 = TextureRegion.split(img5_4,270,270);
		//animationFrame = new TextureRegion[25];
		//animationFrame2 = new TextureRegion[25];
		mage_run = new TextureRegion[6];
		mage_atk = new TextureRegion[10];
		melee_down = new TextureRegion[4];
		melee_up = new TextureRegion[4];
		melee_left = new TextureRegion[4];
		melee_right = new TextureRegion[4];
		wiz_atk = new TextureRegion[9];
		wiz_die = new TextureRegion[10];
		wiz_fly = new TextureRegion[6];
		wiz_idle = new TextureRegion[10];
		//int index = 0;
		int index_mage_run = 0;
		int index_mage_atk = 0;
		int index_melee_down = 0;
		int index_melee_up = 0;
		int index_melee_right = 0;
		int index_melee_left = 0;
		int index_wiz_atk = 0;
		int index_wiz_die = 0;
		int index_wiz_fly = 0;
		int index_wiz_idle = 0;
		/*
		for (int i =0;i<1;i++){
			for (int j =0;j<25;j++){
				sprite = new Sprite(tempFrame[i][j]);
				sprite2 = new Sprite(tempFrame[i][j]);
				animationFrame[index] = sprite;
				sprite.flip(true,false);
				animationFrame2[index++] = sprite2;
			}
		}*/

		for(int i = 0;i<1;i++){
			for (int j = 0;j<6;j++){
				mage_runSp = new Sprite(tempFrame2[i][j]);
				mage_run[index_mage_run++] = mage_runSp;
			}
		}

		for(int i = 0;i<1;i++){
			for (int j = 0;j<10;j++){
				mage_atkSp = new Sprite(tempFrame3[i][j]);
				mage_atk[index_mage_atk++] = mage_atkSp;
			}
		}
//------------------------------------------------------------------------------------------
		for(int i = 0;i<1;i++){
			for (int j = 0;j<4;j++){
				melee_downSp = new Sprite(tempFrame4[i][j]);
				melee_down[index_melee_down++] = melee_downSp;
			}
		}
		for(int i = 1;i<2;i++){
			for (int j = 0;j<4;j++){
				melee_upSp = new Sprite(tempFrame4[i][j]);
				melee_up[index_melee_up++] = melee_upSp;
			}
		}

		for(int i = 2;i<3;i++){
			for (int j = 0;j<4;j++){
				melee_rightSp = new Sprite(tempFrame4[i][j]);
				melee_right[index_melee_right++] = melee_rightSp;
			}
		}
		for(int i = 3;i<4;i++){
			for (int j = 0;j<4;j++){
				melee_leftSp = new Sprite(tempFrame4[i][j]);
				melee_left[index_melee_left++] = melee_leftSp;
			}
		}
		for(int i = 0;i<2;i++){
			for (int j = 0;j<7;j++){
				if(i == 1 && j == 2)
					break;
				wiz_atkSp = new Sprite(tempFrame5_1[i][j]);
				wiz_atk[index_wiz_atk++] = wiz_atkSp;
			}
		}

		for (int j = 0;j<10;j++){
			wiz_dieSp = new Sprite(tempFrame5_2[0][j]);
			wiz_die[index_wiz_die++] = wiz_dieSp;
		}
		for (int j = 0;j<6;j++){
			wiz_flySp = new Sprite(tempFrame5_3[0][j]);
			wiz_fly[index_wiz_fly++] = wiz_flySp;
		}for (int j = 0;j<10;j++){
			wiz_idleSp = new Sprite(tempFrame5_4[0][j]);
			wiz_idle[index_wiz_idle++] = wiz_idleSp;
		}

		//animation = new Animation(1f/5f,animationFrame);
		//animation2 = new Animation(1f/5f,animationFrame2);
		mage_atkAni = new Animation(1f/5f,mage_atk);
		mage_runAni = new Animation(1f/5f,mage_run);
		melee_upAni = new Animation(1f/5f,melee_up);
		melee_downAni = new Animation(1f/5f,melee_down);
		melee_leftAni = new Animation(1f/5f,melee_left);
		melee_rightAni = new Animation(1f/5f,melee_right);
		wiz_atkAni = new Animation(1f/5f,wiz_atk);
		wiz_dieAni = new Animation(1f/5f,wiz_die);
		wiz_flyAni = new Animation(1f/5f,wiz_fly);
		wiz_idleAni = new Animation(1f/5f,wiz_idle);
	}

	@Override
	public void render () {
		//cam.update();

		//if(Gdx.input.isTouched()){
		//	pos.set(Gdx.input.getX(),Gdx.input.getY(),0);
		//	cam.unproject(pos);

		//}

		//		Gdx.gl.glClearColor(1, 1, 1, 1);

		//sr.begin(ShapeRenderer.ShapeType.Filled);
		//sr.setColor(Color.GREEN);
		//sr.circle(pos.x,pos.y,64);
		//sr.end();
		elaspedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw((TextureRegion) animation.getKeyFrame(elaspedTime,true),0,0);
		//batch.draw((TextureRegion) animation2.getKeyFrame(elaspedTime,true),379,0);
		batch.draw((TextureRegion) mage_runAni.getKeyFrame(elaspedTime,true),758,0);
		batch.draw((TextureRegion) mage_atkAni.getKeyFrame(elaspedTime,true),0,380);
		batch.draw((TextureRegion) melee_upAni.getKeyFrame(elaspedTime,true),0,650);
		batch.draw((TextureRegion) melee_downAni.getKeyFrame(elaspedTime,true),270,650);
		batch.draw((TextureRegion) melee_leftAni.getKeyFrame(elaspedTime,true),540,650);
		batch.draw((TextureRegion) melee_rightAni.getKeyFrame(elaspedTime,true),810,650);
		batch.draw((TextureRegion) wiz_atkAni.getKeyFrame(elaspedTime,true),0,0);
		batch.draw((TextureRegion) wiz_dieAni.getKeyFrame(elaspedTime,true),270,0);
		batch.draw((TextureRegion) wiz_flyAni.getKeyFrame(elaspedTime,true),540,0);
		batch.draw((TextureRegion) wiz_idleAni.getKeyFrame(elaspedTime,true),810,0);
		batch.end();
	}
	
	/*@Override
	public void dispose () {
		sr.dispose();
		batch.dispose();
		img.dispose();
		}*/
}
