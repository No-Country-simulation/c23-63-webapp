import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"

import Logo from "../../assets/Logo"


export default function Login() {

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();

   const googleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  const handleLoginSubmit = async (event) => {
    event.preventDefault()
    console.log(email, password)
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error("Error al iniciar sesión");
      }

      const data = await response.json();
      console.log("Token recibido:", data.token);
      console.log("Datos del usuario:", data.usuarioAuth);

      localStorage.setItem("token", data.token);

      navigate("/");
    } catch (error) {
      setErrorMessage("Credenciales inválidas. Intenta nuevamente.");
      console.error(error);
    }

  }
  
  return (
    <>
      <header className="grid gap-10 justify-items-center">
        <Logo />
        <p className="text-4xl ">¡Que bueno verte!</p>
        <button onClick={googleLogin} className="bg-black w-full p-3 rounded-2xl text-16 font-medium font-popins">Ingresar con Google</button>
      </header>
      
      <div className="flex items-center">
        <hr className="flex-grow border-t-2 border-gray-300"/>
        <span className="mx-2 paragraph-xs">O</span>
        <hr className="flex-grow border-t-2 border-gray-300"/>
      </div>

      <form onSubmit={handleLoginSubmit} className="flex flex-col items-center gap-4">
        
        <input 
          className="input"
          type="email"
          value={email}
          placeholder="E-mail"
          onChange={({target}) => setEmail(target.value)}
        />

        <input 
          className="input"
          type="password" 
          value={password}
          placeholder="Contraseña"
          onChange={({target}) => setPassword(target.value)}
        />

        <Link className="text-primary-100 paragraph-s" to="/auth/recover-password">Recuperar contraseña</Link>
        <section className="mt-auto w-full grid justify-items-center gap-4">
          <button className="bg-primary-200 w-full p-3 rounded-2xl text-16 font-medium font-popins">Ingresar</button>
          <p className="paragraph-s">¿No tienes cuenta aún? <Link className="text-primary-100" to='/auth/register'>Registrate aquí</Link></p>
        </section>
      </form>
    </>
  )
}