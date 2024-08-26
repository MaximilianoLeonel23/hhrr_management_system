import React from 'react';
import { colorMap, getColorByDepartment } from '../../utils/colorMap';
import { useNavigate } from 'react-router-dom';

const EmployeeTableItem = ({ employee, color }) => {
	const { firstname, lastname, department, email, role, id } = employee;
	const bgColor = colorMap[color] || 'bg-gray-500';
	const departmentColor = getColorByDepartment(department);

	const navigate = useNavigate();
	return (
		<div
			onClick={() => navigate(`/employee/${id}`)}
			className='flex items-center p-4 hover:bg-gray-100 hover:cursor-pointer'
		>
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
