package com.gree.ssm.bean;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午8:12 2017/12/13
 * @Description:
 */
public class LoginParams {

    private String storeId ;
    private String catalogId ;
    private String langId ;
    private String reLogonURL ;
    private String fromOrderId ;
    private String toOrderId ;
    private String deleteIfEmpty ;
    private String continues ;
    private String createIfEmpty ;
    private String updatePrices ;
    private String URL ;
    private String logonId ;
    private String logonPassword ;
    private String calculationUsageId ;
    private String logonWay ;
    private String logonType ;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getReLogonURL() {
        return reLogonURL;
    }

    public void setReLogonURL(String reLogonURL) {
        this.reLogonURL = reLogonURL;
    }

    public String getFromOrderId() {
        return fromOrderId;
    }

    public void setFromOrderId(String fromOrderId) {
        this.fromOrderId = fromOrderId;
    }

    public String getToOrderId() {
        return toOrderId;
    }

    public void setToOrderId(String toOrderId) {
        this.toOrderId = toOrderId;
    }

    public String getDeleteIfEmpty() {
        return deleteIfEmpty;
    }

    public void setDeleteIfEmpty(String deleteIfEmpty) {
        this.deleteIfEmpty = deleteIfEmpty;
    }

    public String getCreateIfEmpty() {
        return createIfEmpty;
    }

    public void setCreateIfEmpty(String createIfEmpty) {
        this.createIfEmpty = createIfEmpty;
    }

    public String getUpdatePrices() {
        return updatePrices;
    }

    public void setUpdatePrices(String updatePrices) {
        this.updatePrices = updatePrices;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getLogonPassword() {
        return logonPassword;
    }

    public void setLogonPassword(String logonPassword) {
        this.logonPassword = logonPassword;
    }

    public String getCalculationUsageId() {
        return calculationUsageId;
    }

    public void setCalculationUsageId(String calculationUsageId) {
        this.calculationUsageId = calculationUsageId;
    }

    public String getLogonWay() {
        return logonWay;
    }

    public void setLogonWay(String logonWay) {
        this.logonWay = logonWay;
    }

    public String getLogonType() {
        return logonType;
    }

    public void setLogonType(String logonType) {
        this.logonType = logonType;
    }

    public String getContinue() {
        return continues;
    }

    public void setContinue(String continues) {
        this.continues = continues;
    }

    @Override
    public String toString() {
        return "loginParams{" +
                "storeId='" + storeId + '\'' +
                ", catalogId='" + catalogId + '\'' +
                ", langId='" + langId + '\'' +
                ", reLogonURL='" + reLogonURL + '\'' +
                ", fromOrderId='" + fromOrderId + '\'' +
                ", toOrderId='" + toOrderId + '\'' +
                ", deleteIfEmpty='" + deleteIfEmpty + '\'' +
                ", continues='" + continues + '\'' +
                ", createIfEmpty='" + createIfEmpty + '\'' +
                ", updatePrices='" + updatePrices + '\'' +
                ", URL='" + URL + '\'' +
                ", logonId='" + logonId + '\'' +
                ", logonPassword='" + logonPassword + '\'' +
                ", calculationUsageId='" + calculationUsageId + '\'' +
                ", logonWay='" + logonWay + '\'' +
                ", logonType='" + logonType + '\'' +
                '}';
    }
}
