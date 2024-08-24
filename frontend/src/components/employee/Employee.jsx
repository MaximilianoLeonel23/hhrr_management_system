import { useEffect, useState } from 'react';
import { employeeIcon } from '../../assets/icons/icons';
import Title from '../titles/Title';
import EmployeesTable from './EmployeesTable';
import { allEmployeesRequest } from '../../utils/requests/employee';

function Employee() {
	const [employees, setEmployees] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const employeeData = await allEmployeesRequest();

				if (employeeData) {
					setEmployees(employeeData.data);
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
				<div className='bg-white bg-indi rounded p-2.5 shadow-md'>{employeeIcon()}</div>
				<Title title='Employees' />
			</div>

			<div>
				<EmployeesTable employees={employees} />
			</div>
		</div>
	);
}

export default Employee;
