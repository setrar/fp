package chapter1

import org.scalatest._
import Figure_1_9._

class RSASpec extends FlatSpec with Matchers {

  "The Generate RSASpec" should "not fail" in {

    // generate primes
    val k = 10
    //    val (p, q) =generate(k, 2,50)

  }

  "The Margaret Rouse Example" should "not fail" in {

    // https://searchsecurity.techtarget.com/definition/RSA

    // two hardcoded primes
    val (p,q) = (11,13)

    // the modulus n used in the
    // RSA public key (n, e)
    val (n, e) = (p * q, 7)

    // RSA Private Key using p and q to extract the totient
    val d = privateKey((p,q), e)
    d shouldEqual 103

    // plaintext message
    val x = 9

    // ciphered message
    val c = encrypt(x, (n, e))

    c shouldEqual 48.0

    // Bob reads the plaintext message with the private key
    decrypt(c.toInt, d, n) shouldEqual x

  }

  "The Book Example " should "not fail" in {

    // two hardcoded primes
    val (p,q) = (5,11)

    // the modulus n used in the
    // RSA public key (n, e)
    val (n, e) = (p * q, 3)

    // RSA Private Key using p and q to extract the totient
    val d = privateKey((p,q), e)
    d shouldEqual 27

    // plaintext message
    val x = 13

    // ciphered message
    val c = encrypt(x, (n, e))

    c shouldEqual 52.0

    // Bob reads the plaintext message with the private key
    decrypt(c.toInt, d, n) shouldEqual x

  }

  "The Exercise 1.27 " should "not fail" in {

    // https://www.chegg.com/homework-help/consider-rsa-key-set-p-17-q-23-n-391-e-3-figure-19-value-d-chapter-1-problem-27-solution-9780073523408-exc

    // two hardcoded primes
    val (p,q) = (17,23)

    // the modulus n used in the
    // RSA public key (n, e)
    val (n, e) = (p * q, 3)

    // RSA Private Key using p and q to extract the totient
    val d = privateKey((p,q), e)
    d shouldEqual 235

    // plaintext message
    val x = 41

    // ciphered message
    val c = encrypt(x, (n, e))

    c shouldEqual 105.0

    // Bob reads the plaintext message with the private key
    decrypt(c.toInt, d, n) shouldEqual x

  }

  "The Exercise 1.28 " should "not fail" in {

    // two hardcoded primes
    val (p,q) = (7,11)

    // the modulus n used in the
    // RSA public key (n, e)
    val (n, e) = (p * q, 13)

    // RSA Private Key using p and q to extract the totient
    val d = privateKey((p,q), e)
    d shouldEqual 37

    // plaintext message
    val x = 13

    // ciphered message
    val c = encrypt(x, (n, e))

    c shouldEqual 41.0

    // Bob reads the plaintext message with the private key
    decrypt(c.toInt, d, n) shouldEqual x

  }

}
