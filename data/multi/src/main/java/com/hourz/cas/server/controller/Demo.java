/**
 * 
 */
package com.hourz.cas.server.controller;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-18
 */
public class Demo {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
