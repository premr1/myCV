package com.example;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Employee entity bean.
 */
@Entity
@Table(name="EMPLOYEES")
public class Employee {
  @Id
  Integer id;
  String name;
  String title;
  BigDecimal salary;

  public Employee()
  {
  }

  public Employee(Integer id, String name, String title, BigDecimal salary)
  {
    this.id = id;
    this.name = name;
    this.title = title;
    this.salary = salary;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
   this.salary = salary;
  }
}
