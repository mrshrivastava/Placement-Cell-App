package com.example.k_g;

public class Uploadmodel {
    private String desc;
    private String dpimg;
    private String img;
    private String link;
    private String pdate;
    private String ptime;
    private String source;





    public Uploadmodel()
    {

    }

    public Uploadmodel(String desc, String dpimg,String img,String link,String pdate,String ptime, String source)
    {
        if(desc.trim().equals(""))
        {
            desc="";
        }
        if(source.trim().equals(""))
        {
            source="";
        }
        if(link.trim().equals(""))
        {
            source="";
        }
        this.desc=desc;
        this.dpimg=dpimg;
        this.link=link;
        this.pdate=pdate;
        this.ptime=ptime;
        this.source=source;
        this.img=img;
    }

    public String getDpimg() {
        return dpimg;
    }

    public void setDpimg(String dpimg) {
        this.dpimg = dpimg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
