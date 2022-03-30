# Student_Management_LAB
School project Java EE

**<Endpoints>**

**Delete:**
localhost:8080/student-management-system/api/students/{id of student to delete}

**Post:**
localhost:8080/student-management-system/api/students


**JSON POST example** 

{
	"firstName" : "Wuffuwff",
	"lastName" : "Niselander",
	"email" : "ae2@gmail.wow",
	"phoneNumber" : "073555555"
}

**GET Query (student last name)**
localhost:8080/student-management-system/api/students/query


**Get ALL**
localhost:8080/student-management-system/api/students

**PATCH**
localhost:8080/student-management-system/api/students/{id of student to update}


**JSON PATCH example** 

{
	"firstName" : "Muffe",
	"lastName" : "Muffesson",
}
