package models.process

import javax.persistence._
import models.domains.Employee
import play.api.Logger
import models.domains.Page
import models.domains.EmployeeForm

trait EmployeeProcess {

  def createEmployee(employee: EmployeeForm): String
  def updateEmployee(id: String, employee: EmployeeForm): String
  def deleteEmployee(id: String): String
  def findAllEmployee(): List[Employee]
  def list(page: Int, orderBy: Int, filter: String): Page[Employee]
  def findEmployeeById(id: String): Option[Employee]

}

class EmployeeProcessImpl(empDAO: EmployeeDAO) extends EmployeeProcess {

  val logger: Logger = Logger(this.getClass())

  def createEmployee(employee: EmployeeForm): String = {
    val emp = populateEmployee(employee)
    empDAO.create(emp) match {
      case Right(success) => success
      case Left(error)    => error
    }
  }

  def updateEmployee(id: String, employee: EmployeeForm): String = {
    val emp = populateEmployee(employee)
    emp.id = id
    empDAO.update(emp) match {
      case Right(success) => success
      case Left(error)    => error
    }
  }

  def deleteEmployee(id: String): String = {
    empDAO.delete(id) match {
      case Right(success) => success
      case Left(error)    => error
    }
  }

  def findAllEmployee(): List[Employee] = {
    empDAO.findAll match {
      case Right(employees) => employees
      case Left(error)      => logger.error(error); Nil
    }
  }

  def list(page: Int, orderBy: Int, filter: String): Page[Employee] = {
    empDAO.list(page = page, orderBy = orderBy, filter = filter) match {
      case Right(page) => page
      case Left(error) => logger.error(error); Page(Nil, page, 0, 0)
    }
  }

  def findEmployeeById(id: String): Option[Employee] = {
    empDAO.findById(id) match {
      case Right(emp)  => emp
      case Left(error) => logger.error(error); None
    }
  }

  private def populateEmployee(empForm: EmployeeForm): Employee = {
    val emp = new Employee
    emp.id = empForm.id
    emp.name = empForm.name
    emp.address = empForm.address
    emp.dob = empForm.dob.toLocaleString
    emp.joiningDate = empForm.joiningDate.toLocaleString
    emp.designation = empForm.designation
    emp
  }

}

object EmployeeProcessImpl extends EmployeeProcessImpl(EmployeeDAOImpl)
