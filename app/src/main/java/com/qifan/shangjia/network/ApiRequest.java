package com.qifan.shangjia.network;


import com.github.retrofitutil.NoNetworkException;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.base.BaseApiRequest;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.response.AddGoodsItem;
import com.qifan.shangjia.network.response.UploadImgItem;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    public static void userRegister(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).userRegister(map).enqueue(callBack);
    }

    public static void getSpinnerData(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getSpinnerData(map).enqueue(callBack);
    }

    public static void userLogin(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).userLogin(map).enqueue(callBack);
    }

    public static void addGoods(Map map, AddGoodsItem item, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).addGoods(map,item).enqueue(callBack);
    }

    public static void editGoods(Map map, AddGoodsItem item, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).editGoods(map,item).enqueue(callBack);
    }


    public static void getGoodsDetail(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getGoodsDetail(map).enqueue(callBack);
    }

    public static void userRetrievePwd(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).userRetrievePwd(map).enqueue(callBack);
    }

    public static void orderList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).orderList(map).enqueue(callBack);
    }

    public static void orderDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).orderDetail(map).enqueue(callBack);
    }


    public static void getGoodsList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getGoodsList(map).enqueue(callBack);
    }




    public static void GrabOrderStep1(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).GrabOrderStep1(map).enqueue(callBack);
    }

    public static void GrabOrderStep2(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).GrabOrderStep2(map).enqueue(callBack);
    }

    public static void GrabOrderStep3(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).GrabOrderStep3(map).enqueue(callBack);
    }

    public static void userWallet(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).userWallet(map).enqueue(callBack);
    }

    public static void userBillList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).userBillList(map).enqueue(callBack);
    }

    public static void Gettixian(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).Gettixian(map).enqueue(callBack);
    }


    public static void startWithdrawals(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).startWithdrawals(map).enqueue(callBack);
    }


    public static void getWithdrawalsList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getWithdrawalsList(map).enqueue(callBack);
    }



    public static void uploadImg(Map map, UploadImgItem item, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).uploadImg(map, item).enqueue(callBack);
    }

    public static void UploadCard(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).UploadCard(map).enqueue(callBack);
    }


    public static void getGoodsCategory(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getGoodsCategory(map).enqueue(callBack);
    }

    public static void addGoodsCategory(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).addGoodsCategory(map).enqueue(callBack);
    }

    public static void deleteGoodsCategory(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).deleteGoodsCategory(map).enqueue(callBack);
    }
    public static void updateGoodsCategory(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).updateGoodsCategory(map).enqueue(callBack);
    }
}
