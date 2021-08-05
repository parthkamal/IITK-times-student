package com.parth.iitktimesstudent.objects;

public class eventsData {
    String eventsCategory,eventDate,eventTime,downloadUrl,uniqueKey;

    public eventsData() {
    }

    public eventsData(String eventsCategory, String eventDate, String eventTime, String downloadUrl, String uniqueKey) {
        this.eventsCategory = eventsCategory;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.downloadUrl = downloadUrl;
        this.uniqueKey = uniqueKey;
    }

    public String getEventsCategory() {
        return eventsCategory;
    }

    public void setEventsCategory(String eventsCategory) {
        this.eventsCategory = eventsCategory;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

}
