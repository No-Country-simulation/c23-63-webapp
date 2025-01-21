import { useState } from "react"
import { Link } from "react-router-dom"

import Logo from "../../assets/Logo"

import { GoogleLogin } from '@react-oauth/google';



export default function Login() {

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleLoginSubmit = (event) => {
    event.preventDefault()
    console.log(email, password)
  }
  return (
    <>
      <header className="grid gap-10 justify-items-center">
        <Logo />
        <p className="text-4xl ">¡Que bueno verte!</p>
        <GoogleLogin
          theme='filled_black'
          size='large'
          onSuccess={credentialResponse => {
            console.log(credentialResponse);
          }}
          onError={() => {
            console.log('Login Failed');
          }}
        />
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