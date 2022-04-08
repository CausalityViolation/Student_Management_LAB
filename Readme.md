# NEW STUDENT (POST)
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

# UPDATE STUDENT (PATCH)
http://localhost:8080/student-management-system/comrade/students/{email}

Json body:
``` Json
{
	"lastName":"grisen",
	"firstName":"g"
}
```

# FIND STUDENT(by Last name) (QUERY)
E.g.
http://localhost:8080/student-management-system/comrade/students/query?lastName=And


# FIND ALL STUDENTS (GET)
http://localhost:8080/student-management-system/comrade/students


# DELETE STUDENT(by Email) (DELETE)
http://localhost:8080/student-management-system/comrade/students/{email}

===========================================================================

# NEW TEACHER (POST)
http://localhost:8080/student-management-system/comrade/teachers

Json body:
``` Json
{
	"firstName":"Oppla",
	"lastName":"Pompa",
	"email":"email@lel.se"
}
```

# UPDATE TEACHER (PATCH)
http://localhost:8080/student-management-system/comrade/teachers/{email}

Json body:
``` Json
{
	"lastName":"nyttNamn",
	"firstName":"nyttEfternamn"
}
```

# GET TEACHER(by email) (GET)
E.g.
http://localhost:8080/student-management-system/comrade/teachers/{email}


# GET ALL TEACHERS (GET)
http://localhost:8080/student-management-system/comrade/teachers


# REMOVE TEACHER(by Email) (DELETE)
http://localhost:8080/student-management-system/comrade/teachers/{email}

# ADD SUBJECT TO TEACHER (PATCH)
http://localhost:8080/student-management-system/comrade/teachers/{SubjectName}/{email}

===========================================================================

# NEW SUBJECT (POST)
http://localhost:8080/student-management-system/comrade/subjects

Json body:
``` Json
{
	"subjectName":"English"
}
```

# ADD STUDENT TO SUBJECT (PATCH)
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}/{studentEmail}


# GET ALL SUBJECTS (GET)
http://localhost:8080/student-management-system/comrade/subjects


# GET SUBJECT BY NAME (GET)
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}


# DELETE SUBJECT BY NAME (DELETE)
http://localhost:8080/student-management-system/comrade/subjects/{subjectName}



