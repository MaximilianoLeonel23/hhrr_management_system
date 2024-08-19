import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../components/sidebar/Sidebar';
import Header from '../components/header/Header';

function MainLayout() {
	return (
		<div className='min-h-screen flex bg-gray-400'>
			<Sidebar />
			<div className='flex flex-col w-full'>
				<Header />
				<main className='bg-gray-50'>
					<Outlet />
				</main>
			</div>
		</div>
	);
}

export default MainLayout;
