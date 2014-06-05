package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.IEmployeeDao.DataAccessException;

public class EmployeeManager
  implements IEmployeeManager
{
  private IEmployeeDao employeeDao;

  /**
   * @see IEmployeeManager#raiseSalariesToBaseline(String, BigDecimal)
   */
  public void raiseSalariesToBaseline(String title, BigDecimal baseline)
  {
    List<Employee> employeesHavingTitle;
    try {
      employeesHavingTitle = employeeDao.findEmployeesByTitle(title);
    }
    catch ( DataAccessException e )
    {
      throw new RuntimeException(e);
    }
    
    if ( employeesHavingTitle.size() == 0 )
      return;

    Iterator<Employee> iter = employeesHavingTitle.iterator();

    // we assume a small result set here, which makes collapsing the source
    // array unnecessary, and we simply add updated employees to a new one.
    Employee employee;
    List<Employee> updateEmployees = new ArrayList<Employee>();

    while ( iter.hasNext() ) {
      employee = iter.next();

      if ( employee.getSalary().compareTo(baseline) == -1 ) {
        employee.setSalary(baseline);
        updateEmployees.add(employee);
      }
    }

    try {
      employeeDao.saveEmployees(updateEmployees);
    }
    catch (DataAccessException e) {
      throw new RuntimeException(e);
    }
  }

  public void setEmployeeDao(IEmployeeDao employeeDao)
  {
    this.employeeDao = employeeDao;
  }
}
