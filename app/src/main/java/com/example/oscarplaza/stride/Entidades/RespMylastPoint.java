package com.example.oscarplaza.stride.Entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespMylastPoint{

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("next")
        @Expose
        private String next;
        @SerializedName("previous")
        @Expose
        private String previous;
        @SerializedName("results")
        @Expose
        private List<RespResult> results = null;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public java.util.List<RespResult> getResults() {
            return results;
        }

        public void setResults(java.util.List<RespResult> results) {
            this.results = results;
        }
}
