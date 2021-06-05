package com.example.gu;

public class data {

    private String imgUrl;
    private String name;
    private String login;

    public data(String imgUrl,String name,String login)
    {
        this.imgUrl=imgUrl;
        this.name=name;
        this.login=login;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
