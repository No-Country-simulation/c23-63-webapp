import { Link, useNavigate } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import BaseForm from "./BaseForm";
import { registerUser } from "../../services/authService";

export default function Register() {
  const navigate = useNavigate()
  const userRef = useRef()

  const [user, setUser] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errorMsg, setErrorMsg] = useState('')

  const handleLoginSubmit = async (event) => {
    event.preventDefault()
    const response = await registerUser(user, email, password)
    if (response.success){
      navigate('/auth/login')
    }else {
      setErrorMsg(response.message)
    }
  }
  
  useEffect(() => {
    userRef.current.focus()
  }, [])

  useEffect(()=> {
    setErrorMsg('')
  }, [user, email, password])

  return (
    <>
      <BaseForm title="¡Comienza aquí!" onSubmit={handleLoginSubmit}>
        <input 
          className="input"
          type="text"
          value={user}
          ref={userRef}
          placeholder="Nombre de usuario"
          onChange={({target}) => setUser(target.value)}
        />
        <input 
          className="input"
          type="password" 
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
        {
          errorMsg && <p className="text-red-400 paragraph-s text-center">{errorMsg}</p>
        }
        <div className="mt-auto w-full grid justify-items-center gap-4">
          <button className="bg-primary-200 w-full p-3 rounded-2xl text-16 font-medium font-popins">Registrarte</button>
          <p className="paragraph-s">¿Ya tienes una cuenta? <Link className="text-primary-100" to='/auth/login'> Inicia sesion aquí</Link></p>
        </div>
      </BaseForm>
    </>
  )
}