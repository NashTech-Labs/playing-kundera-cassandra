package models.domains

import java.util.Date
import javax.persistence._
import java.util.UUID

@Entity
@Table(name = "employees", schema = "KunderaExamples@cassandra_employees")
class Employee {

  @Id
  var id: String = UUID.randomUUID.toString()

  @Column(name = "name")
  var name: String = "Employee"

  @Column(name = "address")
  var address: String = "Address"

  @Column(name = "dob")
  var dob: String = (new Date).toLocaleString()

  @Column(name = "joiningDate")
  var joiningDate: String = (new Date).toLocaleString()

  @Column(name = "designation")
  var designation: String = "Test"
    
}


