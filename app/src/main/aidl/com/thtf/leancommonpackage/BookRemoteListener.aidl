// BookRemoteListener.aidl
package com.thtf.leancommonpackage;

// Declare any non-default types here with import statements

interface BookRemoteListener {
    void remoteAction(in int action , in Message msg);
}
