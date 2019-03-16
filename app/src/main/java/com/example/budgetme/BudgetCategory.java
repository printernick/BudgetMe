package com.example.budgetme;

import java.util.ArrayList;

public class BudgetCategory
{
    private String name;
    private double budget;
    //private ArrayList<BudgetItem> items;

    BudgetCategory()
    {
        budget = 0.0;
        name = "";
    }

    BudgetCategory(String name, double budget)
    {
        this.name = name;
        this.budget = budget;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }

    @Override
    public String toString()
    {
        return name + " budget";
    }

    /*public void addBudgetItem(String name, double cost)
    {
        items.add(new BudgetItem(name, cost));
    }
    */
}
