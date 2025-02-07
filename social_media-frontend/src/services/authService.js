import axiosApi from './axiosApi';
import { ERROR_MESSAGES } from './errorMessages';

export const authenticateUser = async (email, password) => {
  try {
    const { data } = await axiosApi.post('auth/login', { email,password })    

    return data.usuarioAuth 

  }catch (err) {
    const message = err?.response
      ? ERROR_MESSAGES.login[err.response.status] || ERROR_MESSAGES[err.response.status] || ERROR_MESSAGES.default 
      : ERROR_MESSAGES.connectionError
    
    throw new Error(message)
  }
}

export const registerUser = async (user, email, password) => {
  try {
    const response = await axiosApi.post('register',{user, email, password})
    return response
  } catch (err){
    const message = err?.response
      ? ERROR_MESSAGES.register[err.response.status] || ERROR_MESSAGES[err.response.status] || ERROR_MESSAGES.default 
      : ERROR_MESSAGES.connectionError
    throw new Error(message)
  }
}