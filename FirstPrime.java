/** A program to find the first ten digit prime number in the digits of e * credit to Babji Prashanth, Chetty for help on creating long digit numbers *and primitive conversion algorithms.
*
 * This program takes advantage of taylor series to develop a definition of e
 * A brute force method was used to test for primes
 * @author Naren Sivagnanadasan
 *
       
*/
import java.math.BigDecimal; 
import java.math.MathContext;  

public class FirstPrime {
    public static void main(String[] args) {
        BigDecimal eDefinition = BigDecimal.ONE;
        eDefinition = calc_e();
        //Receive the e definition from the below method
        String test_set = (eDefinition + "").substring(2);
        //remove the first 2 characters of e: "2, ."
        int x = 1;
        //set up to iterators to report digit number and number prime
        int y = 1;
        for(int i = 0; i < test_set.length() - 10; i++) {
            // iterate from the 0th digit to the 10th last digit
            long test_number = Long.parseLong(test_set.substring(i, i + 10));
            //remove the digits up to i and the digits 10 digits after i
            if(isPrime(test_number)) {
                System.out.println("#" +x+ " 10 digit prime: " + test_number+ " @ digit:" + y);
                x = x + 1;
            } 
        }
        //increase the count by one if a prime is found
        y = y + 1;
    }

    //increase the digit count
    public static BigDecimal calc_e(){
        BigDecimal e = BigDecimal.ONE;
        //the definition of e, big decimal object is required to
        //hold the value
        BigDecimal factorial = BigDecimal.ONE;
        //factorial object, must be the same type as e to be able
        //to use the big decimal methods add and divide
        for(int i = 1; i < 500; i++) {
            //summation of the taylor series definition of e for the
            //first 500 iterations 1/1+1/1+1/2....+1/499+1/500
            factorial = factorial.multiply(new BigDecimal(i * 1.0 + ""));
            e = e.add(new BigDecimal(1.0 + "").divide(factorial, new MathContext(10000)));
        }
        /*String eString = (e + "");
        //supposed to print out e, computing resources run out of memory
        long eDef = Long.parseLong(eString);
        System.out.println("e = " +eDef);*/
        return e;
    }
    
    public static boolean isPrime (long n) {
        if (n <= 1){
            //if the number is less than 1 it cannot be prime
            return false;
        }
        if (n % 2 == 0) {
            //if the number is divisable by 2 then is cannot be prime
            return false;
        }
        long m = (long) Math.sqrt(n);
        //there is no need to test past the square root of a number
        //since the square root is always a divisor of a number
        for (long i = 3; i <= m; i += 2) {
            //tests every number from three to the square root of n
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
} 
