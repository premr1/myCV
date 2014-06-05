package com.example;

import java.util.List;

/**
 * Contract for Employee related data access.
 */
public interface IEmployeeDao {
  public List<Employee> findEmployeesByTitle(String title)
    throws DataAccessException;

  public void saveEmployees(List<Employee> employees)
    throws DataAccessException;

  @SuppressWarnings("serial")
  public class DataAccessException extends Exception
  {
    public DataAccessException(Throwable cause)
    {
      super(cause);
    }

    public DataAccessException(String message)
    {
      super(message);
    }
  }
}
