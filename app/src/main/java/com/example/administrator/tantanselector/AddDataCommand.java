package com.example.administrator.tantanselector;

/**
 * Created by Administrator on 2018/2/9.
 */

public class AddDataCommand {
    String string ;

    public AddDataCommand(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
