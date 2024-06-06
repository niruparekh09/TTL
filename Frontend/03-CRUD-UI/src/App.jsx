import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [emp, setEmp] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/employee");
      setEmp(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const deleteEmployee = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/employee/${id}`);
      console.log("Post deleted:", id);
      setEmp(emp.filter((emp) => emp.id !== id));
    } catch (error) {
      console.error("Error deleting post:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <table className="w-full w-600 table-auto border border-gray-300">
        <thead>
          <tr className="bg-gray-100 text-left text-gray-600 font-medium">
            <th className="px-4 py-2">ID</th>
            <th className="px-4 py-2">First Name</th>
            <th className="px-4 py-2">Last Name</th>
            <th className="px-4 py-2">City</th>
            <th className="px-4 py-2">Salary</th>
            <th className="px-4 py-2">Email</th>
            <th className="px-4 py-2">Department</th>
            <th className="px-4 py-2">Delete</th>
          </tr>
        </thead>
        <tbody>
          {emp.map((employee) => (
            <tr key={employee.id} className="border-b border-gray-300">
              <td className="px-4 py-2">{employee.id}</td>
              <td className="px-4 py-2">{employee.firstName}</td>
              <td className="px-4 py-2">{employee.lastName}</td>
              <td className="px-4 py-2">{employee.city}</td>
              <td className="px-4 py-2">{employee.salary}</td>
              <td className="px-4 py-2">{employee.email}</td>
              <td className="px-4 py-2">{employee.dept}</td>
              <td className="px-4 py-2"><button onClick={() => deleteEmployee(employee.id)}>Delete</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
