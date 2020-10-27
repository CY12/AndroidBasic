package com.example.space.livedata;

import androidx.lifecycle.MutableLiveData;

/**
 * public class StatusLiveData extends MutableLiveData<Status> {
 *     private StatusLiveData() {
 *     }
 *
 *     private static class Holder {
 *         public static final StatusLiveData INSTANCE = new StatusLiveData();
 *     }
 *
 *     public static StatusLiveData getInstance() {
 *         return Holder.INSTANCE;
 *     }
 * }
 *
 * //MutableLiveData在LiveData基础上暴露两个设值接口
 * public class MutableLiveData<T> extends LiveData<T> {
 *     @Override
 *     public void postValue(T value) {
 *         super.postValue(value);
 *     }
 *
 *     @Override
 *     public void setValue(T value) {
 *         super.setValue(value);
 *     }
 * }
 */
public class LampLiveData extends MutableLiveData<Lamp> {

    private static volatile LampLiveData lampLiveData;

    private LampLiveData(){}

    public static LampLiveData getInstance(){
        if (lampLiveData == null){
            synchronized (LampLiveData.class){
                if (lampLiveData == null){
                    lampLiveData = new LampLiveData();
                }
            }

        }
        return lampLiveData;
    }

}
