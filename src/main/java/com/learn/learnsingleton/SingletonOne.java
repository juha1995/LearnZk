package com.learn.learnsingleton;

//饿汉式，创建对象时直接实例化
public class SingletonOne {

    // 1、创建类中私有构造,不可以在外面使用new SingletonOne进行实例化，只可以通过getInstance获取该实例，全局唯一
    private SingletonOne() {
    }

    //2、创建该类型的私有静态实例
    private static SingletonOne instance = new SingletonOne();

    //3、创建共有静态方法，返回静态实例对象
    public static SingletonOne getInstance(){
        return instance;
    }
}
