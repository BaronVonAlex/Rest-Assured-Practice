package ge.tbcacad.steps.employeeservice;

import com.example.springboot.soap.interfaces.*;
import ge.tbcacad.util.MarshallSoapRequestUtil;
import ge.tbcacad.util.XmlDateUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmployeeSoapServiceSteps {
    private final ObjectFactory objectFactory;
    private final AddEmployeeRequest addEmployeeRequest;
    private final GetEmployeeByIdRequest getEmployeeByIdRequest;
    private final DeleteEmployeeRequest deleteEmployeeRequest;
    private final UpdateEmployeeRequest updateEmployeeRequest;
    private EmployeeInfo employeeInfo;

    public EmployeeSoapServiceSteps() {
        this.objectFactory = new ObjectFactory();
        this.addEmployeeRequest = objectFactory.createAddEmployeeRequest();
        resetEmployeeInfo();
        this.getEmployeeByIdRequest = objectFactory.createGetEmployeeByIdRequest();
        this.updateEmployeeRequest = objectFactory.createUpdateEmployeeRequest();
        this.deleteEmployeeRequest = objectFactory.createDeleteEmployeeRequest();
    }

    @Step("Reset EmployeeInfo")
    public EmployeeSoapServiceSteps resetEmployeeInfo() {
        this.employeeInfo = objectFactory.createEmployeeInfo();
        return this;
    }

    // EMPLOYEE ADD SERVICE

    @Step("Set employee name to {name}")
    public EmployeeSoapServiceSteps setName(String name) {
        employeeInfo.setName(name);
        return this;
    }

    @Step("Set employee ID to {id}")
    public EmployeeSoapServiceSteps setEmployeeId(int id) {
        employeeInfo.setEmployeeId(id);
        return this;
    }

    @Step("Set employee phone number to {phone}")
    public EmployeeSoapServiceSteps setPhone(String phone) {
        employeeInfo.setPhone(phone);
        return this;
    }

    @Step("Set employee birth date to {birthDate}")
    public EmployeeSoapServiceSteps setBirthDate(String birthDate) throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregorianCalendar = XmlDateUtil.toXMLGregorianCalendar(birthDate);
        employeeInfo.setBirthDate(xmlGregorianCalendar);
        return this;
    }

    @Step("Set employee department to {department}")
    public EmployeeSoapServiceSteps setDepartment(String department) {
        employeeInfo.setDepartment(department);
        return this;
    }

    @Step("Add employee")
    public Response addEmployee() {
        addEmployeeRequest.setEmployeeInfo(employeeInfo);
        String body = MarshallSoapRequestUtil.marshallSoapRequest(addEmployeeRequest);

        return given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest")
                .body(body)
                .post("http://localhost:8087/ws");
    }

    @Step("Validate that employee name is {expectedName}")
    public EmployeeSoapServiceSteps validateAddEmployeeResponse(Response response, String expectedName) {
        assertThat(response.xmlPath().getString("Envelope.Body.addEmployeeResponse.serviceStatus.message"), Matchers.hasToString(expectedName));
        return this;
    }

    // EMPLOYEE ID REQUEST

    @Step("Set employee ID to {employeeId}")
    public EmployeeSoapServiceSteps setEmployeeId(long employeeId) {
        getEmployeeByIdRequest.setEmployeeId(employeeId);
        return this;
    }

    @Step("Send GetEmployeeById request")
    public Response getEmployeeByIdRequest() {
        String body = MarshallSoapRequestUtil.marshallSoapRequest(getEmployeeByIdRequest);

        return given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(body)
                .post("http://localhost:8087/ws");
    }

    @Step("Validate that employee name is {expectedName}")
    public EmployeeSoapServiceSteps validateEmployeeName(Response response, String expectedName) {
        assertThat(response.xmlPath().getString("Envelope.Body.getEmployeeByIdResponse.employeeInfo.name"), is(expectedName));
        return this;
    }

    @Step("Validate that employee department is {expectedDepartment}")
    public EmployeeSoapServiceSteps validateEmployeeDepartment(Response response, String expectedDepartment) {
        assertThat(response.xmlPath().getString("Envelope.Body.getEmployeeByIdResponse.employeeInfo.department"), is(expectedDepartment));
        return this;
    }

    @Step("Validate that employee phone is {expectedPhone}")
    public EmployeeSoapServiceSteps validateEmployeePhone(Response response, String expectedPhone) {
        assertThat(response.xmlPath().getString("Envelope.Body.getEmployeeByIdResponse.employeeInfo.phone"), is(expectedPhone));
        return this;
    }

    @Step("Validate that employee birth date is {expectedAdditionMessage}")
    public EmployeeSoapServiceSteps validateEmployeeBirthDate(Response response, String expectedAdditionMessage) {
        assertThat(response.xmlPath().getString("Envelope.Body.getEmployeeByIdResponse.employeeInfo.birthDate"), is(expectedAdditionMessage));
        return this;
    }

    // UPDATE EMPLOYEE

    @Step("Put Updated Employee ID {ID}")
    public EmployeeSoapServiceSteps updateEmployeeId(Long id) {
        employeeInfo.setEmployeeId(id);
        return this;
    }

    @Step("Put Updated Employee Name {updatedName}")
    public EmployeeSoapServiceSteps updateName(String updatedName) {
        employeeInfo.setName(updatedName);
        return this;
    }

    @Step("Put Updated Employee Department {updatedDepartment}")
    public EmployeeSoapServiceSteps updateDepartment(String updatedDepartment) {
        employeeInfo.setDepartment(updatedDepartment);
        return this;
    }

    @Step("Put Updated Employee Birthday {updatedBirthday}")
    public EmployeeSoapServiceSteps updateBirthday(String updatedBirthday) throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregorianCalendar = XmlDateUtil.toXMLGregorianCalendar(updatedBirthday);
        employeeInfo.setBirthDate(xmlGregorianCalendar);
        return this;
    }

    @Step("Put Updated Employee Phone {updatedPhone}")
    public EmployeeSoapServiceSteps updatePhone(String updatedPhone) {
        employeeInfo.setPhone(updatedPhone);
        return this;
    }

    @Step("Update Employee")
    public Response updateEmployee() {
        updateEmployeeRequest.setEmployeeInfo(employeeInfo);
        String body = MarshallSoapRequestUtil.marshallSoapRequest(updateEmployeeRequest);

        return given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest")
                .body(body)
                .post("http://localhost:8087/ws");
    }

    @Step("Validate that employee name is {expectedResponseMessage}")
    public EmployeeSoapServiceSteps validateUpdateEmployeeResponse(Response response, String expectedResponseMessage) {
        assertThat(response.xmlPath().getString("Envelope.Body.updateEmployeeResponse.serviceStatus.message"), Matchers.hasToString(expectedResponseMessage));
        return this;
    }

    // EMPLOYEE DELETION REQUEST

    @Step("Delete Employee by ID - {id}")
    public EmployeeSoapServiceSteps deleteEmployeeById(Long id) {
        deleteEmployeeRequest.setEmployeeId(id);
        return this;
    }

    @Step("Send Employee Deletion Request")
    public Response deleteEmployee() {
        String body = MarshallSoapRequestUtil.marshallSoapRequest(deleteEmployeeRequest);

        return given()
                .header("Content-type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest")
                .body(body)
                .post("http://localhost:8087/ws");
    }

    @Step("Validate if Request was successful")
    public EmployeeSoapServiceSteps validateServiceStatus(Response response, String expectedMessage) {
        assertThat(response.xmlPath().getString("Envelope.Body.deleteEmployeeResponse.serviceStatus.message"), is(expectedMessage));
        return this;
    }
}
