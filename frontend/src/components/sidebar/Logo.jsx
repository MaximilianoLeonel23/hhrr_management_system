import React from 'react';
import logo from '../../assets/images/logo.png';

function Logo() {
	return (
		<>
			<img src={logo} alt='Logo' className='w-fit' />
			<h4 className='font-bold text-gray-800 text-lg'>HR Management</h4>
		</>
	);
}

export default Logo;
