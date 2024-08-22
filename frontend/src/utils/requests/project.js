import axiosInstance from '../axios/axios';

export const allProjectsRequest = () => axiosInstance.get('/projects');
