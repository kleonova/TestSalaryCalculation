import React, { useState, useEffect } from "react";
import EmployeeService from "../../services/EmployeeService";

function EmployeeList() {
    const [employees, setEmployees] = useState([]);
    const [commonAmount, setCommonAmount] = useState(0);

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = () => {
        EmployeeService.getAll()
            .then(response => {
                setEmployees(response.data);
                const sumWithInitial = response.data.reduce(
                    (accumulator, currentValue) => accumulator + currentValue.currentPayment,
                    0,
                );
                setCommonAmount(sumWithInitial);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const formatDate = (dateStr) => {
        const year = dateStr.substr(0, 4);
        const month = dateStr.substr(5, 2);
        const day = dateStr.substr(8, 2);
        return [day, month, year].join(".")
    }

    const currentDate = () => {
        const today = new Date();
        return [today.getDate(), today.getMonth(), today.getFullYear()].join(".")
    }

    return (
        <>
            <dl className="row">
                <dt className="col-sm-3">Current date</dt>
                <dd className="col-sm-9">{ currentDate() }</dd>

                <dt className="col-sm-3">Common payment</dt>
                <dd className="col-sm-9">{ commonAmount }</dd>
            </dl>

            <table className="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Group</th>
                        <th>Salary</th>
                        <th>Employment date</th>
                        <th>Current payment</th>
                    </tr>
                </thead>
                <tbody>
                    {employees && employees.map((employee, index) => (
                        <tr key={employee.id}>
                            <td>{ index + 1 }</td>
                            <td>{ employee.name }</td>
                            <td>{ employee.employeeGroup }</td>
                            <td>{ employee.salary }</td>
                            <td>{ formatDate(employee.employmentDate) }</td>
                            <td>{ employee.currentPayment }</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default EmployeeList;