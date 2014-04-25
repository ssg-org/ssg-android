package org.sredisvojgrad.ulica.model;

/**
 * Created by harisdautovic on 23/04/14.
 */
public class Categories {

    public Integer id;
    public String color;
    public String descript;
    public String icon;
    public String name;
    public Integer parent_id;


    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", descript='" + descript + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}
