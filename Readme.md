# NEW STUDENT
http://localhost:8080/student-management-system/comrade/students

Json body:
``` Json
{
	"firstName" : "Wuffuwff",
	"lastName" : "Niselander",
	"email" : "nisselander@muffe.se",
	"phoneNumber" : "073555555"
}
```

# UPDATE STUDENT
http://localhost:8080/student-management-system/comrade/students/{email}

Json body:
``` Json
{
	"lastName":"fittluder",
	"firstName":"g"
}
```

# GET STUDENT(by Last name)
E.g.
http://localhost:8080/student-management-system/comrade/students/query?lastName=And


# GET ALL STUDENTS
http://localhost:8080/student-management-system/comrade/students


# DELETE STUDENT(by Email)
http://localhost:8080/student-management-system/comrade/students/{email}

========================================================================================

# NEW TEACHER
http://localhost:8080/student-management-system/comrade/teachers

Json body:
``` Json
{
	"firstName":"Oppla",
	"lastName":"Pompa",
	"email":"email@lel.se"
}
```

# UPDATE TEACHER
http://localhost:8080/student-management-system/comrade/teachers/{email}

Json body:
``` Json
{
	"lastName":"nyttNamn",
	"firstName":"nyttEfternamn"
}
```

# GET TEACHER(by email)
E.g.
http://localhost:8080/student-management-system/comrade/teachers/{email}


# GET ALL TEACHERS
http://localhost:8080/student-management-system/comrade/teachers


# REMOVE TEACHER(by Email)
http://localhost:8080/student-management-system/comrade/teachers/{email}

# ADD SUBJECT TO TEACHER
http://localhost:8080/student-management-system/comrade/teachers/{SubjectName}/{email}

========================================================================================

# NEW SUBJECT
http://localhost:8080/student-management-system/comrade/subjects

Json body:
``` Json
{
	"subjectName":"English"
}
```

# ADD STUDENT TO SUBJECT
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}/{studentEmail}


# GET ALL SUBJECTS
http://localhost:8080/student-management-system/comrade/subjects


# GET SUBJECT BY NAME
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}


# DELETE SUBJECT BY NAME
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}



