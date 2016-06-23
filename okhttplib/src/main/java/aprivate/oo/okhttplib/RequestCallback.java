package aprivate.oo.okhttplib;

/**
 * Created by zhuxiaolong on 16/5/25.
 */
public interface RequestCallback {
    void onSuccess(String rjson);
    void onServerError();
    void onParamError();
    void onTimeout();
    void onNetError();
    void unknowError();
}
