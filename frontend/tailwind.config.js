/** @type {import('tailwindcss').Config} */
export default {
	content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
	theme: {
		extend: {
			fontFamily: {
				inter: ['Inter', 'sans-serif'],
			},
			colors: {
				'primary-yellow': '#FFC466',
				'primary-red': '#F9623E',
				'primary-pink': '#FF81A9',
				'primary-violet': '#8E5AFF',
				'primary-green': '#6ECFBD',
				'primary-lavender': '#8FA1FF',
				'primary-indigo': '#3843D0',
			},
		},
	},
	plugins: [],
};
