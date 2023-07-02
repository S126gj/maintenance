package com.device.common.exception;

import cn.hutool.core.collection.CollUtil;
import com.device.common.utils.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 校验器
 *
 * @Author: 沈国纪
 * @Date: 2023/06/02
 */
public interface Checker {

    @FunctionalInterface
    interface Verifier<X extends Exception> {

        boolean verify() throws X;
    }

    /**
     * 如果 假 则 抛出异常
     *
     * @param verifier
     * @param exceptionSupplier 异常构造者
     * @param <X>
     * @throws X
     */
    static <X extends Exception, Y extends Exception> void ifNotThrow(Verifier<Y> verifier,
        Supplier<X> exceptionSupplier) throws X, Y {
        ifThrow(!verifier.verify(), exceptionSupplier);
    }

    /**
     * 如果 假 则 抛出异常
     *
     * @param condition
     * @param exceptionSupplier 异常构造者
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifNotThrow(boolean condition, Supplier<X> exceptionSupplier) throws X {
        ifThrow(!condition, exceptionSupplier);
    }

    /**
     * 如果 真 则 抛出异常
     *
     * @param verifier          校验
     * @param exceptionSupplier 异常构造者
     * @param <X>               异常
     * @throws X
     */
    static <X extends Exception, Y extends Exception> void ifThrow(Verifier<Y> verifier, Supplier<X> exceptionSupplier)
        throws X, Y {
        ifThrow(verifier.verify(), exceptionSupplier);
    }

    /**
     * 如果 真 则 抛出异常
     *
     * @param verifier          校验
     * @param exceptionSupplier 异常构造者
     * @param <X>               异常
     * @throws X
     */
    static <X extends Exception, Y extends Exception> void ifNotThrow(Verifier<Y> verifier, Consumer<Boolean> consumer,
        Supplier<X> exceptionSupplier)
        throws X, Y {
        ifThrow(!verifier.verify(), consumer, exceptionSupplier);
    }

    /**
     * 如果 真 则 抛出异常
     *
     * @param condition         条件
     * @param exceptionSupplier 异常构造者
     * @param <X>               异常
     * @throws X
     */
    static <X extends Exception, Y extends Exception> void ifNotThrow(boolean condition, Consumer<Boolean> consumer,
        Supplier<X> exceptionSupplier)
        throws X, Y {
        ifThrow(!condition, consumer, exceptionSupplier);
    }

    /**
     * 如果 真 则 抛出异常
     *
     * @param condition         条件
     * @param exceptionSupplier 异常构造者
     * @throws X
     */
    static <X extends Exception> void ifThrow(boolean condition, Supplier<X> exceptionSupplier) throws X {
        if (condition) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 如果 真 则 抛出异常
     *
     * @param condition         条件
     * @param exceptionSupplier 异常构造者
     * @throws X
     */
    static <X extends Exception> void ifThrow(boolean condition, Consumer<Boolean> consumer,
        Supplier<X> exceptionSupplier) throws X {
        if (condition) {
            consumer.accept(true);
            throw exceptionSupplier.get();
        }
    }

    /**
     * 如果为空 则 抛出异常 兼容 Optional
     *
     * @param data
     * @param exceptionSupplier
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifNullThrow(Object data, Supplier<X> exceptionSupplier) throws X {
        if (data == null || (data instanceof Optional) && (!((Optional) data).isPresent())) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 如果为空 则 抛出异常 兼容 Optional
     *
     * @param data
     * @param exceptionSupplier
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifNullThrow(Object data, Consumer<Boolean> consumer,
        Supplier<X> exceptionSupplier) throws X {
        if (data == null || (data instanceof Optional) && (!((Optional) data).isPresent())) {
            consumer.accept(true);
            throw exceptionSupplier.get();
        }
    }

    /**
     * 如果为空 则 抛出异常
     *
     * @param data
     * @param exceptionSupplier
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifEmptyThrow(Object data, Supplier<X> exceptionSupplier) throws X {
        if (data == null
            || (data instanceof String && StringUtil.isEmpty((CharSequence) data))
            || (data instanceof Collection && CollectionUtils.isEmpty((Collection<?>) data))
            || (data instanceof Map && CollectionUtils.isEmpty((Collection<?>) data))) {
            throw exceptionSupplier.get();
        }
    }


    /**
     * 如果不为空 则 抛出异常 兼容 Optional
     *
     * @param data
     * @param exceptionSupplier
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifNotNullThrow(Object data, Supplier<X> exceptionSupplier) throws X {
        if (data != null || (data instanceof Optional) && (((Optional) data).isPresent())) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 如果不为空 则 抛出异常
     *
     * @param data
     * @param exceptionSupplier
     * @param <X>
     * @throws X
     */
    static <X extends Exception> void ifNotEmptyThrow(Object data, Supplier<X> exceptionSupplier) throws X {
        if (data != null
            || (data instanceof String && StringUtil.isNotEmpty((CharSequence) data))
            || (data instanceof Collection && CollUtil.isNotEmpty((Collection<?>) data))
            || (data instanceof Map && CollUtil.isNotEmpty((Collection<?>) data))) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * true 则执行
     *
     * @param target
     * @param predicate
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifThen(T target, Predicate<T> predicate, Consumer<? super T> consumer) {
        if (predicate.test(target)) {
            consumer.accept(target);
            return true;
        }
        return false;
    }

    /**
     * true 则执行
     *
     * @param condition
     * @param consumer
     * @return
     */
    static boolean ifThen(Boolean condition, Consumer<Boolean> consumer) {
        if (condition) {
            consumer.accept(true);
            return true;
        }
        return false;
    }

    /**
     * false则执行
     *
     * @param condition
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifNotThen(Boolean condition, Consumer<Boolean> consumer) {
        if (!condition) {
            consumer.accept(true);
            return true;
        }
        return false;
    }

    /**
     * false则执行
     *
     * @param target
     * @param predicate
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifNotThen(T target, Predicate<T> predicate, Consumer<? super T> consumer) {
        if (!predicate.test(target)) {
            consumer.accept(target);
            return true;
        }
        return false;
    }

    /**
     * 非空则执行
     *
     * @param target
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifPresent(T target, Consumer<? super T> consumer) {
        return ifThen(target, Objects::nonNull, consumer);
    }

    /**
     * 空则执行
     *
     * @param target
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifNullThen(T target, Consumer<? super T> consumer) {
        return ifThen(target, Objects::isNull, consumer);
    }

    /**
     * 是否不同
     *
     * @param target
     * @param expect
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifNotEqualsThen(T target, Object expect, Consumer<? super T> consumer) {
        return ifThen(target, t -> !Objects.equals(t, expect), consumer);
    }

    /**
     * 是否相同
     *
     * @param target
     * @param expect
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifEqualsThen(T target, Object expect, Consumer<? super T> consumer) {
        return ifThen(target, t -> Objects.equals(t, expect), consumer);
    }

    /**
     * 如果为空
     *
     * @param target
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifEmptyThen(T target, Consumer<? super T> consumer) {
        return ifThen(target,
            t -> Objects.isNull(t) || (t instanceof String && StringUtil.isEmpty((CharSequence) t))
                || (t instanceof Collection && CollectionUtils.isEmpty((Collection<?>) t))
                || (t instanceof Map && CollectionUtils.isEmpty((Collection<?>) t)),
            consumer);
    }

    /**
     * 如果不为空
     *
     * @param target
     * @param consumer
     * @param <T>
     * @return
     */
    static <T> boolean ifNotEmptyThen(T target, Consumer<? super T> consumer) {
        return ifNotThen(target,
            t -> Objects.isNull(t) || (t instanceof String && StringUtil.isEmpty((CharSequence) t))
                || (t instanceof Collection && CollectionUtils.isEmpty((Collection<?>) t))
                || (t instanceof Map && CollectionUtils.isEmpty((Collection<?>) t)),
            consumer);
    }

    /**
     * 非空白字符串则执行
     *
     * @param target
     * @param consumer
     * @return
     */
    static boolean ifStringNotEmpty(String target, Consumer<String> consumer) {
        return ifThen(target, t -> !StringUtil.isEmpty(t), consumer);
    }

    /**
     * 空白字符串则执行
     *
     * @param target
     * @param consumer
     * @return
     */
    static boolean ifStringEmpty(String target, Consumer<String> consumer) {
        return ifThen(target, t -> StringUtil.isEmpty(t), consumer);
    }

    /**
     * 如果是日期数组， 且0 为开始时间 1 为结束时间
     *
     * @param dateBetween
     * @param consumer
     * @return
     */
    static boolean ifDateBetween(List<Date> dateBetween, Consumer<List<Date>> consumer) {
        return ifThen(dateBetween, dates -> !CollectionUtils.isEmpty(dates) && dates.size() == 2, consumer);
    }
}