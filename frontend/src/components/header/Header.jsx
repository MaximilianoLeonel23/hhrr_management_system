import React from 'react';
import { useAuth } from '../../context/authContext';
import ProfileLogo from './ProfileLogo';

function Header() {
	const { user } = useAuth();
	return (
		<header className='w-full flex items-center justify-between p-4 bg-white border-b border-gray-200'>
			<h4 className='text-gray-400 font-medium'>
				Bienvenid@, <br /> <span className='text-gray-500'>{user?.username ? user.username : ''}</span>
			</h4>
			<ProfileLogo />
		</header>
	);
}

export default Header;
