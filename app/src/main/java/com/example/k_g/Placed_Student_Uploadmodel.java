package com.example.k_g;

public class Placed_Student_Uploadmodel {
    private String batch;
    private String branch;
    private String company;
    private String contact;

    private String img;
    private String name;
    private String pkg;





    public Placed_Student_Uploadmodel()
    {

    }

    public Placed_Student_Uploadmodel(String batch, String branch, String company, String contact, String img, String name,String pkg)
    {
        this.batch=batch;
        this.branch=branch;
        this.company=company;
        this.contact=contact;

        this.img=img;
        this.name=name;
        this.pkg=pkg;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
