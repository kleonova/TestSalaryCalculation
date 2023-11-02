import http from '../http-common';

const getAll = () => {
    return http.get("/employee/all");
};

const create = data => {
    return http.post("/employee/add", data);
};

const EmployeeService = {
    getAll,
    create
};

export default EmployeeService;

