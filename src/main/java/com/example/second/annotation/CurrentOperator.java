package com.example.second.annotation;

import java.lang.annotation.*;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentOperator {

}
