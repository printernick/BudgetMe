package com.example.budgetme;

import java.util.ArrayList;

public class BudgetCategory
{
    private double budget;
    private ArrayList<BudgetItem> items;

    BudgetCategory()
    {
        budget = 0.0;
    }

    BudgetCategory(double budget)
    {
        this.budget = budget;
    }

    public void addBudgetItem(String name, double cost)
    {
        items.add(new BudgetItem(name, cost));
    }
}
