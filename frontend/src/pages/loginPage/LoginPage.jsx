import { useAuth } from '../../context/authContext';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import LoginForm from './components/LoginForm';

function LoginPage() {
	const { login, isAuthenticated } = useAuth();
	const navigate = useNavigate();

	const submitHandle = async data => {
		await login(data);
	};

	useEffect(() => {
		if (isAuthenticated) {
			navigate('/');
		}
	}, [isAuthenticated]);

	return (
		<main className='min-h-screen flex'>
			<div className='w-1/2 bg-gray-300'></div>
			<div className='w-1/2 flex flex-col justify-center items-center'>
				<h4>My Application Logo</h4>
				<LoginForm submitHandle={submitHandle} />
			</div>
		</main>
	);
}

export default LoginPage;
