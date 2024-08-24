import { useEffect, useState } from 'react';
import { dashboardIcon } from '../../assets/icons/icons';
import Title from '../titles/Title';
import DashboardCard from './DashboardCard';
import { allEmployeesRequest } from '../../utils/requests/employee';
import { allPayrollsRequest } from '../../utils/requests/payroll';
import { allProjectsRequest } from '../../utils/requests/project';
import { allTasksRequest } from '../../utils/requests/task';

function Dashboard() {
	const [employees, setEmployees] = useState([]);
	const [payrolls, setPayrolls] = useState(0);
	const [tasks, setTasks] = useState([]);
	const [projects, setProjects] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const employeeData = await allEmployeesRequest();
				const payrollData = await allPayrollsRequest();
				const projectsData = await allProjectsRequest();
				const tasksData = await allTasksRequest();

				if (employeeData) {
					setEmployees(employeeData.data);
				}
				if (projectsData) {
					setProjects(projectsData.data);
				}
				if (tasksData) {
					setTasks(tasksData.data);
				}
				if (payrollData) {
					const totalPayrollNetpay = payrollData.data.reduce(
						(sum, payroll) => (sum += payroll.netPay),
						0,
					);
					setPayrolls(totalPayrollNetpay);
				}
			} catch (error) {
				console.log(error);
			}
		};

		fetchData();
	}, []);

	return (
		<div className='flex flex-col gap-4'>
			<div className='flex items-center gap-4'>
				<div className='bg-white bg-indi rounded p-2.5 shadow-md'>{dashboardIcon()}</div>
				<Title title='Dashboard' />
			</div>
			<div className='flex gap-4'>
				<DashboardCard title={'Total Employees'} number={employees.length} color={'indigo'} />
				<DashboardCard title={'Total Projects'} number={projects.length} color={'pink'} />
				<DashboardCard title={'Total Tasks'} number={tasks.length} color={'yellow'} />
				<DashboardCard title={'Total Payrolls'} number={payrolls} color={'green'} coin />
			</div>
		</div>
	);
}

export default Dashboard;
