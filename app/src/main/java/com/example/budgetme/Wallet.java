package com.example.budgetme;

import java.util.HashMap;

public class Wallet
{
    //The key is a string for the name of the budget category
    private HashMap<String, BudgetCategory> budgets;

    //Representing monthly budget which will be divided up
    private int money;

    Wallet()
    {
        money = 0;
    }

    Wallet(int money)
    {
        this.money = money;
    }

    public int getMoney()
    {
        return money;
    }

    private void setMoney(int money)
    {
        this.money = money;
    }

    public void addSubBudget(String category, double budget)
    {
        budgets.put(category, new BudgetCategory(budget));
        money -= budget;
    }
}
