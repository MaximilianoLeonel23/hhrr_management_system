import React, { useEffect, useState } from 'react';
import { Outlet } from 'react-router-dom';
import { employeeIcon } from '../assets/icons/icons';
import Title from '../components/titles/Title';
import { allEmployeesRequest } from '../utils/requests/employee';

function EmployeeLayout() {
	const [departmentList, setDepartmentList] = useState([]);
	const [roleList, setRoleList] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const employeeData = await allEmployeesRequest();

				if (employeeData) {
					const departmentSet = new Set();
					const roleSet = new Set();

					employeeData.data.forEach(employee => {
						departmentSet.add(employee.department);
						roleSet.add(employee.role);
					});

					setDepartmentList(Array.from(departmentSet));
					setRoleList(Array.from(roleSet));
				}
			} catch (error) {
				console.log(error);
			}
		};
		fetchData();
	}, []);
	return (
		<div className='flex flex-col gap-4 p-4'>
			<div className='flex items-center gap-4'>
				<div className='bg-white rounded p-2.5 shadow text-gray-600'>{employeeIcon()}</div>
				<Title title='Employees' />
			</div>
			<Outlet context={{ departmentList, roleList }} />
		</div>
	);
}

export default EmployeeLayout;
