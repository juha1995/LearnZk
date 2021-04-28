package com.learn.learnsingleton;

//懒汉式，创建对象时不进行实例化操作，使用时才进行实例化
public class SingletonTwo {

    // 1、创建类中私有构造,不可以在外面使用new SingletonOne进行实例化，只可以通过getInstance获取该实例，全局唯一
    private SingletonTwo() {
    }

    //2、创建该类型的私有静态实例
    private static SingletonTwo instance = null;

    //3、创建公有有静态方法，返回静态实例对象,为空时进行实例化
    public static SingletonTwo getInstance(){
        if (instance==null){
            instance = new SingletonTwo();
        }
        return instance;
    }
}
