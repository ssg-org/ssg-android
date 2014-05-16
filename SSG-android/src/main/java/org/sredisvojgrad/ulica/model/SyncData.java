package org.sredisvojgrad.ulica.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haris on 18/04/14.
 */
public class SyncData {
    private static SyncData ourInstance = new SyncData();

    public static List<City> cities = new ArrayList<City>();
    public static List<Categories> categories = new ArrayList<Categories>();

    public static SyncData getInstance() {
        return ourInstance;
    }

    private SyncData() {


    }


}
