package com.spicstome.server;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Encryption {

	public static String toSHA256(String s) {
		byte[] hash = null;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return Base64.encode(hash);
	}
}
