import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import './styles.css';

const EmployeeForm = ({ initialValues, onSubmit, isUpdate }) => {
  const validationSchema = Yup.object().shape({
    firstName: Yup.string().required('First Name is required'),
    lastName: Yup.string().required('Last Name is required'),
    city: Yup.string().required('City is required'),
    salary: Yup.number().required('Salary is required'),
    email: Yup.string().email('Invalid email').required('Email is required'),
    password: Yup.string().required('Password is required'),
    dept: Yup.string().required('Department is required'),
  });

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    >
      {({ isSubmitting }) => (
        <Form className="container">
          <h2>{isUpdate ? 'Update' : 'Add'} Employee</h2>
          <div>
            <label>First Name</label>
            <Field type="text" name="firstName" />
            <ErrorMessage name="firstName" component="div" className="error" />
          </div>
          <div>
            <label>Last Name</label>
            <Field type="text" name="lastName" />
            <ErrorMessage name="lastName" component="div" className="error" />
          </div>
          <div>
            <label>City</label>
            <Field type="text" name="city" />
            <ErrorMessage name="city" component="div" className="error" />
          </div>
          <div>
            <label>Salary</label>
            <Field type="number" name="salary" />
            <ErrorMessage name="salary" component="div" className="error" />
          </div>
          <div>
            <label>Email</label>
            <Field type="email" name="email" />
            <ErrorMessage name="email" component="div" className="error" />
          </div>
          <div>
            <label>Password</label>
            <Field type="password" name="password" />
            <ErrorMessage name="password" component="div" className="error" />
          </div>
          <div>
            <label>Department</label>
            <Field type="text" name="dept" />
            <ErrorMessage name="dept" component="div" className="error" />
          </div>
          <input
            type="submit"
            value={isUpdate ? 'Update' : 'Add'}
            disabled={isSubmitting}
          />
        </Form>
      )}
    </Formik>
  );
};

export default EmployeeForm;
