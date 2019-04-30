package oo.core.infrastructure.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 일자관련한 기능을 제공하는 유틸리티이다.
 * 
 * @author itt
 */
public class DateUtil {
	/**
	 * 최소 년월일(1900-01-01)
	 */
	public static final Date MIN = GregorianCalendar.getInstance().getTime();

	/**
	 * 일자형식 : 년월일(yyyyMMdd)
	 */
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";

	/**
	 * 일자형식 : 년월일시분초(yyyyMMddHHmmss)
	 */
	public static final String DATE_FORMAT_YMS = "yyyyMMddHHmmss";

	/**
	 * 외부의 직접적인 접근을 방지하기 위해 접근을 제한시킨 기본 생성자이다.
	 */
	private DateUtil() {
	}

	/**
	 * 현재 시간을 반환한다.
	 * 
	 * @return 현재시간
	 */
	public static Date now() {
		return GregorianCalendar.getInstance().getTime();
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 실제 일자를 문자열로 변환하여 반환한다.
	 * 
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 문자열로 변환된 일자
	 */
	public static String toYmdString(Date date) {
		return toString(DATE_FORMAT_YMD, date);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 문자열로 구성된 일자를 실제 일자로 변환하여 반환한다.
	 * 
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 변환된 일자
	 */
	public static Date toDateYmd(String date) {
		return toDate(DATE_FORMAT_YMD, date);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 일자를 변환하여 반환한다.
	 * 
	 * @param date
	 *            일자
	 * @return 일자형식으로 변환된 일자
	 */
	public static Date toDateYmd(Date date) {
		return toDate(DATE_FORMAT_YMD, date);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 두 일자가 동한지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 동일한 일자 여부
	 */
	public static boolean equalsDateYmd(Date date1, Date date2) {
		return equalsDate(DATE_FORMAT_YMD, date1, date2);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 일자2 보다 일자1이 이후 일자인지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean afterDateYmd(Date date1, Date date2) {
		return afterDate(DATE_FORMAT_YMD, date1, date2);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 일자2 보다 일자1이 이전 일자인지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean beforeDateYmd(Date date1, Date date2) {
		return beforeDate(DATE_FORMAT_YMD, date1, date2);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 일자1, 일자2가 시작일자 또는 종료일자와 같거나 일자1, 일자2가 시작일자와 종료일자 사이에
	 * 포함되는지 여부를 반환한다.
	 * 
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDateYmd(Date start, Date end, Date date1, Date date2) {
		return betweenDate(DATE_FORMAT_YMD, start, end, date1, date2);
	}

	/**
	 * 년월일(yyyyMMdd) 일자형식으로 일자가 시작일자 또는 종료일자와 같거나 일자가 시작일자와 종료일자 사이에 포함되는지 여부를
	 * 반환한다.
	 * 
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date
	 *            일자
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDateYmd(Date start, Date end, Date date) {
		return betweenDate(DATE_FORMAT_YMD, start, end, date);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 실제 일자를 문자열로 변환하여 반환한다.
	 * 
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 문자열로 변환된 일자
	 */
	public static String toYmsString(Date date) {
		return toString(DATE_FORMAT_YMS, date);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 문자열로 구성된 일자를 실제 일자로 변환하여 반환한다.
	 * 
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 변환된 일자
	 */
	public static Date toDateYms(String date) {
		return toDate(DATE_FORMAT_YMS, date);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자를 변환하여 반환한다.
	 * 
	 * @param date
	 *            일자
	 * @return 일자형식으로 변환된 일자
	 */
	public static Date toDateYms(Date date) {
		return toDate(DATE_FORMAT_YMS, date);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자를 변환하여 반환한다.
	 * 
	 * @param date
	 *            일자
	 * @param format
	 *            포맷
	 * @return 일자형식으로 변환된 일자
	 */
	public static Date toDateYms(Date date, String format) {
		return toDate(format, date);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 두 일자가 동한지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 동일한 일자 여부
	 */
	public static boolean equalsDateYms(Date date1, Date date2) {
		return equalsDate(DATE_FORMAT_YMS, date1, date2);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자2 보다 일자1이 이후 일자인지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean afterDateYms(Date date1, Date date2) {
		return afterDate(DATE_FORMAT_YMS, date1, date2);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자2 보다 일자1이 이전 일자인지 여부를 반환한다.
	 * 
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean beforeDateYms(Date date1, Date date2) {
		return beforeDate(DATE_FORMAT_YMS, date1, date2);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자1, 일자2가 시작일자 또는 종료일자와 같거나 일자1, 일자2가 시작일자와
	 * 종료일자 사이에 포함되는지 여부를 반환한다.
	 * 
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDateYms(Date start, Date end, Date date1, Date date2) {
		return betweenDate(DATE_FORMAT_YMS, start, end, date1, date2);
	}

	/**
	 * 년월일시분초(yyyyMMddHHmmss) 일자형식으로 일자가 시작일자 또는 종료일자와 같거나 일자가 시작일자와 종료일자 사이에
	 * 포함되는지 여부를 반환한다.
	 * 
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date
	 *            일자
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDateYms(Date start, Date end, Date date) {
		return betweenDate(DATE_FORMAT_YMS, start, end, date);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 주어진 일자형식으로 실제 일자를 문자열로 변환하여 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 문자열로 변환된 일자
	 */
	public static String toString(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		
		return sdf.format(date);
	}

	/**
	 * 주어진 일자형식으로 문자열로 구성된 일자를 실제 일자로 변환하여 반환한다.
	 * 
	 * @param pattern
	 *            일자형식
	 * @param date
	 *            문자열로 구성된 일자
	 * @return 변환된 일자
	 */
	public static Date toDate(String format, String date) {
		Date toDate = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			
			toDate = sdf.parse(date);
		} 
		catch (Throwable e) {
//			throw new IllegalArgException(CorErrCd.ESCORS001.name(), new Object[] {format, date}, e);
		}

		return toDate;
	}

	/**
	 * 주어진 일자형식으로 일자를 변환하여 반환한다.
	 * 
	 * @param pattern
	 *            일자형식
	 * @param date
	 *            일자
	 * @return 일자형식으로 변환된 일자
	 */
	public static Date toDate(String format, Date date) {
		Date toDate = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
						
			toDate = sdf.parse(sdf.format(date));
		} 
		catch (Throwable e) {
//			throw new IllegalArgException(CorErrCd.ESCORS001.name(), new Object[] {format, date}, e);
		}

		return toDate;
	}

	/**
	 * 주어진 일자형식으로 두 일자가 동한지 여부를 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 동일한 일자 여부
	 */
	public static boolean equalsDate(String format, Date date1, Date date2) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTime(toDate(format, date1));

		Calendar c2 = GregorianCalendar.getInstance();
		c2.setTime(toDate(format, date2));

		return c1.equals(c2);
	}

	/**
	 * 일자2 보다 일자1이 이후 일자인지 여부를 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean afterDate(String format, Date date1, Date date2) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTime(toDate(format, date1));

		Calendar c2 = GregorianCalendar.getInstance();
		c2.setTime(toDate(format, date2));

		return c1.after(c2);
	}

	/**
	 * 일자2 보다 일자1이 이전 일자인지 여부를 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 일자1이 이후 일자인지 여부
	 */
	public static boolean beforeDate(String format, Date date1, Date date2) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTime(toDate(format, date1));

		Calendar c2 = GregorianCalendar.getInstance();
		c2.setTime(toDate(format, date2));

		return c1.before(c2);
	}

	/**
	 * 주어진 일자형식으로 일자1, 일자2가 시작일자 또는 종료일자와 같거나 일자1, 일자2가 시작일자와 종료일자 사이에 포함되는지 여부를
	 * 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date1
	 *            일자1
	 * @param date2
	 *            일자2
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDate(String format, Date start, Date end, Date date1, Date date2) {
		return betweenDate(format, start, end, date1) && betweenDate(format, start, end, date2);
	}

	/**
	 * 주어진 일자형식으로 일자가 시작일자 또는 종료일자와 같거나 일자가 시작일자와 종료일자 사이에 포함되는지 여부를 반환한다.
	 * 
	 * @param format
	 *            일자형식
	 * @param start
	 *            시작일자
	 * @param end
	 *            종료일자
	 * @param date
	 *            일자
	 * @return 시작일자와 종료일자 사이에 포함여부
	 */
	public static boolean betweenDate(String format, Date start, Date end, Date date) {
		boolean isBetween = false;

		Calendar sDate = GregorianCalendar.getInstance();
		sDate.setTime(toDate(format, start));

		Calendar eDate = GregorianCalendar.getInstance();
		eDate.setTime(toDate(format, end));

		Calendar tDate = GregorianCalendar.getInstance();
		tDate.setTime(toDate(format, date));

		// before : 이후, after : 이전
		isBetween = (tDate.equals(sDate) || tDate.equals(eDate) || !(tDate.before(sDate) || tDate.after(eDate)));

		return isBetween;
	}
}
