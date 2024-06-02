import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import EmployeeList from './EmployeeList';
import AddEmployee from './AddEmployee';
import UpdateEmployee from './UpdateEmployee';
import './styles.css';

const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route exact path="/employees" element={<EmployeeList />} />
          <Route exact path="/add" element={<AddEmployee />} />
          <Route exact path="/update/:id" element={<UpdateEmployee />} />
          <Route path="*" element={<div>404 Not Found</div>} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
