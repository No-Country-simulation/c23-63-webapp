import { useState } from "react";
import BaseForm from "./BaseForm";

export default function PasswordResetConfirmation() {

  const [code, setCode] = useState('')
  const [newPassword, setNewPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')

  const handleLoginSubmit = (event) => {
    event.preventDefault()
  }
  return (
    <>
      <BaseForm onSubmit={handleLoginSubmit}> 
      <input 
          className="input"
          type="text"
          value={code}
          placeholder="Código de confirmación"
          onChange={({target}) => setCode(target.value)}
        />
        
        <input 
          className="input"
          type="text"
          value={newPassword}
          placeholder="Nueva contraseña"
          onChange={({target}) => setNewPassword(target.value)}
        />

        <input 
          className="input"
          type="password" 
          value={confirmPassword}
          placeholder="Confirmar contraseña"
          onChange={({target}) => setConfirmPassword(target.value)}
        />

        <div className="mt-auto w-full grid justify-items-center gap-4">
          <button className="bg-primary-200 w-full p-3 rounded-2xl text-16 font-medium font-popins">Restablecer contraseña</button>
          <button className="w-full p-3 rounded-2xl text-16 font-medium font-popins border-white border-[1px]">Cancelar</button>
        </div>
      </BaseForm>
    </>
  )
}