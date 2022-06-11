package TestExcercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeTest {
    @Test
    void test_isPrime_0(){
        assertFalse(Prime.isPrime(0));
    }

    @Test
    void test_isPrime_1(){
        assertFalse(Prime.isPrime(1));
    }

    @Test
    void test_isPrime_2(){
        assertTrue(Prime.isPrime(2));
    }

    @Test
    void test_isPrime_3(){
        assertTrue(Prime.isPrime(3));
    }

    @Test
    void test_isPrime_5(){
        assertTrue(Prime.isPrime(5));
    }

    @Test
    void test_isPrime_negative_11(){
        assertFalse(Prime.isPrime(-2));
    }

    @Test
    void test_isPrime_2147483647(){
        assertTrue(Prime.isPrime(2147483647));
    }

    @Test
    void test_isPrime_4(){
        assertFalse(Prime.isPrime(4));
    }
}