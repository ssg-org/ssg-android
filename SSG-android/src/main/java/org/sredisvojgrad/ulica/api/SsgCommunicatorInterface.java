package org.sredisvojgrad.ulica.api;

import org.sredisvojgrad.ulica.model.SyncData;

import java.util.Objects;

/**
 * Created by harisdautovic on 22/04/14.
 */
public interface SsgCommunicatorInterface {



    void recivedData (SyncData data);
    void fetchingData (Error error);
    void getResponse  (String code, Objects responseObject);


}
