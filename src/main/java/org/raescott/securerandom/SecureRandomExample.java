package org.raescott.securerandom;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
public class SecureRandomExample {
	static final char[] CHARACTERS = "ABCDEFGHJKLMNPQRSTWXYZ23456789".toCharArray();

	public static void main(String[] args) {
		SecureRandom secureRandom = null;
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algorithm for generating secure random numbers is missing from the JVM implementation.");
		}
		secureRandom.setSeed(System.currentTimeMillis());
		int number = 10;
		int length = 6;
		byte[] bytes = new byte[number];
		secureRandom.nextBytes(bytes);
		System.out.println("Output of the byte array from SecureRandom");
		for (int i = 0; i < number; ++i) {
			System.out.println(bytes[i]);
		}

		System.out.println("********************************************************************************");
		System.out.println("Output using RandomStringUtils with a SecureRandom algorithm.");
		for (int i = 0; i < number; ++i) {
			//String output = RandomStringUtils.random(length, CHARACTERS);
			// random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random)
			String output = RandomStringUtils.random(length, 0, CHARACTERS.length, false, false, CHARACTERS, secureRandom);
			System.out.println(output);
		}
	}
}
