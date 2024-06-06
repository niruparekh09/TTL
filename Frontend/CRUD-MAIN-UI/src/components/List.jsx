// src/components/List.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

function List({ history }) {
  const [employees, setEmployees] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);

      try {
        const response = await axios.get('http://localhost:8080/employee');
        setEmployees(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [history]);

  return (
    <div className="container mx-auto py-8">
      {isLoading && <p>Loading employees...</p>}
      {error && <p>Error: {error.message}</p>}
      {employees.length > 0 && (
        <table className="w-full border-collapse">
          <thead>
            <tr className="bg-gray-200">
              <th className="border px-4 py-2">ID</th>
              <th className="border px-4 py-2">First Name</th>
              <th className="border px-4 py-2">Last Name</th>
              <th className="border px-4 py-2">City</th>
              <th className="border px-4 py-2">Salary</th>
              <th className="border px-4 py-2">Email</th>
              <th className="border px-4 py-2">Department</th>
              <th className="border px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.id} className="bg-gray-100">
                <td className="border px-4 py-2">{employee.id}</td>
                <td className="border px-4 py-2">{employee.firstName}</td>
                <td className="border px-4 py-2">{employee.lastName}</td>
                <td className="border px-4 py-2">{employee.city}</td>
                <td className="border px-4 py-2">{employee.salary}</td>
                <td className="border px-4 py-2">{employee.email}</td>
                <td className="border px-4 py-2">{employee.dept}</td>
                <td className="border px-4 py-2">
                  <button
                    onClick={() => history.push(`/update/${employee.id}`)}
                    className="bg-blue-500 hover:bg-blue-700 text-white py-1 px-2 rounded"
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default withRouter(List);
