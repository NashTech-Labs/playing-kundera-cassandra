package models.process

import javax.persistence._
import models.domains.Employee
import play.api.Logger

trait EmployeeProcess {

  def createEmployee(employee: Employee): String
  def findAllEmployee(): List[Employee]

}

class EmployeeProcessImpl(empDAO: EmployeeDAO) extends EmployeeProcess {
  
  val logger: Logger = Logger(this.getClass())
  
  def createEmployee(employee: Employee): String = {
    empDAO.create(employee) match {
      case Right(success) => success
      case Left(error) => error
    }
  }
  
  def findAllEmployee(): List[Employee] = {
    empDAO.findAll match {
      case Right(employees) => employees
      case Left(error) => logger.error(error); Nil
    }
  }

}

object EmployeeProcessImpl extends EmployeeProcessImpl(EmployeeDAOImpl)
