package com.shoukry.workgraduate.Models;

public class ProviderModel {

        private int id;
        private int userId;
        private String detailsAddress;
        private String lat;
        private String _long;
        private int workId;

    public ProviderModel(int id, int userId, String detailsAddress, String lat, String _long, int workId) {
        this.id = id;
        this.userId = userId;
        this.detailsAddress = detailsAddress;
        this.lat = lat;
        this._long = _long;
        this.workId = workId;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDetailsAddress() {
            return detailsAddress;
        }

        public void setDetailsAddress(String detailsAddress) {
            this.detailsAddress = detailsAddress;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public int getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }


}
