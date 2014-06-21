package controllers

import play.api._
import play.api.mvc._
import models.process.EmployeeProcess
import models.process.EmployeeProcessImpl
import java.util.UUID
import models.domains.Employee
import java.util.Date

class Application(empProcess: EmployeeProcess) extends Controller {
  
  val logger: Logger = Logger(this.getClass())

  def index = Action {
    val emp = new Employee
    emp.id = (UUID.randomUUID.toString())
    emp.name=("Anand")
    emp.address=("New Delhi")
    emp.designation=("Developer")

    empProcess.createEmployee(emp)
    Ok(views.html.index("Employees : " + empProcess.findAllEmployee.mkString))
  }

}

object Application extends Application(EmployeeProcessImpl)