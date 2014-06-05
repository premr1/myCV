package com.example;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FindByPredicates;

public class EbeanEmployeeDao
  implements IEmployeeDao
{
  /**
   * @see IEmployeeDao#findEmployeesByTitle(String)
   */
  @SuppressWarnings("unchecked")
  public List<Employee> findEmployeesByTitle(String title)
    throws DataAccessException
  {
    if ( title == null )
      throw new IllegalArgumentException("Job title cannot be null.");

    List<Employee> result;
    try {
      FindByPredicates find = new FindByPredicates(Employee.class);
      find.add("title", find.EQ, title);
      result = Ebean.findList(find);
    }
    catch ( Throwable e ) {
      throw new DataAccessException(e);
    }

    return result;
  }

  /**
   * @see IEmployeeDao#saveEmployees(List)
   */
  public void saveEmployees(List<Employee> employees)
    throws DataAccessException
  {
    try {
      Ebean.save(employees.iterator());
    }
    catch ( Throwable e ) {
      throw new DataAccessException(e);
    }
  }
}
