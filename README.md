# HKTVMall_IT_TASK
#Li Tung Leong HKTVMall_IT_TASK

how to set up:

1:
install:
    java
    maven
    XAMPP (localhost mysql)

2: create database hktvmall in http://localhost/phpmyadmin/

3: create table item (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    quantity INT(10) NOT NULL 
)

4: run :
mvn spring-boot:run 
in pom base directory

5: upload csv like sample1.csv




I learn from google everyday