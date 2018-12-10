package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RegisterScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Viewport viewport;
	private Stage stage;

	private TextureAtlas atlas;
	protected Skin skin;

	private Label uNameLabel;
	private Label pWordLabel;

	private TextField txtUsername;
	private String userName;
	private TextField txtPassword;
	private String passWord;
	
	private TextButton backButton;
	private boolean back = false;

	public RegisterScreen() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		camera.update();
		viewport.apply();

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
		Gdx.input.setInputProcessor(stage);

		// Creo table
		Table mainTable = new Table();
		// Dico alla table di riempire lo stage
		mainTable.setFillParent(true);
		// Allineo le cose nella table
		mainTable.center();
		
		backButton = new TextButton("Back", skin);

		// In ascolto di eventi
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				back = true;
			}
		});

		uNameLabel = new Label("Username: ", skin);
		pWordLabel = new Label("Password: ", skin);

		txtUsername = new TextField("", skin);
		txtUsername.setMessageText("UserName");

		txtPassword = new TextField("", skin);
		txtPassword.setPasswordCharacter('*');
		txtPassword.setPasswordMode(true);
		txtPassword.setMessageText("PassWord");

		mainTable.add(uNameLabel);
		mainTable.row();
		mainTable.add(txtUsername);
		mainTable.row();
		mainTable.add(pWordLabel);
		mainTable.row();
		mainTable.add(txtPassword);
		mainTable.row();
		mainTable.add(backButton);
		mainTable.row();

		// Aggiungo table allo stage
		stage.addActor(mainTable);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.end();

		stage.act();
		stage.draw();
		
		if (back) {
			back = false;
			ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
		}

		userName = txtUsername.getText();
		System.out.println(userName);
		passWord = txtPassword.getText();
		System.out.println(passWord);

	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		skin.dispose();
		atlas.dispose();

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
