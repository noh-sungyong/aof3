/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.util
 * @File : StringUtil.java
 * @Title : String 관련 Util
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : String 관련 Util
 */
public class StringUtil extends StringUtils {
	/**
	 * Checks if is empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(Long l) {
		return l == null || l.equals(0L);
	}

	/**
	 * Checks if is not empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	/**
	 * Checks if is not empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isNotEmpty(Long l) {
		return !isEmpty(l);
	}

	/**
	 * if is null ""
	 * 
	 * @param str
	 * @return
	 */
	public static String safeStr(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * 마지막 토큰
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String lastToken(String str, String delimiter) {
		String[] parts = str.split(delimiter);
		return (parts.length == 0) ? "" : parts[parts.length - 1];
	}

	/**
	 * 소문자로 변환(safely)
	 * 
	 * @param src
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		} else {
			return str.toLowerCase();
		}
	}

	/**
	 * 대문자로 변환(safely)
	 * 
	 * @param src
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		} else {
			return str.toUpperCase();
		}
	}

	/**
	 * 주어진 숫자를 파일 사이즈 형식으로 리턴.
	 * 
	 * @param long (size)
	 * @return String
	 */
	public static String getFilesize(long size) {
		String[] suffix = { "B", "KB", "MB", "GB", "TB", "PB", "EB" };
		int unit = 1024;
		if (size < unit) {
			return size + " " + suffix[0];
		}
		int exp = (int)Math.floor(Math.log(size) / Math.log(unit));
		return (Math.floor(size / Math.pow(unit, exp) * 10) / 10) + " " + suffix[exp];
	}

	/**
	 * value를 pattern 으로 변환한다. 1234567, #,### -> 1,234,567
	 * 
	 * @param value
	 * @param pattern
	 * @return format nummber
	 */
	public static String getFormatNumber(String value, String pattern) {
		StringBuffer buffer = new StringBuffer();
		DecimalFormat df = new DecimalFormat(pattern);
		try {
			buffer.append(df.format(Long.parseLong(value)));
		} catch (NumberFormatException e) {
			buffer.append(df.format(Double.parseDouble(value)));
		}
		return buffer.toString();
	}

	/**
	 * sourceString을 factor만큼 반복
	 * 
	 * @param str
	 * @param times
	 * @return
	 */
	public static String repeat(String str, int times) {
		if (times < 1) {
			return "";
		}
		if (times == 1) {
			return str;
		}
		StringBuilder sb = new StringBuilder(times * str.length());
		while (times > 0) {
			sb.append(str);
			times--;
		}
		return sb.toString();
	}

	/**
	 * 첫문자를 대문자로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char first = str.charAt(0);
		char capitalized = Character.toUpperCase(first);
		return (first == capitalized) ? str : capitalized + str.substring(1);
	}

	/**
	 * 주어진 숫자의 길이 만큼 랜덤한 문자열을 만들어 리턴.
	 * 
	 * @param int (len)
	 * @return String
	 */
	public static String getRandomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += chars.charAt((int)(Math.random() * 100) % chars.length());
		}
		return ret;
	}

	/**
	 * unique 문자열
	 * 
	 * @return
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 주어진 문자열을 암호화.
	 * 
	 * @param String str
	 * @param String encryptKey
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String encrypt(String str, String encryptKey) throws InvalidKeyException, Exception {
		if (str == null || str.length() == 0) {
			return "";
		}
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(), "DESede");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		byte[] plainText = str.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plainText);

		String enc = encodeBase64(cipherText);
		enc = enc.replace("\r", "").replace("\n", "");
		return enc;
	}

	/**
	 * 주어진 문자열을 복호화.
	 * 
	 * @param String str
	 * @param String decryptKey
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String decrypt(String str, String decryptKey) throws InvalidKeyException, Exception {
		if (str == null || str.length() == 0) {
			return "";
		}
		str = str.replaceAll(" ", "+"); // http parameter로 넘어온 경우 + 가 space로
										// 바뀌므로.
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes(), "DESede");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		byte[] cipherText = cipher.doFinal(decodeBase64(str));
		return new String(cipherText, "UTF-8");
	}

	/**
	 * md5 encrypt
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String encryptMD5(String str) throws NoSuchAlgorithmException, IOException {
		if (str == null || str.length() == 0) {
			return "";
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes());
		byte[] md5Bytes = md5.digest();
		String enc = encodeBase64(md5Bytes);
		enc = enc.replace("\r", "").replace("\n", "");
		return enc;
	}

	/**
	 * base64 encoding
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static String encodeBase64(byte[] bytes) throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		encoder.encodeBuffer(bais, baos);
		byte[] buffer = baos.toByteArray();
		return new String(buffer);
	}

	/**
	 * base64 decoding
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeBase64(String str) throws IOException {
		BASE64Decoder encoder = new BASE64Decoder();
		ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		encoder.decodeBuffer(bais, baos);
		byte[] buffer = baos.toByteArray();
		return buffer;
	}

	/**
	 * escapeXML
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeXML(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		return Util.escapeXml(str);
	}

	/**
	 * unescapeXML
	 * 
	 * @param str
	 * @return
	 */
	public static String unescapeXML(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		StringBuffer buf = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; ++i) {
			char c = str.charAt(i);
			if (c == '&') {
				int pos = str.indexOf(";", i);
				if (pos == -1) { // Really evil
					buf.append('&');
				} else if (str.charAt(i + 1) == '#') {
					int val = Integer.parseInt(str.substring(i + 2, pos), 16);
					buf.append((char)val);
					i = pos;
				} else {
					String substr = str.substring(i, pos + 1);
					if (substr.equals("&amp;")) {
						buf.append('&');
					} else if (substr.equals("&lt;")) {
						buf.append('<');
					} else if (substr.equals("&gt;")) {
						buf.append('>');
					} else if (substr.equals("&quot;")) {
						buf.append('"');
					} else if (substr.equals("&apos;")) {
						buf.append('\'');
					} else {
						buf.append(substr);
					}
					i = pos;
				}
			} else {
				buf.append(c);
			}
		}
		return buf.toString();
	}

	/**
	 * 주어진 문자열을 모두 unicode로 변환하여 리턴.
	 * 
	 * @param String
	 * @return String
	 */
	public static String toUnicode(String str) {
		int length = str.length();
		char arrayBuffer[] = str.toCharArray();
		StringBuffer escapedBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int c = (int)arrayBuffer[i];
			escapedBuffer.append(("&#" + c).toCharArray());
		}
		return escapedBuffer.toString();
	}

	/**
	 * html 문자열의 html 태그를 모두 제거하여 리턴.
	 * 
	 * @param String
	 * @return String
	 */
	public static String stripTags(String s) {
		Pattern ignorecase = Pattern.compile("<\\w+(\\s(\"[^\"]*\"|'[^']*'|[^>])+)?>|<\\/\\w+>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = ignorecase.matcher(s);
		return matcher.replaceAll("");
	}

	/**
	 * html 문자열의 script를 제거하여 리턴.
	 * 
	 * @param String
	 * @return String
	 */
	public static String stripScripts(String s) {
		Pattern ignorecase = Pattern.compile("<script[^>]*>([\\S\\s]*?)<\\/script>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = ignorecase.matcher(s);
		return matcher.replaceAll("");
	}

	/**
	 * html 문자열의 style를 제거하여 리턴.
	 * 
	 * @param String
	 * @return String
	 */
	public static String stripStyles(String s) {
		Pattern ignorecase = Pattern.compile("<style[^>]*>([\\S\\s]*?)<\\/style>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = ignorecase.matcher(s);
		return matcher.replaceAll("");
	}

	/**
	 * 문자열 바꾸기.
	 * 
	 * @param s
	 * @param template
	 * @return String
	 */
	public static String replaceTemplate(String s, String[] template) {
		for (int i = 0; i < template.length; i++) {
			s = s.replaceAll("\\{" + (i + 1) + "\\}", template[i]);
		}
		return s;
	}

	/**
	 * html 관련 모든 태그 정보를 제거한다. <style> 제거 <script> 제거 <tag> 제거.
	 * 
	 * @param s
	 * @return
	 */
	public static String stripHtml(String s) {
		return stripTags(stripScripts(stripStyles(s)));
	}

	/**
	 * 범위내의 정수 중에서 random 값을 리턴.
	 * 
	 * @param range
	 * @return int
	 */
	public static int random(int rangeLow, int rangeHigh) {
		if (rangeLow > rangeHigh) {
			return 0;
		}
		int range = rangeHigh - rangeLow + 1;
		int len = 1;
		int r = range;
		while (r > 10) {
			len++;
			r = r / 10;
		}
		int random = ((int)(Math.random() * (Math.pow(10, len)))) % range;
		return random + rangeLow;
	}

	/**
	 * 범위 내의 정수 중에서 중복되지 않는 랜덤수를 length 배열로 리턴한다
	 * 
	 * @param rangeLow
	 * @param rangeHigh
	 * @param count
	 * @return
	 */
	public static int[] randoms(int rangeLow, int rangeHigh, int length) {
		if (length < 1) {
			return null;
		}
		if (rangeHigh - rangeLow + 1 < length) {
			length = rangeHigh - rangeLow + 1;
		}
		List<Integer> list = new ArrayList<Integer>();
		while (list.size() < length) {
			int random = random(rangeLow, rangeHigh);
			if (list.contains(random) == false) {
				list.add(random);
			}
		}
		int[] randoms = new int[length];
		for (int index = 0; index < list.size(); index++) {
			randoms[index] = ((Integer)list.get(index)).intValue();
		}
		return randoms;
	}

	/**
	 * Ant Path pattern 검사
	 * 
	 * @param path
	 * @param pattern
	 * @return boolean
	 */
	public static boolean matchAntPathPattern(String pattern, String path) {
		PathMatcher matcher = new AntPathMatcher();
		return matcher.match(pattern, path);
	}

	/**
	 * javascript의 unescape
	 * 
	 * @param s
	 * @return
	 */
	public static String unescape(String s) {
		StringBuffer buffer = new StringBuffer();
		char[] chars = s.toCharArray();
		int i;
		for (i = 0; i < chars.length; i++) {
			if (chars[i] == '%') {
				String hex;
				if (chars[i + 1] == 'u') { // 유니코드.
					hex = s.substring(i + 2, i + 6);
					i += 5;
				} else { // ascii
					hex = s.substring(i + 1, i + 3);
					i += 2;
				}
				try {
					buffer.append(new String(Character.toChars(Integer.parseInt(hex, 16))));
				} catch (NumberFormatException e) {
					buffer.append("%");
					i -= (hex.length() > 2 ? 5 : 2);
				}
			} else {
				buffer.append(chars[i]);
			}
		}

		return buffer.toString();
	}

	/**
	 * javascript의 escape
	 * 
	 * @param s
	 * @return
	 */
	public static String escape(String s) {
		char c;
		StringBuffer buffer = new StringBuffer();
		buffer.ensureCapacity(s.length() * 6);
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c)) {
				buffer.append(c);
			} else if (c < 256) {
				buffer.append("%");
				if (c < 16) {
					buffer.append("0");
				}
				buffer.append(Integer.toString(c, 16));
			} else {
				buffer.append("%u");
				buffer.append(Integer.toString(c, 16));
			}
		}
		return buffer.toString();
	}

	/**
	 * 두 문자열의 유사성 비교(Levenshtein Distance 알고리즘 적용)
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static float getSimilarity(String s, String t, boolean ignoreCase) {
		int n = s.length();
		int m = t.length();

		float maxLen = n < m ? m : n;
		if (maxLen == 0.0F) {
			return 1.0F;
		}

		if (n == 0 || m == 0) {
			return 0.0F;
		}

		float[][] distance = new float[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			distance[i][0] = i;
		}
		for (int j = 1; j <= m; j++) {
			distance[0][j] = j;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				float cost = 0.0F;
				if (ignoreCase) {
					cost = String.valueOf(s.charAt(i - 1)).equalsIgnoreCase(String.valueOf(t.charAt(j - 1))) ? 0.0F : 1.0F;
				} else {
					cost = s.charAt(i - 1) == t.charAt(j - 1) ? 0.0F : 1.0F;
				}
				distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1.0F, distance[i][j - 1] + 1.0F), distance[i - 1][j - 1] + cost);
			}
		}
		return 1.0F - distance[n][m] / maxLen;
	}

	/**
	 * buffer 에서 일치하는 charset을 리턴한다.
	 * 
	 * @param buffer
	 * @return
	 * @throws CharacterCodingException
	 */
	public static String getCharset(byte[] buffer) throws CharacterCodingException {
		String[] charsets = { "UTF-8", "EUC-KR", "CP437", "KSC-5601", "ISO-8859-1" };

		String matched = "";
		for (String charsetName : charsets) {
			Charset charset = Charset.forName(charsetName);
			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();
			boolean identified = false;
			try {
				decoder.decode(ByteBuffer.wrap(buffer));
				identified = true;
			} catch (CharacterCodingException e) {
				identified = false;
			}
			if (identified) {
				matched = charsetName;
				break;
			}
		}
		return matched;
	}

	/**
	 * userAgent의 값으로 부터 단말기 정보를 리턴한다. ios, android, iemobile, etcmobile, app, pc, etc.
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String detectDevice(String userAgent) {
		userAgent = userAgent.toLowerCase();
		// ipod, ipad, iphone
		if (userAgent.indexOf("ipod") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("iphone") > -1) {
			return "ios";
		}
		// android 계열
		if (userAgent.indexOf("android") > -1) {
			return "android";
		}
		// windows ce - 옴니아, symbian - nokia, blackberry
		if (userAgent.indexOf("windows ce") > -1 || userAgent.indexOf("symbian") > -1 || userAgent.indexOf("blackberry") > -1
				|| userAgent.indexOf("opera mobi") > -1) {
			return "etcmobile";
		}
		// windows phone
		if (userAgent.indexOf("iemobile") > -1) {
			return "iemobile";
		}
		// 4csoft - app
		if (userAgent.indexOf("4csoft") > -1) {
			return "app";
		}
		// windows, macintosh - pc(descktop)
		if (userAgent.indexOf("windows") > -1 || userAgent.indexOf("macintosh") > -1) {
			return "pc";
		}
		// otherwise - etc
		return "etc";

	}

	// public static void main(String[] args) throws Exception {
	// }

}
