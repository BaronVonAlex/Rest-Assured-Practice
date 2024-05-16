package ge.tbcacad.employee;

import ge.tbcacad.steps.employeeservice.EmployeeSoapServiceSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Employee SOAP Service")
@Feature("Employee Services")
@Link(name = "WSDL Document", url = "https://schemas.xmlsoap.org/wsdl/")
public class EmployeeSoapServiceTests {

    protected static EmployeeSoapServiceSteps employeeSoapServiceSteps;

    @BeforeTest
    public void setUp() throws Exception {
        RestAssured.filters(new AllureRestAssured());
        employeeSoapServiceSteps = new EmployeeSoapServiceSteps();
    }

    @Story("Employee Adding Service")
    @Test(priority = 1, description = "Create AddEmployee Request and with xPath validate that appropriate message was received.")
    public void newEmployeeAdditionTests() throws DatatypeConfigurationException {
        employeeSoapServiceSteps
                .resetEmployeeInfo()
                .setName(EMPLOYEE_NAME_INITIAL)
                .setEmployeeId(EMPLOYEE_ID_INITIAL)
                .setPhone(EMPLOYEE_PHONE_INITIAL)
                .setBirthDate(EMPLOYEE_BIRTHDAY_INITIAL)
                .setDepartment(EMPLOYEE_DEPARTMENT_INITIAL);
        Response response = employeeSoapServiceSteps.addEmployee();

        employeeSoapServiceSteps.validateAddEmployeeResponse(response, CONTENT_ADDITION_SUCCESS_MSG);
    }

    @Story("Employee by ID Service")
    @Test(priority = 2, description = "Get employee by ID and check if they have expected values")
    public void employeeByIDTests() {
        Response response = employeeSoapServiceSteps
                .resetEmployeeInfo()
                .setEmployeeId(15L)
                .getEmployeeByIdRequest();

        employeeSoapServiceSteps
                .validateEmployeeName(response, EMPLOYEE_NAME_INITIAL)
                .validateEmployeeDepartment(response, EMPLOYEE_DEPARTMENT_INITIAL)
                .validateEmployeePhone(response, EMPLOYEE_PHONE_INITIAL)
                .validateEmployeeBirthDate(response, EMPLOYEE_BIRTHDAY_INITIAL);
    }

    @Story("Employee Updating Service")
    @Test(priority = 3, description = "Construct SOAP Request and update existing Employee, Validate if values are correct.")
    public void updateEmployeeTests() throws DatatypeConfigurationException {
        employeeSoapServiceSteps
                .resetEmployeeInfo()
                .updateEmployeeId(15L)
                .updateName(EMPLOYEE_NAME_UPDATED)
                .updateBirthday(EMPLOYEE_BIRTHDAY_UPDATED)
                .updateDepartment(EMPLOYEE_DEPARTMENT_UPDATED)
                .updatePhone(EMPLOYEE_PHONE_UPDATED);

        Response response = employeeSoapServiceSteps.updateEmployee();

        employeeSoapServiceSteps.validateUpdateEmployeeResponse(response, CONTENT_UPDATE_SUCCESS_MSG);

        Response updatedResponse = employeeSoapServiceSteps
                .resetEmployeeInfo()
                .setEmployeeId(15L)
                .getEmployeeByIdRequest();

        employeeSoapServiceSteps
                .validateEmployeeName(updatedResponse, EMPLOYEE_NAME_UPDATED)
                .validateEmployeeDepartment(updatedResponse, EMPLOYEE_DEPARTMENT_UPDATED)
                .validateEmployeePhone(updatedResponse, EMPLOYEE_PHONE_UPDATED)
                .validateEmployeeBirthDate(updatedResponse, EMPLOYEE_BIRTHDAY_UPDATED);
    }

    @Story("Employee Deletion Service")
    @Test(priority = 4, description = "Delete Employee with ID, then validate with response message and new ID Request.")
    public void deleteEmployeeTests() {
        employeeSoapServiceSteps
                .resetEmployeeInfo()
                .deleteEmployeeById(15L);

        Response response = employeeSoapServiceSteps.deleteEmployee();

        employeeSoapServiceSteps.validateServiceStatus(response, CONTENT_DELETION_SUCCESS_MSG);

        Response updatedResponse = employeeSoapServiceSteps
                .resetEmployeeInfo()
                .setEmployeeId(15L)
                .getEmployeeByIdRequest();

        assertThat(updatedResponse.getStatusCode(), Matchers.is(EXPECTED_STATUS_CODE));
    }
}
