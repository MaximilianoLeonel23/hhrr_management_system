import React from 'react';
import { useAuth } from '../../context/authContext';

function ProfileLogo() {
	const { user } = useAuth();
	return (
		<div className='flex items-center justify-center w-12 h-12 text-white text-lg font-bold rounded-full bg-gray-400'>
			{user ? user.username.slice(0, 1).toUpperCase() : '?'}
		</div>
	);
}

export default ProfileLogo;
