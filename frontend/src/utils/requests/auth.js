import axiosInstance from '../axios/axios';

export const loginRequest = data => axiosInstance.post('/auth/login', data);
