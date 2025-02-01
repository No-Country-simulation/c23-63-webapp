import { useState } from "react";
import BaseForm from "./BaseForm";
import { Link } from "react-router-dom";

export default function PasswordRecoveryRequest() {
  const [email, setEmail] = useState('')

  const handleLoginSubmit = (event) => {
    event.preventDefault()

  }

  return(
    <>
      <BaseForm onSubmit={handleLoginSubmit}>
        <input 
          className="input"
          type="email" 
          value={email}
          placeholder="Email"
          onChange={({target}) => setEmail(target.value)}
        />
        <p className="paragraph-s text-center">
          Se enviará un código de confirmación al correo ingresado
        </p>
        <div className="mt-auto w-full grid justify-items-center gap-4">
          <Link 
            className="bg-primary-200 w-full p-3 rounded-2xl text-16 font-medium font-popins text-center"
            to={'/auth/password-reset'}
          >
            Enviar código
          </Link>
          <button className="w-full p-3 rounded-2xl text-16 font-medium font-popins border-white border-[1px]">Cancelar</button>
        </div>
      </BaseForm>
    </>
  )
}