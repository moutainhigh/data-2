package com.hourz.cas.server.config;

/**
 * <p>列出数据源类型</p>
 * @author hourz
 * @since 2019-01-25
 */
public enum DatabaseType {

    master("write"), slave("read");


    DatabaseType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DatabaseType{" +
                "name='" + name + '\'' +
                '}';
    }
}
