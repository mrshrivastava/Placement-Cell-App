package com.example.k_g;

public class Past_Companies_Uploadmodel {
    private String batch;
    private String branch;
    private String company;
    private String desc;
    private String hired;
    private String img;
    private String pkg;
    private String sem;





    public Past_Companies_Uploadmodel()
    {

    }

    public Past_Companies_Uploadmodel(String batch, String branch, String company, String desc, String hired,String img, String pkg, String sem)
    {
        this.batch=batch;
        this.branch=branch;
        this.company=company;
        this.desc=desc;
        this.hired=hired;
        this.img=img;
        this.pkg=pkg;
        this.sem=sem;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
