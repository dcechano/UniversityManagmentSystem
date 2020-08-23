-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS DEPARTMENT;

CREATE TABLE DEPARTMENT(
    ID BIGINT IDENTITY PRIMARY KEY,
    VERSION INT,
    MODIFIED_AT TIMESTAMP,
    NAME VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS PROGRAM;

CREATE TABLE PROGRAM(
    ID BIGINT IDENTITY PRIMARY KEY,
    VERSION INT,
    MODIFIED_AT TIMESTAMP,
    NAME VARCHAR(50),
    DEPARTMENT BIGINT NOT NULL,
    FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT(ID)
);

DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON(
    ID BIGINT IDENTITY PRIMARY KEY,
    USERNAME VARCHAR(50) UNIQUE NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    VERSION INT,
    MODIFIED_AT TIMESTAMP,
    PASSWORD VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS ROLES;

CREATE TABLE ROLES(
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS PERSON_ROLES;

CREATE TABLE PERSON_ROLES(
    ID BIGINT NOT NULL,
    ROLE BIGINT NOT NULL,
    FOREIGN KEY (ID) REFERENCES PERSON(ID),
    FOREIGN KEY (ROLE) REFERENCES ROLES(ID),
    CONSTRAINT PER_ROLES_KEY PRIMARY KEY (ID, ROLE)
);

DROP TABLE IF EXISTS FACULTYMEMBER;

CREATE TABLE FACULTYMEMBER
(
  ID BIGINT IDENTITY PRIMARY KEY,
  VERSION INT,
  MODIFIED_AT TIMESTAMP,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  STATUS VARCHAR(16),
  HIRING_DATE TIMESTAMP,
  DEPARTMENT BIGINT  NOT NULL,
  FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT(ID)
);

DROP TABLE IF EXISTS STAFFMEMBER;

CREATE TABLE STAFFMEMBER
(
  ID BIGINT IDENTITY PRIMARY KEY,
  VERSION INT,
  MODIFIED_AT TIMESTAMP,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  HIRING_DATE TIMESTAMP,
  DEPARTMENT BIGINT NOT NULL,
  STATUS VARCHAR(16),
  FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT(ID)
);

DROP TABLE IF EXISTS STUDENT;

CREATE TABLE STUDENT
(
  ID BIGINT IDENTITY PRIMARY KEY,
  VERSION INT,
  MODIFIED_AT TIMESTAMP,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  ENROLLMENT_DATE TIMESTAMP,
  MAJOR BIGINT,
  STATUS VARCHAR(18),
  FOREIGN KEY (MAJOR) REFERENCES PROGRAM(ID)
);

DROP TABLE IF EXISTS COURSE;

CREATE TABLE COURSE(
  ID BIGINT IDENTITY PRIMARY KEY,
  VERSION INT,
  MODIFIED_AT TIMESTAMP,
  NAME VARCHAR(50) NOT NULL,
  INSTRUCTOR BIGINT NOT NULL,
  DEPARTMENT BIGINT NOT NULL,
  FOREIGN KEY (INSTRUCTOR) REFERENCES FACULTYMEMBER(ID),
  FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT(ID)
);

DROP TABLE IF EXISTS FACULTY_COURSES;

CREATE TABLE COURSE_GRADE(
    STUDENT_ID BIGINT NOT NULL,
    COURSE_ID BIGINT NOT NULL,
    MODIFIED_AT TIMESTAMP,
    GRADE VARCHAR(2),
    FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(ID),
    FOREIGN KEY (COURSE_ID) REFERENCES COURSE(ID),
    CONSTRAINT COURSE_GRADE_KEY PRIMARY KEY (STUDENT_ID, COURSE_ID)
);

