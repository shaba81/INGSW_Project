package com.mygdx.controller.database.persistence.dao;

import java.util.ArrayList;

import com.mygdx.controller.database.model.User;

import utilis.Log;

public interface UserDAO {

	public String getIdUser()throws Exception;
	public boolean registration(User user) throws Exception;
	public void deleteUtente(String email) throws Exception;
	public int userExist(String email, String telephoneNumber, String nickName ) throws Exception;
	public boolean validateUserAdminCredentials(String password, String id) throws Exception;
	public boolean validateUserOneTimePAss(String password, String email) throws Exception;
	public boolean isFirstRegistrationForThisForniture(Long idSupply, String idUser) throws Exception;
	public String[] updateCredentilsAdministrator(String idUser, Long idSupply, String newPass) throws Exception;
	public void insertCommand(String idUser, String command) throws Exception;
	public ArrayList<Log> selectCommandLog() throws Exception;

}
