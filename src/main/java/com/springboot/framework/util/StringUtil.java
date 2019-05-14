package com.springboot.framework.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LJH
 * @version 1.0
 * @date 2018/1/28 13:00
 */
public class StringUtil {

    private static final String REGEX_EMOJI = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";

    /**
     * 判断是否为空，为空则返回true
     * 为空的条件：null、""、"null"
     */
    public static boolean isBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        String str = obj.toString().trim();
        return ("".equals(str) || "null".equalsIgnoreCase(str));
    }

    /**
     * 判断是否不为空，不为空则返回true
     * 为空的条件：null、""、"null"
     */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    /**
     * 获取换行符
     */
    public static String getNewLine() {
        return System.getProperty("line.separator");
    }

    /**
     * 获取uuid字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取uuid字符串，无横杠
     */
    public static String uuidNotLine() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 去除前后空格，若obj为null返回""空字串
     */
    public static String trim(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    /**
     * 指定长度UUID
     */
    public static String getUUID(int length) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        if (uuid.length() > length) {
            uuid = uuid.substring(uuid.length() - length, uuid.length());
        }
        return uuid;
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumeric(String str) {
        if (null == str || str.trim().length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串是否为空。
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1) {
            return false;
        }

        int i = 0;
        if (length > 1 && chars[0] == '-') {
            i = 1;
        }

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     */
    public static String stripNonValidXMLCharacters(String input) {
        if (input == null || ("".equals(input))) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            boolean existed = (current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF));
            if (existed) {
                out.append(current);
            }
        }
        return out.toString();
    }

    private static String numFormat(double d, String format) {
        return new DecimalFormat(format).format(d);
    }

    public static String numFormat(double d) {
        return numFormat(d, "#0.00");
    }

    /**
     * yyyyMMddHHmmss+param+6位随机数
     */
    public static String serialNum(Long param) {
        return DateUtil.format(new Date(), DateUtil.yyyyMMddHHmmss) + param + (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 返回对象所有值为null的字段 String空串也是为null
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            boolean existed = srcValue == null || (srcValue instanceof String && "".equals(srcValue.toString().trim()));
            if (existed) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 去除问题标签中无效的字符
     */
    public static String trimInvalidStr(String tag) {
        if (tag != null) {
            tag = tag.replaceAll("；", ";").replaceAll(" ", "").trim();
        }
        return tag;
    }

    /**
     * 判别是否包含Emoji表情
     */
    public static boolean containsEmoji(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (isEmojiCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 过滤emoji表情
     */
    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile(REGEX_EMOJI, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("");
                return source;
            }
            return source;
        }
        return source;
    }

    /**
     * 去除字符数组中的重复字符串
     */
    public static String[] removeDuplicate(String[] oldArray) {
        if (oldArray == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (String s : oldArray) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        String[] newArray = (String[]) list.toArray(new String[list.size()]);
        return newArray;
    }

    /**
     * 获取问题中的图片或音频的数量
     */
    public static Integer getNumByRegex(String content, String regex) {
        if (content == null) {
            return 0;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        int num = 0;
        while (m.find()) {
            num++;
        }
        return num;
    }

    /**
     * 随机n位数字
     */
    public static String random(int n) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, n - 1)) + "";
    }

    /**
     * 随机生成16位不重复的数字
     */
    public static String getOrderSNByUUId() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    public static String fixWithSingleQuotes(String values) {
        if (isBlank(values)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String[] valueArr = values.split(",");
        for (String value : valueArr) {
            sb.append("'").append(value).append("'").append(",");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }

        return "";
    }

}
