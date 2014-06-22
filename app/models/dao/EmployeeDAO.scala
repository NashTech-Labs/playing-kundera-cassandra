package models.process

import javax.persistence._
import models.domains.Employee
import play.api.Logger
import models.domains.Page

trait EmployeeDAO {

  def create(employee: Employee): Either[String, String]
  def update(employee: Employee): Either[String, String]
  def delete(id: String): Either[String, String]
  def findAll(): Either[String, List[Employee]]
  def list(page: Int, orderBy: Int, filter: String): Either[String, Page[Employee]]
  def findById(id: String): Either[String, Option[Employee]]

}

object EmployeeDAOImpl extends EmployeeDAO {

  val logger: Logger = Logger(this.getClass())

  val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("cassandra_employees")

  /**
   * @param employee
   * @return
   */
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

  def update(employee: Employee): Either[String, String] = {
    try {
      val em = emf.createEntityManager()
      em.merge(employee)
      em.close()
      Right("Employee has been updated successfully.")
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

  def delete(id: String): Either[String, String] = {
    try {
      val em = emf.createEntityManager()
      val query = em.createQuery(s"SELECT e FROM Employee e WHERE e.id = '$id'")
      val employees: List[Employee] = query.getResultList().toArray.toList.asInstanceOf[List[Employee]]
      em.remove(employees.head)
      em.close()
      Right("Employee has been updated successfully.")
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

  /**
   * @return
   */
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

  /**
   * Return a page of (Employee).
   *
   * @param page Page to display
   * @param pageSize Number of employees per page
   * @param orderBy Employee property used for sorting
   * @param filter Filter applied on the name column
   */
  def list(page: Int = 0, orderBy: Int = 1, filter: String = "%"): Either[String, Page[Employee]] = {
    try {
      val pageSize: Int = 10
      val offset = pageSize * page

      val em = emf.createEntityManager()

      val query = s"""SELECT e FROM Employee e"""
      val countQuery = s"""SELECT e FROM Employee e'"""

      val employees: List[Employee] = em.createQuery(query).getResultList().toArray.toList.asInstanceOf[List[Employee]]
      val totalRows: Long = employees.size

      Right(Page(employees, page, offset, totalRows))
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

  def findById(id: String): Either[String, Option[Employee]] = {
    try {
      val em = emf.createEntityManager()
      val query = em.createQuery(s"SELECT e FROM Employee e WHERE e.id = '$id'")
      val employees: List[Employee] = query.getResultList().toArray.toList.asInstanceOf[List[Employee]]
      em.close()
      Right(employees.headOption)
    }
    catch {
      case ex: Exception => logger.error("Error : ", ex); Left(ex.getMessage())
    }
  }

}
