package io.zero.db.configuration;

import io.zero.db.en.DynamicDataSourceGlobal;

/**
 * @Author zhurui
 * @Date 2021/3/7 9:00 下午
 * @Version 1.0
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<DynamicDataSourceGlobal>();

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

}
