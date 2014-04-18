package org.sredisvojgrad.ulica.model;

/**
 * Created by haris on 18/04/14.
 */
public class SyncData {
    private static SyncData ourInstance = new SyncData();

    public static SyncData getInstance() {
        return ourInstance;
    }

    private SyncData() {



    }
}
