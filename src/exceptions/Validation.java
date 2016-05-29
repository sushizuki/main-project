package exceptions;

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
	 * Check if a given string contains only numbers within
	 * @param string - The string to be checked
	 * @return TRUE if it contains only numbers, or FALSE if it does not
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

}
