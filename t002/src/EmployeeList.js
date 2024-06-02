import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api from './api';
import './styles.css';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api
      .get('/')
      .then((response) => setEmployees(response.data))
      .catch((error) => console.error('Error fetching employees:', error));
  }, []);

  const handleDelete = (id) => {
    api
      .delete(`/${id}`)
      .then(() =>
        setEmployees(employees.filter((employee) => employee.id !== id))
      )
      .catch((error) => console.error('Error deleting employee:', error));
  };

  return (
    <div className="container">
      <div className="header">
        <h2>Employee List</h2>
        <Link to="/add" className="link">
          Add Employee
        </Link>
      </div>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}>
            {employee.firstName} {employee.lastName}
            <div>
              <Link to={`/update/${employee.id}`} className="link">
                Update
              </Link>
              <button onClick={() => handleDelete(employee.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EmployeeList;
