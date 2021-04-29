package com.example.space.thinking.factory;

/**
 * 抽象工厂: 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法是一个类的实例化延迟到其子类。
 * 工厂结合策略模式，是将创建的工具换成创建具体的策略
 */
public abstract class StrategyFactory<T> {
    abstract CalculateStrategy createStrategy(Class<T> c);
}
