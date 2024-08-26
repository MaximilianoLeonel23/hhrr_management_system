import axios from 'axios';
import axiosInstance from '../axios/axios';

export const allEmployeesRequest = () => axiosInstance.get('/employees');

export const createNewEmployeeRequest = data => axiosInstance.post('/employees', data);

export const getEmployeeByIdRequest = id => axiosInstance.get(`employees/${id}`);

export const updateEmployeeByIdRequest = (id, data) => axiosInstance.put(`employees/${id}`, data);
