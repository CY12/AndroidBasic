package com.example.space.thinking.factory;

public class CalculateContext {
    private CalculateStrategy strategy;

    public CalculateContext(CalculateStrategy strategy){
        this.strategy = strategy;
    }
    public void doStrategy(){}
}
