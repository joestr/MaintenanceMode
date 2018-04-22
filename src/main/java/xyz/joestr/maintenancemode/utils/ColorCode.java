package xyz.joestr.maintenancemode.utils;

public class ColorCode {
	
	public ColorCode() {
		
	}
	
	public static String toValid(String targetString, String colorCodeCharacter) {
		
	    return targetString.replace(colorCodeCharacter, "§");
	}
	
	public static String toAlternative(String targetString, String colorCodeCharacter) {
		
		return targetString.replace("§", colorCodeCharacter);
	}
}
