package com.qifan.shangjia.network.response;

import java.util.List;

/**
 * Created by Administrator on 2018/7/4 0004.
 */

public class ReturnObj {


    private int ErrCode;
    private String ErrMsg;
    private int  Response;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int errCode) {
        ErrCode = errCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getResponse() {
        return Response;
    }

    public void setResponse(int response) {
        Response = response;
    }
}
