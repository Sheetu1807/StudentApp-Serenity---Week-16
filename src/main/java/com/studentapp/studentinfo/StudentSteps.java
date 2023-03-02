package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSteps {

    @Step("getting all information :{0}")
    public ValidatableResponse getAllStudentInfo(){
        return   SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();

    }

    @Step("creating student with firstName :{0},lastName: {1},email:{2},programme:{3} and courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> coursesList) {


        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(coursesList);
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(studentPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Step("getting student info by lastName:{1}")
    public HashMap<String, Object> getStudentInfoBylastName(String lastName) {
        String part1 = "findAll{it.lastName=='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract().path(part1 + lastName + part2);

    }


    @Step("update student info with studentID:{0},firstName :{1},lastName: {2},email:{3},programme:{4} and courses:{5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, List<String> courseList) {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();
    }

    @Step("deleting student information with studentId:{0}")
    public ValidatableResponse deleteStudentInfoByID(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("getting student info By studentId:{0}")
    public ValidatableResponse getStudentInfoByStudentId(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }


//    @Step("getting student info by email:{0}")
//    public HashMap<String,Object>getStudentInfoByEmail(String email){
//
//        String part1 ="findAll{it.email=='";
//        String part2 = "'}.get(0)";
//
//        HashMap<String,Object>studentMapData =SerenityRest.given()
//                .log().all()
//                .when()
//                .get(EndPoints.GET_ALL_STUDENT)
//                .then().statusCode(200).extract().path(part1+ email + part2);
//        return studentMapData;
//    }
}