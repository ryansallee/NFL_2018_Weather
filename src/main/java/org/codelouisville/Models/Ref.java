package org.codelouisville.Models;

//Ref is used as arguments can only be passed by value in Java ,but objects can still be manipulated in methods. The class
//is a generic so that it can take any type.
public class Ref<T> {
    //Getter
    public T getVal() {
        return val;
    }

    //Setter
    public void setVal(T val) {
        this.val = val;
    }

    //Field
    private T val;

    //Constructor
    public Ref(T val)
    {
        this.val = val;
    }


}
