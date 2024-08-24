import React, { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import Sidebar from '../components/sidebar/Sidebar';
import Header from '../components/header/Header';
import { useAuth } from '../context/authContext';

function MainLayout() {
	const { isAuthenticated } = useAuth();
	const navigate = useNavigate();
	useEffect(() => {
		if (!isAuthenticated) {
			navigate('/login');
		}
	}, [isAuthenticated]);

	return (
		<div className='min-h-screen flex bg-gray-400'>
			<Sidebar />
			<div className='flex flex-col w-full'>
				<Header />
				<main className='flex flex-col p-4 bg-gray-50 h-full'>
					<Outlet />
				</main>
			</div>
		</div>
	);
}

export default MainLayout;
