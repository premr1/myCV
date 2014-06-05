package com.example;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.unitils.easymock.EasyMockUnitils.refEq;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.example.IEmployeeDao.DataAccessException;

public class EmployeeManagerTest extends UnitilsJUnit4
{
  @Mock
  @InjectIntoByType
  private IEmployeeDao employeeDao;

  @TestedObject
  private IEmployeeManager employeeManager = new EmployeeManager();

  private static final String RAISE_TITLE = "Sr. Software Engineer";
  private static final BigDecimal RAISE_BASELINE = new BigDecimal(87000);

  public List<Employee> newEmployeeFixture()
  {
    return Arrays.asList( new Employee[] {
      new Employee(100, "Koizumi Sato", RAISE_TITLE,
          new BigDecimal(88500)),
      new Employee(101, "Richard Moore", RAISE_TITLE,
          new BigDecimal(83000)),
      new Employee(102, "Judy Black", RAISE_TITLE,
          new BigDecimal(85000)),
      new Employee(103, "Michael Smith", RAISE_TITLE,
          new BigDecimal(88000))
    });
  }

  @Test
  public void raisesSalaries()
    throws DataAccessException
  {
    expect(employeeDao.findEmployeesByTitle(RAISE_TITLE))
      .andReturn(newEmployeeFixture()); 

    List<Employee> raisedEmployees = newEmployeeFixture();
    raisedEmployees.get(1).setSalary(RAISE_BASELINE);
    raisedEmployees.get(2).setSalary(RAISE_BASELINE);

    employeeDao.saveEmployees(refEq(raisedEmployees.subList(1, 3),
        ReflectionComparatorMode.LENIENT_ORDER));
    expectLastCall();

    EasyMockUnitils.replay();

    employeeManager.raiseSalariesToBaseline(RAISE_TITLE, RAISE_BASELINE);
  }

  @Test(expected = RuntimeException.class)
  public void throwsUncheckedOnDaoException()
    throws DataAccessException
  {
    expect(employeeDao.findEmployeesByTitle(RAISE_TITLE))
      .andThrow(new DataAccessException("simulated"));
    
    EasyMockUnitils.replay();

    employeeManager.raiseSalariesToBaseline(RAISE_TITLE, RAISE_BASELINE);
  }
}
