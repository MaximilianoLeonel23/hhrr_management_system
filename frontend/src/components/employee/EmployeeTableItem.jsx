import React from 'react';

const EmployeeTableItem = ({ employee }) => {
	const { firstname, lastname, department, email, role } = employee;
	return (
		<div className='flex items-center border-b border-gray-200 p-4'>
			<div className='w-1/2 flex justify-start items-center gap-2'>
				<div className='h-8 w-8 bg-indigo-500 rounded-full'></div>
				<div>
					<p className='text-gray-700 text-sm font-medium'>{`${firstname} ${lastname}`}</p>
					<p className='text-gray-500 text-sm'>{email}</p>
				</div>
			</div>
			<div className='w-1/4'>
				<p className='flex items-center gap-2 py-1 px-3 w-fit bg-green-300 rounded-full text-gray-700 text-sm'>
					<span className='h-2 w-2 bg-green-500 rounded'></span>
					{department ? department : 'Not assigned'}
				</p>
			</div>
			<div className='w-1/4 text-gray-600 font-medium'>{role ? role : 'Nor assigned'}</div>
		</div>
	);
};

export default EmployeeTableItem;
