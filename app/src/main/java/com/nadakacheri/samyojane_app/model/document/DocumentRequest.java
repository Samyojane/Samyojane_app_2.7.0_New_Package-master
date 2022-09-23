package com.nadakacheri.samyojane_app.model.document;

import com.google.gson.annotations.SerializedName;

public class DocumentRequest {
    @SerializedName("Flag1")
    private String flag1;

    @SerializedName("Flag2")
    private String flag2;

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("UD_GscNo")
    private String uDGscNo;

    @SerializedName("Document_Id")
    private Integer documentId;

    @SerializedName("File")
    private String file;

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUDGscNo() {
        return uDGscNo;
    }

    public void setUDGscNo(String uDGscNo) {
        this.uDGscNo = uDGscNo;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
