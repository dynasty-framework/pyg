package com.pyg.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 钱币转换
 */
public class MoneyUtil {

    private static final String ENTIRE = "整";
    /**
     * 大写数字
     */
    private static final String[] NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 整数部分的单位
     */
    private static final String[] IUNIT = {"圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};

    /**
     * 汉字数字（不包含圆）的整数部分,最后一位为空
     */
    private static final String[] IUNIT_NOMONEY = {"","拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};

    /**
     * 小数部分的单位
     */
    private static final String[] DUNIT = {"角", "分", "厘"};

    /**
     * 标度lixinping
     */
    private static int newScale = 2;
    private static int roundingMode = BigDecimal.ROUND_HALF_UP;
    /**
     * 将钱币使用逗号分隔
     */
    private static String pattern="###,##0.00";

    /**
     * 转换数字后按位输出汉字
     */
    public static String outputChinese(String str){
    	String result = "";
    	String[] s = str.split("");
    	for(int i=0;i<s.length;i++){
    		if(!"".equals(s[i]) && s[i]!=null)
    			result += NUMBERS[Integer.parseInt(s[i])];
    	}
    	return result;
    }

    /**
     * 转换数字后按位输出汉字
     */
    public static String outputChinese(Integer sint){
    	String result = "";
    	String str = sint.toString();
    	String[] s = str.split("");
    	for(int i=0;i<s.length;i++){
    		if(!"".equals(s[i]) && s[i]!=null)
    			result += NUMBERS[Integer.parseInt(s[i])];
    	}
    	return result;
    }

    /**
     * 得到大写金额。
     */
    public static String toChinese(String str) {
        boolean flag = false;
        if(str.indexOf("-")==0){
            str=str.substring(1);
            flag = true;
        }
        str = str.replaceAll(",", "");// 去掉","
        String integerStr;// 整数部分数字
        String decimalStr;// 小数部分数字
        String entire = "";
        // 初始化：分离整数部分和小数部分
        if (str.indexOf(".") > 0) {
            integerStr = str.substring(0, str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
            entire = checkedEntire(integerStr, decimalStr);
        } else if (str.indexOf(".") == 0) {
            integerStr = "";
            decimalStr = str.substring(1);
            entire = checkedEntire(decimalStr);

        } else {
            integerStr = str;
            decimalStr = "";
            entire = checkedEntire(integerStr, "0");
        }
        // integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
        if (!integerStr.equals("")) {
            integerStr = Long.toString(Long.parseLong(integerStr));
            if (integerStr.equals("0")) {
                integerStr = "";
            }
        }
        // overflow超出处理能力，直接返回
        if (integerStr.length() > IUNIT.length) {
            System.out.println(str + ":超出处理能力");
            return str;
        }

        int[] integers = toArray(integerStr);// 整数部分数字
        boolean isMust5 = isMust5(integerStr);// 设置万单位
        int[] decimals = toArray(decimalStr);// 小数部分数字
        return flag?"(负)"+getChineseInteger(integers, isMust5) + getChineseDecimal(decimals) + entire:getChineseInteger(integers, isMust5) + getChineseDecimal(decimals) + entire;
    }

    /**
     * 得到大写金额。
     */
    public static String toChinese(BigDecimal db) {
        boolean flag = false;
        String str = db.toString();
        if(str.indexOf("-")==0){
            str=str.substring(1);
            flag = true;
        }
        str = str.replaceAll(",", "");// 去掉","
        String integerStr;// 整数部分数字
        String decimalStr;// 小数部分数字
        String entire = "";
        // 初始化：分离整数部分和小数部分
        if (str.indexOf(".") > 0) {
            integerStr = str.substring(0, str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
            entire = checkedEntire(integerStr, decimalStr);
        } else if (str.indexOf(".") == 0) {
            integerStr = "";
            decimalStr = str.substring(1);
            entire = checkedEntire(decimalStr);

        } else {
            integerStr = str;
            decimalStr = "";
            entire = checkedEntire(integerStr, "0");
        }

        // overflow超出处理能力，直接返回
        if (integerStr.length() > IUNIT.length) {
            System.out.println(str + ":超出处理能力");
            return str;
        }

        int[] integers = toArray(integerStr);// 整数部分数字
        boolean isMust5 = isMust5(integerStr);// 设置万单位
        int[] decimals = toArray(decimalStr);// 小数部分数字
        return flag?"(负)"+getChineseInteger(integers, isMust5) + getChineseDecimal(decimals) + entire:getChineseInteger(integers, isMust5) + getChineseDecimal(decimals) + entire;
    }

    /**
     * 整数部分和小数部分转换为数组，从高位至低位
     */
    private static int[] toArray(String number) {
        int[] array = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return array;
    }

    private static String checkedEntire(String str) {
        try {
            int i = Integer.parseInt(str);
            if (i == 0) {
                return MoneyUtil.NUMBERS[0] + MoneyUtil.IUNIT[0] + MoneyUtil.ENTIRE;
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    private static String checkedEntire(String fir, String str) {
        try {
            int j = Integer.parseInt(fir);
            int i = Integer.parseInt(str);
            if (i == 0 && j == 0) {
                return MoneyUtil.NUMBERS[0] + MoneyUtil.IUNIT[0] + MoneyUtil.ENTIRE;
            }
            if (i == 0) {
                return MoneyUtil.ENTIRE;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ;
            return "";
        }
        return "";
    }

    /**
     * 得到中文金额的整数部分。
     */
    private static String getChineseInteger(int[] integers, boolean isMust5) {
        StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;
        for (int i = 0; i < length; i++) {
            // 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
            // 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
            String key = "";
            if (integers[i] == 0) {
                if ((length - i) == 13)// 万(亿)(必填)
                    key = IUNIT[4];
                else if ((length - i) == 9)// 亿(必填)
                    key = IUNIT[8];
                else if ((length - i) == 5 && isMust5)// 万(不必填)
                    key = IUNIT[4];
                else if ((length - i) == 1)// 元(必填)
                    key = IUNIT[0];
                // 0遇非0时补零，不包含最后一位
                if ((length - i) > 1 && integers[i + 1] != 0)
                    key += NUMBERS[0];
            }
            chineseInteger.append(integers[i] == 0 ? key
                    : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
        }
        return chineseInteger.toString();
    }

    /**
     * 转换数字 11 -> 壹拾壹
     */
    private static String toChineseNumber(int[] integers){
    	StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;
        for (int i = 0; i < length; i++) {
            String key = "";
            chineseInteger.append(integers[i] == 0 ? key
                    : (NUMBERS[integers[i]] + IUNIT_NOMONEY[length - i - 1]));
        }
        return chineseInteger.toString();
    }

    /**
     * 得到中文金额的小数部分。
     */
    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");
        for (int i = 0; i < decimals.length; i++) {
            // 舍去3位小数之后的
            if (i == 3)
                break;
            chineseDecimal.append(decimals[i] == 0 ? ""
                    : (NUMBERS[decimals[i]] + DUNIT[i]));
        }
        return chineseDecimal.toString();
    }

    /**
     * 判断第5位数字的单位"万"是否应加。
     */
    private static boolean isMust5(String integerStr) {
        int length = integerStr.length();
        if (length > 4) {
            String subInteger = "";
            if (length > 8) {
                // 取得从低位数，第5到第8位的字串
                subInteger = integerStr.substring(length - 8, length - 4);
            } else {
                subInteger = integerStr.substring(0, length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        } else {
            return false;
        }
    }

    /**
     * 保留钱币的两位小数
     *
     * @param bd
     * @return
     */
    public static String round(BigDecimal bd) {
        if (bd == null) {
            return "0";
        }
        String str = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String divide(BigDecimal money, int count) {
        if(money == null || count == 0) {
            return "0";
        }
        return money.divide(new BigDecimal(count), 4, RoundingMode.HALF_DOWN).toString();
    }

    public static String divide(BigDecimal money, long count) {
        if(money == null || count == 0) {
            return "0";
        }
        return money.divide(new BigDecimal(count), 4, RoundingMode.HALF_DOWN).toString();
    }

    /**
     * 保留钱币的 scale 位小数
     *
     * @param bd
     * @return
     */
    public static String round(BigDecimal bd, Integer scale) {
        if (bd == null) {
            return "0";
        }

        if(scale <= 0) {
            scale = 4;
        }

        String str = bd.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String formatMoney(BigDecimal money){
        if(money == null){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(money);
    }

    public static BigDecimal  setScale(BigDecimal bigDecimal){
    	return  bigDecimal==null?new BigDecimal("0"):bigDecimal.setScale(newScale, roundingMode);
    }

    public static String formatPercentage(Double d){
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(d);
        return r;
    }


    /**
     * 支票月,1-10月 前面加零，11、12月不加
     * @param month
     * @return
     */
    public static String toZPMonth(String month){
    	String result = "";
    	int m = Integer.parseInt(month);
    	int[] mr = toArray(month);
    	if(m<=10){
    		result = NUMBERS[0] + toChineseNumber(mr);
    	}else{
    		result = toChineseNumber(mr);
    	}
    	return result;
    }

    /**
     * 支票日，小于10一律加"零"
     * @param day
     * @return
     */
	public static String toZPDay(String day){
		String result = "";
    	int d = Integer.parseInt(day);
    	int[] dr = toArray(day);
    	if(d<10){
    		result = NUMBERS[0] + toChineseNumber(dr);
    	}else{
    		result = toChineseNumber(dr);
    	}
   		return result;
	}

    /**
     * 取得Date的年
     * @param date
     * @return  int 如果date==null return 1900
     */
    public int getYear(Date date){
        if(date == null)return 1900;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public String toZpYear(Date date){
        return outputChinese(getYear(date));
    }

    /**
     * 取得Date的月
     * @param date
     * @return  int 如果date==null return 1
     */
    public int getMouth(Date date){
        if(date == null)return 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    public String toZPMonth(Date date){
        return toZPMonth(String.valueOf(getMouth(date)));
    }

    public String getMonthStr(Date date){
        return getMouth(date)>9?String.valueOf(getMouth(date)):"0" + getMouth(date);
    }

    /**
     * 取得Date的日
     * @param date
     * @return  int 如果date==null return 1
     */
    public int getDay(Date date){
        if(date == null)return 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public String getDayStr(Date date){
        return getDay(date)>9?String.valueOf(getDay(date)):"0" + getDay(date);
    }

    public String toZPDay(Date date){
        return toZPDay(String.valueOf(getDay(date)));
    }



    /**
     * 通过金额得到字符串数组,不包含 ".",保留两位小数
     * @param summoney
     * @return
     */
    public String[] getStr(BigDecimal summoney) {
        DecimalFormat format = new DecimalFormat("#.00");
        String sumStr = format.format(summoney);
        String splitStr = sumStr.replaceAll("[.]","");
        String[] temp = splitStr.split("");
        String[] result = new String[12];
        int count = 0;
        for(int i=0;i<12;i++){
            //前几位替换为@E
            if(i<12-temp.length){
                result[i] = "@E";
                count ++;
            }
            //第一个替换为@N
            if(i==(12-temp.length)){
                result[i] = "@N";
                count++;
            }
            if(i>(12-temp.length)){
                result[i] = temp[i-count+1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
    	BigDecimal bigDecimal=new BigDecimal(9);
    	System.out.println(MoneyUtil.setScale(bigDecimal));

    }
}