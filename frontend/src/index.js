import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import "./bootstrap.min.css";

import Navbar from "./components/Navbar";
import EmployeeList from "./components/employee/EmployeeList";
import EmployeeInfo from "./components/employee/EmployeeInfo";
import EmployeeReception from "./components/employee/EmployeeReception";

const router = createBrowserRouter([
    {
        path: "/",
        element: <EmployeeList />,
    },
    {
        path: "/employee/add",
        element: <EmployeeReception />,
    },
    {
        path: "/employee/:id",
        element: <EmployeeInfo />,
    },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <Navbar />
        <div className="container mt-3">
            <RouterProvider router={router} />
        </div>
    </React.StrictMode>
);