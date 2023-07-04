package com.device.common.annotation.handler;

import com.device.common.annotation.Phone;
import com.device.common.constanst.Constanst;
import com.device.common.utils.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/4 15:48
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private Pattern pattern = Pattern.compile(Constanst.DEFAULT_PATTERN);

    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean matches = true;
        if (StringUtil.isNotBlank(value)) {
            matches = pattern.matcher(value).matches();
        }
        return matches;
    }
}
