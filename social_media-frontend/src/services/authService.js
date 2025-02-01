import axiosApi from './axiosApi';

const errorMessages = {
  400: 'Email o contraseña incorrectos',
  401: 'No autorizado',
  connectionError: 'Error de conexión',
  default: 'Petición fallida'
}

export const authenticateUser = async (email, password) => {
  try {
    const { data } = await axiosApi.post('/auth/login', { email,password })
    
    return { user: data }

  }catch (err) {
    const message = err?.response
      ? errorMessages[err.response.status] || errorMessages.default 
      : errorMessages.connectionError
    
    throw new Error(message)
  }
}

export const registerUser = async (email, password) => {
  try {
    const response = await axiosApi.post('/register',{email, password})
    if (response.success) return 'yes'
  } catch (err){
    console.log(err)
  }
}