package com.example.modul2.entity;

public class Partner {
    private Integer partnersId;
    private Integer partnersType; // Изменено с String на int
    private String partnerTitle;
    private String fullName;
    private String partnersEmail;
    private String partnersPhone;
    private String addressPartners;
    private Long partnersInn;
    private Integer partnersRating;
    private Double discount;

    public Partner(Integer partnersId, Integer partnersType, String partnerTitle, String fullName, String partnersEmail, String partnersPhone, String addressPartners, Long partnersInn, Integer partnersRating) {
        this.partnersId = partnersId;
        this.partnersType = partnersType;
        this.partnerTitle = partnerTitle;
        this.fullName = fullName;
        this.partnersEmail = partnersEmail;
        this.partnersPhone = partnersPhone;
        this.addressPartners = addressPartners;
        this.partnersInn = partnersInn;
        this.partnersRating = partnersRating;
        this.discount = 0.0; // Инициализация скидки по умолчанию
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setPartnersRating(Integer partnersRating) {
        this.partnersRating = partnersRating;
    }

    public void setPartnersType(Integer partnersType) {
        this.partnersType = partnersType;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public int getPartnersId() {
        return partnersId;
    }

    public void setPartnersId(int partnersId) {
        this.partnersId = partnersId;
    }

    public int getPartnersType() {
        return partnersType;
    }

    public void setPartnersType(int partnersType) {
        this.partnersType = partnersType;
    }

    public String getPartnerTitle() {
        return partnerTitle;
    }

    public void setPartnerTitle(String partnerTitle) {
        this.partnerTitle = partnerTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPartnersEmail() {
        return partnersEmail;
    }

    public void setPartnersEmail(String partnersEmail) {
        this.partnersEmail = partnersEmail;
    }

    public String getPartnersPhone() {
        return partnersPhone;
    }

    public void setPartnersPhone(String partnersPhone) {
        this.partnersPhone = partnersPhone;
    }

    public String getAddressPartners() {
        return addressPartners;
    }

    public void setAddressPartners(String addressPartners) {
        this.addressPartners = addressPartners;
    }

    public Long getPartnersInn() {
        return partnersInn;
    }

    public void setPartnersInn(Long partnersInn) {
        this.partnersInn = partnersInn;
    }

    public int getPartnersRating() {
        return partnersRating;
    }

    public void setPartnersRating(int partnersRating) {
        this.partnersRating = partnersRating;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "partnersId=" + partnersId +
                ", partnersType=" + partnersType +
                ", partnerTitle='" + partnerTitle + '\'' +
                ", fullName='" + fullName + '\'' +
                ", partnersEmail='" + partnersEmail + '\'' +
                ", partnersPhone='" + partnersPhone + '\'' +
                ", addressPartners='" + addressPartners + '\'' +
                ", partnersInn=" + partnersInn +
                ", partnersRating=" + partnersRating +
                ", discount=" + discount +
                '}';
    }
}
