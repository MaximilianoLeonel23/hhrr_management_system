import React from 'react';
import { bulbIcon, calendarIcon, pencilIcon } from '../../assets/icons/icons';

function EmployeeProjectCard({ project }) {
	if (!project)
		return (
			<article className='min-h-72 w-[calc(50%-1rem)] flex flex-col flex-grow gap-8 p-4 bg-gray-200 border border-gray-200 rounded'></article>
		);

	return (
		<article className='min-h-72 w-[calc(50%-1rem)] flex flex-col flex-grow gap-8 p-4 border border-gray-200 rounded'>
			<div>
				<h5 className='flex text-gray-500'> Project Name</h5>
				<div className='flex items-start gap-4'>
					<span className='w-4 h-4 text-gray-700'>{bulbIcon()}</span>
					<p className='text-gray-700 font-medium'>{project.name}</p>
				</div>
			</div>
			<div>
				<h5 className='text-gray-500'>Timeline</h5>
				<div className='flex items-start gap-4'>
					<span className='w-4 h-4 text-gray-700'>{calendarIcon()}</span>
					<div className='flex items-center gap-2'>
						<p className='text-gray-700 font-medium'>{project.startDate}</p>
						<span>-</span>
						<p className='text-gray-700 font-medium'>
							{project.endDate ? project.endDate : 'Not finished'}
						</p>
					</div>
				</div>
			</div>
			<div className='flex flex-col gap-2'>
				<h5 className='text-gray-500'>Description</h5>
				<div className='flex items-start gap-4'>
					<span className='w-4 h-4 text-gray-700'>{pencilIcon()}</span>
					<p className='text-gray-700 font-medium'>{project.description}</p>
				</div>
			</div>
		</article>
	);
}

export default EmployeeProjectCard;
