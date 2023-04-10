package com.example.k_g;

public class MainModel {



    public MainModel() {
        this.img = img;
        this.desc = desc;
        this.source = source;
        this.link=link;
    }

    String img,desc,link,source;

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
