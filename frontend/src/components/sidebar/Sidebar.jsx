import React from 'react';
import {
	dashboardIcon,
	employeeIcon,
	payrollIcon,
	projectIcon,
	settingIcon,
	taskIcon,
} from '../../assets/icons/icons';

function Sidebar() {
	return (
		<aside className='w-1/5 bg-white p-4 flex flex-col justify-between border border-gray-200'>
			<div>
				<h4>APPLICATION LOGO</h4>
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
							<span>1 </span>
							<a href='/'>Bussiness</a>
						</li>
						<li className='sidebar-item'>
							<span>2 </span>
							<a href='/'>Design</a>
						</li>
						<li className='sidebar-item'>
							<span>3 </span>
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
