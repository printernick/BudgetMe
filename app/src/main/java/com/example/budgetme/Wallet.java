package com.example.budgetme;

import java.util.HashMap;

public class Wallet
{
    private HashMap<String, BudgetCategory> budgets;
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
    }
}
