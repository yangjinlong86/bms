package org.dj.bms.enumeration;

/**
 * Created by YANGJINLONG on 2017-11-10.
 */
public enum ResponseEnum {
    // success
    SUCCESS("true"),
    // failed
    FAILED("false");

    private String status;

    ResponseEnum(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}