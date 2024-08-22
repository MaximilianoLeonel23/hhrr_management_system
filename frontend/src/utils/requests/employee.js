import axiosInstance from '../axios/axios';

export const allEmployeesRequest = () => axiosInstance.get('/employees');
