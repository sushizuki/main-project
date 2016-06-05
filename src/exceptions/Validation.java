package exceptions;

import java.util.regex.Pattern;

public abstract class Validation {
	
	/**
	 * Check if a given string is not null or empty
	 * @param string - The string to be checked
	 * @return TRUE if the string is NOT empty, or FALSE if it does
	 */
	public static boolean isNotEmpty(final String string){
		
		boolean isNotEmpty = false;
		
		if(string != null){			
			isNotEmpty = !string.isEmpty();
		}else{
			isNotEmpty = false;
		}
		
		return isNotEmpty;
	}
	
	/**
	 * Check if a given string contains only numbers or decimal
	 * @param string - The string to be checked
	 * @return TRUE if it contains only numbers, or FALSE if it does not
	 */
	public static boolean isNumber(String string){
		
		boolean isNumber = false;
		
		if(string.matches("-?\\d+(\\.\\d+)?")){
			isNumber = true;
		}else{
			isNumber = false;
		}
		
		return isNumber;
	}
	
	/**
	 * Check if a given string contains only numbers within
	 * @param string - The string to be checked
	 * @return TRUE if it contains only numbers or decimal, or FALSE if it does not
	 */
	public static boolean containsOnlyNumbers(String string){
		
		boolean containsOnlyNumbers = false;
		
		if(isNotEmpty(string) && string.matches("[0-9]+")){
				containsOnlyNumbers = true;
		} else{
			containsOnlyNumbers = false;
		}
		
		return containsOnlyNumbers;
	}
	
	/**
	 * Check if a given string contains only alphabetical characters
	 * @param string - the string to be checked
	 * @return TRUE if it contains only letters, or FALSE if it does not
	 */
	public static boolean containsOnlyLetters(final String string){
		
		boolean containsOnlyLetters = false;
		
		if(isNotEmpty(string) && string.matches("[a-zA-Zà-úÀ-Ú]+")){			
			containsOnlyLetters = true;		
		} else{
			containsOnlyLetters = false;
		}
		
		return containsOnlyLetters;
	}
	
	/**
	 * Check if a given string contains only alphabetical characters or spaces within 
	 * @param string - The string to be checked
	 * @return TRUE if it contains only alphabetical characters or spaces, or FALSE if it does not
	 */
	public static boolean containsOnlyLettersAndSpaces(final String string){
		
		boolean containsOnlyLetters = false;
		
		if(isNotEmpty(string) && string.matches("[a-zA-Zà-úÀ-Ú\\s]+")){	
				containsOnlyLetters = true;			
		} else{
			containsOnlyLetters = false;
		}
		
		return containsOnlyLetters;
	}
	
	/**
	 * Check if a given number is positive 
	 * @param number - The integer to be checked
	 * @return TRUE if is bigger than 0, or FALSE if it does not
	 */
	public static boolean isPositive(double number){
		
		boolean isPositive = false;
		
		if(number > 0){
			isPositive = true;
		}else{
			isPositive = false;
		}
		
		return isPositive;
	}
	
	public static boolean isEmail(String email){
		
		boolean isEmail = false;
		Pattern emailPatern = Pattern.compile(".+@.+\.[a-z]+");
		
		if(number > 0){
			isPositive = true;
		}else{
			isPositive = false;
		}
		
		return isPositive;
	}
}
