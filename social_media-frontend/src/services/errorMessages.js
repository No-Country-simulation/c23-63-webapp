export const ERROR_MESSAGES = {
  400: 'Solicitud incorrecta. Verifica los parámetros.',
  401: 'No autorizado. Por favor, inicia sesión.',
  403: 'Acceso prohibido. No tienes permisos para ver este contenido.',
  404: 'Perfil no encontrado.',
  422: 'Datos inválidos. Verifica que los campos sean correctos.',
  500: 'Error interno del servidor. Intenta más tarde.',
  503: 'El servicio está temporalmente no disponible. Intenta más tarde.',
  connectionError: 'Error de conexión. Por favor, revisa tu red o intenta más tarde.',
  default: 'Petición fallida. Intenta de nuevo.',
  login: {
    400: 'Email o contraseña incorrectos',
    401: 'No autorizado. Verifica tus credenciales.',
    403: 'Acceso denegado. No tienes permisos para iniciar sesión.',
    404: 'Usuario no encontrado. Regístrate antes de iniciar sesión.',
    500: 'Error del servidor al intentar autenticar. Intenta más tarde.'
  },
  register: {
    400: 'Error en el registro. Verifica los datos ingresados.',
    409: 'El email ya está registrado. Intenta con otro.',
    422: 'Formato de datos incorrecto. Revisa los campos.',
    500: 'Error del servidor al registrar. Inténtalo más tarde.'
  }
}