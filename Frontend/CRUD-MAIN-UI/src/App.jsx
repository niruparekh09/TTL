// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import List from './components/List';
import Add from './components/Add';
import Update from './components/Update';
import Delete from './components/Delete';

function App() {
  return (
    <Router>
      <div className="bg-gray-100 min-h-screen">
        <nav className="bg-blue-500 p-4">
          <ul className="flex justify-center space-x-4 text-white">
            <li>
              <a href="/" className="hover:underline">
                List Employees
              </a>
            </li>
            <li>
              <a href="/add" className="hover:underline">
                Add Employee
              </a>
            </li>
            <li>
              <a href="/delete" className="hover:underline">
                Delete Employee
              </a>
            </li>
          </ul>
        </nav>
        <div className="container mx-auto p-4">
          <Switch>
            <Route exact path="/" component={List} />
            <Route path="/add" component={Add} />
            <Route path="/update/:id" component={Update} />
            <Route path="/delete" component={Delete} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
