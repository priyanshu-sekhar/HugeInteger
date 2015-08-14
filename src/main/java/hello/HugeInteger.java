package hello;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HugeInteger implements HugeIntegerInterface{
	
	int[] hugeInteger;
	
	public HugeInteger(){
		
	}
	public HugeInteger(String hugeInteger){
		this.hugeInteger = parse(hugeInteger);
	}
	
	@RequestMapping(value = "add")
	public String add(@RequestParam(value = "integer") String integer,
			@RequestParam(value = "addTo") String hi){
		int[] hiArr = parse(hi);
		this.hugeInteger = parse(integer);
		int[] result = new int[(hugeInteger.length > hiArr.length ? hugeInteger.length: hiArr.length) + 1];
		ArrayUtils.reverse(hiArr); 
		ArrayUtils.reverse(this.hugeInteger);

		for (int i = 0; i < result.length - 1; i++){
			result[i] += hugeInteger[i] + hiArr[i];
			if (result[i] > 9) {
				result[i + 1] = result[i] / 10;
			    result[i] = result[i] % 10;
			}
		}
		
		ArrayUtils.reverse(result);
		hugeInteger = result;

		return toString();
	}

	@RequestMapping(value = "sub")
	public String sub(@RequestParam(value = "integer") String integer,
			@RequestParam(value = "subFrom") String hi){
	        int[] hiArr = parse(hi);
		this.hugeInteger = parse(integer);
		ArrayUtils.reverse(hiArr); 
		ArrayUtils.reverse(this.hugeInteger);

		if (this.isLessThan(new HugeInteger(hi))){

			int[] temp = hugeInteger;
			hugeInteger = hiArr;
			hiArr = temp;
		}
			
	    for (int i = 0; i < hugeInteger.length ; i++){
			hugeInteger[i] -= hiArr[i];
			if (hugeInteger[i] < 0) {
				hugeInteger[i] = 10 + hugeInteger[i];
				if (i != hugeInteger.length - 1)
				hugeInteger[i + 1] -= 1;
			}
		}
		ArrayUtils.reverse(hugeInteger);
		removeLeadingZeros(toString());
		return toString();
	}
	
	
	@RequestMapping("/isZero")
	public boolean isZero(){
		return Arrays.asList(ArrayUtils.toObject(hugeInteger)).stream()
		.map(t -> t == 0)
		.reduce(true, (x,y) -> x && y);
		
	}
	
	@RequestMapping("/HugeInteger")
	public boolean operations(@RequestParam(value = "integer")String val,
			@RequestParam(value = "operation")String operation,
			@RequestParam(value = "compare", defaultValue = "123")String hgInt){
	    this.hugeInteger = parse(val);
	    hgInt = removeLeadingZeros(hgInt);
	    HugeInteger comparedInteger = new HugeInteger(hgInt);
	    Predicate<Integer> isZero = t -> {return isZero();};
	   
	    Predicate<HugeInteger> isEqualTo = t -> {return isEqualTo(t);};
	    Predicate<HugeInteger> isNotEqualTo = t -> {return isNotEqualTo(t);};
	    Predicate<HugeInteger> isGreaterThan = t -> {return isGreaterThan(t);};
	    Predicate<HugeInteger> isLessThan = t -> {return isLessThan(t);};
	    Predicate<HugeInteger> isGreaterThanOrEqualTo = t -> {return isGreaterThanOrEqualTo(t);};
	    Predicate<HugeInteger> isLessThanOrEqualTo = t -> {return isLessThanOrEqualTo(t);};
	    
	    
	    switch(operation){
	    case "isZero":
	    	return isZero.test(7);
	    case "isEqualTo":
	    	return isEqualTo.test(comparedInteger);
	    case "isNotEqualTo":
	    	return isNotEqualTo.test(comparedInteger);
	    case "isGreaterThan":
	    	return isGreaterThan.test(comparedInteger);
	    case "isLessThan":
	    	return isLessThan.test(comparedInteger);
	    case "isGreaterThanOrEqualTo":
	    	return isGreaterThanOrEqualTo.test(comparedInteger);
	    case "isLessThanOrEqualTo":
	    	return isLessThanOrEqualTo.test(comparedInteger);
	    }
		return true;
	}
	
	@Override
	@RequestMapping("/toString")
	public String toString(){
		StringBuilder sb = new StringBuilder();
        for(Object obj : hugeInteger) sb.append(obj.toString());
        return sb.toString();
	}
	
	private String removeLeadingZeros(String hi){
		while (hi.charAt(0) - ZERO == 0 && hi.length() > 1)
			hi = hi.substring(1);
		return hi;
	}
	
	private int[] parse(String hgInt){
		hgInt = removeLeadingZeros(hgInt);
		String[] intArr = hgInt.split("");
		int[] hugeInteger = new int[intArr.length];
		for (int i = 0; i < intArr.length; i++)
			hugeInteger[i] = Integer.parseInt(intArr[i]);
		
		return hugeInteger;
	}

	@Override
	public Boolean isEqualTo(HugeInteger that) {
		String hi = that.toString();
		if (hi.length() != hugeInteger.length)
			return false;
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] != hi.charAt(i) - 48)
				return false;
		return true;
	}

	@Override
	public Boolean isNotEqualTo(HugeInteger that) {
		return !isEqualTo(that);
	}

	@Override
	public Boolean isGreaterThan(HugeInteger that) {
		String hi = that.toString();
		if (hugeInteger.length != hi.length())
			return hugeInteger.length > hi.length();
		
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] < hi.charAt(i) - ZERO)
				return false;
		return isNotEqualTo(that);	
	}

	@Override
	public Boolean isLessThan(HugeInteger that) {
		return !isGreaterThan(that);
	}

	@Override
	public Boolean isGreaterThanOrEqualTo(HugeInteger that) {
		return isGreaterThan(that) || isEqualTo(that);
	}

	@Override
	public Boolean isLessThanOrEqualTo(HugeInteger that) {
		return isLessThan(that) || isEqualTo(that);
	}
	
}
