package utils;

public class VerifUtils {
	public static boolean isValidMail(String str){
		/* RFC 5322 without double quote and square brackets address type */
		return str.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	}

	public static boolean isNumber(String str){
		/* Nombre composé de chiffre de 0 à 9 */
		return str.matches("[0-9]+");
	}
	
	public static boolean isFloat(String str){
		/* Nombre flottant ou non avec partie exposant ou non */
		return str.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
	}
	
	public static boolean isStreet(String str){
		/* Champ rue du type "numero Nom De La Rue" */
		return str.matches("[0-9]+ [A-Za-z- ]+");
	}
	
	public static boolean isZipCode(String str){
		/* Code postal à 5 chiffre */
		return str.matches("[0-9]{5}");
	}
	
	public static boolean isCity(String str){
		return str.matches("[A-Za-z- ]+");
	}
}
