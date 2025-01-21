import { Link } from "react-router-dom";

export default function Register() {
  return (
    <>
      <div>
        form register
      </div>
      <p 
        className="paragraph-xs"
      >
        ¿Ya tienes una cuenta? <Link className="text-primary-100" to='/auth/login'>Inicia sesión aquí</Link>
      </p>
    </>
  )
}