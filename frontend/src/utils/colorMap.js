export const colorMap = {
	yellow: 'bg-primary-yellow',
	red: 'bg-primary-red',
	pink: 'bg-primary-pink',
	violet: 'bg-primary-violet',
	green: 'bg-primary-green',
	lavender: 'bg-primary-lavender',
	indigo: 'bg-primary-indigo',
};

export const getColorByDepartment = department => {
	let departmentColor;
	switch (department) {
		case 'Engineering':
			departmentColor = 'bg-primary-yellow';
			break;
		case 'Marketing':
			departmentColor = 'bg-primary-pink';
			break;
		case 'Sales':
			departmentColor = 'bg-primary-red';
			break;
		case 'HR':
			departmentColor = 'bg-primary-green';
			break;
		default:
			departmentColor = 'bg-gray-500';
	}

	return departmentColor;
};
