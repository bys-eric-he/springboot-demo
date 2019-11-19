package com.example.annotation;

import com.example.config.StorageAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 通过@import注解，spring容器会自动加载StorageAutoConfigure并自动化进行配置。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(StorageAutoConfigure.class)
@Documented
public @interface EnableStorage {
}
