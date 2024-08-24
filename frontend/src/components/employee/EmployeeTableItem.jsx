import React from 'react';
import { colorMap, getColorByDepartment } from '../../utils/colorMap';

const EmployeeTableItem = ({ employee, color }) => {
	const { firstname, lastname, department, email, role } = employee;
	const bgColor = colorMap[color] || 'bg-gray-500';
	const departmentColor = getColorByDepartment(department);
	return (
		<div className='flex items-center border-b border-gray-200 p-4'>
			<div className='w-1/2 flex justify-start items-center gap-2'>
				<div className={`h-8 w-8 ${bgColor} rounded-full`}></div>
				<div>
					<p className='text-gray-700 text-sm font-medium'>{`${firstname} ${lastname}`}</p>
					<p className='text-gray-500 text-sm'>{email}</p>
				</div>
			</div>
			<div className='w-1/4'>
				<p
					className={`flex items-center gap-2 py-1 px-3 w-fit ${departmentColor} rounded-full text-gray-700 text-sm opacity-90`}
				>
					<span className={`h-2 w-2 ${departmentColor} rounded brightness-75`}></span>
					{department ? department : 'Not assigned'}
				</p>
			</div>
			<div className='w-1/4 text-gray-600 font-medium'>{role ? role : 'Nor assigned'}</div>
		</div>
	);
};

export default EmployeeTableItem;
