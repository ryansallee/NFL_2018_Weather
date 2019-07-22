package org.codelouisville.Models;

//Ref is used as arguments can only be passed by value in Java ,but objects can still be manipulated in methods.
public class Ref<T> {
    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    private T val;

    public Ref(T val)
    {
        this.val = val;
    }


}
