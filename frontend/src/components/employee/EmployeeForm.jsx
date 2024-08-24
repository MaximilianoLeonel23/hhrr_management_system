import React from 'react';
import { useForm } from 'react-hook-form';
import { useOutletContext } from 'react-router-dom';

function EmployeeForm() {
	const { register, handleSubmit } = useForm();
	const { departmentList } = useOutletContext();
	const submitHandle = async data => {
		console.log(data);
	};

	return (
		<section className='flex flex-col gap-4'>
			<form
				onSubmit={handleSubmit(submitHandle)}
				className='flex flex-col gap-2 bg-white px-4 py-8 rounded-xl border border-gray-200'
			>
				<h4 className='title-h4'>Register new employee</h4>
				<div className='grid grid-cols-2 gap-8'>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='firstname'>
							Firstname
						</label>
						<input
							className='form-input'
							type='text'
							name='firstname'
							required
							{...register('firstname', { required: true })}
						/>
					</div>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='lastname'>
							Lastname
						</label>
						<input
							className='form-input'
							type='text'
							name='lastname'
							required
							{...register('lastname', { required: true })}
						/>
					</div>
				</div>
				<div className='grid grid-cols-2 gap-8'>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='email'>
							Email
						</label>
						<input
							className='form-input'
							type='text'
							name='email'
							required
							{...register('email', { required: true })}
						/>
					</div>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='department'>
							Department
						</label>
						<select className='select-input' name='department' {...register('department')}>
							<option value=''>None</option>
							{departmentList.map(deparment => {
								return (
									<option key={deparment} value={deparment}>
										{deparment}
									</option>
								);
							})}
						</select>
					</div>
				</div>
				<div className='grid grid-cols-2 gap-8'>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='role'>
							Role
						</label>
						<input className='form-input' type='text' name='role' {...register('role')} />
					</div>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='dateOfHire'>
							Date of Hire
						</label>
						<input
							className='form-input'
							type='date'
							name='dateOfHire'
							{...register('dateOfHire', { required: true })}
						/>
					</div>
				</div>
				<div className='grid grid-cols-2 gap-8'>
					<div className='flex flex-col gap-2'>
						<label className='form-label' htmlFor='salary'>
							Salary
						</label>
						<input className='form-input' type='number' name='salary' {...register('salary')} />
					</div>
				</div>
				<div className='grid grid-cols-2 gap-8'>
					<button className='btn-ghost'>Create</button>
				</div>
			</form>
		</section>
	);
}

export default EmployeeForm;
