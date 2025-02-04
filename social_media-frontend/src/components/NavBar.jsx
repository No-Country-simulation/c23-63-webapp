import { Link, useLocation } from "react-router-dom";
import { LogotipoNexo } from "../assets/Icons/Nexo";
import { useAuthContext } from "../context/AuthContext";

export default function NavBar() {
  const { isAuthenticated, user } = useAuthContext()
  const location = useLocation()
  console.log(user)

  const isActive = (path) => location.pathname === path

  return (
    <nav className="bg-primary-400 sticky top-0 z-10 mb-3 flex justify-center nav-bar">
      <div className="flex p-4 justify-between w-full max-w-7xl">
        <Link to={'/'}>
          <LogotipoNexo />
        </Link>
        
        <ul className="flex gap-4 items-center">
          {
            isAuthenticated
            ? (
              <>
                <li className={`link-navbar ${isActive('/') ? 'active': ''}`}>
                  <Link to={'/'}>Inicio</Link>
                </li>
                <li className={`link-navbar ${isActive(`/profile/${user.id}`) ? 'active': ''}`}>
                  <Link to={`/profile/${user.id}`}>Perfil</Link>
                </li>
              </>
              )
            : (
                <>
                  <li className={`link-navbar`}>
                    <Link to={"/auth/login"}>Iniciar sesión</Link>
                  </li>
                  <li className={`link-navbar`}>
                    <Link to={"/auth/register"}>Registrarse</Link>
                  </li>
                </>
              )
          }
        </ul>
      </div>
    </nav>
  )
}