import axiosInstance from '../axios/axios';

export const allPayrollsRequest = () => axiosInstance.get('/payrolls');
