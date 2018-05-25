package com.aaxis.schedule;

public class Email {

    private String toAddr;

    private String emailBody;

    public Email() {
    }

    public Email(String toAddr, String emailBody) {
        this.toAddr = toAddr;
        this.emailBody = emailBody;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @Override
    public String toString() {
        return "msg {" +
                "toAddr='" + toAddr + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
