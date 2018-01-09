package ie.gmit.sw;

public class PrimeFunction {
	/**
	 * Finds the next prime number larger than a starting integer.
	 * @param n Starting integer.
	 * @return The next prime number larger than starting integer.
	 */
	public static int nextPrime(int n) {
		boolean isPrime = false;

		int m = n;
		while(!isPrime) {
			isPrime = isPrime(++m);
		}	
		return(m);
	}

	/**
	 * Checks if integer is prime or not.
	 * This is based on the sieve of eratosthenes.
	 * This particular implementation is is based off information from:
	 * http://en.wikipedia.org/wiki/Primality_test
	 * 
	 * @param n Integer to check for primality.
	 * @return true if n is prime otherwise false.
	 */
	public static boolean isPrime(int n) {
		if(n == 1) return(false);
		else if(n == 2 || n == 3) return(true);
		else if(n % 2 == 0 || n % 3 == 0) return(false);
		else {
			for(int i = 5; i*i < n + 1; i += 6) {
				if(n % i == 0 || n % (i + 2) == 0) {
					return(false);
				}
			}
			return(true);
		}
	}
}
