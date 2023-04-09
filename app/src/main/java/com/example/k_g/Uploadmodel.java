package com.example.k_g;

public class Uploadmodel {
    private String desc;
    private String img;
    private String source;


    public Uploadmodel()
    {

    }

    public Uploadmodel(String desc, String img, String source)
    {
        if(desc.trim().equals(""))
        {
            desc="";
        }
        if(source.trim().equals(""))
        {
            source=" ";
        }
        this.desc=desc;
        this.source=source;
        this.img=img;
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
