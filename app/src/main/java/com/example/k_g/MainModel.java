package com.example.k_g;

public class MainModel {



    public MainModel() {
        this.img = img;
        this.desc = desc;
        this.source = source;
        this.link=link;
        this.dpimg=dpimg;
        this.pdate=pdate;
        this.ptime=ptime;
    }

    String img,desc,link,source,dpimg,pdate,ptime;

    public String getDpimg() {
        return dpimg;
    }

    public void setDpimg(String dpimg) {
        this.dpimg = dpimg;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
