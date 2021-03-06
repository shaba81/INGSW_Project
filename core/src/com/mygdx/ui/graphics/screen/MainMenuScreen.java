package com.mygdx.ui.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.application.exception_manager.ExceptionsManager;
import com.mygdx.application.screen_manager.ScreenManager;
import com.mygdx.foundation.utils.Utils;
import com.mygdx.services.factory_methos_screens.FaceDetectionScreenCreator;
import com.mygdx.services.factory_methos_screens.LoginScreenCreator;

public class MainMenuScreen extends AbstractScreen {

	private Texture title;
	private TextButton accessBotton, administrationButton, exitButton;

	private boolean access;
	private boolean administration;
	private boolean exit;

	public MainMenuScreen() {
		this.access = false;
		this.administration = false;
		this.exit = false;
	}

	@Override
	public void show() {

		super.show();
		mainTable.bottom();

		title = new Texture(Gdx.files.internal("title.png"));
		// Creo bottoni
		if (Utils.userLogged == "-1")
			accessBotton = new TextButton("Access", skin);

		administrationButton = new TextButton("Administration", skin);
		backButton = new TextButton("Back to simulation", skin);
		exitButton = new TextButton("Exit", skin);

		// In ascolto di eventi
		if (Utils.userLogged == "-1") {
			accessBotton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					access = true;
				}
			});
		}
		administrationButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				administration = true;
			}
		});
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				back = true;
			}
		});
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				exit = true;
			}
		});
		// Aggiungo bottoni alla table
		this.add(accessBotton);
		this.add(administrationButton);
		this.add(backButton);
		this.add(exitButton);

		// Aggiungo table allo stage
		this.stage.addActor(mainTable);
	}

	@Override
	public void render(float delta) {
		try {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			batch.draw(title, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2, 700, 150);
			batch.end();

			stage.act();
			stage.draw();

			if (access) {
				access = false;
				Utils.isAccess = true;
				ScreenManager.getInstance().showScreen(new FaceDetectionScreenCreator());
//			ScreenManager.getInstance().showScreen(ScreenEnum.GAME_SCREEN);
			}

			if (administration) {
				Utils.isAccess = false;
				administration = false;
				ScreenManager.getInstance().showScreen(new LoginScreenCreator());
			}

			if (back) {
				back = false;
				Utils.showPopUp(Utils.SCREEN_BACK_GAME_SIMU_POPUP, skin, stage, Utils.GAME_SCREEN_POP);
			}

			if (exit) {
				Utils.isAccess = false;
				exit = false;
				// Utils.resp = Utils.EXIT_THE_SIMULATION_LOG;
				Utils.saveOnLog(Utils.EXIT_THE_SIMULATION_LOG);
				Utils.showPopUp(Utils.MAIN_MENU_BACK_POPUP, skin, stage, Utils.EXIT_POP);
			}
		} catch (Exception e) {
			ExceptionsManager.getExceptionsManager().manageException(e, skin, stage);
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
