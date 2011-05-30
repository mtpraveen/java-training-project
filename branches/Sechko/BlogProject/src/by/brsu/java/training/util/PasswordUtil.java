package by.brsu.java.training.util;

import org.bouncycastle.crypto.digests.SHA256Digest;

public class PasswordUtil {
	public static String hashString(String text) {
		SHA256Digest sha256 = new SHA256Digest();
		byte[] input = text.getBytes();
		byte[] output = new byte[32];
		sha256.update(input, 0, input.length);
		sha256.doFinal(output, 0);
		String result = new String(output);
		return result;
	}
	
}
