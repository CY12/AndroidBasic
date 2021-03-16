// IAidlInterface.aidl
package com.example.space.ipc;

// Declare any non-default types here with import statements
import com.example.space.ipc.IAidlCallBack;
interface IAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void registerCallBack(IAidlCallBack iAidlCallBack);

    void unregisterCallBack(IAidlCallBack iAidlCallBack);

    void sendMessage(String message);

    List<String> getMessages();

}