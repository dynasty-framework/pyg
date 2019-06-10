package com.pyg.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * guid生成
 * @author airpei
 *
 */
public class UUIDFactory {

	public UUIDFactory() {
	}

	/**
	 * 生成guid
	 * @return
	 */
	public static String getUUIDStr() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		s = s.replaceAll("[-]", "");
		return s.toUpperCase();
	}

	public static String getNumberUUIDStr() {
		Calendar c = Calendar.getInstance();
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date.setTime(sdf.parse("1985-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long seconds = c.getTimeInMillis() - date.getTimeInMillis();
		return seconds + "";
	}

}
