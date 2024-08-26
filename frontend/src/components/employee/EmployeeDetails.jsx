import React, { useEffect, useState } from 'react';
import { useOutletContext, useParams } from 'react-router-dom';
import { getEmployeeByIdRequest, updateEmployeeByIdRequest } from '../../utils/requests/employee';
import { getColorByDepartment } from '../../utils/colorMap';
import { useForm } from 'react-hook-form';
import EmployeeProjectCard from './EmployeeProjectCard';

function EmployeeDetails() {
	const { id } = useParams();
	const [employee, setEmployee] = useState(null);
	const { departmentList } = useOutletContext();
	const { register, handleSubmit } = useForm();

	useEffect(() => {
		const fetchEmployee = async () => {
			try {
				const response = await getEmployeeByIdRequest(id);
				if (response.status == 200) {
					setEmployee(response.data);
				}
			} catch (error) {
				console.log(error);
			}
		};
		fetchEmployee();
	}, [id]);

	const submitHandle = async data => {
		try {
			const response = await updateEmployeeByIdRequest(id, data);
			if (response.status == 200) {
				console.log(response.data);
			}
		} catch (error) {
			console.log(error);
		}
	};

	if (!employee) return <section className='flex flex-col gap-4'>Loading...</section>;

	return (
		<>
			<section className='flex flex-col'>
				<form onSubmit={handleSubmit(submitHandle)}>
					<div className='flex items-center gap-8 py-8'>
						<div className={`h-24 w-24 rounded-full ${getColorByDepartment(employee.department)}`}></div>
						<button className='btn-ghost'>Change picture</button>
					</div>
					<div className='flex items-start border-y border-gray-200 py-8'>
						<div className='w-2/5 text-left'>
							<h4 className='text-gray-800 font-semibold'>Personal Information</h4>
						</div>
						<div className='w-2/5 text-left flex flex-col gap-4'>
							<div className='flex'>
								<div className='employee-label'>Firstname</div>
								<input
									className='employee-input'
									type='text'
									defaultValue={employee.firstname}
									{...register('firstname', { required: true })}
								/>
							</div>
							<div className='flex'>
								<div className='employee-label'>Lastname</div>
								<input
									className='employee-input'
									type='text'
									defaultValue={employee.lastname}
									{...register('lastname', { required: true })}
								/>
							</div>
							<div className='flex'>
								<div className='employee-label'>Email</div>
								<input
									className='employee-input'
									type='email'
									defaultValue={employee.email}
									{...register('email', { required: true })}
								/>
							</div>
						</div>
					</div>
					<div className='flex items-start border-b border-gray-200 py-8'>
						<div className='w-2/5 text-left'>
							<h4 className='text-gray-800 font-semibold'>Professional information</h4>
						</div>
						<div className='w-2/5 text-left flex flex-col gap-4'>
							<div className='flex'>
								<div className='employee-label'>Department</div>
								<select
									name='department'
									className='employee-input'
									{...register('department', { required: true })}
								>
									{departmentList.map(d => {
										return (
											<option key={d} value={d}>
												{d}
											</option>
										);
									})}
									<option value=''>None</option>
								</select>
							</div>
							<div className='flex'>
								<div className='employee-label'>Role</div>
								<input
									className='employee-input'
									type='text'
									defaultValue={employee.role}
									{...register('role', { required: true })}
								/>
							</div>
						</div>
					</div>
					<div className='flex items-start justify-end py-8'>
						<div className='flex items-center gap-4 w-1/5'>
							<button className='btn-primary'>Save changes</button>
						</div>
					</div>
				</form>
				<div className='flex items-start border-t border-gray-200 py-8'>
					<div className='w-2/5 text-left'>
						<h4 className='text-gray-800 font-semibold'>Current Projects</h4>
					</div>
					<div className='w-3/5 flex flex-wrap justify-start items-start gap-4'>
						{employee.projects.map(project => {
							return <EmployeeProjectCard key={project.id} project={project} />;
						})}
					</div>
				</div>
			</section>
		</>
	);
}

export default EmployeeDetails;
