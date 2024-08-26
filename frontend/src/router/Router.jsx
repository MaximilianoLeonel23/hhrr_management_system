import { createBrowserRouter } from 'react-router-dom';
import Dashboard from '../components/dashboard/Dashboard';
import LoginPage from '../pages/loginPage/LoginPage';
import Employee from '../components/employee/Employee';
import Tasks from '../components/task/Tasks';
import Projects from '../components/project/Projects';
import Payroll from '../components/payroll/Payroll';
import Setting from '../components/setting/Setting';
import MainLayout from '../layout/MainLayout';
import EmployeeLayout from '../layout/EmployeeLayout';
import { element } from 'prop-types';
import EmployeeForm from '../components/employee/EmployeeForm';
import EmployeeDetails from '../components/employee/EmployeeDetails';

const router = createBrowserRouter([
	{
		path: '/',
		element: <MainLayout />,
		children: [
			{
				index: true,
				element: <Dashboard />,
			},
			{
				path: '/employee',
				element: <EmployeeLayout />,
				children: [
					{
						index: true,
						element: <Employee />,
					},
					{
						path: '/employee/add',
						element: <EmployeeForm />,
					},
					{
						path: '/employee/:id',
						element: <EmployeeDetails />,
					},
				],
			},
			{
				path: '/project',
				element: <Projects />,
			},
			{
				path: '/payroll',
				element: <Payroll />,
			},
			{
				path: '/tasks',
				element: <Tasks />,
			},
			{
				path: '/setting',
				element: <Setting />,
			},
		],
	},
	{
		path: '/login',
		element: <LoginPage />,
	},
]);

export default router;
