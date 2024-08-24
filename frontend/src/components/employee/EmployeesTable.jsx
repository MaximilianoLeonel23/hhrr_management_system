import React from 'react';
import EmployeeTableItem from './EmployeeTableItem';
import { colorMap } from '../../utils/colorMap';
import { plusIcon } from '../../assets/icons/icons';

function EmployeesTable({ employees }) {
	const colors = Object.keys(colorMap);

	return (
		<section className='flex flex-col gap-4'>
			<div className='flex px-4 py-2 bg-gray-100 rounded-lg border border-gray-200 '>
				<p className='text-gray-500 text-sm font-medium w-1/2 text-left'>Employee Name</p>
				<p className='text-gray-500 text-sm font-medium w-1/4 text-left'>Department</p>
				<p className='text-gray-500 text-sm font-medium w-1/4 text-left'>Role</p>
			</div>
			<div className='flex flex-col px-4 bg-white rounded-lg border border-gray-200'>
				{employees &&
					employees.map((employee, index) => {
						const color = colors[index % colors.length];
						return <EmployeeTableItem key={employee.id} employee={employee} color={color} />;
					})}
			</div>
		</section>
	);
}

export default EmployeesTable;
