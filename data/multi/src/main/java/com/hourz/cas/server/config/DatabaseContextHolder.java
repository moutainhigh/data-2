package com.hourz.cas.server.config;

/**
 * <p>保存一个线程安全的DatabaseType容器</p>
 * @author hourz
 * @since 2019-01-25
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
