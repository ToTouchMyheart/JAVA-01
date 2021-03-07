package io.zero.db.annotations;

import io.zero.db.en.DynamicDataSourceGlobal;

/**
 * @Author zhurui
 * @Date 2021/3/7 8:54 下午
 * @Version 1.0
 */
public @interface SelfDataSource {
    public DynamicDataSourceGlobal value() default DynamicDataSourceGlobal.READ;
}
