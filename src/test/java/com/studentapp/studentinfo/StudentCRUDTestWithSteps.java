package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StudentCRUDTestWithSteps extends TestBase {

    static String firstName = "Sheetal"+ TestUtils.getRandomValue();
    static String lastName = "Dussooa"+ TestUtils.getRandomValue();
    static String email = "sheetal20"+ TestUtils.getRandomValue()+ "@yahoo.com";
    static String programme = "Criminology";
    static int studentId;

    @Steps
    StudentSteps studentSteps;

    @Title("This will create a new student")
    @Test
    public void test001() {
        List<String> courses= new ArrayList<>();
        courses.add("Philosophy of Law");
        courses.add("Crime and Justice");

        ValidatableResponse response =studentSteps.createStudent(firstName,lastName,email,programme,courses);
        response.statusCode(201);


    }

    @Title("Verify if student is created")
    @Test
    public void test002() {
        HashMap<String,Object> studentMapData =studentSteps.getStudentInfoBylastName(lastName);
        Assert.assertThat(studentMapData,hasValue(lastName));
        studentId= (int) studentMapData.get("id");
        System.out.println(studentId);

    }

    @Title("Update the user information")
    @Test
    public void test003() {

        lastName = lastName + "Talwar";

        List<String> courses = new ArrayList<>();
        courses.add("Computer Science");
        courses.add("Financial Analysis");
        studentSteps.updateStudent(studentId,firstName,lastName,email,programme,courses);
        HashMap<String,Object> studentMap=studentSteps.getStudentInfoBylastName(lastName);
        Assert.assertThat(studentMap,hasValue(lastName));

    }
    @Title("Delete student info by StudentID and verify its deleted")
    @Test
    public void test004(){

        studentSteps.deleteStudentInfoByID(studentId).statusCode(204);
        studentSteps.getStudentInfoByStudentId(studentId).statusCode(404);

    }

}