import React from 'react';
import { useNavigate } from 'react-router-dom';
import EmployeeForm from './EmployeeForm';
import api from './api';
import './styles.css';

const AddEmployee = () => {
  const navigate = useNavigate();

  const handleAddEmployee = (values, { setSubmitting }) => {
    api
      .post('/', values)
      .then(() => {
        setSubmitting(false);
        navigate('/employees');
      })
      .catch((error) => {
        console.error('Error adding employee:', error);
        setSubmitting(false);
      });
  };

  const initialValues = {
    firstName: '',
    lastName: '',
    city: '',
    salary: '',
    email: '',
    password: '',
    dept: '',
  };

  return (
    <div>
      <EmployeeForm
        initialValues={initialValues}
        onSubmit={handleAddEmployee}
      />
    </div>
  );
};

export default AddEmployee;
