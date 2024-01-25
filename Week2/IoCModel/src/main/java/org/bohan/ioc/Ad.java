package org.bohan.ioc;

public class Ad {
    private String name; // 广告名称
    private String company; // 广告公司
    private String content;// 广告内容

    public Ad(String name, String company, String content) {
        this.name = name;
        this.company = company;
        this.content = content;
    }

    public Ad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
