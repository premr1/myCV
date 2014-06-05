package com.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.example.IEmployeeDao.DataAccessException;

public class EmployeeDaoTest extends UnitilsJUnit4
{
  private IEmployeeDao employeeDao;

  @Before
  public void setUpDao()
  {
    employeeDao = new EbeanEmployeeDao();
  }

  @Test
  @DataSet
  public void findsForTitleOnly()
    throws DataAccessException
  {
    List<Integer> expectedEmployeeIds = Arrays.asList
      ( new Integer[] { 100, 103 } );
    
    List<Employee> actualEmployees
      = employeeDao.findEmployeesByTitle("Software Engineer");

    ReflectionAssert.assertPropertyRefEquals("id", expectedEmployeeIds,
        actualEmployees, ReflectionComparatorMode.LENIENT_ORDER);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwsOnNullTitle()
    throws DataAccessException
  {
    employeeDao.findEmployeesByTitle(null);
  }

  @Test
  @DataSet
  @ExpectedDataSet
  public void savesSalaryChanges()
    throws DataAccessException
  {
    List<Employee> raiseEmployees
      = employeeDao.findEmployeesByTitle("Worker Bee");

    raiseEmployees.get(0).setSalary(new BigDecimal(58000.00));
    raiseEmployees.get(1).setSalary(new BigDecimal(61000.00));

    employeeDao.saveEmployees(raiseEmployees);
  }
}
