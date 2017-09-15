package giantbing.zonlinks.com.giantbaselibrary.Util;

/**
 * Created by P on 2017/9/15.
 * 安全认证工具类
 *
 * @author giantbing
 */

public class ConvertUtil {
    // TODO: 2017/9/15 继续增加其他安全转换
    public static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                LogUtil.e(e1.toString());
                return defaultValue;
            }
        }
    }
}
