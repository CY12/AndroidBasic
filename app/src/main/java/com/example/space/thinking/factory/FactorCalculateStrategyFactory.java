package com.example.space.thinking.factory;

/**
 * 具体工厂创建具体的策略
 */
public class FactorCalculateStrategyFactory extends StrategyFactory{

    @Override
    CalculateStrategy createStrategy(Class c) {
        CalculateStrategy product = null;
        try {
            product = (CalculateStrategy) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
