import React, { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import api from './api';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const history = useHistory();

  useEffect(() => {
    api.get('/')
      .then(response => setEmployees(response.data))
      .catch(error => console.error('Error fetching employees:', error));
  }, []);

  const handleDelete = (id) => {
    api.delete(`/${id}`)
      .then(() => setEmployees(employees.filter(employee => employee.id !== id)))
      .catch(error => console.error('Error deleting employee:', error));
  };

  return (
    <div>
      <h2>Employee List</h2>
      <Link to="/add">Add Employee</Link>
      <ul>
        {employees.map(employee => (
          <li key={employee.id}>
            {employee.firstName} {employee.lastName}
            <Link to={`/update/${employee.id}`}>Update</Link>
            <button onClick={() => handleDelete(employee.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EmployeeList;
