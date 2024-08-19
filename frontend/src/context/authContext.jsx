import { createContext, useContext, useState } from 'react';
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
	const [token, setToken] = useState(null);
	const [isAuthenticated, setIsAuthenticated] = useState(false);

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
			} else {
				console.error('Login failed');
			}
		} catch (error) {
			console.error('Error during login: ', error);
		}
	};

	return (
		<AuthContext.Provider value={{ login, user, token, isAuthenticated }}>
			{children}
		</AuthContext.Provider>
	);
};

AuthProvider.propTypes = {
	children: PropTypes.node.isRequired,
};
