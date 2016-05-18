package com.leeeeo.mydict.models.beans;

/**
 * Created by Jacob on 16/5/17.
 * Email: Jacob.Deng@about-bob.com
 */
public class DictBean {
    public String dictName;            //词库名

    public DictBean() {

    }

    public DictBean(String dictName, String fileName) {
        this.dictName = dictName;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String fileName;            //文件名
    public String time;                 //时间
    public boolean isHandled;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public void setIsHandled(boolean isHandled) {
        this.isHandled = isHandled;
    }

}
