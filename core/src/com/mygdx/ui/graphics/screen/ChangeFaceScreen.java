package com.mygdx.ui.graphics.screen;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.application.exception_manager.ExceptionsManager;
import com.mygdx.application.screen_manager.ScreenManager;
import com.mygdx.domain.controller.Controller;
import com.mygdx.foundation.utils.Utils;
import com.mygdx.services.factory_methos_screens.AdministrationScreenCreator;
import com.mygdx.services.factory_methos_screens.RegistrationCredentialsScreenCreator;

public class ChangeFaceScreen extends AbstractScreen {

	private TextureRegion imgRegion;
	private Texture frameTexture;
	private Image img;
	private Table imageTable;
	private Stage imgStage;

	private TextButton backButton;
	private boolean back;

	private TextButton changeCredentialsButton;
	private boolean changeCredentials;

	private TextButton backAdminScreen;
	private boolean backAdmin;

	private TextButton redoButton;
	private boolean redo;

	public ChangeFaceScreen() {
		// TODO Auto-generated constructor stub
		this.imgStage = new Stage();
		// Gdx.input.setInputProcessor(imgStage);
		this.imageTable = new Table();
		this.imageTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.imageTable.center();
		// imgStage.addActor(imageTable);
		this.imageTable.debug();
		if (Utils.backToRegistrationScreen)
			this.frameTexture = new Texture(Gdx.files.internal("resources/frame.jpg"));
		else
			this.frameTexture = new Texture(Gdx.files.internal("resources/frame1.jpg"));
		this.imgRegion = new TextureRegion(frameTexture);
		this.img = new Image(imgRegion);
		this.imageTable.add(this.img).center();

		this.redo = this.changeCredentials = this.back = false;
	}

	@Override
	public void show() {
		super.show();
		this.mainTable.right();
		this.imageTable.center();

		Gdx.input.setInputProcessor(this.imgStage);

		String text = "Change credentials";
		this.changeCredentialsButton = new TextButton(text, this.skin);

		this.changeCredentialsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				changeCredentials = true;
			}
		});

		text = "Redo";
		this.redoButton = new TextButton(text, this.skin);

		this.redoButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				redo = true;
			}
		});

		text = "Back";
		this.backButton = new TextButton(text, this.skin);

		this.backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				back = true;
			}
		});

		text = "Back to Administration Screen";
		this.backAdminScreen = new TextButton(text, this.skin);

		this.backAdminScreen.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				backAdmin = true;
			}
		});

		if( !Utils.captured )
			Controller.getController().getFaceController().init();

		this.mainTable.add(this.changeCredentialsButton);
		this.mainTable.row();
		this.mainTable.add(this.redoButton);
		this.mainTable.row();
		this.mainTable.add(this.backButton);
		this.mainTable.row();
		this.mainTable.add(this.backAdminScreen);
		this.mainTable.row();

		this.imgStage.addActor(this.imageTable);
		// imageTable.addActor(this.mainTable);
		this.imgStage.addActor(this.mainTable);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		try {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			this.imgStage.act();
			this.imgStage.draw();
			if (com.mygdx.foundation.utils.Utils.capturing) {
				this.updateFrame(this.imageTable, this.frameTexture, this.imgRegion, this.img);
				com.mygdx.foundation.utils.Utils.capturing = false;
			}

//		if(Utils.captured && Utils.changeUserCredentials)
//		{
//		    Controller.getController().getUserDAO().updateUserCredentials(Utils.credentials.get(0), Utils.credentials.get(1), Utils.credentials.get(2), "resources/images/" + Utils.credentials.get(4) + ".jpg", Controller.getController().getUserDAO().getIdUser());
//		    Utils.changeUserCredentials = false;
//		    Utils.showMessageDialog(Utils.CHANGE_USER_CREDENTIALS_SUCCESSFULLY_MESSAGE, this.skin, this.imgStage);
//		}
//		
			if (Utils.captured && Utils.backToRegistrationScreen) {
				Utils.backToRegistrationScreen = false;
				Utils.showMessageDialog(Utils.ALREADY_CAPTURE_FACE_POPUP, skin, imgStage);
			}

			if (this.changeCredentials) {
				this.changeCredentials = false;

				if (Utils.captured) {
					File file = new File("resources/images/" + Utils.credentials.get(4) + ".jpg");
					Utils.removeAfileInAFolder(file);

					Utils.moveNewUserToImageFolder("resources/temp_image", "temp.jpg", "resources/images",
							Utils.credentials.get(4) + ".jpg");
					Controller.getController().getUserDAO().updateUserCredentials(Utils.credentials.get(0),
							Utils.credentials.get(1), Utils.credentials.get(2),
							"resources/images/" + Utils.credentials.get(4) + ".jpg", Utils.credentials.get(4));

					Controller.getController().getFaceController().setClosed();
					Utils.showMessageDialog(Utils.CHANGE_USER_CREDENTIALS_SUCCESSFULLY_MESSAGE, skin, imgStage);
					Utils.captured = false;
					Utils.credentials.clear();
					Utils.changeUserCredentials = false;
					ScreenManager.getInstance().showScreen(new AdministrationScreenCreator());
				} else {
					Utils.showMessageDialog(Utils.CANT_COME_BACK_WITHOUT_FACE_CAPTURE, skin, imgStage);
				}
			}

			if (this.redo) {
				this.redo = false;
				Utils.captured = false;
				Utils.backToRegistrationScreen = false;
				Controller.getController().getFaceController().setClosed();
				Controller.getController().getFaceController().init();
			}

			if (this.backAdmin) {
				this.backAdmin = false;
				Controller.getController().getFaceController().setClosed();
				Utils.showPopUp(Utils.BACK_TO_ADMIN_SCREEN, skin, imgStage, Utils.ADMIN_SCREEN_POP);
			}

			if (this.back) {
				this.back = false;

				if (Utils.captured) {
					Utils.backToRegistrationScreen = true;
					ScreenManager.getInstance().showScreen(new RegistrationCredentialsScreenCreator());
				} else {
					Utils.showMessageDialog(Utils.CANT_COME_BACK_WITHOUT_FACE_CAPTURE, skin, imgStage);
				}
			}
		} catch (Exception e) {
			ExceptionsManager.getExceptionsManager().manageException(e, skin, imgStage);
		}
	}

	public void dispose() {
		super.dispose();
		imgRegion.getTexture().dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
