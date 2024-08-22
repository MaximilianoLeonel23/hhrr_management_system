import React from 'react';
import PropTypes from 'prop-types';
function Title({ title }) {
	return <h2 className='text-xl font-bold text-gray-700'>{title}</h2>;
}

Title.propTypes = {
	title: PropTypes.string.isRequired,
};

export default Title;
