import axiosInstance from '../axios/axios';

export const allTasksRequest = () => axiosInstance.get('/tasks');
