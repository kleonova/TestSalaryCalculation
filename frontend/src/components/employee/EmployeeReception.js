import React, { useState } from "react";
import EmployeeService from "../../services/EmployeeService";

function EmployeeReception () {
    const initialNewEmployeeState = {
        id: null,
        firstName: "",
        secondName: "",
        lastName: "",
        employmentGroup: "EMPLOYEE",
        salary: "",
        employmentDate: '2020-03-03'
    };

    const [employee, setEmployee] = useState(initialNewEmployeeState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setEmployee({ ...employee, [name]: value });
    };

    const saveEmployee = () => {
        let data = {
            ...employee
        };
        delete data.id;

        EmployeeService.create(data)
            .then(response => {
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const newEmployee = () => {
        setEmployee(initialNewEmployeeState);
        setSubmitted(false);
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newEmployee}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="lastName">Last Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="lastName"
                            required
                            value={employee.lastName}
                            onChange={handleInputChange}
                            name="lastName"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="firstName">First Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="firstName"
                            required
                            value={employee.firstName}
                            onChange={handleInputChange}
                            name="firstName"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="secondName">Second Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="secondName"
                            required
                            value={employee.secondName}
                            onChange={handleInputChange}
                            name="secondName"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="employmentGroup">Group</label>
                        <select
                            className="form-select"
                            id="employmentGroup"
                            name="employmentGroup"
                            onChange={handleInputChange}
                        >
                            <option value="EMPLOYEE">EMPLOYEE</option>
                            <option value="MANAGER">MANAGER</option>
                            <option value="SALESMAN">SALESMAN</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label htmlFor="salary">Salary</label>
                        <input
                            type="text"
                            className="form-control"
                            id="salary"
                            required
                            value={employee.salary}
                            onChange={handleInputChange}
                            name="salary"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="employmentDate">Employment Date</label>
                        <input
                            type="date"
                            className="form-control"
                            id="employmentDate"
                            value={employee.employmentDate}
                            onChange={handleInputChange}
                            name="employmentDate"
                        />
                    </div>



                    <button onClick={saveEmployee} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
}

export default EmployeeReception;