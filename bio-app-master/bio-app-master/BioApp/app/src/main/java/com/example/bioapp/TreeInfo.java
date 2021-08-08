package com.example.bioapp;

public class TreeInfo {
    private int Id;
    private String name;
    private String Description;
    private String Good;
    private String Bad;
    private int imageid;

    // constructor


    public TreeInfo(int imageid, int Id, String name, String description, String good, String bad) {
        this.name = name;
        this.Description = description;
        this.Good = good;
        this.Bad = bad;
        this.Id = Id;
        this.imageid = imageid;
    }

    public void setBad(String bad) {
        Bad = bad;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getGood() {
        return Good;
    }

    public String getBad() {
        return Bad;
    }

    public int getImageid() {
        return imageid;
    }
}
