import { useEffect, useState } from 'react';
import { plusIcon } from '../../assets/icons/icons';

import EmployeesTable from './EmployeesTable';
import { allEmployeesRequest } from '../../utils/requests/employee';
import { useNavigate, useOutletContext } from 'react-router-dom';

function Employee() {
	const [employees, setEmployees] = useState([]);
	const [filteredEmployees, setFilteredEmployees] = useState([]);
	const [department, setDepartment] = useState('');
	const [role, setRole] = useState('');
	const { departmentList, roleList } = useOutletContext();

	const navigate = useNavigate();

	useEffect(() => {
		const fetchData = async () => {
			try {
				const employeeData = await allEmployeesRequest();
				if (employeeData) {
					setEmployees(employeeData.data);
					setFilteredEmployees(employeeData.data);
				}
			} catch (error) {
				console.log(error);
			}
		};
		fetchData();
	}, []);

	useEffect(() => {
		handleFilter();
	}, [department, role]);

	const handleFilter = () => {
		const employeesFiltered = employees.filter(employee => {
			return (
				(!department || employee.department.toLowerCase().includes(department.toLowerCase())) &&
				(!role || employee.role.toLowerCase().includes(role.toLowerCase()))
			);
		});
		setFilteredEmployees(employeesFiltered);
	};

	return (
		<section className='flex flex-col gap-4'>
			<div className='flex items-center gap-4'>
				<button className='flex items-center gap-1 btn-ghost' onClick={() => navigate('/employee/add')}>
					{plusIcon()}Add new Employee
				</button>
				<div className='flex items-center gap-4'>
					<p>Filter by department:</p>
					<select
						className='select-input'
						name='department'
						value={department}
						onChange={e => setDepartment(e.target.value)}
					>
						<option value=''>All</option>
						{departmentList.map(department => (
							<option key={department} value={department}>
								{department}
							</option>
						))}
					</select>
				</div>
				<div className='flex items-center gap-4'>
					<p>Filter by role:</p>
					<select
						className='select-input'
						name='role'
						value={role}
						onChange={e => setRole(e.target.value)}
					>
						<option value=''>All</option>
						{roleList &&
							roleList.map(role => (
								<option key={role} value={role}>
									{role}
								</option>
							))}
					</select>
				</div>
			</div>
			<div>
				<EmployeesTable employees={filteredEmployees} />
			</div>
		</section>
	);
}

export default Employee;
