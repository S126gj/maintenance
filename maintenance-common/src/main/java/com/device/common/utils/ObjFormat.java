package com.device.common.utils;


public class ObjFormat {

    public static final int NUMBER_SCALE = 2;// 数量位数
    public static final int PRICE_SCALE = 2;// 单价位数
    public static final int AMOUNT_SCALE = 2;// 金额位数

    public static Integer intFormat(Object v, int init) {
        if (v == null || v.toString().length() == 0) {
            return init;
        }
        if (v instanceof Integer) {
            return (Integer) v;
        }
        if (v instanceof Double) {
            return (int) ((Double) v).doubleValue();
        }
        if (v instanceof String) {
            return (int) (Double.parseDouble(v.toString()));
        }
        return Integer.parseInt(v.toString());
    }

    public static Integer intFormat(Object v) {
        return intFormat(v, 0);
    }

    /**
     * 提供String Double null空-->0
     *
     * @param v
     * @return Integer
     */
    public static Double doubleFormat(Object v) {
        if (v == null || v.toString().length() == 0) {
            return 0D;
        }
        if (v instanceof Double) {
            return (Double) v;
        }
        return Double.parseDouble(v.toString());
    }

    /**
     * 仅提供String (或者明确为String值的Object类型) null空-->""
     *
     * @param v
     * @return String
     */
    public static String strFormat(Object v) {
        if (v == null) {
            return "";
        }
        return v + "";
    }

    /**
     * 金额初始化
     *
     * @param v
     * @return
     */
    public static double formatAmount(Object v) {
        if (v == null || v.toString().length() == 0) {
            v = 0D;
        }
        return Arith.round(Double.parseDouble(v.toString()), AMOUNT_SCALE);
    }

    /**
     * double型数初始化 <br/> 四舍五入，保留x位小数<br/> 防止精度丢失
     */
    public static double format(Object v, int num) {
        if (v == null || v.toString().length() == 0) {
            v = 0D;
        }
        return Arith.round(Double.parseDouble(v.toString()), num);
    }
}
