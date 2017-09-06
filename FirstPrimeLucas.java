/** A program to find the first ten digit prime number in the digits of e * credit to Babji Prashanth, Chetty for help on creating long digit numbers * and primitive conversion algorithms.
*
 * This program takes advantage of taylor series to develop a definition of e
 * A Lucas Primality Test was used to test for primes
 * @author Naren Sivagnanadasan
 *
         
*/
import java.math.BigDecimal; 
import java.math.MathContext; 
import java.lang.Math;
 
public class FirstPrimeLucas {
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
                System.out.println("#" +x+ " 10 digit prime: " + test_number+ "@ digit:" + y);
                x = x + 1; //increase the count by one if a prime is found
            }
            y = y + 1; //increase the digit count
        }
    }
    public static BigDecimal calc_e() {
        BigDecimal e = BigDecimal.ONE;
        //the definition of e, big decimal object is required to hold the value
        BigDecimal factorial = BigDecimal.ONE;
        //factorial object, must be the same type as e to be able to use
        //the big decimal methods add and divide
        for(int i = 1; i < 500; i++) {
            //summation of the taylor series definition of e for the first
            //500 iterations 1/1+1/1+1/2....+1/499+1/500
            factorial = factorial.multiply(new BigDecimal(i * 1.0 + ""));
            e = e.add(new BigDecimal(1.0 + "").divide(factorial, new MathContext(10000)));
        }
        /*String eString = (e + "");    //supposed to print out e, computing resources run out of memory
        long eDef = Long.parseLong(eString);
        System.out.println("e = " +eDef);*/
        return e;
    }
    public static boolean isPrime (long n) {
        boolean prime = prime(n);
        return prime;
    }
       
     public static boolean prime(long n){
        double test = Math.pow(2,(n-1)) - 1;
        //Calculate the test number
        if (test % n == 0) {
            // check divisibility
            int[] prime_factors = find_prime_factors(n);
            for(int i = 0; i <= 40; i++ ) {
                if(prime_factors[i] != 0) {
                    //make sure not to divide by zero
                    double carmichael =  Math.pow(2,((n - 1) / prime_factors[i])) - 1;
                    if(carmichael % n == 0) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static int[] find_prime_factors(long n) {
        int[] factors = new int[40];
        int x = 0;
        //  #2 that divide n
        while (n % 2 == 0) {
            factors[x] = 2;
            x++;
            n = n / 2; 
        }
        // n is odd a. can skip one test (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i = i+2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                factors[x] = i;
                x++;
                n = n / i;
            } 
        }
        // final prime
        if (n > 2) {
            factors[x] = (int) n;
        }
        return factors;
    }
}
