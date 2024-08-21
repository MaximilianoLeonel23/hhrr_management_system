import React from 'react';
import {
	dashboardIcon,
	employeeIcon,
	payrollIcon,
	projectIcon,
	settingIcon,
	taskIcon,
} from '../../assets/icons/icons';
import Logo from './Logo';

function Sidebar() {
	return (
		<aside className='w-1/5 bg-white p-4 flex flex-col justify-between border-r border-gray-200'>
			<div>
				<div className='flex items-center gap-2 py-2'>
					<Logo />
				</div>
				<div className='divider'></div>
				<div className='py-2'>
					<h4 className='sidebar-title'>MAIN MENU</h4>
					<ul className='flex flex-col'>
						<li className='sidebar-item'>
							<span>{dashboardIcon()}</span>
							<a href='/'>Dashboard</a>
						</li>
						<li className='sidebar-item'>
							<span>{employeeIcon()}</span>
							<a href='/employee'>Employee</a>
						</li>
						<li className='sidebar-item'>
							<span>{projectIcon()}</span>
							<a href='/project'>Projects</a>
						</li>
						<li className='sidebar-item'>
							<span>{payrollIcon()}</span>
							<a href='/payroll'>Payroll</a>
						</li>
						<li className='sidebar-item'>
							<span>{taskIcon()}</span>
							<a href='/tasks'>Tasks</a>
						</li>
					</ul>
				</div>
				<div className='divider'></div>
				<div className='py-2'>
					<h4 className='sidebar-title'>DEPARTMENT</h4>
					<ul>
						<li className='sidebar-item'>
							<span className='rounded h-2.5 w-2.5 bg-red-500'></span>
							<a href='/'>Bussiness</a>
						</li>
						<li className='sidebar-item'>
							<span className='rounded h-2.5 w-2.5 bg-yellow-500'></span>
							<a href='/'>Design</a>
						</li>
						<li className='sidebar-item'>
							<span className='rounded h-2.5 w-2.5 bg-green-500'></span>
							<a href='/'>Sales</a>
						</li>
					</ul>
				</div>
			</div>
			<div>
				<h4 className='sidebar-title'>OTHER</h4>
				<div className='sidebar-item'>
					<span>{settingIcon()}</span>
					<a href='/setting'>Setting</a>
				</div>
			</div>
		</aside>
	);
}

export default Sidebar;
