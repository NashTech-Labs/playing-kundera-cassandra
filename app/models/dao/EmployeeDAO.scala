package models.process

import javax.persistence._
import models.domains.Employee
import models.domains.Employee
import models.domains.Employee
import play.api.Logger

trait EmployeeDAO {

  def create(employee: Employee): Either[String, String]
  def findAll(): Either[String, List[Employee]]

}

object EmployeeDAOImpl extends EmployeeDAO {

  val logger: Logger = Logger(this.getClass())

  val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("cassandra_employees")

  def create(employee: Employee): Either[String, String] = {
    try {
      val em = emf.createEntityManager()
      em.persist(employee)
      em.close()
      Right("Employee has been created successfully.")
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

  def findAll(): Either[String, List[Employee]] = {
    try {
      val em = emf.createEntityManager()
      val query = em.createQuery("SELECT e FROM Employee e")
      val employees: List[Employee] = query.getResultList().toArray.toList.asInstanceOf[List[Employee]]
      em.close()
      Right(employees)
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

}
