package com.example;

import java.math.BigDecimal;

/**
 * Contract for high-level operations on groups of employees.
 */
public interface IEmployeeManager
{
  public void raiseSalariesToBaseline(String title, BigDecimal baseline);
  public void setEmployeeDao(IEmployeeDao employeeDao);
}
