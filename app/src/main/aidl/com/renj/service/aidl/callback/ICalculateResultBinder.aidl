package com.renj.service.aidl.callback;

import com.renj.service.aidl.callback.ICalculateResultCallBack;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-28   09:31
 * <p>
 * 描述：直接将回调作为参数，将结果通过回调返回，类似监听
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface ICalculateResultBinder {
    void operatorAdd(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack);

    void operatorSubtract(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack);

    void operatorMultiply(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack);

    void operatorDivide(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack);
}
