import { useForm } from 'react-hook-form';
import { PropTypes } from 'prop-types';

function LoginForm({ submitHandle }) {
	const { register, handleSubmit } = useForm();

	return (
		<form onSubmit={handleSubmit(submitHandle)} id='login-form' className='w-1/2'>
			<div className='flex flex-col'>
				<label htmlFor='username'>Username</label>
				<input
					type='text'
					id='username'
					{...register('username', { required: true })}
					placeholder='Type your username'
				/>
			</div>
			<div className='flex flex-col'>
				<label htmlFor='password'>Password</label>
				<input
					type='password'
					id='password'
					{...register('password', { required: true })}
					placeholder='Type your password'
				/>
			</div>
			<button className='bg-indigo-500 w-full py-2 px-4'>LOGIN</button>
		</form>
	);
}

LoginForm.propTypes = {
	submitHandle: PropTypes.func,
};

export default LoginForm;
