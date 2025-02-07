import { useEffect, useRef, useState } from "react"
import { Link, useNavigate } from "react-router-dom"

import BaseForm from "./BaseForm";
import GoogleIcon from "../../assets/Icons/GoogleIcon";

import { useAuthContext } from "../../context/AuthContext";
import { authenticateUser } from '../../services/authService'


export default function Login() {
  const { login } = useAuthContext()

  const navigate = useNavigate()
  const emailRef = useRef()

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errorMsg, setErrorMsg] = useState('')

  useEffect(() => {
    emailRef.current.focus()
  }, [])

  useEffect(() => {
    setErrorMsg('')
  }, [email, password])

  const resetForm = () => {
    setErrorMsg('');
    setErrorMsg('')
    setEmail('')
    setPassword('')
  };

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  const handleLoginSubmit = async (event) => {
    event.preventDefault()
    try {
      const response = await authenticateUser(email, password)
      login(response)
      resetForm()
      navigate('/')
    } catch (err){
      setErrorMsg(err.message)
    }
  }

  return (
    <>
      <BaseForm title="¡Que bueno verte!" onSubmit={handleLoginSubmit}>
          <button
            type="button"
            onClick={handleGoogleLogin}
            className="w-full bg-neutral-800 p-3 flex justify-center items-center gap-2 rounded-lg paragraph-s"
          >
            <GoogleIcon/>
            Ingresar con Google
          </button>

        <div className="flex w-full items-center">
          <hr className="flex-grow border-t-2 border-gray-300"/>
          <span className="mx-2 paragraph-xs">O</span>
          <hr className="flex-grow border-t-2 border-gray-300"/>
        </div>

        <input 
          className="input"
          type="email"
          placeholder="E-mail"
          ref={emailRef}
          onChange={({target}) => setEmail(target.value)}
          value={email}
          required
        />

        <input 
          className="input"
          type="password" 
          placeholder="Contraseña"
          onChange={({target}) => setPassword(target.value)}
          value={password}
          required
        />
        {
          errorMsg && <p className="text-red-400 paragraph-s text-center">{errorMsg}</p>
        }
        <Link className="text-primary-100 paragraph-s" to="/auth/password-recovery">Olvidé mi contraseña</Link>
        <div className="mt-auto w-full grid justify-items-center gap-4">
          <button className="bg-primary-200 w-full p-3 rounded-2xl text-16 font-medium font-popins">Ingresar</button>
          <p className="paragraph-s">¿No tienes cuenta aún? <Link className="text-primary-100" to='/auth/register'>Registrate aquí</Link></p>
        </div>
      </BaseForm>   
    </>
  )
}