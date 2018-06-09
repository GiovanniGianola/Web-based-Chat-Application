/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.alert;

/**
 * Type of alert: 
 *      ° confirm   code: 0
 *      ° error     code: 1
 */

public class AlertHandler {

    private static final String ERROR_PREFIX = "[ERROR]: ";
    
    private int code;

    public AlertHandler() {
    }

    public String getAlert( String alert) {
        
        String[] values = alert.split("/");
        if(values.length == 2){
            alert = values[0];
            this.code = Integer.parseInt(values[1]);

            if(this.code == 0){
                return alert;
            }else if(this.code == 1){
                return ERROR_PREFIX + alert;
            }
        }
        return "Wrong code";
    }
}
