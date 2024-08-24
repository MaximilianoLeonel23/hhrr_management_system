import React from 'react';
import PropTypes from 'prop-types';
import { colorMap } from '../../utils/colorMap';
function DashboardCard({ title, number, color, coin = false }) {
	const backgroundColor = colorMap[color] || 'bg-gray-500';
	return (
		<article className={`w-1/4 flex flex-col gap-2 p-4 ${backgroundColor} rounded-xl`}>
			<h5 className='text-gray-100'>{title}</h5>
			<p className='text-gray-50 text-4xl'>
				{coin ? '$ ' : ''}
				{number}
			</p>
		</article>
	);
}

DashboardCard.propTypes = {
	title: PropTypes.string.isRequired,
	number: PropTypes.number.isRequired,
	color: PropTypes.string.isRequired,
};

export default DashboardCard;
