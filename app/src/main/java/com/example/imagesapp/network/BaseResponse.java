package com.example.imagesapp.network;

import com.squareup.moshi.Json;

public class BaseResponse<E ,R> {


    @Json(name = "status")
    private String status;

    @Json(name = "errors")
    private E errorsData;

    @Json(name = "data")
    private R data;

    public BaseResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public E getErrorsData() {
        return errorsData;
    }

    public void setErrorsData(E errorsData) {
        this.errorsData = errorsData;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

}
