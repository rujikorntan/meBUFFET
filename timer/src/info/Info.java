package info;

import javafx.scene.text.Text;

import java.io.Serializable;

public class Info implements Serializable {
    private String info_company, info_vat, info_address, info_phone, info_detail, info_discount, info_username, info_password;

    public Info(){}
    public Info(String info_company, String info_vat, String info_address, String info_phone,
                String info_discount, String info_detail, String info_username, String  info_password) {
        this.info_company = info_company;
        this.info_vat = info_vat;
        this.info_address = info_address;
        this.info_phone = info_phone;
        this.info_detail = info_detail;
        this.info_discount = info_discount;
        this.info_username = info_username;
        this.info_password = info_password;
    }

    public String getInfo_password() {
        return info_password;
    }

    public void setInfo_password(String info_password) {
        this.info_password = info_password;
    }

    public String getInfo_username() {
        return info_username;
    }

    public void setInfo_username(String info_username) {
        this.info_username = info_username;
    }

    public String getInfo_company() {
        return info_company;
    }

    public void setInfo_company(String info_company) {
        this.info_company = info_company;
    }

    public String getInfo_vat() {
        return info_vat;
    }

    public void setInfo_vat(String info_vat) {
        this.info_vat = info_vat;
    }

    public String getInfo_address() {
        return info_address;
    }

    public void setInfo_address(String info_address) {
        this.info_address = info_address;
    }

    public String getInfo_phone() {
        return info_phone;
    }

    public void setInfo_phone(String info_phone) {
        this.info_phone = info_phone;
    }

    public String getInfo_detail() {
        return info_detail;
    }

    public void setInfo_detail(String info_detail) {
        this.info_detail = info_detail;
    }

    public String getInfo_discount() {
        return info_discount;
    }

    public void setInfo_discount(String info_discount) {
        this.info_discount = info_discount;
    }
}
