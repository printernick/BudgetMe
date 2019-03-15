package com.example.budgetme;

public class BudgetItem
{
    private String name;
    private double cost;

    BudgetItem()
    {
        name = "";
        cost = 0.0;
    }

    BudgetItem(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
}
