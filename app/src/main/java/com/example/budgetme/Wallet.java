package com.example.budgetme;

import java.util.HashMap;

public class Wallet
{
    //The key is a string for the name of the budget category
    private HashMap<String, BudgetCategory> budgets;

    //Representing monthly budget which will be divided up
    private double money;

    Wallet()
    {
        money = 0;
    }

    Wallet(double money)
    {
        this.money = money;
    }

    Wallet(Wallet w)
    {
        this.money = w.money;
        budgets = new HashMap<String, BudgetCategory>(w.budgets);
    }

    public double getMoney()
    {
        return money;
    }

    private void setMoney(double money)
    {
        this.money = money;
    }

//    public void addSubBudget(String category, double budget)
//    {
//        budgets.put(category, new BudgetCategory(budget));
//        money -= budget;
//    }
}
