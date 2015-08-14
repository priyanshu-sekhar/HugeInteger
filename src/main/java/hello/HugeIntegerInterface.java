package hello;

public interface HugeIntegerInterface {
	/*
	 * Create a service HugeInteger which uses a 40-element array of digits to
	 * store integers as large as 40 digits each. Provide methods parse,
	 * toString, add and subtract. Method parse should receive a String, extract
	 * each digit using method charAt and place the integer equivalent of each
	 * digit into the integer array. For comparing HugeInteger objects, provide
	 * the following methods: isEqualTo, isNotEqualTo, isGreaterThan,
	 * isLessThan, isGreaterThanOrEqualTo and isLessThanOrEqualTo. Each of these
	 * is a predicate method that returns true if the relationship holds between
	 * the two HugeInteger objects and returns false if the relationship does
	 * not hold. Provide a predicate method isZero. If you feel ambitious, also
	 * provide methods multiply, divide and remainder. [Note: Primitive boolean
	 * values can be output as the word “true” or the word “false” with format
	 * specifier %b.]I
	 */
	public static final Integer NUMBER_OF_DIGITS = 40;
	public static final Character ZERO = '0';
	public static final String EMPTY_STRING="";
	public Integer[] number = new Integer[NUMBER_OF_DIGITS];

	public default void parseString(String digits) {
		for (int index=0; index < digits.length(); index++) {
			number[index] = digits.charAt(index) - ZERO;
		}
		
	}
	
	public Boolean isEqualTo(HugeInteger that);
	
	public Boolean isNotEqualTo(HugeInteger that);
	
	public Boolean isGreaterThan(HugeInteger that);
	
	public Boolean isLessThan(HugeInteger that);
	
	public Boolean isGreaterThanOrEqualTo(HugeInteger that);
	
	public Boolean isLessThanOrEqualTo(HugeInteger that);
	
	@Override
	public String toString();
	
}
