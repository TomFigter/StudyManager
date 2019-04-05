// BookInterface.aidl
package com.thtf.leancommonpackage;
import com.thtf.leancommonpackage.BookRemoteListener;
import com.thtf.leanpackage.Book;

// Declare any non-default types here with import statements

interface BookInterface {
     
     void action(in int action ,in int position,in Book book);
     
     void registerListener(BookRemoteListener listener);
     void unregisterListener(BookRemoteListener listener);
     
     Message getCurrentBookInfo();
}
