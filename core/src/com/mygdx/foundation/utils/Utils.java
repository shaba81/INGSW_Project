package com.mygdx.foundation.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import org.opencv.core.Mat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.gson.Gson;
import com.mygdx.application.screen_manager.ScreenManager;
import com.mygdx.domain.controller.Controller;
import com.mygdx.domain.models.User;
import com.mygdx.services.factory_methos_screens.AdministrationScreenCreator;
import com.mygdx.services.factory_methos_screens.HousimCreator;
import com.mygdx.services.factory_methos_screens.LoginScreenCreator;
import com.mygdx.services.factory_methos_screens.MainMenuScreenCreator;
import com.mygdx.services.factory_methos_screens.RegistrationCredentialsScreenCreator;
import com.mygdx.ui.graphics.game.Housim;

/**
 * Classe di utilità per la lettura del file .json
 * 
 * @author Antonio
 *
 */
public class Utils {

	public static boolean stopCamera = true;
	public static boolean isFirstAccess = false;
	public static boolean capturing = false;
	public static boolean isAccess = true;
	public static boolean captured = false;
	public static boolean backToRegistrationScreen = false;
	public static boolean treeTimesAccessError = false;
	public static boolean songPlay = false;
	public static boolean changeUserCredentials = false;
	public static Vector3 positionVector = new Vector3(0f, 15f, 50f);
	public static int countErrorTimes = 0;

	public static ArrayList<String> credentials = new ArrayList<String>();

	public static boolean doorIsOpen = false;

	public static final Long ID_SUPPLY = (long) 1;
	public static final String ID_ADMIN_USER = "5";

	public static final String OBJ_EMAIL_RECOVERY_PASS_ADMIN = "Recovery administrator credentials.";
	public static final String MESSAGE_RECOVERY_PASS_ADMIN_EMAIL = "Dear customer, \nhere are the new login credentials. The mode is always the same as the ID can enter the same assigned to it during purchase and as a password the new password mentioned at the bottom of the message.";
	public static final String MESSAGE_RECOVERY_PASS_ADMIN_SMS = "Dear customer, \nhere are the new login credentials.";

	public static final String MESSAGE_ONE_TIME_PASS = "Dear customer,\nbelow you will find the one-time-password to be entered in the appropriate field.\nRemember that this code will only be valid for one hour from now.";
	public static final String OGJ_ONE_TIME_PASS = "One time password";

	/*
	 * PATH per i file di configurazione
	 */
	public static final String CONFIG_PATH_EMAIL = "resources/config_email.json";
	public static final String CONFIG_PATH_SMS = "resources/config_sms.json";
	public static final String CONFIG_PATH_DB = "resources/config_db.json";
	public static final String DB_PATH_QUERY = "resources/query.json";

	/*
	 * PopUp string
	 */
	public static final String REGISTRATION_NOT_PERMITTED = "Sorry but the maximum limit of registered users has been reached.";
	public static final String ADMIN_REG_CRED_CHANGE_POPUP = "Are you sure do you want to change the credentials of ";
	public static final String ACCESS_BACK_TO_MAIN_SCREEN_POP = "Are you sure do you want to exit without access?";
	public static final String SCREEN_BACK_GAME_POPUP = "Are you sure you want to return to simulation without registration?";
	public static final String SCREEN_BACK_GAME_SIMU_POPUP = "Are you sure you want to return to simulation?";
	public static final String MAIN_MENU_BACK_POPUP = "Are you sure do you want to exit from simulation?";
	public static final String LOGIN_SCREEN_WRONG_CREDENTIAL_POPUP = "Wrong credentials, please click on ENTER or OK to reinsert the credentials.";
	public static final String LOGIN_SCREEN_BACK_POPUP = "Are you sure you want to quit from the Login Screen?";
	public static final String ADMINISTRATION_SCREEN_BACK_POPUP = "Are you sure you want to quit from the Administration Screen?";
	public static final String FACE_CAPTURE_SCREEN_BACK_POPUP = "Are you sure you want to quit from Face Capture screen?";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_BACK_POPUP = "Are you sure do you want to quit from Registration screen?";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_MISSING_CRED_POPUP = "Missing one or more of the three credentials.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_EMAIL_EXIST_POPUP = "Email already exists, , please click on ENTER or OK to continue.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_TELEPHONE_EXIST_POPUP = "Telephone number already exists, please click on ENTER or OK to continue.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_NICKNAME_EXIST_POPUP = "Nickname already exists, please click on ENTER or OK to continue.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_MISSING_AT_EMAIL_POPUP = "Wrong email format. This field must contain's '@' caharacter.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_MISSING_POINT_EMAIL_POPUP = "Wrong email format. This field must contain's '.' character.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_NUMBER_CONTAINS_LETTER_POPUP = "Wrong telephone numebr format. This field mustn't contain's any letter or other character.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_NUMBER_TOO_SHORT_POPUP = "Wrong telephone numebr format. The field must be composed of the '+' character at the beginning, \nthe next two numbers represent the area code and the next ten numbers the telephone number.";
	public static final String REGISTRATION_CREDENTIALS_SCREEN_MISSIN_PLUS_NUMBER_POPUP = "Wrong telephone numebr format. Missing the '+' character at the beginning of the number.";
	public static final String LOGIN_SCREEN_FIRST_ACCESS_POPUP = "Hi, this is the first request for access to the house. \nYou will be registered as an administrator of this simulation. Please insert your administration credentials to login.\nClick OK or press ENTER to continue recording.";
	public static final String REGISTRATION_FAILED_POPUP = "Sorry registration was not successful, the user already exists in Housim.";
	public static final String REGISTRATION_SUCCESS_POPUP = "The registration of the user has happened successfully.";
	public static final String LOGIN_SCREEN_TOO_MANY_FAILED_ATTEMPTS_POPUP = "Sorry, you've reached the maximum attempt limit for entering your credentials. \nYou will receive an email and a text message with the new credentials to log in. The old ones will no longer be valid. \nPlease check your email and mobile phone in order to enter your new credentials.";
	public static final String LOGIN_SCREEN_NO_ID_INSERT_POPUP = "The ID's field is empty. Please enter your ID for log in.";
	public static final String LOGIN_SCREEN_NO_PASSWORD_INSERT_POPUP = "The Password's field is empty. Please enter your Password for log in.";
	public static final String ALREADY_CAPTURE_FACE_POPUP = "Attention, the face has already been captured.\n Press REDO to recapture the face or proceed with registration.";
	public static final String ACCESS_FAILED_POPUP = "Access denied.\n Please, click OK or press ENTER to retry access.";
	public static final String ACCESS_FAILED_THREE_TIMES = "You tried too many times without success.\nPlease enter the email with which you registered or press ESCAPE to retry to access.\nThe field doens't have to be empty.";
	public static final String ACCESS_ONE_TIME_PASS = "Please check the email entered previously to which a code\nhas been sent from the validity of one hour with which you can access.\nPlease enter the code, the field doens't have to be empty..";
	public static final String ACCESS_RECOVERY_EMAIL_NOT_FOUND = "Email doesn't eixists in our system.\nPlease retry to enter the email with which you registered.\\nThe field doens't have to be empty.";
	public static final String ACCESS_ONE_TIME_PASS_NOT_MATCH = "Password entered not matches. Please check the email entered previously to which a code\nhas been sent from the validity of one hour with which you can access.\nPlease enter the code, the field doens't have to be empty..";
	public static final String CHANGE_USER_CREDENTIALS_SUCCESSFULLY_MESSAGE = "Credentials have been changed successfull!";
	public static final String CANT_COME_BACK_WITHOUT_FACE_CAPTURE = "Sorry, first you have to capture your face!";
	public static final String CANT_REGISTER_WITHOUT_FACE_CAPTURE = "Sorry, first you have to capture your face to change credentials!";
	public static final String BACK_TO_ADMIN_SCREEN = "Are you sure do you want to back to Administration Screen?";

	/*
	 * command for log
	 */
	public static final String ACCESS_FAILED_THREE_TIMES_LOG = "He tried to access three times without success.";
	public static final String ACCESS_SUCCESS_LOG = "Access to the house successfully executed.";
	public static final String ACCESS_FAILED_LOG = "Access to the house denied.";
	public static final String ACCESS_THREE_TIMES_FAILED_LOG = "Access to the house denied for three times.";
	public static final String LOGIN_ADMIN_SCREEN_SUCCESS_LOG = "Login to the administration screen successfully executed.";
	public static final String LOGIN_ADMIN_SCREEN_FAILED_LOG = "Login to the administration screen denied.";
	public static final String LOGIN_ADMIN_SCREEN_FAILED_THREE_TIMES_LOG = "Login to the administration screen denied for three times.";
	public static final String GO_OUT_HOME_LOG = "User goes out home.";
	public static final String EXIT_THE_SIMULATION_LOG = "User exits the simulation.";
	public static final String SUCCESS_USER_REGISTRATION_LOG = "User registration has been successful.";
	public static final String FAILURE_USER_REGISTRATION_LOG = "User registration has failed.";
	public static final String SUCCESS_ADMIN_REGISTRATION_LOG = "Administration registration has been successful.";
	public static final String REGISTRATION_CREDENTIALS_SUCCESSFULLY_INSERT = "User has successfully insered his registraion credentials.";
	public static final String ADMIN_REGISTERS_A_NEW_USER = "Admin registers a new user.";
	public static final String TV_ON_LOG = "He asked to switch on the television.";
	public static final String TV_OFF_LOG = "He asked to switch off the television.";
	public static final String LIGHT_ON_LOG = "He asked to switch on the light.";
	public static final String LIGHT_OFF_LOG = "He asked to switch off the light.";
	public static final String RADIO_ON_LOG = "He asked to switch on the stereo.";
	public static final String RADIO_OFF_LOG = "He asked to switch off the stereo.";
	public static final String FAN_ON_LOG = "He asked to switch on the fan.";
	public static final String FAN_OFF_LOG = "He asked to switch off the fan.";
	public static final String HELP_LOG = "He asked help";
	public static final String SAFEBOX_ON_LOG = "He asked to switch on the safebox";
	public static final String SAFEBOX_OFF_LOG = "He asked to switch off the safebox";
	public static final String DOORB_OPEN_LOG = "He asked to open door B";
	public static final String DOORA_OPEN_LOG = "He asked to open door A";

	public static final String CHANGE_USER_CREDENTIALS_SUCCESSFULLY = "User credentials have been changed successfully.";

	/*
	 * For popUp
	 */
	public static final String REGISTRATION_SCREEN_POP = "RegistrationScreen";
	public static final String MAIN_SCREEN_POP = "MainScreen";
	public static final String GAME_SCREEN_POP = "GameScreen";
	public static final String LOGIN_SCREEN_POP = "LoginScreen";
	public static final String ADMIN_SCREEN_POP = "AdminScreen";
	public static final String EXIT_POP = "Exit";

	/*
	 * Exceptions' messages
	 */
	public static final String ADDRESSE_INVALID_EMAIL_MESSAGE = "Invalid e-mail. \n Please click OK or ENTER to retry.";
	public static final String MESSAGINGE_MESSAGE = "Unable to connect to the e-mail service.\n Please check your internet connection.";
	public static final String IOE_CANT_READ_INPUT_FILE_MESSAGE = "Your file is corrupt or invalid.\n Please retry.";

	/**
	 * Vocal recognition variables
	 */
	public static String resp = "";

	/*
	 * User logged
	 */
	public static String userLogged = "-1";

	/*
	 * path of user
	 */
	public static String pathImageUser = "";

	/**
	 * Useful log method. If user vocal command contains one of the two words below,
	 * it will be saved on log.
	 * 
	 * @param word1      -> Possible user word command 1
	 * @param word2      -> Possible user word command 2
	 * @param lightOnLog
	 * @throws SQLException
	 */
	public static void commandLog(String word1, String word2, String command) throws SQLException {
		if (Utils.resp.contains(word1) || Utils.resp.contains(word2))
			saveOnLog(command);
	}

	public static void logout() {
		User user = new User();
		user.setIdUser("-1");
		Housim.getHousim().setUser(user);
		stopCamera = true;
		isFirstAccess = false;
		capturing = false;
		isAccess = true;
		captured = false;
		backToRegistrationScreen = false;
		treeTimesAccessError = false;
		if (!Housim.getHousim().primo) {
			songPlay = true;
		}

		changeUserCredentials = false;

//		GameScreen.getGameScreen().hc = true;
//		GameScreen.getGameScreen(). wcl = true;
//	    GameScreen.getGameScreen().primo = true;
		Housim.getHousim().contWrongCommand = 0;
		Housim.getHousim().inputManager.nAccessButton = false;
		// comandi
		Housim.getHousim().inputManager.isLightOn = false;
		Housim.getHousim().inputManager.isTvOn = false;
		Housim.getHousim().inputManager.activateFan = false;
		Housim.getHousim().inputManager.activateSpeaker = false;
		Housim.getHousim().inputManager.safeBox = false;

		countErrorTimes = 0;
		pathImageUser = "";
		resp = "";
		userLogged = "-1";
		credentials = new ArrayList<String>();
	}

	/**
	 * It's the method which saves on log the actual state of user(Ex: user home
	 * access, etc...)
	 * 
	 * @throws SQLException
	 */
	public static void saveOnLog(String command) throws SQLException {
		Controller.getController().getUserDAO().insertCommand(userLogged, command);
		resp = "";
	}

	public static void removeAfileInAFolder(File myfile) throws IOException {
		Files.deleteIfExists(myfile.toPath());
	}

	/**
	 * 
	 * @param original
	 * @return
	 */
	public static BufferedImage matToBufferedImage(Mat original) {
		// init
		BufferedImage image = null;
		int width = original.width(), height = original.height(), channels = original.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		original.get(0, 0, sourcePixels);

		if (original.channels() > 1) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		} else {
			image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		}
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

		return image;
	}

	/**
	 * Sposta il file dalla cartella 'tmep_image' alla 'images'
	 * 
	 * @param file
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void moveNewUserToImageFolder(String fromfolder_path, String fromimage_path, String tofolder_path,
			String toimage_path) throws IOException {
		// aggiungo il file nella cartella 'images'
		Files.move(Paths.get(fromfolder_path + "/" + fromimage_path), Paths.get(tofolder_path + "/" + toimage_path));

	}

	/**
	 * Metodo che prende come parametri Il Tipo di classe che ha all'interno i dati
	 * che saranno nel file .json {@link Configuration}, e il path dove si trova il
	 * file
	 * 
	 * @param type
	 * @param fileHandle
	 * @return
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")

	public static Object getJsonFile(@SuppressWarnings("rawtypes") Class type, String fileHandle)
			throws FileNotFoundException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileHandle));
		Gson gson = new Gson();
		return gson.fromJson(bufferedReader, type);
	}

	public static int lenghtUserNameForShowPopUp = 0;

	public static void showPopUp(String text, Skin skin, Stage stage, final String screenCall) {
		String text2 = text.substring(0, text.length() - lenghtUserNameForShowPopUp);

		Dialog dialog = new Dialog("", skin, "dialog") {
			public void result(Object obj) {
				if (obj.equals("true")) {
					if (screenCall.equals(MAIN_SCREEN_POP)) {
						Utils.countErrorTimes = 0;
						Utils.treeTimesAccessError = false;
						ScreenManager.getInstance().showScreen(new MainMenuScreenCreator());
					} else if (screenCall.equals(REGISTRATION_SCREEN_POP))
						ScreenManager.getInstance().showScreen(new RegistrationCredentialsScreenCreator());
					else if (screenCall.equals(GAME_SCREEN_POP))
						ScreenManager.getInstance().showScreen(new HousimCreator());
					else if (screenCall.equals(LOGIN_SCREEN_POP))
						ScreenManager.getInstance().showScreen(new LoginScreenCreator());
					else if (screenCall.equals(ADMIN_SCREEN_POP)) {
						captured = false;
						credentials.clear();
						if (text.equals(CHANGE_USER_CREDENTIALS_SUCCESSFULLY_MESSAGE)) {
							changeUserCredentials = false;
						} else if (text.equals(BACK_TO_ADMIN_SCREEN)) {
							if (Utils.captured)
								Utils.backToRegistrationScreen = true;

							Utils.changeUserCredentials = false;
						} else if (text.equals(REGISTRATION_CREDENTIALS_SCREEN_BACK_POPUP)) {
							changeUserCredentials = false;
							backToRegistrationScreen = false;
						}

						ScreenManager.getInstance().showScreen(new AdministrationScreenCreator());
					} else if (screenCall.equals(EXIT_POP))
						Gdx.app.exit();
				} else {
					if (text2.equals(ADMIN_REG_CRED_CHANGE_POPUP) && changeUserCredentials) {
						changeUserCredentials = false;
					} else if (text.equals(BACK_TO_ADMIN_SCREEN)) {
						Controller.getController().getFaceController().setClosed();

						if (!captured) {
							Controller.getController().getFaceController().init();
						}

					} else if (text.equals(ACCESS_BACK_TO_MAIN_SCREEN_POP)) {
						Controller.getController().getFaceController().init();
					}
				}

			}
		};
		dialog.text(text);
		dialog.button("Yes", "true"); // sends "true" as the result
		dialog.button("No", "false"); // sends "false" as the result
		dialog.key(Keys.ENTER, "true"); // sends "true" when the ENTER key is pressed
		dialog.show(stage);
		stage.addActor(dialog);
	}

	public static void showMessageDialog(String text, Skin skin, Stage stage) {
		Dialog dialog = new Dialog("", skin);
		dialog.text(text);
		dialog.button("Ok", "true");
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
		stage.addActor(dialog);
	}

	public static String getIdUserFromImage(String path) {
		for (int i = path.length() - 1, slashI = 0, pointI = path.length() - 4; i >= 0; i--) {
			if (path.charAt(i) == 's') {
				slashI = i + 2;
				path = path.substring(slashI, pointI);
				break;
			}
		}
		return path;
	}
}
