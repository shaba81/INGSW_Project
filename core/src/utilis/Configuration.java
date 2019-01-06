package utilis;

/**
 * Classe delegata ad avere Stringhe utilizzate dalle classi del codice
 * per avere i valori del file .json
 * @author Antonio
 *
 */
public interface Configuration {

	/*
	 * email
	 */
	public static final String userEmail = "fantaleague.siw18@gmail.com";
	public String userPassword = "112358.siw18";

	/*
	 * sms
	 */
	public String baseUrlSms = "https://api.skebby.it/API/v1.0/REST/";
	public String messageQuality = "SI";
	public String userNameSms = "tagete";
	public String userPasswordSms = "pr08qwe03";

	/*
	 * DB set
	 */
	public String jdbc = "postgresql";
	public String host = "ingsw.ddns.net";
	public String port = "5432";
	public String database = "ingsw";
	public String username = "ingswuser";
	public String password = "ingswuser112358";
	public String driver = "org.postgresql.Driver";

	/*
	 * DB query
	 */
	public String nextId =  "select nextval('ingswschema.sequence_id') AS id;";
	public String insertSupply = "insert into ingswschema.supply values(nextval('ingswschema.sequence_id'));";
	public String updateUserAdminPass =  "update ingswschema.users set password_registration=crypt(?, gen_salt('bf')) where id_user=? and id_supply=?;";
	public String validateUserAdminPass = "select ingswschema.users.id_user, (password_registration= crypt(?, password_registration)) AS pswmatch from ingswschema.users where id_user=?;";
	public String updateUserAdmin = "update ingswschema.users set email=?, nick_name=?, telephone_number=?, path_image=?, is_administrator=? where id_user=?;";
	public String insertUserNormal = "insert into ingswschema.users(id_user,email,nick_name,telephone_number,path_image,is_administrator,id_supply) values (nextval('ingswschema.sequence_id'),?,?,?,?,?,1);";
	public String insertUserAdmin = "insert into ingswschema.users(email,nick_name,telephone_number,path_image,is_administrator,id_supply) values (?,?,?,?,?,1);";
	public String findById = "select email, telephone_number from ingswschema.users where id_user=?;";

	public String checkIfEmailExist = "select u.id_user from ingswschema.users u where u.email=?;";
	public String checkIfTelephoneNumberExist = "select u.id_user from ingswschema.users u where u.telephone_number=?;";
	public String checkIfNickNameExist = "select u.id_user from ingswschema.users u where u.nick_name=?;";

	public String isFirstRegistrationForThisForniture = " select u.email, u.nick_name, u.telephone_number from ingswschema.users u where u.id_supply=? or u.id_user=?;";

	public String deleteUserById = "delete from ingswschema.users where id_user=?;";
}