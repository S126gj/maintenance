package com.device.common.utils;

import cn.hutool.core.util.NumberUtil;
import com.device.common.exception.BIZException;

import java.math.BigDecimal;

public class Arith {

    /**
     * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
     * 确的浮点数运算，包括加减乘除和四舍五入。
     */
    private static final int DEF_DIV_SCALE = 10;

    private Arith(){
    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v2)));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v2)));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mulMore(Double v1, Double v2){
        v1 = ObjFormat.doubleFormat(v1);
        v2 = ObjFormat.doubleFormat(v2);
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }

    /**
     * 向上取整，避免出现小数
     * @param v1
     * @param v2
     * @return
     */
    public static int divUp(int v1,int v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2,BigDecimal.ROUND_UP).intValue();
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(ObjFormat.doubleFormat(v2)));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(Double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if(v==null){
            v=0D;
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double round(Object v,int scale){
        return round(ObjFormat.doubleFormat(v),scale);
    }


    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @param vs 其余加数
     * @return 和
     */
    public static double addMore(Object v1,Object v2,Object... vs){
        double result = add(ObjFormat.doubleFormat(v1), ObjFormat.doubleFormat(v2));
        for(Object v:vs){
            result = add(result, ObjFormat.doubleFormat(v));
        }
        return result;
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @param vs 其余减数
     * @return 差
     */
    public static double subMore(Object v1,Object v2,Object... vs){
        double result = sub(ObjFormat.doubleFormat(v1), ObjFormat.doubleFormat(v2));
        for(Object v:vs){
            result = sub(result, ObjFormat.doubleFormat(v));
        }
        return result;
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(Object v1,Object v2){
        try {
            v1=ObjFormat.doubleFormat(v1);
            v2=ObjFormat.doubleFormat(v2);
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch (ArithmeticException e){
            throw new BIZException("除数不能为0");
        }
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(Object v1,Object v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        v1=ObjFormat.doubleFormat(v1);
        v2=ObjFormat.doubleFormat(v2);
        try {
            BigDecimal b1 = new BigDecimal(v1.toString());
            BigDecimal b2 = new BigDecimal(v2.toString());
            return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch (ArithmeticException e){
            throw new BIZException("除数不能为0");
        }
    }
    /**
     * 提供精确的除法运算。
     * @param v1 被除数
     * @param v2 除数
     * @param vs 其余除数
     * @return 商
     */
    public static double divMore(Object v1,Object v2,Object... vs){
        try {
            double result = div(v1, v2);
            for(Object v:vs){
                result = div(result, v);
            }
            return result;
        }catch (ArithmeticException e){
            throw new BIZException("除数不能为0");
        }
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mulMore(Object v1, Object v2, Object...vs){
        double result = mulMore(ObjFormat.doubleFormat(v1), ObjFormat.doubleFormat(v2));
        for(Object v:vs){
            result = mulMore(result, ObjFormat.doubleFormat(v));
        }
        return result;
    }

    /**
     * 四舍五入保留指定小数
     * @param v
     * @return
     */
    public static double halfUp(int scale, Object v){
        return BigDecimal.valueOf(ObjFormat.doubleFormat(v)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 向上取整
     * @param v
     * @return
     */
    public static int ceil(Object v){
        Double result = Math.ceil(ObjFormat.doubleFormat(v));
        return result.intValue();
    }

    /**
     * 向下取整
     * @param v
     * @return
     */
    public static int floor(Object v){
        Double result = Math.floor(ObjFormat.doubleFormat(v));
        return result.intValue();
    }
    /**是否为奇数*/
    public static Boolean isOdd(int a) {
        return (a & 1) == 1;
    }
    /**向下取偶*/
    public static int floorEven(Object v) {
        int tmp = floor(v);
        if(isOdd(tmp)){
            return tmp-1;
        }else{
            return tmp;
        }
    }
    /**向上取偶*/
    public static int ceilEven(Object v) {
        int tmp = ceil(v);
        if(isOdd(tmp)){
            return tmp+1;
        }else{
            return tmp;
        }
    }

    /**
     * 获取完成率
     * @param finishNumber
     * @param number
     * @return
     */
    public static double getFinishRate(Double finishNumber,Double number ){
        Double finishRate=0.00;
        if(finishNumber!=null && finishNumber!=0 && number!=null && number!=0){
            finishRate=finishNumber.doubleValue()/number.doubleValue();
            finishRate=NumberUtil.round(finishRate,2).doubleValue();
        }
        return finishRate;
    }

    /**
     * 获取无税单价
     * 不含税单价 = 含税单价/适用税率(1+17%或13%)
     * @param v1 含税单价
     * @param v2 税率
     * @param scale 保留小数位数
     * @return
     */
    public static double getNoTaxPrice(Double v1,Double v2,int scale ){
        v1=ObjFormat.doubleFormat(v1);
        v2=ObjFormat.doubleFormat(v2);
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        BigDecimal b3 = new BigDecimal("100");
        BigDecimal b4 = new BigDecimal("1");
        b2 = b2.divide(b3);
        b2 = b2.add(b4);
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 获取含税单价
     * 含税单价=不含税单价 * 适用税率(1+17%或13%)
     * @param v1 含税单价
     * @param v2 税率
     * @return
     */
    public static double getTaxPrice(Double v1,Double v2){
        if(v1==null) v1=0D;
        if(v2==null) v2=13D;

        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        BigDecimal b3 = new BigDecimal("100");
        BigDecimal b4 = new BigDecimal("1");
        b2 = b2.divide(b3);
        b2 = b2.add(b4);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 获取税额
     * 税额=含税金额/（1+税率/100）* 适用税率
     * @param v1 含税单价
     * @param v2 税率
     * @return
     */
    public static double getTax(Double v1,Double v2,int scale){
        if(v1==null) v1=0D;
        if(v2==null) v2=13D;

        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        BigDecimal b3 = new BigDecimal("100");
        BigDecimal b4 = new BigDecimal("1");
        b2 = b2.divide(b3,scale,BigDecimal.ROUND_HALF_UP);
        b2 = b2.add(b4);
        BigDecimal divide = b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP);
        BigDecimal subtract = b2.subtract(b4);
        return divide.multiply(subtract).doubleValue();
    }
    /**
     * 比较两数大小，
     *
     * @param v1 比较数
     * @param v2 被比较数
     * @return v1>v2 返回 1
     *          v1==v2 返回 0
     *          v1<v2 返回-1
     */
    public static double compare(Object v1,Object v2 ){
        Double v3 = ObjFormat.doubleFormat(v1);
        Double v4 = ObjFormat.doubleFormat(v2);
        return v3.compareTo(v4);
    }

    // public static boolean hasValue(Object value) {
    //     if (value instanceof Integer) {
    //         return (Integer) value != null && (Integer) value > 0;
    //     } else if (value instanceof Boolean) {
    //         return (Boolean) value != null && (Boolean) value;
    //     } else if (value instanceof Double) {
    //         return (Double) value != null && (Double) value > 0D;
    //     } else if (value instanceof String) {
    //         return value.toString() != null && !value.toString().equals("");
    //     }
    //     return value != null && value.toString() != "";
    // }
    public static boolean hasValue(Integer value) {
        return value != null && value > 0;
    }
    public static boolean hasValue(Object value) {
        return value != null && value.toString() != "";
    }

    /**
     * @param reverse 反转
     * @return
     */
    public static boolean hasValue(Boolean value,Boolean reverse) {
        return value != null && reverse;
    }

    public static boolean hasValue(Boolean value) {
        return value != null && value;
    }
    public static boolean hasValue(Double value) {
        return value != null && value > 0D;
    }
    public static boolean hasValue(String value) {
        return value != null && !value.equals("");
    }
}
