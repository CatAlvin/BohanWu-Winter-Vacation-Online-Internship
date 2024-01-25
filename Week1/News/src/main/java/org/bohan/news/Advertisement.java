package org.bohan.news;

import java.sql.Date;

public class Advertisement {
    private int adId;
    private String title;
    private String textContent;
    private String imageUrl;
    private String placement;
    private String advertiser;
    private Date validityPeriodStart;
    private Date validityPeriodEnd;
    private String regionLimit;
    private String deviceLimit;
    private int clicks;
    private int impressions;
    private int priority;

    public Advertisement() {
        this.adId = 0;
        this.title = null;
        this.textContent = null;
        this.imageUrl = null;
        this.placement = null;
        this.advertiser = null;
        this.validityPeriodStart = null;
        this.validityPeriodEnd = null;
        this.regionLimit = null;
        this.deviceLimit = null;
        this.clicks = 0;
        this.impressions = 0;
        this.priority = 0;
    }

    public Advertisement(String title, String textContent, String placement, String advertiser) {
        this.title = title;
        this.textContent = textContent;
        this.imageUrl = null;
        this.placement = placement;
        this.advertiser = advertiser;
        this.validityPeriodStart = null;
        this.validityPeriodEnd = null;
        this.regionLimit = null;
        this.deviceLimit = null;
        this.clicks = 0;
        this.impressions = 0;
        this.priority = 0;
    }

    public int getAdId() {
        return adId;
    }
    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public Date getValidityPeriodStart() {
        return validityPeriodStart;
    }

    public void setValidityPeriodStart(Date validityPeriodStart) {
        this.validityPeriodStart = validityPeriodStart;
    }

    public Date getValidityPeriodEnd() {
        return validityPeriodEnd;
    }

    public void setValidityPeriodEnd(Date validityPeriodEnd) {
        this.validityPeriodEnd = validityPeriodEnd;
    }

    public String getRegionLimit() {
        return regionLimit;
    }

    public void setRegionLimit(String regionLimit) {
        this.regionLimit = regionLimit;
    }

    public String getDeviceLimit() {
        return deviceLimit;
    }

    public void setDeviceLimit(String deviceLimit) {
        this.deviceLimit = deviceLimit;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
