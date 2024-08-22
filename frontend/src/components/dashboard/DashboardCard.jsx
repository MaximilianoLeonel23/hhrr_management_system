import React from 'react';
import PropTypes from 'prop-types';
function DashboardCard({ title, number, color, coin = false }) {
	return (
		<article className={`flex flex-col gap-4 p-2 bg-${color} rounded`}>
			<h5 className='text-gray-300'>{title}</h5>
			<p className='text-gray-300'>
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
