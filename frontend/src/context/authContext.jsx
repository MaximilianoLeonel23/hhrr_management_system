import { createContext, useContext, useEffect, useState } from 'react';
import axiosInstance from '../utils/axios/axios';
import PropTypes from 'prop-types';

export const AuthContext = createContext();

export const useAuth = () => {
	const context = useContext(AuthContext);

	if (!context) throw new Error('useAuth must be used within an AuthProvider');
	return context;
};

export const AuthProvider = ({ children }) => {
	const [user, setUser] = useState(null);
	const [token, setToken] = useState(localStorage.getItem('token') || null);
	const [isAuthenticated, setIsAuthenticated] = useState(!!token);

	const login = async data => {
		try {
			const response = await axiosInstance.post('/auth/login', {
				username: data.username,
				password: data.password,
			});
			if (response.status === 200) {
				console.log('Login successful: ', response.data);
				setUser(response.data.user);
				setToken(response.data.token);
				setIsAuthenticated(true);
				localStorage.setItem('token', response.data.token);
				localStorage.setItem('user', JSON.stringify(response.data.user));
			} else {
				console.error('Login failed');
			}
		} catch (error) {
			console.error('Error during login: ', error);
		}
	};

	const logout = () => {
		setUser(null);
		setToken(null);
		setIsAuthenticated(false);
		localStorage.removeItem('token');
		localStorage.removeItem('user');
	};

	useEffect(() => {
		const token = localStorage.getItem('token');
		const user = localStorage.getItem('user');

		if (token && user) {
			setToken(token);
			setUser(JSON.parse(user));
			setIsAuthenticated(true);
		} else {
			logout();
		}
	}, []);
	return (
		<AuthContext.Provider value={{ login, user, token, isAuthenticated, logout }}>
			{children}
		</AuthContext.Provider>
	);
};

AuthProvider.propTypes = {
	children: PropTypes.node.isRequired,
};
