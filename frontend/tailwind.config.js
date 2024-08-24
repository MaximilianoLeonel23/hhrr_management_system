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
				'primery-red': '#F9623E',
				'primery-pink': '#FF81A9',
				'primery-violet': '#8E5AFF',
				'primery-green': '#6ECFBD',
				'primery-lavender': '#8FA1FF',
				'primery-indigo': '#3843D0',
			},
		},
	},
	plugins: [],
};
