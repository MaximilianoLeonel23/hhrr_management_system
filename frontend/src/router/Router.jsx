import { createBrowserRouter } from 'react-router-dom';
import Dashboard from '../components/dashboard/Dashboard';
import LoginPage from '../pages/loginPage/LoginPage';
import Employee from '../components/employee/Employee';
import Tasks from '../components/task/Tasks';
import Projects from '../components/project/Projects';
import Payroll from '../components/payroll/Payroll';
import Setting from '../components/setting/Setting';
import MainLayout from '../layout/MainLayout';

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
				element: <Employee />,
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
