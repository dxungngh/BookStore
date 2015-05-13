package com.dungnh8.truyen_ngon_tinh.listener;

public interface GenericListener<T> {
    public void onSuccess(T result);

    public void onFailed(Throwable error);
}