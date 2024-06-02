// src/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/employee',
});

export default api;
