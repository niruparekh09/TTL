import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import EmployeeForm from './EmployeeForm';
import api from './api';
import './styles.css';

const UpdateEmployee = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [initialValues, setInitialValues] = useState(null);

  useEffect(() => {
    api
      .get(`/${id}`)
      .then((response) => setInitialValues(response.data))
      .catch((error) => console.error('Error fetching employee:', error));
  }, [id]);

  const handleUpdateEmployee = (values, { setSubmitting }) => {
    api
      .put(`/${id}`, values)
      .then(() => {
        setSubmitting(false);
        navigate('/employees');
      })
      .catch((error) => {
        console.error('Error updating employee:', error);
        setSubmitting(false);
      });
  };

  if (!initialValues) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <EmployeeForm
        initialValues={initialValues}
        onSubmit={handleUpdateEmployee}
        isUpdate={true}
      />
    </div>
  );
};

export default UpdateEmployee;
