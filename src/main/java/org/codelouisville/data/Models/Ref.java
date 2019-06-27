package org.codelouisville.data.Models;

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
