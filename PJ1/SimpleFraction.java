/*************************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are integers.
 *
 * Requirements:
 * 1. Implement interfaces: SimpleFractionInterface and Comparable (i.e. compareTo())
 * 2. Implement methods equals() and toString() from class Object
 * 3. Must work for both positive and negative fractions
 * 4. Must not reduce fraction to lowest term unless simplifySimpleFraction() is invoked 
 * 5. Must not reduce fraction to lowest term for operations add(), subtract(), multiply() and divide()
 * 6. For input 3/-10 & -3/-10, must convert them to -3/10 & 3/10 respectively (see Hint 2. below)
 * 7. Must display negative fraction as -x/y, 
 *    example: (-3)/10 or 3/(-10), must display as -3/10
 * 8. Must throw only SimpleFractionException in case of errors
 * 9. Must not add new or modify existing data fields
 * 10.Must not add new public methods
 * 11.May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 *    the numerator and the denominator by their greatest common denominator.
 *    The greatest common denominator of 4 and 8 is 4, so when you divide
 *    the numerator and denominator of 4/8 by 4, you get the fraction 1/2.
 *    The recursive algorithm which finds the greatest common denominator of
 *    two positive integers is implemnted (see code)
 *       
 * 2. It will be easier to determine the correct sign of a fraction if you force
 *    the fraction's denominator to be positive. However, your implementation must 
 *    handle negative denominators that the client might provide.
 *           
 * 3. You need to downcast reference parameter SimpleFractionInterface to SimpleFraction if  
 *    you want to use it as SimpleFraction. See add, subtract, multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed
 *
 ************************************************************************************/

package PJ1;
import java.math.*;

public class SimpleFraction implements SimpleFractionInterface, Comparable<SimpleFraction>
{
	// integer numerator and denominator
	private	int num;	
	private	int den;	

	public SimpleFraction()
	{
		// implement this method!
		// set fraction to default = 0/1
		this(0,1);
	}	// end default constructor

	public SimpleFraction(int num, int den)
	{
		// implement this method!
        this.setSimpleFraction(num, den);
	}	// end constructor

	public void setSimpleFraction(int num, int den)
	{
		// implement this method!
		// return SimpleFractionException if initialDenominator is 0
        this.num = num;
        this.den = den;

        //throw exception if den = 0
        if (this.den == 0)
        {
            throw new SimpleFractionException();
        }

        //convert symbols
        if(this.den < 0 && this.num < 0)
        {
            this.num *= -1;
            this.den *= -1;
        }
        else if (this.den < 0)
        {
            this.num *= -1;
            this.den *= -1;
        }
	}	// end setSimpleFraction


	public SimpleFractionInterface simplifySimpleFraction()
    {
		// implement this method!
		// return this fraction
        reduceSimpleFractionToLowestTerms();

        //point to interface type
        SimpleFractionInterface frac = new SimpleFraction(this.num, this.den);
        return frac;
	}

	public double toDouble()
	{
		// return double floating point value
		// implement this method!
		//convert to decimal
		return (double) this.num/ (double) this.den;
	}	// end toDouble 

	public SimpleFractionInterface add(SimpleFractionInterface secondFraction)
	{
		// implement this method!
		// use formula: a/b + c/d is (ad + cb)/(bd)
                // Note: do not reduce the result
        SimpleFraction frac2 = (SimpleFraction) secondFraction;
        int num2 = frac2.num;
        int den2 = frac2.den;

        int newNum = (this.num * den2) + (this.den * num2);
        int newDen = (this.den * den2);

        //point to interface type
        SimpleFractionInterface newFrac = new SimpleFraction(newNum, newDen);

		//throw exception if den = 0
        if (newDen == 0) { throw new SimpleFractionException();}

		return newFrac;
	}	// end add

	public SimpleFractionInterface subtract(SimpleFractionInterface secondFraction)
	{
		// implement this method!
		// use formula: a/b - c/d is (ad - cb)/(bd)
                // Note: do not reduce the result
        SimpleFraction frac2 = (SimpleFraction) secondFraction;
        int num2 = frac2.num;
        int den2 = frac2.den;

        int newNum = (this.num * den2) - (this.den * num2);
        int newDen = (this.den * den2);

        //point to interface type
        SimpleFractionInterface newFrac = new SimpleFraction(newNum, newDen);

		//throw exception if den = 0
        if (newDen == 0) { throw new SimpleFractionException();}

        return newFrac;
	}	// end subtract

	public SimpleFractionInterface multiply(SimpleFractionInterface secondFraction)
	{
		// implement this method!
		// use formula: a/b * c/d is (ac)/(bd)
                // Note: do not reduce the result
        SimpleFraction frac2 = (SimpleFraction) secondFraction;
        int num2 = frac2.num;
        int den2 = frac2.den;

        int newNum = this.num * num2;
        int newDen = this.den * den2;

		//point to interface type
        SimpleFractionInterface newFrac = new SimpleFraction(newNum, newDen);

		//throw exception if den = 0
        if (newDen == 0) { throw new SimpleFractionException();}

        return newFrac;
	}	// end multiply

	public SimpleFractionInterface divide(SimpleFractionInterface secondFraction)
	{
		// implement this method!
		// return SimpleFractionException if secondFraction is 0
		// use formula: a/b / c/d is (ad)/(bc)
                // Note: do not reduce the result
        SimpleFraction frac2 = (SimpleFraction) secondFraction;

        if (frac2.num == 0) { throw new SimpleFractionException();}

        //reciprocal
        int num2 = frac2.den;
        int den2 = frac2.num;

        frac2.num = num2;
        frac2.den = den2;

		//point to interface type
        SimpleFractionInterface newFrac = multiply(frac2);

		//throw exception if den = 0
        if (frac2.den == 0) { throw new SimpleFractionException();}

        return newFrac;
	}	// end divide


	public boolean equals(Object other)
	{
		// implement this method!
        SimpleFraction frac2 = (SimpleFraction) other;

        if ((this.num / this.den) == (frac2.num / frac2.den)) {return true;}

		return false;
	} // end equals


	public int compareTo(SimpleFraction other)
	{
		// implement this method!
		//less than
        if ((this.num/this.den) < (other.num/other.den))
        {
            return -1;
        }
        //more than
        else if ((this.num/this.den) > (other.num/other.den))
        {
            return 1;
        }

		return 0;
	} // end compareTo

	
	public String toString()
	{
		return num + "/" + den;
	} // end toString



        //-----------------------------------------------------------------
        //  private methods start here
        //-----------------------------------------------------------------

	/** Task: Reduces a fraction to lowest terms. */
	private void reduceSimpleFractionToLowestTerms()
	{
                // implement this method!
                //
                // Outline:
                // compute GCD of num & den
                // GCD works for + numbers.
                // So, you should eliminate - sign
                // then reduce numbers : num/GCD and den/GCD
        int gcd = GCD(Math.abs(this.num), Math.abs(this.den));

        if (gcd != 0)
        {
            this.num /= gcd;
            this.den /= gcd;
        }

		
	}	// end reduceSimpleFractionToLowestTerms

  	/** Task: Computes the greatest common divisor of two integers.
	 *  @param integerOne	 an integer
	 *  @param integerTwo	 another integer
	 *  @return the greatest common divisor of the two integers */
	private int GCD(int integerOne, int integerTwo)
	{
		 int result;

		 if (integerOne % integerTwo == 0)
			result = integerTwo;
		 else
			result = GCD(integerTwo, integerOne % integerTwo);

		 return result;
	}	// end GCD


	//-----------------------------------------------------------------
	//  Some tests are provided here 
	//  Make sure your results are same as expected results

	public static void main(String[] args)
	{
		SimpleFractionInterface firstOperand = null;
		SimpleFractionInterface secondOperand = null;
		SimpleFractionInterface result = null;
                double doubleResult = 0.0;

		System.out.println("\n=========================================\n");
		firstOperand = new SimpleFraction(-12, -20);
		System.out.println("Input numerator & denominator: -12 & -20");
		System.out.println("Result Fraction:\t\t" + firstOperand);
		System.out.println("\tExpected result :\t12/20\n");
		firstOperand.simplifySimpleFraction();
		System.out.println("\nAfter simplifySimpleFraction():" + firstOperand);
		System.out.println("\tExpected result :\t3/5\n");

		System.out.println("\n=========================================\n");
		firstOperand = new SimpleFraction(20, -40);
		System.out.println("Input numerator & denominator: 20 & -40");
		System.out.println("Result Fraction:\t\t" + firstOperand);
		System.out.println("\tExpected result :\t-20/40\n");
		firstOperand.simplifySimpleFraction();
		System.out.println("\nAfter simplifySimpleFraction():" + firstOperand);
		System.out.println("\tExpected result :\t-1/2\n");


		SimpleFraction nineSixteenths = new SimpleFraction(9, 16);  // 9/16
		SimpleFraction oneFourth = new SimpleFraction(1, 4);        // 1/4

		System.out.println("\n=========================================\n");
		// 7/8 + 9/16
		firstOperand = new SimpleFraction(7, 8);
		result = firstOperand.add(nineSixteenths);
		System.out.println("The sum of " + firstOperand + " and " +
				nineSixteenths + " is \t\t" + result);
		System.out.println("\tExpected result :\t\t184/128\n");

		if (result != null) {
		System.out.println("\nAfter simplifySimpleFraction():" + result.simplifySimpleFraction());
		System.out.println("\tExpected result :\t\t23/16\n");
		}

		System.out.println("\n=========================================\n");
		// 9/16 - 7/8
		firstOperand = nineSixteenths;
		secondOperand = new SimpleFraction(7, 8);
		result = firstOperand.subtract(secondOperand);
		System.out.println("The difference of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);
		System.out.println("\tExpected result :\t\t-40/128\n");


		System.out.println("\n=========================================\n");
		// 15/-2 * 1/4
		firstOperand = new SimpleFraction(15, -2); 
		result = firstOperand.multiply(oneFourth);
		System.out.println("The product of " + firstOperand	+
				" and " +	oneFourth + " is \t" + result);
		System.out.println("\tExpected result :\t\t-15/8\n");

		System.out.println("\n=========================================\n");
		// (-21/2) / (3/7)
		firstOperand = new SimpleFraction(-21, 2); 
		secondOperand= new SimpleFraction(3, 7); 
		result = firstOperand.divide(secondOperand);
		System.out.println("The quotient of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);
		System.out.println("\tExpected result :\t\t-147/6\n");

		System.out.println("\n=========================================\n");
		// -21/2 + 7/8
		firstOperand = new SimpleFraction(-21, 2); 
		secondOperand= new SimpleFraction(7, 8); 
		result = firstOperand.add(secondOperand);
		System.out.println("The sum of " + firstOperand	+
				" and " +	secondOperand + " is \t\t" + result);
		System.out.println("\tExpected result :\t\t-154/16\n");

		if (result != null) {
		System.out.println("\nAfter simplifySimpleFraction():" + result.simplifySimpleFraction());
		System.out.println("\tExpected result :\t\t-77/8\n");
		}
		System.out.println("\n=========================================\n");

                // 0/10, 5/(-15), (-22)/7
		firstOperand = new SimpleFraction(0, 10); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t0.0\n");
		firstOperand = new SimpleFraction(1, -3); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t-0.333333333...\n");
		firstOperand = new SimpleFraction(-22, 7); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t-3.142857142857143");
		System.out.println("\n=========================================\n");
		firstOperand = new SimpleFraction(-21, 2); 
		System.out.println("First = " + firstOperand);
		// equality check
		System.out.println("check First equals First: ");
		if (firstOperand.equals(firstOperand))
			System.out.println("Identity of fractions OK");
		else
			System.out.println("ERROR in identity of fractions");

		secondOperand = new SimpleFraction(-42, 4); 
		System.out.println("\nSecond = " + secondOperand);
		System.out.println("check First equals Second: ");
		if (firstOperand.equals(secondOperand))
			System.out.println("Equality of fractions OK");
		else
			System.out.println("ERROR in equality of fractions");

		// comparison check
		SimpleFraction first  = (SimpleFraction)firstOperand;
		SimpleFraction second = (SimpleFraction)secondOperand;
		
		System.out.println("\ncheck First compareTo Second: ");
		if (first.compareTo(second) == 0)
			System.out.println("SimpleFractions == operator OK");
		else
			System.out.println("ERROR in fractions == operator");

		second = new SimpleFraction(7, 8);
		System.out.println("\nSecond = " + second);
		System.out.println("check First compareTo Second: ");
		if (first.compareTo(second) < 0)
			System.out.println("SimpleFractions < operator OK");
		else
			System.out.println("ERROR in fractions < operator");

		System.out.println("\ncheck Second compareTo First: ");
		if (second.compareTo(first) > 0)
			System.out.println("SimpleFractions > operator OK");
		else
			System.out.println("ERROR in fractions > operator");

		System.out.println("\n=========================================");

		System.out.println("\ncheck SimpleFractionException: 1/0");
		try {
			SimpleFraction a1 = new SimpleFraction(1, 0);		    
		        System.out.println("Error! No SimpleFractionException");
		}
		catch ( SimpleFractionException fe )
           	{
              		System.err.printf( "Exception: %s\n", fe );
           	} // end catch
		System.out.println("Expected result : SimpleFractionException!\n");

		System.out.println("\ncheck SimpleFractionException: division");
		try {
			SimpleFraction a2 = new SimpleFraction();		    
			SimpleFraction a3 = new SimpleFraction(1, 2);		    
			a3.divide(a2);
		        System.out.println("Error! No SimpleFractionException");
		}
		catch ( SimpleFractionException fe )
           	{
              		System.err.printf( "Exception: %s\n", fe );
           	} // end catch
		System.out.println("Expected result : SimpleFractionException!\n");



	}	// end main
} // end SimpleFraction

